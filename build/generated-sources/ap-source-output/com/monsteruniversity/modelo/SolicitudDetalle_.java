package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.BienProveedor;
import com.monsteruniversity.modelo.Solicitud;
import com.monsteruniversity.modelo.SolicitudDetallePK;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-12T15:25:19")
@StaticMetamodel(SolicitudDetalle.class)
public class SolicitudDetalle_ { 

    public static volatile SingularAttribute<SolicitudDetalle, Solicitud> solicitud;
    public static volatile SingularAttribute<SolicitudDetalle, BigDecimal> solDetCantidadSolicitada;
    public static volatile SingularAttribute<SolicitudDetalle, BienProveedor> bienProveedor;
    public static volatile SingularAttribute<SolicitudDetalle, SolicitudDetallePK> solicitudDetallePK;

}