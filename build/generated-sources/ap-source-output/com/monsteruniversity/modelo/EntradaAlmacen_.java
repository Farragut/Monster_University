package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.DetalleEntrada;
import com.monsteruniversity.modelo.EntradaAlmacenPK;
import com.monsteruniversity.modelo.Proveedor;
import com.monsteruniversity.modelo.SalidaAlmacen;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-11T01:51:19")
@StaticMetamodel(EntradaAlmacen.class)
public class EntradaAlmacen_ { 

    public static volatile SingularAttribute<EntradaAlmacen, Date> entFecha;
    public static volatile SingularAttribute<EntradaAlmacen, EntradaAlmacenPK> entradaAlmacenPK;
    public static volatile SingularAttribute<EntradaAlmacen, Integer> entNumeroFactura;
    public static volatile SingularAttribute<EntradaAlmacen, Proveedor> proveedor;
    public static volatile SingularAttribute<EntradaAlmacen, BigDecimal> entValorTotal;
    public static volatile CollectionAttribute<EntradaAlmacen, SalidaAlmacen> salidaAlmacenCollection;
    public static volatile CollectionAttribute<EntradaAlmacen, DetalleEntrada> detalleEntradaCollection;

}