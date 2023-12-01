/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.veterinaria_tp.Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author July
 */
@Entity
@Table(name = "gato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gato.findAll", query = "SELECT g FROM Gato g")
    , @NamedQuery(name = "Gato.findByIdGato", query = "SELECT g FROM Gato g WHERE g.idGato = :idGato")
    , @NamedQuery(name = "Gato.findByColorPelaje", query = "SELECT g FROM Gato g WHERE g.colorPelaje = :colorPelaje")})
public class Gato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_gato")
    private Integer idGato;
    @Basic(optional = false)
    @Column(name = "colorPelaje")
    private String colorPelaje;
    @JoinColumn(name = "id_mascota", referencedColumnName = "id_mascota")
    @ManyToOne(optional = false)
    private Mascota idMascota;

    public Gato() {
    }

    public Gato(Integer idGato) {
        this.idGato = idGato;
    }

    public Gato(Integer idGato, String colorPelaje) {
        this.idGato = idGato;
        this.colorPelaje = colorPelaje;
    }

    public Integer getIdGato() {
        return idGato;
    }

    public void setIdGato(Integer idGato) {
        this.idGato = idGato;
    }

    public String getColorPelaje() {
        return colorPelaje;
    }

    public void setColorPelaje(String colorPelaje) {
        this.colorPelaje = colorPelaje;
    }

    public Mascota getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Mascota idMascota) {
        this.idMascota = idMascota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGato != null ? idGato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gato)) {
            return false;
        }
        Gato other = (Gato) object;
        if ((this.idGato == null && other.idGato != null) || (this.idGato != null && !this.idGato.equals(other.idGato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.veterinaria_tp.Persistencia.Gato[ idGato=" + idGato + " ]";
    }
    
}
