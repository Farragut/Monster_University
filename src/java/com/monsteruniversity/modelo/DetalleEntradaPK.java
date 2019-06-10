/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsteruniversity.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Reaper
 */
@Embeddable
public class DetalleEntradaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "PROV_ID")
    private int provId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENT_ID")
    private int entId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BIEN_PROV_ID")
    private int bienProvId;

    public DetalleEntradaPK() {
    }

    public DetalleEntradaPK(int provId, int entId, int bienProvId) {
        this.provId = provId;
        this.entId = entId;
        this.bienProvId = bienProvId;
    }

    public int getProvId() {
        return provId;
    }

    public void setProvId(int provId) {
        this.provId = provId;
    }

    public int getEntId() {
        return entId;
    }

    public void setEntId(int entId) {
        this.entId = entId;
    }

    public int getBienProvId() {
        return bienProvId;
    }

    public void setBienProvId(int bienProvId) {
        this.bienProvId = bienProvId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) provId;
        hash += (int) entId;
        hash += (int) bienProvId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleEntradaPK)) {
            return false;
        }
        DetalleEntradaPK other = (DetalleEntradaPK) object;
        if (this.provId != other.provId) {
            return false;
        }
        if (this.entId != other.entId) {
            return false;
        }
        if (this.bienProvId != other.bienProvId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.DetalleEntradaPK[ provId=" + provId + ", entId=" + entId + ", bienProvId=" + bienProvId + " ]";
    }
    
}
