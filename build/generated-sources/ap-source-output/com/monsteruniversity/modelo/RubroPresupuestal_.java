package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.Solicitud;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-12T19:45:54")
@StaticMetamodel(RubroPresupuestal.class)
public class RubroPresupuestal_ { 

    public static volatile SingularAttribute<RubroPresupuestal, String> rubDescripcion;
    public static volatile CollectionAttribute<RubroPresupuestal, Solicitud> solicitudCollection;
    public static volatile SingularAttribute<RubroPresupuestal, Integer> rubId;

}