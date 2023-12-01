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
import java.util.List;
import javax.management.Query;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author July
 */
public class TurnoMedicoController implements Serializable{
    
    public TurnoMedicoController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(TurnoMedico turnoMedico) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profecional medico = turnoMedico.getIdProfecional();
            if (medico != null) {
                medico = em.getReference(medico.getClass(), medico.getIdProfecional());
                turnoMedico.setIdProfecional(medico);
            }
            em.persist(turnoMedico);
            if (medico != null) {
                medico.getTurnomedicoList().add(turnoMedico);
                medico = em.merge(medico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(TurnoMedico turnoMedico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TurnoMedico tMedico = em.find(TurnoMedico.class, turnoMedico.getIdTurno());
            Profecional medico = tMedico.getIdProfecional();
            Profecional newMedico = turnoMedico.getIdProfecional();
            if (newMedico != null) {
                newMedico = em.getReference(newMedico.getClass(), newMedico.getIdProfecional());
                turnoMedico.setIdProfecional(newMedico);
            }
            turnoMedico = em.merge(turnoMedico);
            if (medico != null && !medico.equals(newMedico)) {
                medico.getTurnomedicoList().remove(turnoMedico);
                medico = em.merge(medico);
            }
            if (newMedico != null && !newMedico.equals(medico)) {
                newMedico.getTurnomedicoList().add(turnoMedico);
                newMedico = em.merge(newMedico);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = turnoMedico.getIdTurno();
                if (findTurnoMedico(id) == null) {
                    throw new NonexistentEntityException("The turnoMedico with id " + id + " no longer exists.");
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
            TurnoMedico turnoMedico;
            try {
                turnoMedico = em.getReference(TurnoMedico.class, id);
                turnoMedico.getIdTurno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The turnoMedico with id " + id + " no longer exists.", enfe);
            }
            Profecional medico = turnoMedico.getIdProfecional();
            if (medico != null) {
                medico.getTurnomedicoList().remove(turnoMedico);
                medico = em.merge(medico);
            }
            em.remove(turnoMedico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TurnoMedico> findTurnoMedicoEntities() {
        return findTurnoMedicoEntities(true, -1, -1);
    }

    public List<TurnoMedico> findTurnoMedicoEntities(int maxResults, int firstResult) {
        return findTurnoMedicoEntities(false, maxResults, firstResult);
    }

    private List<TurnoMedico> findTurnoMedicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TurnoMedico.class));
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

    public Integer findTurnoMedico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TurnoMedico.class, id);
        } finally {
            em.close();
        }
    }

    public int getTurnoMedicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TurnoMedico> rt = cq.from(TurnoMedico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
