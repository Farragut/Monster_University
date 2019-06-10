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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "entrada_almacen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntradaAlmacen.findAll", query = "SELECT e FROM EntradaAlmacen e")
    , @NamedQuery(name = "EntradaAlmacen.findByProvId", query = "SELECT e FROM EntradaAlmacen e WHERE e.entradaAlmacenPK.provId = :provId")
    , @NamedQuery(name = "EntradaAlmacen.findByEntId", query = "SELECT e FROM EntradaAlmacen e WHERE e.entradaAlmacenPK.entId = :entId")
    , @NamedQuery(name = "EntradaAlmacen.findByEntNumeroFactura", query = "SELECT e FROM EntradaAlmacen e WHERE e.entNumeroFactura = :entNumeroFactura")
    , @NamedQuery(name = "EntradaAlmacen.findByEntFecha", query = "SELECT e FROM EntradaAlmacen e WHERE e.entFecha = :entFecha")
    , @NamedQuery(name = "EntradaAlmacen.findByEntValorTotal", query = "SELECT e FROM EntradaAlmacen e WHERE e.entValorTotal = :entValorTotal")})
public class EntradaAlmacen implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EntradaAlmacenPK entradaAlmacenPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENT_NUMERO_FACTURA")
    private int entNumeroFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENT_FECHA")
    @Temporal(TemporalType.DATE)
    private Date entFecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENT_VALOR_TOTAL")
    private BigDecimal entValorTotal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entradaAlmacen")
    private Collection<DetalleEntrada> detalleEntradaCollection;
    @JoinColumn(name = "PROV_ID", referencedColumnName = "PROV_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proveedor proveedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entradaAlmacen")
    private Collection<SalidaAlmacen> salidaAlmacenCollection;

    public EntradaAlmacen() {
    }

    public EntradaAlmacen(EntradaAlmacenPK entradaAlmacenPK) {
        this.entradaAlmacenPK = entradaAlmacenPK;
    }

    public EntradaAlmacen(EntradaAlmacenPK entradaAlmacenPK, int entNumeroFactura, Date entFecha, BigDecimal entValorTotal) {
        this.entradaAlmacenPK = entradaAlmacenPK;
        this.entNumeroFactura = entNumeroFactura;
        this.entFecha = entFecha;
        this.entValorTotal = entValorTotal;
    }

    public EntradaAlmacen(int provId, int entId) {
        this.entradaAlmacenPK = new EntradaAlmacenPK(provId, entId);
    }

    public EntradaAlmacenPK getEntradaAlmacenPK() {
        return entradaAlmacenPK;
    }

    public void setEntradaAlmacenPK(EntradaAlmacenPK entradaAlmacenPK) {
        this.entradaAlmacenPK = entradaAlmacenPK;
    }

    public int getEntNumeroFactura() {
        return entNumeroFactura;
    }

    public void setEntNumeroFactura(int entNumeroFactura) {
        this.entNumeroFactura = entNumeroFactura;
    }

    public Date getEntFecha() {
        return entFecha;
    }

    public void setEntFecha(Date entFecha) {
        this.entFecha = entFecha;
    }

    public BigDecimal getEntValorTotal() {
        return entValorTotal;
    }

    public void setEntValorTotal(BigDecimal entValorTotal) {
        this.entValorTotal = entValorTotal;
    }

    @XmlTransient
    public Collection<DetalleEntrada> getDetalleEntradaCollection() {
        return detalleEntradaCollection;
    }

    public void setDetalleEntradaCollection(Collection<DetalleEntrada> detalleEntradaCollection) {
        this.detalleEntradaCollection = detalleEntradaCollection;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @XmlTransient
    public Collection<SalidaAlmacen> getSalidaAlmacenCollection() {
        return salidaAlmacenCollection;
    }

    public void setSalidaAlmacenCollection(Collection<SalidaAlmacen> salidaAlmacenCollection) {
        this.salidaAlmacenCollection = salidaAlmacenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entradaAlmacenPK != null ? entradaAlmacenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntradaAlmacen)) {
            return false;
        }
        EntradaAlmacen other = (EntradaAlmacen) object;
        if ((this.entradaAlmacenPK == null && other.entradaAlmacenPK != null) || (this.entradaAlmacenPK != null && !this.entradaAlmacenPK.equals(other.entradaAlmacenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.EntradaAlmacen[ entradaAlmacenPK=" + entradaAlmacenPK + " ]";
    }
    
}
