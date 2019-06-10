/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsteruniversity.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Reaper
 */
@Entity
@Table(name = "bien_proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BienProveedor.findAll", query = "SELECT b FROM BienProveedor b")
    , @NamedQuery(name = "BienProveedor.findByBienProvId", query = "SELECT b FROM BienProveedor b WHERE b.bienProvId = :bienProvId")
    , @NamedQuery(name = "BienProveedor.findByBienCantidad", query = "SELECT b FROM BienProveedor b WHERE b.bienCantidad = :bienCantidad")
    , @NamedQuery(name = "BienProveedor.findByBienValorUnitario", query = "SELECT b FROM BienProveedor b WHERE b.bienValorUnitario = :bienValorUnitario")})
public class BienProveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BIEN_PROV_ID")
    private Integer bienProvId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "BIEN_CANTIDAD")
    private BigDecimal bienCantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BIEN_VALOR_UNITARIO")
    private BigDecimal bienValorUnitario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bienProveedor")
    private Collection<DetalleEntrada> detalleEntradaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bienProveedor")
    private Collection<SolicitudDetalle> solicitudDetalleCollection;
    @JoinColumn(name = "BIEN_ID", referencedColumnName = "BIEN_ID")
    @ManyToOne
    private Bien bienId;
    @JoinColumn(name = "PROV_ID", referencedColumnName = "PROV_ID")
    @ManyToOne
    private Proveedor provId;
    @JoinColumn(name = "UNI_ID", referencedColumnName = "UNI_ID")
    @ManyToOne
    private UnidadMedida uniId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bienProveedor")
    private Collection<SalidaBien> salidaBienCollection;
    @OneToMany(mappedBy = "bienProvId")
    private Collection<BienInterno> bienInternoCollection;

    public BienProveedor() {
    }

    public BienProveedor(Integer bienProvId) {
        this.bienProvId = bienProvId;
    }

    public BienProveedor(Integer bienProvId, BigDecimal bienCantidad, BigDecimal bienValorUnitario) {
        this.bienProvId = bienProvId;
        this.bienCantidad = bienCantidad;
        this.bienValorUnitario = bienValorUnitario;
    }

    public Integer getBienProvId() {
        return bienProvId;
    }

    public void setBienProvId(Integer bienProvId) {
        this.bienProvId = bienProvId;
    }

    public BigDecimal getBienCantidad() {
        return bienCantidad;
    }

    public void setBienCantidad(BigDecimal bienCantidad) {
        this.bienCantidad = bienCantidad;
    }

    public BigDecimal getBienValorUnitario() {
        return bienValorUnitario;
    }

    public void setBienValorUnitario(BigDecimal bienValorUnitario) {
        this.bienValorUnitario = bienValorUnitario;
    }

    @XmlTransient
    public Collection<DetalleEntrada> getDetalleEntradaCollection() {
        return detalleEntradaCollection;
    }

    public void setDetalleEntradaCollection(Collection<DetalleEntrada> detalleEntradaCollection) {
        this.detalleEntradaCollection = detalleEntradaCollection;
    }

    @XmlTransient
    public Collection<SolicitudDetalle> getSolicitudDetalleCollection() {
        return solicitudDetalleCollection;
    }

    public void setSolicitudDetalleCollection(Collection<SolicitudDetalle> solicitudDetalleCollection) {
        this.solicitudDetalleCollection = solicitudDetalleCollection;
    }

    public Bien getBienId() {
        return bienId;
    }

    public void setBienId(Bien bienId) {
        this.bienId = bienId;
    }

    public Proveedor getProvId() {
        return provId;
    }

    public void setProvId(Proveedor provId) {
        this.provId = provId;
    }

    public UnidadMedida getUniId() {
        return uniId;
    }

    public void setUniId(UnidadMedida uniId) {
        this.uniId = uniId;
    }

    @XmlTransient
    public Collection<SalidaBien> getSalidaBienCollection() {
        return salidaBienCollection;
    }

    public void setSalidaBienCollection(Collection<SalidaBien> salidaBienCollection) {
        this.salidaBienCollection = salidaBienCollection;
    }

    @XmlTransient
    public Collection<BienInterno> getBienInternoCollection() {
        return bienInternoCollection;
    }

    public void setBienInternoCollection(Collection<BienInterno> bienInternoCollection) {
        this.bienInternoCollection = bienInternoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bienProvId != null ? bienProvId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BienProveedor)) {
            return false;
        }
        BienProveedor other = (BienProveedor) object;
        if ((this.bienProvId == null && other.bienProvId != null) || (this.bienProvId != null && !this.bienProvId.equals(other.bienProvId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.BienProveedor[ bienProvId=" + bienProvId + " ]";
    }
    
}
