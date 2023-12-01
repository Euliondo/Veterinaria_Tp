/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.veterinaria_tp.Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author July
 */
@Entity
@Table(name = "mascota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mascota.findAll", query = "SELECT m FROM Mascota m")
    , @NamedQuery(name = "Mascota.findByIdMascota", query = "SELECT m FROM Mascota m WHERE m.idMascota = :idMascota")
    , @NamedQuery(name = "Mascota.findByNombre", query = "SELECT m FROM Mascota m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "Mascota.findBySexo", query = "SELECT m FROM Mascota m WHERE m.sexo = :sexo")
    , @NamedQuery(name = "Mascota.findByRaza", query = "SELECT m FROM Mascota m WHERE m.raza = :raza")
    , @NamedQuery(name = "Mascota.findByEdad", query = "SELECT m FROM Mascota m WHERE m.edad = :edad")
    , @NamedQuery(name = "Mascota.findByFechaNacimiento", query = "SELECT m FROM Mascota m WHERE m.fechaNacimiento = :fechaNacimiento")})
public class Mascota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mascota")
    private Integer idMascota;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "sexo")
    private String sexo;
    @Basic(optional = false)
    @Column(name = "raza")
    private String raza;
    @Basic(optional = false)
    @Column(name = "edad")
    private int edad;
    @Basic(optional = false)
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMascota")
    private List<Perro> perroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMascota")
    private List<Gato> gatoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMascota")
    private List<TurnoMedico> turnomedicoList;

    public Mascota() {
    }

    public Mascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public Mascota(Integer idMascota, String nombre, String sexo, String raza, int edad, Date fechaNacimiento) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.sexo = sexo;
        this.raza = raza;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @XmlTransient
    public List<Perro> getPerroList() {
        return perroList;
    }

    public void setPerroList(List<Perro> perroList) {
        this.perroList = perroList;
    }

    @XmlTransient
    public List<Gato> getGatoList() {
        return gatoList;
    }

    public void setGatoList(List<Gato> gatoList) {
        this.gatoList = gatoList;
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
        hash += (idMascota != null ? idMascota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mascota)) {
            return false;
        }
        Mascota other = (Mascota) object;
        if ((this.idMascota == null && other.idMascota != null) || (this.idMascota != null && !this.idMascota.equals(other.idMascota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.veterinaria_tp.Persistencia.Mascota[ idMascota=" + idMascota + " ]";
    }
    
}
