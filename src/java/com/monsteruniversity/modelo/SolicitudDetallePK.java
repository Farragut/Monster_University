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
public class SolicitudDetallePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "SOL_ID")
    private int solId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BIEN_PROV_ID")
    private int bienProvId;

    public SolicitudDetallePK() {
    }

    public SolicitudDetallePK(int solId, int bienProvId) {
        this.solId = solId;
        this.bienProvId = bienProvId;
    }

    public int getSolId() {
        return solId;
    }

    public void setSolId(int solId) {
        this.solId = solId;
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
        hash += (int) solId;
        hash += (int) bienProvId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudDetallePK)) {
            return false;
        }
        SolicitudDetallePK other = (SolicitudDetallePK) object;
        if (this.solId != other.solId) {
            return false;
        }
        if (this.bienProvId != other.bienProvId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.SolicitudDetallePK[ solId=" + solId + ", bienProvId=" + bienProvId + " ]";
    }
    
}
