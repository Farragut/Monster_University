package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.Empleado;
import com.monsteruniversity.modelo.SalidaAlmacen;
import com.monsteruniversity.modelo.Solicitud;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-11T01:51:19")
@StaticMetamodel(Area.class)
public class Area_ { 

    public static volatile SingularAttribute<Area, Integer> areaId;
    public static volatile CollectionAttribute<Area, Solicitud> solicitudCollection;
    public static volatile CollectionAttribute<Area, Empleado> empleadoCollection;
    public static volatile CollectionAttribute<Area, SalidaAlmacen> salidaAlmacenCollection;
    public static volatile SingularAttribute<Area, String> areaDescripcion;

}