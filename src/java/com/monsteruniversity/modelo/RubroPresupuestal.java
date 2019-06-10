/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsteruniversity.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Reaper
 */
@Entity
@Table(name = "rubro_presupuestal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RubroPresupuestal.findAll", query = "SELECT r FROM RubroPresupuestal r")
    , @NamedQuery(name = "RubroPresupuestal.findByRubId", query = "SELECT r FROM RubroPresupuestal r WHERE r.rubId = :rubId")
    , @NamedQuery(name = "RubroPresupuestal.findByRubDescripcion", query = "SELECT r FROM RubroPresupuestal r WHERE r.rubDescripcion = :rubDescripcion")})
public class RubroPresupuestal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RUB_ID")
    private Integer rubId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "RUB_DESCRIPCION")
    private String rubDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rubId")
    private Collection<Solicitud> solicitudCollection;

    public RubroPresupuestal() {
    }

    public RubroPresupuestal(Integer rubId) {
        this.rubId = rubId;
    }

    public RubroPresupuestal(Integer rubId, String rubDescripcion) {
        this.rubId = rubId;
        this.rubDescripcion = rubDescripcion;
    }

    public Integer getRubId() {
        return rubId;
    }

    public void setRubId(Integer rubId) {
        this.rubId = rubId;
    }

    public String getRubDescripcion() {
        return rubDescripcion;
    }

    public void setRubDescripcion(String rubDescripcion) {
        this.rubDescripcion = rubDescripcion;
    }

    @XmlTransient
    public Collection<Solicitud> getSolicitudCollection() {
        return solicitudCollection;
    }

    public void setSolicitudCollection(Collection<Solicitud> solicitudCollection) {
        this.solicitudCollection = solicitudCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rubId != null ? rubId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RubroPresupuestal)) {
            return false;
        }
        RubroPresupuestal other = (RubroPresupuestal) object;
        if ((this.rubId == null && other.rubId != null) || (this.rubId != null && !this.rubId.equals(other.rubId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.RubroPresupuestal[ rubId=" + rubId + " ]";
    }
    
}
