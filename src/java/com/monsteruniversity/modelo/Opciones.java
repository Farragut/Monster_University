/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsteruniversity.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "opciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opciones.findAll", query = "SELECT o FROM Opciones o")
    , @NamedQuery(name = "Opciones.findByOpcId", query = "SELECT o FROM Opciones o WHERE o.opcId = :opcId")
    , @NamedQuery(name = "Opciones.findByOpcNombre", query = "SELECT o FROM Opciones o WHERE o.opcNombre = :opcNombre")})
public class Opciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OPC_ID")
    private Integer opcId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "OPC_NOMBRE")
    private String opcNombre;
    @OneToMany(mappedBy = "opcId")
    private Collection<PerfilOpciones> perfilOpcionesCollection;

    public Opciones() {
    }

    public Opciones(Integer opcId) {
        this.opcId = opcId;
    }

    public Opciones(Integer opcId, String opcNombre) {
        this.opcId = opcId;
        this.opcNombre = opcNombre;
    }

    public Integer getOpcId() {
        return opcId;
    }

    public void setOpcId(Integer opcId) {
        this.opcId = opcId;
    }

    public String getOpcNombre() {
        return opcNombre;
    }

    public void setOpcNombre(String opcNombre) {
        this.opcNombre = opcNombre;
    }

    @XmlTransient
    public Collection<PerfilOpciones> getPerfilOpcionesCollection() {
        return perfilOpcionesCollection;
    }

    public void setPerfilOpcionesCollection(Collection<PerfilOpciones> perfilOpcionesCollection) {
        this.perfilOpcionesCollection = perfilOpcionesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opcId != null ? opcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opciones)) {
            return false;
        }
        Opciones other = (Opciones) object;
        if ((this.opcId == null && other.opcId != null) || (this.opcId != null && !this.opcId.equals(other.opcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.Opciones[ opcId=" + opcId + " ]";
    }
    
}
