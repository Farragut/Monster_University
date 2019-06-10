package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.BienProveedor;
import com.monsteruniversity.modelo.SalidaAlmacen;
import com.monsteruniversity.modelo.SalidaBienPK;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-10T16:07:12")
@StaticMetamodel(SalidaBien.class)
public class SalidaBien_ { 

    public static volatile SingularAttribute<SalidaBien, SalidaAlmacen> salidaAlmacen;
    public static volatile SingularAttribute<SalidaBien, SalidaBienPK> salidaBienPK;
    public static volatile SingularAttribute<SalidaBien, BienProveedor> bienProveedor;
    public static volatile SingularAttribute<SalidaBien, BigDecimal> salBienCantidadEntregada;

}