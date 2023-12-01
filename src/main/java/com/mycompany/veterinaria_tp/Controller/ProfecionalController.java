/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.veterinaria_tp.Controller;

import com.mycompany.veterinaria_tp.Entidades.Profecional;
import com.mycompany.veterinaria_tp.Entidades.TurnoMedico;
import com.mycompany.veterinaria_tp.Exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
/**
 *
 * @author July
 */
public class ProfecionalController implements Serializable {
    
    public ProfecionalController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Profecional medico) {
        if (medico.getTurnomedicoList() == null) {
            medico.setTurnomedicoList(new ArrayList<TurnoMedico>());;
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<TurnoMedico> attachedTurnos = new ArrayList<TurnoMedico>();
            for (TurnoMedico turnosTurnoMedicoToAttach : medico.getTurnomedicoList()) {
                turnosTurnoMedicoToAttach = em.getReference(turnosTurnoMedicoToAttach.getClass(), turnosTurnoMedicoToAttach.getIdTurno());
                attachedTurnos.add(turnosTurnoMedicoToAttach);
            }
            medico.setTurnomedicoList(attachedTurnos);
            em.persist(medico);
            for (TurnoMedico turnosTurnoMedico : medico.getTurnomedicoList()) {
                Profecional medicoTurnos = turnosTurnoMedico.getIdProfecional();
                turnosTurnoMedico.setIdProfecional(medico);
                turnosTurnoMedico = em.merge(turnosTurnoMedico);
                if (medicoTurnos != null) {
                    medicoTurnos.getTurnomedicoList().remove(turnosTurnoMedico);
                    medicoTurnos = em.merge(medicoTurnos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Profecional medico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profecional med = em.find(Profecional.class, medico.getIdProfecional());
            List<TurnoMedico> turnosPasados = med.getTurnomedicoList();
            List<TurnoMedico> nuevosTurnos = medico.getTurnomedicoList();
            List<TurnoMedico> TurnosNew = new ArrayList<TurnoMedico>();
            for (TurnoMedico turnosNewTurnoMedicoToAttach : nuevosTurnos) {
                turnosNewTurnoMedicoToAttach = em.getReference(turnosNewTurnoMedicoToAttach.getClass(), turnosNewTurnoMedicoToAttach.getId());
                TurnosNew.add(turnosNewTurnoMedicoToAttach);
            }
            nuevosTurnos = TurnosNew;
            medico.setTurnomedicoList(nuevosTurnos);
            medico = em.merge(medico);
            for (TurnoMedico turnos : turnosPasados) {
                if (!nuevosTurnos.contains(turnos)) {
                    turnos.setIdProfecional(null);
                    turnos = em.merge(turnos);
                }
            }
            for (TurnoMedico turnosNew : nuevosTurnos) {
                if (!turnosPasados.contains(turnosNew)) {
                    Profecional medicoTurnos = turnosNew.getIdProfecional();
                    turnosNew.setIdProfecional(medico);
                    turnosNew = em.merge(turnosNew);
                    if (medicoTurnos != null && !medicoTurnos.equals(medico)) {
                        medicoTurnos.getTurnomedicoList().remove(turnosNew);
                        medicoTurnos = em.merge(medicoTurnos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = medico.getIdProfecional();
                if (findMedico(id) == null) {
                    throw new NonexistentEntityException("The medico with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profecional medico;
            try {
                medico = em.getReference(Profecional.class, id);
                medico.getIdProfecional();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medico with id " + id + " no longer exists.", enfe);
            }
            List<TurnoMedico> turnos = medico.getTurnomedicoList();
            for (TurnoMedico turnosTurnoMedico : turnos) {
                turnosTurnoMedico.setIdProfecional(null);
                turnosTurnoMedico = em.merge(turnosTurnoMedico);
            }
            em.remove(medico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Profecional> findMedicoEntities() {
        return (List<Profecional>) findMedicoEntities(true, -1, -1);
    }

    public List<Profecional> findMedicoEntities(int maxResults, int firstResult) {
        return (List<Profecional>) findMedicoEntities(false, maxResults, firstResult);
    }

    private List<Profecional> findMedicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Profecional.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Profecional findMedico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Profecional.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Profecional> rt = cq.from(Profecional.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
