/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Permiso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Stephi
 */
@Stateless
public class PermisoFacade extends AbstractFacade<Permiso> {

    @PersistenceContext(unitName = "proyectoOMCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermisoFacade() {
        super(Permiso.class);
    }
    
}
