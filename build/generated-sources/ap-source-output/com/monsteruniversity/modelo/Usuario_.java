package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.Empleado;
import com.monsteruniversity.modelo.Perfil;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-12T15:25:19")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> usuNombre;
    public static volatile SingularAttribute<Usuario, Empleado> empId;
    public static volatile SingularAttribute<Usuario, Perfil> perId;
    public static volatile SingularAttribute<Usuario, Integer> usuId;
    public static volatile SingularAttribute<Usuario, String> usuPassword;

}