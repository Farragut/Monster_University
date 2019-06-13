package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.Empleado;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-12T19:45:54")
@StaticMetamodel(EstadoCivil.class)
public class EstadoCivil_ { 

    public static volatile SingularAttribute<EstadoCivil, Integer> estId;
    public static volatile SingularAttribute<EstadoCivil, String> estDescripcion;
    public static volatile CollectionAttribute<EstadoCivil, Empleado> empleadoCollection;

}