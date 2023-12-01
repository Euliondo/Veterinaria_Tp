/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.veterinaria_tp.DAO;

import com.mycompany.veterinaria_tp.Entidades.Mascota;
import java.util.List;

/**
 *
 * @author July
 */
public interface MascotaDao {
    Mascota obtenerPorId(int id);
    List<Mascota> obtenerTodos();
    void guardar(Mascota mascota);
    void actualizar(Mascota mascota);
    void eliminar(int id);
}
