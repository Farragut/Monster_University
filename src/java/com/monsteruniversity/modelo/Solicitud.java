/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsteruniversity.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s")
    , @NamedQuery(name = "Solicitud.findBySolId", query = "SELECT s FROM Solicitud s WHERE s.solId = :solId")
    , @NamedQuery(name = "Solicitud.findByEstId", query = "SELECT s FROM Solicitud s WHERE s.estId = :estId")
    , @NamedQuery(name = "Solicitud.findBySolFecha", query = "SELECT s FROM Solicitud s WHERE s.solFecha = :solFecha")
    , @NamedQuery(name = "Solicitud.findBySolAprobacionJa", query = "SELECT s FROM Solicitud s WHERE s.solAprobacionJa = :solAprobacionJa")
    , @NamedQuery(name = "Solicitud.findBySolAprobacionDf", query = "SELECT s FROM Solicitud s WHERE s.solAprobacionDf = :solAprobacionDf")
    , @NamedQuery(name = "Solicitud.findBySolValorTotal", query = "SELECT s FROM Solicitud s WHERE s.solValorTotal = :solValorTotal")})
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SOL_ID")
    private Integer solId;
    @Column(name = "EST_ID")
    private Integer estId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SOL_FECHA")
    @Temporal(TemporalType.DATE)
    private Date solFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SOL_APROBACION_JA")
    private boolean solAprobacionJa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SOL_APROBACION_DF")
    private boolean solAprobacionDf;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "SOL_VALOR_TOTAL")
    private BigDecimal solValorTotal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "solicitud")
    private Collection<SolicitudDetalle> solicitudDetalleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "solicitud")
    private Collection<OrdcSolicitud> ordcSolicitudCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "solId")
    private Collection<Cotizacion> cotizacionCollection;
    @JoinColumn(name = "AREA_ID", referencedColumnName = "AREA_ID")
    @ManyToOne(optional = false)
    private Area areaId;
    @JoinColumn(name = "EMP_ID", referencedColumnName = "EMP_ID")
    @ManyToOne
    private Empleado empId;
    @JoinColumn(name = "RUB_ID", referencedColumnName = "RUB_ID")
    @ManyToOne(optional = false)
    private RubroPresupuestal rubId;

    public Solicitud() {
    }

    public Solicitud(Integer solId) {
        this.solId = solId;
    }

    public Solicitud(Integer solId, Date solFecha, boolean solAprobacionJa, boolean solAprobacionDf, BigDecimal solValorTotal) {
        this.solId = solId;
        this.solFecha = solFecha;
        this.solAprobacionJa = solAprobacionJa;
        this.solAprobacionDf = solAprobacionDf;
        this.solValorTotal = solValorTotal;
    }

    public Integer getSolId() {
        return solId;
    }

    public void setSolId(Integer solId) {
        this.solId = solId;
    }

    public Integer getEstId() {
        return estId;
    }

    public void setEstId(Integer estId) {
        this.estId = estId;
    }

    public Date getSolFecha() {
        return solFecha;
    }

    public void setSolFecha(Date solFecha) {
        this.solFecha = solFecha;
    }

    public boolean getSolAprobacionJa() {
        return solAprobacionJa;
    }

    public void setSolAprobacionJa(boolean solAprobacionJa) {
        this.solAprobacionJa = solAprobacionJa;
    }

    public boolean getSolAprobacionDf() {
        return solAprobacionDf;
    }

    public void setSolAprobacionDf(boolean solAprobacionDf) {
        this.solAprobacionDf = solAprobacionDf;
    }

    public BigDecimal getSolValorTotal() {
        return solValorTotal;
    }

    public void setSolValorTotal(BigDecimal solValorTotal) {
        this.solValorTotal = solValorTotal;
    }

    @XmlTransient
    public Collection<SolicitudDetalle> getSolicitudDetalleCollection() {
        return solicitudDetalleCollection;
    }

    public void setSolicitudDetalleCollection(Collection<SolicitudDetalle> solicitudDetalleCollection) {
        this.solicitudDetalleCollection = solicitudDetalleCollection;
    }

    @XmlTransient
    public Collection<OrdcSolicitud> getOrdcSolicitudCollection() {
        return ordcSolicitudCollection;
    }

    public void setOrdcSolicitudCollection(Collection<OrdcSolicitud> ordcSolicitudCollection) {
        this.ordcSolicitudCollection = ordcSolicitudCollection;
    }

    @XmlTransient
    public Collection<Cotizacion> getCotizacionCollection() {
        return cotizacionCollection;
    }

    public void setCotizacionCollection(Collection<Cotizacion> cotizacionCollection) {
        this.cotizacionCollection = cotizacionCollection;
    }

    public Area getAreaId() {
        return areaId;
    }

    public void setAreaId(Area areaId) {
        this.areaId = areaId;
    }

    public Empleado getEmpId() {
        return empId;
    }

    public void setEmpId(Empleado empId) {
        this.empId = empId;
    }

    public RubroPresupuestal getRubId() {
        return rubId;
    }

    public void setRubId(RubroPresupuestal rubId) {
        this.rubId = rubId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (solId != null ? solId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.solId == null && other.solId != null) || (this.solId != null && !this.solId.equals(other.solId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.Solicitud[ solId=" + solId + " ]";
    }
    
}
