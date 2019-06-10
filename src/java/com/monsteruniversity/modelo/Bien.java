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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "bien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bien.findAll", query = "SELECT b FROM Bien b")
    , @NamedQuery(name = "Bien.findByBienId", query = "SELECT b FROM Bien b WHERE b.bienId = :bienId")
    , @NamedQuery(name = "Bien.findByBienNombre", query = "SELECT b FROM Bien b WHERE b.bienNombre = :bienNombre")
    , @NamedQuery(name = "Bien.findByBienCaracter", query = "SELECT b FROM Bien b WHERE b.bienCaracter = :bienCaracter")})
public class Bien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BIEN_ID")
    private Integer bienId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "BIEN_NOMBRE")
    private String bienNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "BIEN_CARACTER")
    private String bienCaracter;
    @OneToMany(mappedBy = "bienId")
    private Collection<BienProveedor> bienProveedorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bien")
    private Collection<OrdcSolicitud> ordcSolicitudCollection;

    public Bien() {
    }

    public Bien(Integer bienId) {
        this.bienId = bienId;
    }

    public Bien(Integer bienId, String bienNombre, String bienCaracter) {
        this.bienId = bienId;
        this.bienNombre = bienNombre;
        this.bienCaracter = bienCaracter;
    }

    public Integer getBienId() {
        return bienId;
    }

    public void setBienId(Integer bienId) {
        this.bienId = bienId;
    }

    public String getBienNombre() {
        return bienNombre;
    }

    public void setBienNombre(String bienNombre) {
        this.bienNombre = bienNombre;
    }

    public String getBienCaracter() {
        return bienCaracter;
    }

    public void setBienCaracter(String bienCaracter) {
        this.bienCaracter = bienCaracter;
    }

    @XmlTransient
    public Collection<BienProveedor> getBienProveedorCollection() {
        return bienProveedorCollection;
    }

    public void setBienProveedorCollection(Collection<BienProveedor> bienProveedorCollection) {
        this.bienProveedorCollection = bienProveedorCollection;
    }

    @XmlTransient
    public Collection<OrdcSolicitud> getOrdcSolicitudCollection() {
        return ordcSolicitudCollection;
    }

    public void setOrdcSolicitudCollection(Collection<OrdcSolicitud> ordcSolicitudCollection) {
        this.ordcSolicitudCollection = ordcSolicitudCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bienId != null ? bienId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bien)) {
            return false;
        }
        Bien other = (Bien) object;
        if ((this.bienId == null && other.bienId != null) || (this.bienId != null && !this.bienId.equals(other.bienId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.Bien[ bienId=" + bienId + " ]";
    }
    
}
