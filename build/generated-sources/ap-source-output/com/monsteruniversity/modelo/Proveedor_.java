package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.BienProveedor;
import com.monsteruniversity.modelo.Cotizacion;
import com.monsteruniversity.modelo.EntradaAlmacen;
import com.monsteruniversity.modelo.OrdenContractual;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-11T01:51:19")
@StaticMetamodel(Proveedor.class)
public class Proveedor_ { 

    public static volatile SingularAttribute<Proveedor, String> provRuc;
    public static volatile CollectionAttribute<Proveedor, Cotizacion> cotizacionCollection;
    public static volatile SingularAttribute<Proveedor, String> provEmail;
    public static volatile SingularAttribute<Proveedor, String> provNombre;
    public static volatile SingularAttribute<Proveedor, String> provTelefono;
    public static volatile CollectionAttribute<Proveedor, BienProveedor> bienProveedorCollection;
    public static volatile CollectionAttribute<Proveedor, EntradaAlmacen> entradaAlmacenCollection;
    public static volatile SingularAttribute<Proveedor, Integer> provId;
    public static volatile SingularAttribute<Proveedor, String> provRazonSocial;
    public static volatile CollectionAttribute<Proveedor, OrdenContractual> ordenContractualCollection;

}