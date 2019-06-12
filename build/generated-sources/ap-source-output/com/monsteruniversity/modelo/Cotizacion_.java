package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.Proveedor;
import com.monsteruniversity.modelo.Solicitud;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-12T18:50:21")
@StaticMetamodel(Cotizacion.class)
public class Cotizacion_ { 

    public static volatile SingularAttribute<Cotizacion, Solicitud> solId;
    public static volatile SingularAttribute<Cotizacion, Integer> cotId;
    public static volatile CollectionAttribute<Cotizacion, Proveedor> proveedorCollection;
    public static volatile SingularAttribute<Cotizacion, Boolean> cotAprobacion;

}