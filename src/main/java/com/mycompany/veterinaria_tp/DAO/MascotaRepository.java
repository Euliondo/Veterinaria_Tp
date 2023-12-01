/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.veterinaria_tp.DAO;

import com.mycompany.veterinaria_tp.Controller.MascotaController;
import com.mycompany.veterinaria_tp.Entidades.Mascota;
import com.mycompany.veterinaria_tp.Exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author July
 */
public class MascotaRepository implements MascotaDao{
    
    public MascotaController controllerMascota;

    public MascotaRepository(MascotaController controllerMascota) {
        this.controllerMascota = controllerMascota;
    }

    @Override
    public Mascota obtenerPorId(int id) {
        return controllerMascota.findMascota(id));
    }

    @Override
    public List<Mascota> obtenerTodos() {
        return controllerMascota.findMascotaEntities();
    }

    @Override
    public void guardar(Mascota mascota) {
        controllerMascota.create(mascota);
    }

    @Override
    public void actualizar(Mascota mascota) {
        try {
            controllerMascota.edit(mascota);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        try {
            controllerMascota.destroy(Long.MIN_VALUE);
        } catch (NonexistentEntityException e) {
            e.printStackTrace();
        }
    }
}
