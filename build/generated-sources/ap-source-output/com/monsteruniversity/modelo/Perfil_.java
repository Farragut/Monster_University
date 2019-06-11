package com.monsteruniversity.modelo;

import com.monsteruniversity.modelo.PerfilOpciones;
import com.monsteruniversity.modelo.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-06-11T01:51:19")
@StaticMetamodel(Perfil.class)
public class Perfil_ { 

    public static volatile SingularAttribute<Perfil, Integer> perId;
    public static volatile CollectionAttribute<Perfil, PerfilOpciones> perfilOpcionesCollection;
    public static volatile SingularAttribute<Perfil, String> perDescripcion;
    public static volatile CollectionAttribute<Perfil, Usuario> usuarioCollection;

}