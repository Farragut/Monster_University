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
@Table(name = "solicitud_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudDetalle.findAll", query = "SELECT s FROM SolicitudDetalle s")
    , @NamedQuery(name = "SolicitudDetalle.findBySolId", query = "SELECT s FROM SolicitudDetalle s WHERE s.solicitudDetallePK.solId = :solId")
    , @NamedQuery(name = "SolicitudDetalle.findByBienProvId", query = "SELECT s FROM SolicitudDetalle s WHERE s.solicitudDetallePK.bienProvId = :bienProvId")
    , @NamedQuery(name = "SolicitudDetalle.findBySolDetCantidadSolicitada", query = "SELECT s FROM SolicitudDetalle s WHERE s.solDetCantidadSolicitada = :solDetCantidadSolicitada")})
public class SolicitudDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SolicitudDetallePK solicitudDetallePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SOL_DET_CANTIDAD_SOLICITADA")
    private BigDecimal solDetCantidadSolicitada;
    @JoinColumn(name = "BIEN_PROV_ID", referencedColumnName = "BIEN_PROV_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private BienProveedor bienProveedor;
    @JoinColumn(name = "SOL_ID", referencedColumnName = "SOL_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Solicitud solicitud;

    public SolicitudDetalle() {
    }

    public SolicitudDetalle(SolicitudDetallePK solicitudDetallePK) {
        this.solicitudDetallePK = solicitudDetallePK;
    }

    public SolicitudDetalle(int solId, int bienProvId) {
        this.solicitudDetallePK = new SolicitudDetallePK(solId, bienProvId);
    }

    public SolicitudDetallePK getSolicitudDetallePK() {
        return solicitudDetallePK;
    }

    public void setSolicitudDetallePK(SolicitudDetallePK solicitudDetallePK) {
        this.solicitudDetallePK = solicitudDetallePK;
    }

    public BigDecimal getSolDetCantidadSolicitada() {
        return solDetCantidadSolicitada;
    }

    public void setSolDetCantidadSolicitada(BigDecimal solDetCantidadSolicitada) {
        this.solDetCantidadSolicitada = solDetCantidadSolicitada;
    }

    public BienProveedor getBienProveedor() {
        return bienProveedor;
    }

    public void setBienProveedor(BienProveedor bienProveedor) {
        this.bienProveedor = bienProveedor;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (solicitudDetallePK != null ? solicitudDetallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudDetalle)) {
            return false;
        }
        SolicitudDetalle other = (SolicitudDetalle) object;
        if ((this.solicitudDetallePK == null && other.solicitudDetallePK != null) || (this.solicitudDetallePK != null && !this.solicitudDetallePK.equals(other.solicitudDetallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.SolicitudDetalle[ solicitudDetallePK=" + solicitudDetallePK + " ]";
    }
    
}
