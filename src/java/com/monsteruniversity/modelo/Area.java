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
import javax.persistence.ManyToMany;
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
@Table(name = "area")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a")
    , @NamedQuery(name = "Area.findByAreaId", query = "SELECT a FROM Area a WHERE a.areaId = :areaId")
    , @NamedQuery(name = "Area.findByAreaDescripcion", query = "SELECT a FROM Area a WHERE a.areaDescripcion = :areaDescripcion")})
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AREA_ID")
    private Integer areaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "AREA_DESCRIPCION")
    private String areaDescripcion;
    @ManyToMany(mappedBy = "areaCollection")
    private Collection<SalidaAlmacen> salidaAlmacenCollection;
    @OneToMany(mappedBy = "areaId")
    private Collection<Empleado> empleadoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaId")
    private Collection<Solicitud> solicitudCollection;

    public Area() {
    }

    public Area(Integer areaId) {
        this.areaId = areaId;
    }

    public Area(Integer areaId, String areaDescripcion) {
        this.areaId = areaId;
        this.areaDescripcion = areaDescripcion;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaDescripcion() {
        return areaDescripcion;
    }

    public void setAreaDescripcion(String areaDescripcion) {
        this.areaDescripcion = areaDescripcion;
    }

    @XmlTransient
    public Collection<SalidaAlmacen> getSalidaAlmacenCollection() {
        return salidaAlmacenCollection;
    }

    public void setSalidaAlmacenCollection(Collection<SalidaAlmacen> salidaAlmacenCollection) {
        this.salidaAlmacenCollection = salidaAlmacenCollection;
    }

    @XmlTransient
    public Collection<Empleado> getEmpleadoCollection() {
        return empleadoCollection;
    }

    public void setEmpleadoCollection(Collection<Empleado> empleadoCollection) {
        this.empleadoCollection = empleadoCollection;
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
        hash += (areaId != null ? areaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.areaId == null && other.areaId != null) || (this.areaId != null && !this.areaId.equals(other.areaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.Area[ areaId=" + areaId + " ]";
    }
    
}
