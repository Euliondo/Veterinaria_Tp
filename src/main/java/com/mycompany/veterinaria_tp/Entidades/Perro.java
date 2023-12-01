/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.veterinaria_tp.Entidades;

import java.io.Serializable;
import jakarta.persistence.Basic;
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
@Table(name = "perro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perro.findAll", query = "SELECT p FROM Perro p")
    , @NamedQuery(name = "Perro.findByIdPerro", query = "SELECT p FROM Perro p WHERE p.idPerro = :idPerro")
    , @NamedQuery(name = "Perro.findByTamanio", query = "SELECT p FROM Perro p WHERE p.tamanio = :tamanio")
    , @NamedQuery(name = "Perro.findByColorPelaje", query = "SELECT p FROM Perro p WHERE p.colorPelaje = :colorPelaje")})
public class Perro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_perro")
    private Integer idPerro;
    @Basic(optional = false)
    @Column(name = "tamanio")
    private String tamanio;
    @Basic(optional = false)
    @Column(name = "colorPelaje")
    private String colorPelaje;
    @JoinColumn(name = "id_mascota", referencedColumnName = "id_mascota")
    @ManyToOne(optional = false)
    private Mascota idMascota;

    public Perro() {
    }

    public Perro(Integer idPerro) {
        this.idPerro = idPerro;
    }

    public Perro(Integer idPerro, String tamanio, String colorPelaje) {
        this.idPerro = idPerro;
        this.tamanio = tamanio;
        this.colorPelaje = colorPelaje;
    }

    public Integer getIdPerro() {
        return idPerro;
    }

    public void setIdPerro(Integer idPerro) {
        this.idPerro = idPerro;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
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
        hash += (idPerro != null ? idPerro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perro)) {
            return false;
        }
        Perro other = (Perro) object;
        if ((this.idPerro == null && other.idPerro != null) || (this.idPerro != null && !this.idPerro.equals(other.idPerro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.veterinaria_tp.Persistencia.Perro[ idPerro=" + idPerro + " ]";
    }
    
}
