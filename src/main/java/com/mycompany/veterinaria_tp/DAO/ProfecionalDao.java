/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.veterinaria_tp.DAO;

import com.mycompany.veterinaria_tp.Entidades.Profecional;
import java.util.List;

/**
 *
 * @author July
 */
public interface ProfecionalDao {
    Profecional obtenerPorId(int id);
    List<Profecional> obtenerTodos();
    void guardar(Profecional medico);
    void actualizar(Profecional medico);
    void eliminar(int id);
}
