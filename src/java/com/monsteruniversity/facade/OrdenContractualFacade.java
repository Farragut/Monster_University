/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsteruniversity.facade;

import com.monsteruniversity.modelo.OrdenContractual;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Reaper
 */
@Stateless
public class OrdenContractualFacade extends AbstractFacade<OrdenContractual> {

    @PersistenceContext(unitName = "MonsterPrimefaces5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdenContractualFacade() {
        super(OrdenContractual.class);
    }
    
}
