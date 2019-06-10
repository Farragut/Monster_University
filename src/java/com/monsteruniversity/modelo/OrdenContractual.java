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
@Table(name = "orden_contractual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenContractual.findAll", query = "SELECT o FROM OrdenContractual o")
    , @NamedQuery(name = "OrdenContractual.findByOrdcId", query = "SELECT o FROM OrdenContractual o WHERE o.ordcId = :ordcId")
    , @NamedQuery(name = "OrdenContractual.findByOrdcFecha", query = "SELECT o FROM OrdenContractual o WHERE o.ordcFecha = :ordcFecha")
    , @NamedQuery(name = "OrdenContractual.findByOrdcFechaEntrega", query = "SELECT o FROM OrdenContractual o WHERE o.ordcFechaEntrega = :ordcFechaEntrega")
    , @NamedQuery(name = "OrdenContractual.findByOrdcValorTotal", query = "SELECT o FROM OrdenContractual o WHERE o.ordcValorTotal = :ordcValorTotal")
    , @NamedQuery(name = "OrdenContractual.findByOrdcAprobacionDf", query = "SELECT o FROM OrdenContractual o WHERE o.ordcAprobacionDf = :ordcAprobacionDf")})
public class OrdenContractual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ORDC_ID")
    private Integer ordcId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDC_FECHA")
    @Temporal(TemporalType.DATE)
    private Date ordcFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDC_FECHA_ENTREGA")
    @Temporal(TemporalType.DATE)
    private Date ordcFechaEntrega;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDC_VALOR_TOTAL")
    private BigDecimal ordcValorTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDC_APROBACION_DF")
    private boolean ordcAprobacionDf;
    @JoinColumn(name = "PROV_ID", referencedColumnName = "PROV_ID")
    @ManyToOne(optional = false)
    private Proveedor provId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenContractual")
    private Collection<OrdcSolicitud> ordcSolicitudCollection;

    public OrdenContractual() {
    }

    public OrdenContractual(Integer ordcId) {
        this.ordcId = ordcId;
    }

    public OrdenContractual(Integer ordcId, Date ordcFecha, Date ordcFechaEntrega, BigDecimal ordcValorTotal, boolean ordcAprobacionDf) {
        this.ordcId = ordcId;
        this.ordcFecha = ordcFecha;
        this.ordcFechaEntrega = ordcFechaEntrega;
        this.ordcValorTotal = ordcValorTotal;
        this.ordcAprobacionDf = ordcAprobacionDf;
    }

    public Integer getOrdcId() {
        return ordcId;
    }

    public void setOrdcId(Integer ordcId) {
        this.ordcId = ordcId;
    }

    public Date getOrdcFecha() {
        return ordcFecha;
    }

    public void setOrdcFecha(Date ordcFecha) {
        this.ordcFecha = ordcFecha;
    }

    public Date getOrdcFechaEntrega() {
        return ordcFechaEntrega;
    }

    public void setOrdcFechaEntrega(Date ordcFechaEntrega) {
        this.ordcFechaEntrega = ordcFechaEntrega;
    }

    public BigDecimal getOrdcValorTotal() {
        return ordcValorTotal;
    }

    public void setOrdcValorTotal(BigDecimal ordcValorTotal) {
        this.ordcValorTotal = ordcValorTotal;
    }

    public boolean getOrdcAprobacionDf() {
        return ordcAprobacionDf;
    }

    public void setOrdcAprobacionDf(boolean ordcAprobacionDf) {
        this.ordcAprobacionDf = ordcAprobacionDf;
    }

    public Proveedor getProvId() {
        return provId;
    }

    public void setProvId(Proveedor provId) {
        this.provId = provId;
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
        hash += (ordcId != null ? ordcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenContractual)) {
            return false;
        }
        OrdenContractual other = (OrdenContractual) object;
        if ((this.ordcId == null && other.ordcId != null) || (this.ordcId != null && !this.ordcId.equals(other.ordcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.OrdenContractual[ ordcId=" + ordcId + " ]";
    }
    
}
