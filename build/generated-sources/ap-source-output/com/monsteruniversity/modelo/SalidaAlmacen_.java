package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.Area;
import com.monsteruniversity.modelo.Empleado;
import com.monsteruniversity.modelo.EntradaAlmacen;
import com.monsteruniversity.modelo.SalidaAlmacenPK;
import com.monsteruniversity.modelo.SalidaBien;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-12T15:25:19")
@StaticMetamodel(SalidaAlmacen.class)
public class SalidaAlmacen_ { 

    public static volatile SingularAttribute<SalidaAlmacen, Empleado> empId;
    public static volatile CollectionAttribute<SalidaAlmacen, SalidaBien> salidaBienCollection;
    public static volatile SingularAttribute<SalidaAlmacen, Integer> estId;
    public static volatile SingularAttribute<SalidaAlmacen, EntradaAlmacen> entradaAlmacen;
    public static volatile SingularAttribute<SalidaAlmacen, SalidaAlmacenPK> salidaAlmacenPK;
    public static volatile SingularAttribute<SalidaAlmacen, Date> salFechaEntrega;
    public static volatile SingularAttribute<SalidaAlmacen, Date> salFecha;
    public static volatile CollectionAttribute<SalidaAlmacen, Area> areaCollection;

}