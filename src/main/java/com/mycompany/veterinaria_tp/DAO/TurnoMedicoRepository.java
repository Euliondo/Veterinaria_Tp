/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.veterinaria_tp.DAO;

import com.mycompany.veterinaria_tp.Controller.TurnoMedicoController;
import com.mycompany.veterinaria_tp.Entidades.TurnoMedico;
import com.mycompany.veterinaria_tp.Exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author July
 */
public class TurnoMedicoRepository implements TurnoMedicoDao{
    
    public TurnoMedicoController controllerTurnoMedico;

    public TurnoMedicoRepository(TurnoMedicoController controllerTurnoMedico) {
        this.controllerTurnoMedico = controllerTurnoMedico;
    }

    @Override
    public TurnoMedico obtenerPorId(int id) {
        return controllerTurnoMedico.findTurnoMedico(id);
    }

    @Override
    public List<TurnoMedico> obtenerTodos() {
        return controllerTurnoMedico.findTurnoMedicoEntities();
    }

    @Override
    public void guardar(TurnoMedico turno) {
        controllerTurnoMedico.create(turno);
    }

    @Override
    public void actualizar(TurnoMedico turno) {
        try {
            controllerTurnoMedico.edit(turno);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            controllerTurnoMedico.destroy(Long.MIN_VALUE);
        } catch (NonexistentEntityException e) {
            e.printStackTrace();
        }
    }
}
