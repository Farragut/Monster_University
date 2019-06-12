package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.Area;
import com.monsteruniversity.modelo.Cotizacion;
import com.monsteruniversity.modelo.Empleado;
import com.monsteruniversity.modelo.OrdcSolicitud;
import com.monsteruniversity.modelo.RubroPresupuestal;
import com.monsteruniversity.modelo.SolicitudDetalle;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-12T15:25:19")
@StaticMetamodel(Solicitud.class)
public class Solicitud_ { 

    public static volatile CollectionAttribute<Solicitud, SolicitudDetalle> solicitudDetalleCollection;
    public static volatile SingularAttribute<Solicitud, Empleado> empId;
    public static volatile SingularAttribute<Solicitud, Boolean> solAprobacionJa;
    public static volatile CollectionAttribute<Solicitud, Cotizacion> cotizacionCollection;
    public static volatile SingularAttribute<Solicitud, Integer> solId;
    public static volatile SingularAttribute<Solicitud, Area> areaId;
    public static volatile SingularAttribute<Solicitud, Integer> estId;
    public static volatile SingularAttribute<Solicitud, Boolean> solAprobacionDf;
    public static volatile SingularAttribute<Solicitud, RubroPresupuestal> rubId;
    public static volatile SingularAttribute<Solicitud, BigDecimal> solValorTotal;
    public static volatile SingularAttribute<Solicitud, Date> solFecha;
    public static volatile CollectionAttribute<Solicitud, OrdcSolicitud> ordcSolicitudCollection;

}