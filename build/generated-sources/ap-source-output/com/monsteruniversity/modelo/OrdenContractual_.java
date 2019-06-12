package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.OrdcSolicitud;
import com.monsteruniversity.modelo.Proveedor;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-12T15:25:19")
@StaticMetamodel(OrdenContractual.class)
public class OrdenContractual_ { 

    public static volatile SingularAttribute<OrdenContractual, BigDecimal> ordcValorTotal;
    public static volatile SingularAttribute<OrdenContractual, Date> ordcFecha;
    public static volatile SingularAttribute<OrdenContractual, Integer> ordcId;
    public static volatile SingularAttribute<OrdenContractual, Boolean> ordcAprobacionDf;
    public static volatile SingularAttribute<OrdenContractual, Date> ordcFechaEntrega;
    public static volatile SingularAttribute<OrdenContractual, Proveedor> provId;
    public static volatile CollectionAttribute<OrdenContractual, OrdcSolicitud> ordcSolicitudCollection;

}