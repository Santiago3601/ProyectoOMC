/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Alquiler;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Stephi
 */
@Stateless
public class AlquilerFacade extends AbstractFacade<Alquiler> {

    @PersistenceContext(unitName = "proyectoOMCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlquilerFacade() {
        super(Alquiler.class);
    }
    
    public void cambiarEstado(Alquiler al){
        Query query;
        try {
            query = em.createNativeQuery("UPDATE alquiler SET estado_alquiler_id_estado = 2 WHERE id_alquiler =:id");
            query.setParameter("id", al.getIdAlquiler());
            
        } catch (Exception e) {
        }
    }
    
}
