/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsteruniversity.modelo;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Reaper
 */
@Entity
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findByEmpId", query = "SELECT e FROM Empleado e WHERE e.empId = :empId")
    , @NamedQuery(name = "Empleado.findByEmpCedula", query = "SELECT e FROM Empleado e WHERE e.empCedula = :empCedula")
    , @NamedQuery(name = "Empleado.findByEmpNombre", query = "SELECT e FROM Empleado e WHERE e.empNombre = :empNombre")
    , @NamedQuery(name = "Empleado.findByEmpApellido", query = "SELECT e FROM Empleado e WHERE e.empApellido = :empApellido")
    , @NamedQuery(name = "Empleado.findByEmpGenero", query = "SELECT e FROM Empleado e WHERE e.empGenero = :empGenero")
    , @NamedQuery(name = "Empleado.findByEmpFechaNacimiento", query = "SELECT e FROM Empleado e WHERE e.empFechaNacimiento = :empFechaNacimiento")
    , @NamedQuery(name = "Empleado.findByEmpTelefono", query = "SELECT e FROM Empleado e WHERE e.empTelefono = :empTelefono")
    , @NamedQuery(name = "Empleado.findByEmpDireccion", query = "SELECT e FROM Empleado e WHERE e.empDireccion = :empDireccion")
    , @NamedQuery(name = "Empleado.findByEmpEmail", query = "SELECT e FROM Empleado e WHERE e.empEmail = :empEmail")
    , @NamedQuery(name = "Empleado.findByEmpEstado", query = "SELECT e FROM Empleado e WHERE e.empEstado = :empEstado")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EMP_ID")
    private Integer empId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "EMP_CEDULA")
    private String empCedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "EMP_NOMBRE")
    private String empNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "EMP_APELLIDO")
    private String empApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "EMP_GENERO")
    private String empGenero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMP_FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date empFechaNacimiento;
    @Size(max = 10)
    @Column(name = "EMP_TELEFONO")
    private String empTelefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "EMP_DIRECCION")
    private String empDireccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "EMP_EMAIL")
    private String empEmail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMP_ESTADO")
    private boolean empEstado;
    @JoinColumn(name = "AREA_ID", referencedColumnName = "AREA_ID")
    @ManyToOne
    private Area areaId;
    @JoinColumn(name = "EST_ID", referencedColumnName = "EST_ID")
    @ManyToOne(optional = false)
    private EstadoCivil estId;
    @OneToMany(mappedBy = "jefeId")
    private Collection<Empleado> empleadoCollection;
    @JoinColumn(name = "JEFE_ID", referencedColumnName = "EMP_ID")
    @ManyToOne
    private Empleado jefeId;
    @OneToMany(mappedBy = "empId")
    private Collection<Solicitud> solicitudCollection;
    @OneToMany(mappedBy = "empId")
    private Collection<Usuario> usuarioCollection;
    @OneToMany(mappedBy = "empId")
    private Collection<SalidaAlmacen> salidaAlmacenCollection;

    public Empleado() {
    }

    public Empleado(Integer empId) {
        this.empId = empId;
    }

    public Empleado(Integer empId, String empCedula, String empNombre, String empApellido, String empGenero, Date empFechaNacimiento, String empDireccion, String empEmail, boolean empEstado) {
        this.empId = empId;
        this.empCedula = empCedula;
        this.empNombre = empNombre;
        this.empApellido = empApellido;
        this.empGenero = empGenero;
        this.empFechaNacimiento = empFechaNacimiento;
        this.empDireccion = empDireccion;
        this.empEmail = empEmail;
        this.empEstado = empEstado;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpCedula() {
        return empCedula;
    }

    public void setEmpCedula(String empCedula) {
        this.empCedula = empCedula;
    }

    public String getEmpNombre() {
        return empNombre;
    }

    public void setEmpNombre(String empNombre) {
        this.empNombre = empNombre;
    }

    public String getEmpApellido() {
        return empApellido;
    }

    public void setEmpApellido(String empApellido) {
        this.empApellido = empApellido;
    }

    public String getEmpGenero() {
        return empGenero;
    }

    public void setEmpGenero(String empGenero) {
        this.empGenero = empGenero;
    }

    public Date getEmpFechaNacimiento() {
        return empFechaNacimiento;
    }

    public void setEmpFechaNacimiento(Date empFechaNacimiento) {
        this.empFechaNacimiento = empFechaNacimiento;
    }

    public String getEmpTelefono() {
        return empTelefono;
    }

    public void setEmpTelefono(String empTelefono) {
        this.empTelefono = empTelefono;
    }

    public String getEmpDireccion() {
        return empDireccion;
    }

    public void setEmpDireccion(String empDireccion) {
        this.empDireccion = empDireccion;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public boolean getEmpEstado() {
        return empEstado;
    }

    public void setEmpEstado(boolean empEstado) {
        this.empEstado = empEstado;
    }

    public Area getAreaId() {
        return areaId;
    }

    public void setAreaId(Area areaId) {
        this.areaId = areaId;
    }

    public EstadoCivil getEstId() {
        return estId;
    }

    public void setEstId(EstadoCivil estId) {
        this.estId = estId;
    }

    @XmlTransient
    public Collection<Empleado> getEmpleadoCollection() {
        return empleadoCollection;
    }

    public void setEmpleadoCollection(Collection<Empleado> empleadoCollection) {
        this.empleadoCollection = empleadoCollection;
    }

    public Empleado getJefeId() {
        return jefeId;
    }

    public void setJefeId(Empleado jefeId) {
        this.jefeId = jefeId;
    }

    @XmlTransient
    public Collection<Solicitud> getSolicitudCollection() {
        return solicitudCollection;
    }

    public void setSolicitudCollection(Collection<Solicitud> solicitudCollection) {
        this.solicitudCollection = solicitudCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @XmlTransient
    public Collection<SalidaAlmacen> getSalidaAlmacenCollection() {
        return salidaAlmacenCollection;
    }

    public void setSalidaAlmacenCollection(Collection<SalidaAlmacen> salidaAlmacenCollection) {
        this.salidaAlmacenCollection = salidaAlmacenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.empId == null && other.empId != null) || (this.empId != null && !this.empId.equals(other.empId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsteruniversity.modelo.Empleado[ empId=" + empId + " ]";
    }
    
}
