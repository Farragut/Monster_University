package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.BienProveedor;
import com.monsteruniversity.modelo.DetalleEntradaPK;
import com.monsteruniversity.modelo.EntradaAlmacen;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-12T15:25:19")
@StaticMetamodel(DetalleEntrada.class)
public class DetalleEntrada_ { 

    public static volatile SingularAttribute<DetalleEntrada, DetalleEntradaPK> detalleEntradaPK;
    public static volatile SingularAttribute<DetalleEntrada, BigDecimal> detEntCantidadEntregada;
    public static volatile SingularAttribute<DetalleEntrada, EntradaAlmacen> entradaAlmacen;
    public static volatile SingularAttribute<DetalleEntrada, BienProveedor> bienProveedor;

}