/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.veterinaria_tp.DAO;

import com.mycompany.veterinaria_tp.Controller.ProfecionalController;
import com.mycompany.veterinaria_tp.Entidades.Profecional;
import com.mycompany.veterinaria_tp.Exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author July
 */
public class ProfecionalRepository implements ProfecionalDao{
    
    public ProfecionalController controllerProfecional;

    public ProfecionalRepository(ProfecionalController controllerProfecional) {
        this.controllerProfecional = controllerProfecional;
    }

    @Override
    public Profecional obtenerPorId(int id) {
        return controllerProfecional.findMedico(id);
    }

    @Override
    public List<Profecional> obtenerTodos() {
        return controllerProfecional.findMedicoEntities();
    }

    @Override
    public void guardar(Profecional medico) {
        controllerProfecional.create(medico);
    }

    @Override
    public void actualizar(Profecional medico) {
         try {
            controllerProfecional.edit(medico);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            controllerProfecional.destroy(Long.MIN_VALUE);
        } catch (NonexistentEntityException e) {
            e.printStackTrace();
        }
    }  
}
