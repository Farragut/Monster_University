package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.BienProveedor;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-12T19:45:54")
@StaticMetamodel(UnidadMedida.class)
public class UnidadMedida_ { 

    public static volatile SingularAttribute<UnidadMedida, String> uniNombre;
    public static volatile SingularAttribute<UnidadMedida, String> uniAbreviacion;
    public static volatile CollectionAttribute<UnidadMedida, BienProveedor> bienProveedorCollection;
    public static volatile SingularAttribute<UnidadMedida, Integer> uniId;

}