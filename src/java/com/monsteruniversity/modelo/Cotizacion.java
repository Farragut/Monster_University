/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsteruniversity.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Reaper
 */
@Entity
@Table(name = "cotizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cotizacion.findAll", query = "SELECT c FROM Cotizacion c")
    , @NamedQuery(name = "Cotizacion.findByCotId", query = "SELECT c FROM Cotizacion c WHERE c.cotId = :cotId")
    , @NamedQuery(name = "Cotizacion.findByCotAprobacion", query = "SELECT c FROM Cotizacion c WHERE c.cotAprobacion = :cotAprobacion")})
public class Cotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COT_ID")
    private Integer cotId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COT_APROBACION")
    private boolean cotAprobacion;
    @JoinTable(name = "proveedor_cotizacion", joinColumns = {
        @JoinColumn(name = "COT_ID", referencedColumnName = "COT_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PROV_ID", referencedColumnName = "PROV_ID")})
    @ManyToMany
    private Collection<Proveedor> proveedorCollection;
    @JoinColumn(name = "SOL_ID", referencedColumnName = "SOL_ID")
    @ManyToOne(optional = false)
    private Solicitud solId;

    public Cotizacion() {
    }

    public Cotizacion(Integer cotId) {
        this.cotId = cotId;
    }

    public Cotizacion(Integer cotId, boolean cotAprobacion) {
        this.cotId = cotId;
        this.cotAprobacion = cotAprobacion;
    }

    public Integer getCotId() {
        return cotId;
    }

    public void setCotId(Integer cotId) {
        this.cotId = cotId;
    }

    public boolean getCotAprobacion() {
        return cotAprobacion;
    }

    public void setCotAprobacion(boolean cotAprobacion) {
        this.cotAprobacion = cotAprobacion;
    }

    @XmlTransient
    public Collection<Proveedor> getProveedorCollection() {
        return proveedorCollection;
    }

    public void setProveedorCollection(Collection<Proveedor> proveedorCollection) {
        this.proveedorCollection = proveedorCollection;
    }

    public Solicitud getSolId() {
        return solId;
    }

    public void setSolId(Solicitud solId) {
        this.solId = solId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cotId != null ? cotId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cotizacion)) {
            return false;
        }
        Cotizacion other = (Cotizacion) object;
        if ((this.cotId == null && other.cotId != null) || (this.cotId != null && !this.cotId.equals(other.cotId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.Cotizacion[ cotId=" + cotId + " ]";
    }
    
}
