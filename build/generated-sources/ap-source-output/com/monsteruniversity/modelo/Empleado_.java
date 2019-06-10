package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.Area;
import com.monsteruniversity.modelo.Empleado;
import com.monsteruniversity.modelo.EstadoCivil;
import com.monsteruniversity.modelo.SalidaAlmacen;
import com.monsteruniversity.modelo.Solicitud;
import com.monsteruniversity.modelo.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-10T16:07:12")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, Integer> empId;
    public static volatile SingularAttribute<Empleado, EstadoCivil> estId;
    public static volatile CollectionAttribute<Empleado, Solicitud> solicitudCollection;
    public static volatile SingularAttribute<Empleado, String> empEmail;
    public static volatile SingularAttribute<Empleado, String> empDireccion;
    public static volatile SingularAttribute<Empleado, Boolean> empEstado;
    public static volatile CollectionAttribute<Empleado, Usuario> usuarioCollection;
    public static volatile SingularAttribute<Empleado, String> empTelefono;
    public static volatile SingularAttribute<Empleado, String> empNombre;
    public static volatile SingularAttribute<Empleado, String> empApellido;
    public static volatile SingularAttribute<Empleado, Area> areaId;
    public static volatile SingularAttribute<Empleado, Empleado> jefeId;
    public static volatile CollectionAttribute<Empleado, Empleado> empleadoCollection;
    public static volatile SingularAttribute<Empleado, Date> empFechaNacimiento;
    public static volatile CollectionAttribute<Empleado, SalidaAlmacen> salidaAlmacenCollection;
    public static volatile SingularAttribute<Empleado, String> empCedula;
    public static volatile SingularAttribute<Empleado, String> empGenero;

}