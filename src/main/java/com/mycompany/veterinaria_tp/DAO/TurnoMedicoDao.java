/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.veterinaria_tp.DAO;

import com.mycompany.veterinaria_tp.Entidades.TurnoMedico;
import java.util.List;

/**
 *
 * @author July
 */
public interface TurnoMedicoDao {
    TurnoMedico obtenerPorId(int id);
    List<TurnoMedico> obtenerTodos();
    void guardar(TurnoMedico turno);
    void actualizar(TurnoMedico turno);
    void eliminar(int id);
}
