/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsteruniversity.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Reaper
 */
@Entity
@Table(name = "salida_almacen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalidaAlmacen.findAll", query = "SELECT s FROM SalidaAlmacen s")
    , @NamedQuery(name = "SalidaAlmacen.findByProvId", query = "SELECT s FROM SalidaAlmacen s WHERE s.salidaAlmacenPK.provId = :provId")
    , @NamedQuery(name = "SalidaAlmacen.findByEntId", query = "SELECT s FROM SalidaAlmacen s WHERE s.salidaAlmacenPK.entId = :entId")
    , @NamedQuery(name = "SalidaAlmacen.findBySalId", query = "SELECT s FROM SalidaAlmacen s WHERE s.salidaAlmacenPK.salId = :salId")
    , @NamedQuery(name = "SalidaAlmacen.findByEstId", query = "SELECT s FROM SalidaAlmacen s WHERE s.estId = :estId")
    , @NamedQuery(name = "SalidaAlmacen.findBySalFecha", query = "SELECT s FROM SalidaAlmacen s WHERE s.salFecha = :salFecha")
    , @NamedQuery(name = "SalidaAlmacen.findBySalFechaEntrega", query = "SELECT s FROM SalidaAlmacen s WHERE s.salFechaEntrega = :salFechaEntrega")})
public class SalidaAlmacen implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SalidaAlmacenPK salidaAlmacenPK;
    @Column(name = "EST_ID")
    private Integer estId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SAL_FECHA")
    @Temporal(TemporalType.DATE)
    private Date salFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SAL_FECHA_ENTREGA")
    @Temporal(TemporalType.DATE)
    private Date salFechaEntrega;
    @JoinTable(name = "area_salida", joinColumns = {
        @JoinColumn(name = "PROV_ID", referencedColumnName = "PROV_ID")
        , @JoinColumn(name = "ENT_ID", referencedColumnName = "ENT_ID")
        , @JoinColumn(name = "SAL_ID", referencedColumnName = "SAL_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "AREA_ID", referencedColumnName = "AREA_ID")})
    @ManyToMany
    private Collection<Area> areaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salidaAlmacen")
    private Collection<SalidaBien> salidaBienCollection;
    @JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID")
    @ManyToOne
    private Empleado empId;
    @JoinColumns({
        @JoinColumn(name = "PROV_ID", referencedColumnName = "PROV_ID", insertable = false, updatable = false)
        , @JoinColumn(name = "ENT_ID", referencedColumnName = "ENT_ID", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private EntradaAlmacen entradaAlmacen;

    public SalidaAlmacen() {
    }

    public SalidaAlmacen(SalidaAlmacenPK salidaAlmacenPK) {
        this.salidaAlmacenPK = salidaAlmacenPK;
    }

    public SalidaAlmacen(SalidaAlmacenPK salidaAlmacenPK, Date salFecha, Date salFechaEntrega) {
        this.salidaAlmacenPK = salidaAlmacenPK;
        this.salFecha = salFecha;
        this.salFechaEntrega = salFechaEntrega;
    }

    public SalidaAlmacen(int provId, int entId, int salId) {
        this.salidaAlmacenPK = new SalidaAlmacenPK(provId, entId, salId);
    }

    public SalidaAlmacenPK getSalidaAlmacenPK() {
        return salidaAlmacenPK;
    }

    public void setSalidaAlmacenPK(SalidaAlmacenPK salidaAlmacenPK) {
        this.salidaAlmacenPK = salidaAlmacenPK;
    }

    public Integer getEstId() {
        return estId;
    }

    public void setEstId(Integer estId) {
        this.estId = estId;
    }

    public Date getSalFecha() {
        return salFecha;
    }

    public void setSalFecha(Date salFecha) {
        this.salFecha = salFecha;
    }

    public Date getSalFechaEntrega() {
        return salFechaEntrega;
    }

    public void setSalFechaEntrega(Date salFechaEntrega) {
        this.salFechaEntrega = salFechaEntrega;
    }

    @XmlTransient
    public Collection<Area> getAreaCollection() {
        return areaCollection;
    }

    public void setAreaCollection(Collection<Area> areaCollection) {
        this.areaCollection = areaCollection;
    }

    @XmlTransient
    public Collection<SalidaBien> getSalidaBienCollection() {
        return salidaBienCollection;
    }

    public void setSalidaBienCollection(Collection<SalidaBien> salidaBienCollection) {
        this.salidaBienCollection = salidaBienCollection;
    }

    public Empleado getEmpId() {
        return empId;
    }

    public void setEmpId(Empleado empId) {
        this.empId = empId;
    }

    public EntradaAlmacen getEntradaAlmacen() {
        return entradaAlmacen;
    }

    public void setEntradaAlmacen(EntradaAlmacen entradaAlmacen) {
        this.entradaAlmacen = entradaAlmacen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salidaAlmacenPK != null ? salidaAlmacenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalidaAlmacen)) {
            return false;
        }
        SalidaAlmacen other = (SalidaAlmacen) object;
        if ((this.salidaAlmacenPK == null && other.salidaAlmacenPK != null) || (this.salidaAlmacenPK != null && !this.salidaAlmacenPK.equals(other.salidaAlmacenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.SalidaAlmacen[ salidaAlmacenPK=" + salidaAlmacenPK + " ]";
    }
    
}
