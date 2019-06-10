/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsteruniversity.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Reaper
 */
@Entity
@Table(name = "ordc_solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdcSolicitud.findAll", query = "SELECT o FROM OrdcSolicitud o")
    , @NamedQuery(name = "OrdcSolicitud.findBySolId", query = "SELECT o FROM OrdcSolicitud o WHERE o.ordcSolicitudPK.solId = :solId")
    , @NamedQuery(name = "OrdcSolicitud.findByOrdcId", query = "SELECT o FROM OrdcSolicitud o WHERE o.ordcSolicitudPK.ordcId = :ordcId")
    , @NamedQuery(name = "OrdcSolicitud.findByBienId", query = "SELECT o FROM OrdcSolicitud o WHERE o.ordcSolicitudPK.bienId = :bienId")
    , @NamedQuery(name = "OrdcSolicitud.findByOrdcSolCantidadSolicitada", query = "SELECT o FROM OrdcSolicitud o WHERE o.ordcSolCantidadSolicitada = :ordcSolCantidadSolicitada")
    , @NamedQuery(name = "OrdcSolicitud.findByOrdcSolCantidadDespachada", query = "SELECT o FROM OrdcSolicitud o WHERE o.ordcSolCantidadDespachada = :ordcSolCantidadDespachada")})
public class OrdcSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrdcSolicitudPK ordcSolicitudPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ORDC_SOL_CANTIDAD_SOLICITADA")
    private BigDecimal ordcSolCantidadSolicitada;
    @Column(name = "ORDC_SOL_CANTIDAD_DESPACHADA")
    private BigDecimal ordcSolCantidadDespachada;
    @JoinColumn(name = "BIEN_ID", referencedColumnName = "BIEN_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Bien bien;
    @JoinColumn(name = "SOL_ID", referencedColumnName = "SOL_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Solicitud solicitud;
    @JoinColumn(name = "ORDC_ID", referencedColumnName = "ORDC_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OrdenContractual ordenContractual;

    public OrdcSolicitud() {
    }

    public OrdcSolicitud(OrdcSolicitudPK ordcSolicitudPK) {
        this.ordcSolicitudPK = ordcSolicitudPK;
    }

    public OrdcSolicitud(int solId, int ordcId, int bienId) {
        this.ordcSolicitudPK = new OrdcSolicitudPK(solId, ordcId, bienId);
    }

    public OrdcSolicitudPK getOrdcSolicitudPK() {
        return ordcSolicitudPK;
    }

    public void setOrdcSolicitudPK(OrdcSolicitudPK ordcSolicitudPK) {
        this.ordcSolicitudPK = ordcSolicitudPK;
    }

    public BigDecimal getOrdcSolCantidadSolicitada() {
        return ordcSolCantidadSolicitada;
    }

    public void setOrdcSolCantidadSolicitada(BigDecimal ordcSolCantidadSolicitada) {
        this.ordcSolCantidadSolicitada = ordcSolCantidadSolicitada;
    }

    public BigDecimal getOrdcSolCantidadDespachada() {
        return ordcSolCantidadDespachada;
    }

    public void setOrdcSolCantidadDespachada(BigDecimal ordcSolCantidadDespachada) {
        this.ordcSolCantidadDespachada = ordcSolCantidadDespachada;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public OrdenContractual getOrdenContractual() {
        return ordenContractual;
    }

    public void setOrdenContractual(OrdenContractual ordenContractual) {
        this.ordenContractual = ordenContractual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordcSolicitudPK != null ? ordcSolicitudPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdcSolicitud)) {
            return false;
        }
        OrdcSolicitud other = (OrdcSolicitud) object;
        if ((this.ordcSolicitudPK == null && other.ordcSolicitudPK != null) || (this.ordcSolicitudPK != null && !this.ordcSolicitudPK.equals(other.ordcSolicitudPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.OrdcSolicitud[ ordcSolicitudPK=" + ordcSolicitudPK + " ]";
    }
    
}
