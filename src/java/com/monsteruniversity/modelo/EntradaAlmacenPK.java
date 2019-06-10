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
public class EntradaAlmacenPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "PROV_ID")
    private int provId;
    @Basic(optional = false)
    @Column(name = "ENT_ID")
    private int entId;

    public EntradaAlmacenPK() {
    }

    public EntradaAlmacenPK(int provId, int entId) {
        this.provId = provId;
        this.entId = entId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) provId;
        hash += (int) entId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntradaAlmacenPK)) {
            return false;
        }
        EntradaAlmacenPK other = (EntradaAlmacenPK) object;
        if (this.provId != other.provId) {
            return false;
        }
        if (this.entId != other.entId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.EntradaAlmacenPK[ provId=" + provId + ", entId=" + entId + " ]";
    }
    
}
