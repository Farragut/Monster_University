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
@Table(name = "salida_bien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalidaBien.findAll", query = "SELECT s FROM SalidaBien s")
    , @NamedQuery(name = "SalidaBien.findByBienProvId", query = "SELECT s FROM SalidaBien s WHERE s.salidaBienPK.bienProvId = :bienProvId")
    , @NamedQuery(name = "SalidaBien.findByProvId", query = "SELECT s FROM SalidaBien s WHERE s.salidaBienPK.provId = :provId")
    , @NamedQuery(name = "SalidaBien.findByEntId", query = "SELECT s FROM SalidaBien s WHERE s.salidaBienPK.entId = :entId")
    , @NamedQuery(name = "SalidaBien.findBySalId", query = "SELECT s FROM SalidaBien s WHERE s.salidaBienPK.salId = :salId")
    , @NamedQuery(name = "SalidaBien.findBySalBienCantidadEntregada", query = "SELECT s FROM SalidaBien s WHERE s.salBienCantidadEntregada = :salBienCantidadEntregada")})
public class SalidaBien implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SalidaBienPK salidaBienPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SAL_BIEN_CANTIDAD_ENTREGADA")
    private BigDecimal salBienCantidadEntregada;
    @JoinColumns({
        @JoinColumn(name = "PROV_ID", referencedColumnName = "PROV_ID", insertable = false, updatable = false)
        , @JoinColumn(name = "ENT_ID", referencedColumnName = "ENT_ID", insertable = false, updatable = false)
        , @JoinColumn(name = "SAL_ID", referencedColumnName = "SAL_ID", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private SalidaAlmacen salidaAlmacen;
    @JoinColumn(name = "BIEN_PROV_ID", referencedColumnName = "BIEN_PROV_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private BienProveedor bienProveedor;

    public SalidaBien() {
    }

    public SalidaBien(SalidaBienPK salidaBienPK) {
        this.salidaBienPK = salidaBienPK;
    }

    public SalidaBien(int bienProvId, int provId, int entId, int salId) {
        this.salidaBienPK = new SalidaBienPK(bienProvId, provId, entId, salId);
    }

    public SalidaBienPK getSalidaBienPK() {
        return salidaBienPK;
    }

    public void setSalidaBienPK(SalidaBienPK salidaBienPK) {
        this.salidaBienPK = salidaBienPK;
    }

    public BigDecimal getSalBienCantidadEntregada() {
        return salBienCantidadEntregada;
    }

    public void setSalBienCantidadEntregada(BigDecimal salBienCantidadEntregada) {
        this.salBienCantidadEntregada = salBienCantidadEntregada;
    }

    public SalidaAlmacen getSalidaAlmacen() {
        return salidaAlmacen;
    }

    public void setSalidaAlmacen(SalidaAlmacen salidaAlmacen) {
        this.salidaAlmacen = salidaAlmacen;
    }

    public BienProveedor getBienProveedor() {
        return bienProveedor;
    }

    public void setBienProveedor(BienProveedor bienProveedor) {
        this.bienProveedor = bienProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salidaBienPK != null ? salidaBienPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalidaBien)) {
            return false;
        }
        SalidaBien other = (SalidaBien) object;
        if ((this.salidaBienPK == null && other.salidaBienPK != null) || (this.salidaBienPK != null && !this.salidaBienPK.equals(other.salidaBienPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.SalidaBien[ salidaBienPK=" + salidaBienPK + " ]";
    }
    
}
