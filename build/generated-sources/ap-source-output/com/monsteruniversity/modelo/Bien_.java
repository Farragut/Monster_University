package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.BienProveedor;
import com.monsteruniversity.modelo.OrdcSolicitud;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-12T18:50:21")
@StaticMetamodel(Bien.class)
public class Bien_ { 

    public static volatile SingularAttribute<Bien, String> bienCaracter;
    public static volatile SingularAttribute<Bien, String> bienNombre;
    public static volatile SingularAttribute<Bien, Integer> bienId;
    public static volatile CollectionAttribute<Bien, BienProveedor> bienProveedorCollection;
    public static volatile CollectionAttribute<Bien, OrdcSolicitud> ordcSolicitudCollection;

}