package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.BienProveedor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-12T19:45:54")
@StaticMetamodel(BienInterno.class)
public class BienInterno_ { 

    public static volatile SingularAttribute<BienInterno, String> bienIntDireccion;
    public static volatile SingularAttribute<BienInterno, BienProveedor> bienProvId;
    public static volatile SingularAttribute<BienInterno, Date> bienIntFechaEntrega;
    public static volatile SingularAttribute<BienInterno, Integer> bienIntId;

}