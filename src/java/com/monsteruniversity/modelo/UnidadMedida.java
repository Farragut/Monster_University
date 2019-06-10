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
@Table(name = "unidad_medida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadMedida.findAll", query = "SELECT u FROM UnidadMedida u")
    , @NamedQuery(name = "UnidadMedida.findByUniId", query = "SELECT u FROM UnidadMedida u WHERE u.uniId = :uniId")
    , @NamedQuery(name = "UnidadMedida.findByUniNombre", query = "SELECT u FROM UnidadMedida u WHERE u.uniNombre = :uniNombre")
    , @NamedQuery(name = "UnidadMedida.findByUniAbreviacion", query = "SELECT u FROM UnidadMedida u WHERE u.uniAbreviacion = :uniAbreviacion")})
public class UnidadMedida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UNI_ID")
    private Integer uniId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "UNI_NOMBRE")
    private String uniNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "UNI_ABREVIACION")
    private String uniAbreviacion;
    @OneToMany(mappedBy = "uniId")
    private Collection<BienProveedor> bienProveedorCollection;

    public UnidadMedida() {
    }

    public UnidadMedida(Integer uniId) {
        this.uniId = uniId;
    }

    public UnidadMedida(Integer uniId, String uniNombre, String uniAbreviacion) {
        this.uniId = uniId;
        this.uniNombre = uniNombre;
        this.uniAbreviacion = uniAbreviacion;
    }

    public Integer getUniId() {
        return uniId;
    }

    public void setUniId(Integer uniId) {
        this.uniId = uniId;
    }

    public String getUniNombre() {
        return uniNombre;
    }

    public void setUniNombre(String uniNombre) {
        this.uniNombre = uniNombre;
    }

    public String getUniAbreviacion() {
        return uniAbreviacion;
    }

    public void setUniAbreviacion(String uniAbreviacion) {
        this.uniAbreviacion = uniAbreviacion;
    }

    @XmlTransient
    public Collection<BienProveedor> getBienProveedorCollection() {
        return bienProveedorCollection;
    }

    public void setBienProveedorCollection(Collection<BienProveedor> bienProveedorCollection) {
        this.bienProveedorCollection = bienProveedorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uniId != null ? uniId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadMedida)) {
            return false;
        }
        UnidadMedida other = (UnidadMedida) object;
        if ((this.uniId == null && other.uniId != null) || (this.uniId != null && !this.uniId.equals(other.uniId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.UnidadMedida[ uniId=" + uniId + " ]";
    }
    
}
