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
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p")
    , @NamedQuery(name = "Proveedor.findByProvId", query = "SELECT p FROM Proveedor p WHERE p.provId = :provId")
    , @NamedQuery(name = "Proveedor.findByProvRuc", query = "SELECT p FROM Proveedor p WHERE p.provRuc = :provRuc")
    , @NamedQuery(name = "Proveedor.findByProvNombre", query = "SELECT p FROM Proveedor p WHERE p.provNombre = :provNombre")
    , @NamedQuery(name = "Proveedor.findByProvRazonSocial", query = "SELECT p FROM Proveedor p WHERE p.provRazonSocial = :provRazonSocial")
    , @NamedQuery(name = "Proveedor.findByProvTelefono", query = "SELECT p FROM Proveedor p WHERE p.provTelefono = :provTelefono")
    , @NamedQuery(name = "Proveedor.findByProvEmail", query = "SELECT p FROM Proveedor p WHERE p.provEmail = :provEmail")})
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PROV_ID")
    private Integer provId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "PROV_RUC")
    private String provRuc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PROV_NOMBRE")
    private String provNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PROV_RAZON_SOCIAL")
    private String provRazonSocial;
    @Size(max = 13)
    @Column(name = "PROV_TELEFONO")
    private String provTelefono;
    @Size(max = 255)
    @Column(name = "PROV_EMAIL")
    private String provEmail;
    @ManyToMany(mappedBy = "proveedorCollection")
    private Collection<Cotizacion> cotizacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provId")
    private Collection<OrdenContractual> ordenContractualCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
    private Collection<EntradaAlmacen> entradaAlmacenCollection;
    @OneToMany(mappedBy = "provId")
    private Collection<BienProveedor> bienProveedorCollection;

    public Proveedor() {
    }

    public Proveedor(Integer provId) {
        this.provId = provId;
    }

    public Proveedor(Integer provId, String provRuc, String provNombre, String provRazonSocial) {
        this.provId = provId;
        this.provRuc = provRuc;
        this.provNombre = provNombre;
        this.provRazonSocial = provRazonSocial;
    }

    public Integer getProvId() {
        return provId;
    }

    public void setProvId(Integer provId) {
        this.provId = provId;
    }

    public String getProvRuc() {
        return provRuc;
    }

    public void setProvRuc(String provRuc) {
        this.provRuc = provRuc;
    }

    public String getProvNombre() {
        return provNombre;
    }

    public void setProvNombre(String provNombre) {
        this.provNombre = provNombre;
    }

    public String getProvRazonSocial() {
        return provRazonSocial;
    }

    public void setProvRazonSocial(String provRazonSocial) {
        this.provRazonSocial = provRazonSocial;
    }

    public String getProvTelefono() {
        return provTelefono;
    }

    public void setProvTelefono(String provTelefono) {
        this.provTelefono = provTelefono;
    }

    public String getProvEmail() {
        return provEmail;
    }

    public void setProvEmail(String provEmail) {
        this.provEmail = provEmail;
    }

    @XmlTransient
    public Collection<Cotizacion> getCotizacionCollection() {
        return cotizacionCollection;
    }

    public void setCotizacionCollection(Collection<Cotizacion> cotizacionCollection) {
        this.cotizacionCollection = cotizacionCollection;
    }

    @XmlTransient
    public Collection<OrdenContractual> getOrdenContractualCollection() {
        return ordenContractualCollection;
    }

    public void setOrdenContractualCollection(Collection<OrdenContractual> ordenContractualCollection) {
        this.ordenContractualCollection = ordenContractualCollection;
    }

    @XmlTransient
    public Collection<EntradaAlmacen> getEntradaAlmacenCollection() {
        return entradaAlmacenCollection;
    }

    public void setEntradaAlmacenCollection(Collection<EntradaAlmacen> entradaAlmacenCollection) {
        this.entradaAlmacenCollection = entradaAlmacenCollection;
    }

    @XmlTransient
    public Collection<BienProveedor> getBienProveedorCollection() {
        return bienProveedorCollection;
    }

    public void setBienProveedorCollection(Collection<BienProveedor> bienProveedorCollection) {
        this.bienProveedorCollection = bienProveedorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (provId != null ? provId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.provId == null && other.provId != null) || (this.provId != null && !this.provId.equals(other.provId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.Proveedor[ provId=" + provId + " ]";
    }
    
}
