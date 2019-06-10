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
import javax.persistence.JoinColumns;
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
@Table(name = "detalle_entrada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleEntrada.findAll", query = "SELECT d FROM DetalleEntrada d")
    , @NamedQuery(name = "DetalleEntrada.findByProvId", query = "SELECT d FROM DetalleEntrada d WHERE d.detalleEntradaPK.provId = :provId")
    , @NamedQuery(name = "DetalleEntrada.findByEntId", query = "SELECT d FROM DetalleEntrada d WHERE d.detalleEntradaPK.entId = :entId")
    , @NamedQuery(name = "DetalleEntrada.findByBienProvId", query = "SELECT d FROM DetalleEntrada d WHERE d.detalleEntradaPK.bienProvId = :bienProvId")
    , @NamedQuery(name = "DetalleEntrada.findByDetEntCantidadEntregada", query = "SELECT d FROM DetalleEntrada d WHERE d.detEntCantidadEntregada = :detEntCantidadEntregada")})
public class DetalleEntrada implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleEntradaPK detalleEntradaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DET_ENT_CANTIDAD_ENTREGADA")
    private BigDecimal detEntCantidadEntregada;
    @JoinColumn(name = "BIEN_PROV_ID", referencedColumnName = "BIEN_PROV_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private BienProveedor bienProveedor;
    @JoinColumns({
        @JoinColumn(name = "PROV_ID", referencedColumnName = "PROV_ID", insertable = false, updatable = false)
        , @JoinColumn(name = "ENT_ID", referencedColumnName = "ENT_ID", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private EntradaAlmacen entradaAlmacen;

    public DetalleEntrada() {
    }

    public DetalleEntrada(DetalleEntradaPK detalleEntradaPK) {
        this.detalleEntradaPK = detalleEntradaPK;
    }

    public DetalleEntrada(int provId, int entId, int bienProvId) {
        this.detalleEntradaPK = new DetalleEntradaPK(provId, entId, bienProvId);
    }

    public DetalleEntradaPK getDetalleEntradaPK() {
        return detalleEntradaPK;
    }

    public void setDetalleEntradaPK(DetalleEntradaPK detalleEntradaPK) {
        this.detalleEntradaPK = detalleEntradaPK;
    }

    public BigDecimal getDetEntCantidadEntregada() {
        return detEntCantidadEntregada;
    }

    public void setDetEntCantidadEntregada(BigDecimal detEntCantidadEntregada) {
        this.detEntCantidadEntregada = detEntCantidadEntregada;
    }

    public BienProveedor getBienProveedor() {
        return bienProveedor;
    }

    public void setBienProveedor(BienProveedor bienProveedor) {
        this.bienProveedor = bienProveedor;
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
        hash += (detalleEntradaPK != null ? detalleEntradaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleEntrada)) {
            return false;
        }
        DetalleEntrada other = (DetalleEntrada) object;
        if ((this.detalleEntradaPK == null && other.detalleEntradaPK != null) || (this.detalleEntradaPK != null && !this.detalleEntradaPK.equals(other.detalleEntradaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.DetalleEntrada[ detalleEntradaPK=" + detalleEntradaPK + " ]";
    }
    
}
