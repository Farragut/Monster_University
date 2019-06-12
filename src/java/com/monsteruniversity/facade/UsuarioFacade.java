/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsteruniversity.facade;

import com.monsteruniversity.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Reaper
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "MonsterPrimefaces5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario iniciarSesion(String user, String password) {
        Query q = em.createNamedQuery("Usuario.login", Usuario.class).setParameter("usuNombre", user).setParameter("usuPassword", password);
        List<Usuario> listado = q.getResultList();
        if (!listado.isEmpty()) {
            return listado.get(0);
        }
        return null;
    }
    
    public Usuario obtenerEmpId(String user) {
        System.out.println("######## USUARIO ##########");
        Query q = em.createNamedQuery("Usuario.findByUsuNombre", Usuario.class).setParameter("usuNombre", user);
        List<Usuario> listado = q.getResultList();
        if (!listado.isEmpty()) {
            System.out.println("USUARIO: " + listado.get(0).getEmpId());
            return listado.get(0);
        }
        return null;
    }
    
}
