/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsteruniversity.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Reaper
 */
@Entity
@Table(name = "bien_interno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BienInterno.findAll", query = "SELECT b FROM BienInterno b")
    , @NamedQuery(name = "BienInterno.findByBienIntId", query = "SELECT b FROM BienInterno b WHERE b.bienIntId = :bienIntId")
    , @NamedQuery(name = "BienInterno.findByBienIntFechaEntrega", query = "SELECT b FROM BienInterno b WHERE b.bienIntFechaEntrega = :bienIntFechaEntrega")
    , @NamedQuery(name = "BienInterno.findByBienIntDireccion", query = "SELECT b FROM BienInterno b WHERE b.bienIntDireccion = :bienIntDireccion")})
public class BienInterno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BIEN_INT_ID")
    private Integer bienIntId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BIEN_INT_FECHA_ENTREGA")
    @Temporal(TemporalType.DATE)
    private Date bienIntFechaEntrega;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "BIEN_INT_DIRECCION")
    private String bienIntDireccion;
    @JoinColumn(name = "BIEN_PROV_ID", referencedColumnName = "BIEN_PROV_ID")
    @ManyToOne
    private BienProveedor bienProvId;

    public BienInterno() {
    }

    public BienInterno(Integer bienIntId) {
        this.bienIntId = bienIntId;
    }

    public BienInterno(Integer bienIntId, Date bienIntFechaEntrega, String bienIntDireccion) {
        this.bienIntId = bienIntId;
        this.bienIntFechaEntrega = bienIntFechaEntrega;
        this.bienIntDireccion = bienIntDireccion;
    }

    public Integer getBienIntId() {
        return bienIntId;
    }

    public void setBienIntId(Integer bienIntId) {
        this.bienIntId = bienIntId;
    }

    public Date getBienIntFechaEntrega() {
        return bienIntFechaEntrega;
    }

    public void setBienIntFechaEntrega(Date bienIntFechaEntrega) {
        this.bienIntFechaEntrega = bienIntFechaEntrega;
    }

    public String getBienIntDireccion() {
        return bienIntDireccion;
    }

    public void setBienIntDireccion(String bienIntDireccion) {
        this.bienIntDireccion = bienIntDireccion;
    }

    public BienProveedor getBienProvId() {
        return bienProvId;
    }

    public void setBienProvId(BienProveedor bienProvId) {
        this.bienProvId = bienProvId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bienIntId != null ? bienIntId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BienInterno)) {
            return false;
        }
        BienInterno other = (BienInterno) object;
        if ((this.bienIntId == null && other.bienIntId != null) || (this.bienIntId != null && !this.bienIntId.equals(other.bienIntId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.BienInterno[ bienIntId=" + bienIntId + " ]";
    }
    
}
