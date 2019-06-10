/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsteruniversity.modelo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Reaper
 */
@Entity
@Table(name = "perfil_opciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerfilOpciones.findAll", query = "SELECT p FROM PerfilOpciones p")
    , @NamedQuery(name = "PerfilOpciones.findByPerOpcId", query = "SELECT p FROM PerfilOpciones p WHERE p.perOpcId = :perOpcId")})
public class PerfilOpciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PER_OPC_ID")
    private Integer perOpcId;
    @JoinColumn(name = "OPC_ID", referencedColumnName = "OPC_ID")
    @ManyToOne
    private Opciones opcId;
    @JoinColumn(name = "PER_ID", referencedColumnName = "PER_ID")
    @ManyToOne
    private Perfil perId;

    public PerfilOpciones() {
    }

    public PerfilOpciones(Integer perOpcId) {
        this.perOpcId = perOpcId;
    }

    public Integer getPerOpcId() {
        return perOpcId;
    }

    public void setPerOpcId(Integer perOpcId) {
        this.perOpcId = perOpcId;
    }

    public Opciones getOpcId() {
        return opcId;
    }

    public void setOpcId(Opciones opcId) {
        this.opcId = opcId;
    }

    public Perfil getPerId() {
        return perId;
    }

    public void setPerId(Perfil perId) {
        this.perId = perId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perOpcId != null ? perOpcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilOpciones)) {
            return false;
        }
        PerfilOpciones other = (PerfilOpciones) object;
        if ((this.perOpcId == null && other.perOpcId != null) || (this.perOpcId != null && !this.perOpcId.equals(other.perOpcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.PerfilOpciones[ perOpcId=" + perOpcId + " ]";
    }
    
}
