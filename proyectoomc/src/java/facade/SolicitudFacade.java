/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Cliente;
import entidades.Solicitud;
import entidades.Solicitud;
import entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Stephi
 */
@Stateless
public class SolicitudFacade extends AbstractFacade<Solicitud> {

    @PersistenceContext(unitName = "proyectoOMCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudFacade() {
        super(Solicitud.class);
    }
     public Cliente obtenerIdUsuario(Usuario us) {
        Cliente cli =null;
        try {
            Query query = em.createQuery("SELECT c FROM Cliente c where c.usuarioId.id = :idUsuario");
            query.setParameter("idUsuario", us.getId());
            List<Cliente> cliL = query.getResultList();
            if (!cliL.isEmpty()) {
                cli = cliL.get(0);
                return cli;
            }
            
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return cli;

    }
    
}
