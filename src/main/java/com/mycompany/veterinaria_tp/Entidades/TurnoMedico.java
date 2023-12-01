/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.veterinaria_tp.Entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author July
 */
@Entity
@Table(name = "turnomedico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Turnomedico.findAll", query = "SELECT t FROM Turnomedico t")
    , @NamedQuery(name = "Turnomedico.findByIdTurno", query = "SELECT t FROM Turnomedico t WHERE t.idTurno = :idTurno")
    , @NamedQuery(name = "Turnomedico.findByFecha", query = "SELECT t FROM Turnomedico t WHERE t.fecha = :fecha")
    , @NamedQuery(name = "Turnomedico.findByAprobado", query = "SELECT t FROM Turnomedico t WHERE t.aprobado = :aprobado")})
public class TurnoMedico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_turno")
    private Integer idTurno;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "aprobado")
    private boolean aprobado;
    @JoinColumn(name = "id_mascota", referencedColumnName = "id_mascota")
    @ManyToOne(optional = false)
    private Mascota idMascota;
    @JoinColumn(name = "id_profecional", referencedColumnName = "id_profecional")
    @ManyToOne(optional = false)
    private Profecional idProfecional;

    public TurnoMedico() {
    }

    public TurnoMedico(Integer idTurno) {
        this.idTurno = idTurno;
    }

    public TurnoMedico(Integer idTurno, Date fecha, boolean aprobado) {
        this.idTurno = idTurno;
        this.fecha = fecha;
        this.aprobado = aprobado;
    }

    public Integer getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Integer idTurno) {
        this.idTurno = idTurno;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public Mascota getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Mascota idMascota) {
        this.idMascota = idMascota;
    }

    public Profecional getIdProfecional() {
        return idProfecional;
    }

    public void setIdProfecional(Profecional idProfecional) {
        this.idProfecional = idProfecional;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTurno != null ? idTurno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TurnoMedico)) {
            return false;
        }
        TurnoMedico other = (TurnoMedico) object;
        if ((this.idTurno == null && other.idTurno != null) || (this.idTurno != null && !this.idTurno.equals(other.idTurno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.veterinaria_tp.Persistencia.Turnomedico[ idTurno=" + idTurno + " ]";
    }
    
}
