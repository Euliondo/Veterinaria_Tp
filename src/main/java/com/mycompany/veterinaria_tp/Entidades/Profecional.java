/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.veterinaria_tp.Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author July
 */
@Entity
@Table(name = "profecional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profecional.findAll", query = "SELECT p FROM Profecional p")
    , @NamedQuery(name = "Profecional.findByIdProfecional", query = "SELECT p FROM Profecional p WHERE p.idProfecional = :idProfecional")
    , @NamedQuery(name = "Profecional.findByNombre", query = "SELECT p FROM Profecional p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Profecional.findByEspecialidad", query = "SELECT p FROM Profecional p WHERE p.especialidad = :especialidad")})
public class Profecional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_profecional")
    private Integer idProfecional;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "especialidad")
    private String especialidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProfecional")
    private List<TurnoMedico> turnomedicoList;

    public Profecional() {
    }

    public Profecional(Integer idProfecional) {
        this.idProfecional = idProfecional;
    }

    public Profecional(Integer idProfecional, String nombre, String especialidad) {
        this.idProfecional = idProfecional;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public Integer getIdProfecional() {
        return idProfecional;
    }

    public void setIdProfecional(Integer idProfecional) {
        this.idProfecional = idProfecional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @XmlTransient
    public List<TurnoMedico> getTurnomedicoList() {
        return turnomedicoList;
    }

    public void setTurnomedicoList(List<TurnoMedico> turnomedicoList) {
        this.turnomedicoList = turnomedicoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfecional != null ? idProfecional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profecional)) {
            return false;
        }
        Profecional other = (Profecional) object;
        if ((this.idProfecional == null && other.idProfecional != null) || (this.idProfecional != null && !this.idProfecional.equals(other.idProfecional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.veterinaria_tp.Persistencia.Profecional[ idProfecional=" + idProfecional + " ]";
    }
    
}
