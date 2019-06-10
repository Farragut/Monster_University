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
public class OrdcSolicitudPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "SOL_ID")
    private int solId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDC_ID")
    private int ordcId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BIEN_ID")
    private int bienId;

    public OrdcSolicitudPK() {
    }

    public OrdcSolicitudPK(int solId, int ordcId, int bienId) {
        this.solId = solId;
        this.ordcId = ordcId;
        this.bienId = bienId;
    }

    public int getSolId() {
        return solId;
    }

    public void setSolId(int solId) {
        this.solId = solId;
    }

    public int getOrdcId() {
        return ordcId;
    }

    public void setOrdcId(int ordcId) {
        this.ordcId = ordcId;
    }

    public int getBienId() {
        return bienId;
    }

    public void setBienId(int bienId) {
        this.bienId = bienId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) solId;
        hash += (int) ordcId;
        hash += (int) bienId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdcSolicitudPK)) {
            return false;
        }
        OrdcSolicitudPK other = (OrdcSolicitudPK) object;
        if (this.solId != other.solId) {
            return false;
        }
        if (this.ordcId != other.ordcId) {
            return false;
        }
        if (this.bienId != other.bienId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.OrdcSolicitudPK[ solId=" + solId + ", ordcId=" + ordcId + ", bienId=" + bienId + " ]";
    }
    
}
