package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.Bien;
import com.monsteruniversity.modelo.BienInterno;
import com.monsteruniversity.modelo.DetalleEntrada;
import com.monsteruniversity.modelo.Proveedor;
import com.monsteruniversity.modelo.SalidaBien;
import com.monsteruniversity.modelo.SolicitudDetalle;
import com.monsteruniversity.modelo.UnidadMedida;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-12T15:25:19")
@StaticMetamodel(BienProveedor.class)
public class BienProveedor_ { 

    public static volatile CollectionAttribute<BienProveedor, SolicitudDetalle> solicitudDetalleCollection;
    public static volatile CollectionAttribute<BienProveedor, SalidaBien> salidaBienCollection;
    public static volatile CollectionAttribute<BienProveedor, BienInterno> bienInternoCollection;
    public static volatile SingularAttribute<BienProveedor, Integer> bienProvId;
    public static volatile SingularAttribute<BienProveedor, BigDecimal> bienCantidad;
    public static volatile SingularAttribute<BienProveedor, BigDecimal> bienValorUnitario;
    public static volatile SingularAttribute<BienProveedor, Bien> bienId;
    public static volatile SingularAttribute<BienProveedor, Proveedor> provId;
    public static volatile CollectionAttribute<BienProveedor, DetalleEntrada> detalleEntradaCollection;
    public static volatile SingularAttribute<BienProveedor, UnidadMedida> uniId;

}