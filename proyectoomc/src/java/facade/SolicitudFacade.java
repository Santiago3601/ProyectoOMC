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
        Cliente cli = null;
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

    public void cambiarEstado(Usuario us) {
        Cliente cli = null;
        try {
            Query query = em.createQuery("SELECT c FROM Cliente c where c.usuarioId.id = :idUsuario");
            query.setParameter("idUsuario", us.getId());
            List<Cliente> cliL = query.getResultList();
            if (!cliL.isEmpty()) {
                cli = cliL.get(0);

            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    public Solicitud obtenerEstado(Cliente cl) {
        Solicitud sol = null;
        try {
            Query query2 = em.createQuery("SELECT s FROM Solicitud s where s.clienteIdCliente = :idCliente");
            query2.setParameter("idCliente", cl.getIdCliente());
            List<Solicitud> solL = query2.getResultList();
            if (!solL.isEmpty()) {
                sol = solL.get(0);
                return sol;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return sol;

    }

    public boolean pedidoAgendado(Cliente cli) {
        boolean sol = false;
        try {
            Query query2 = em.createNativeQuery("SELECT * FROM solicitud WHERE cliente_id_cliente = ?1");
            query2.setParameter(1, cli.getIdCliente());
            List<Object[]> solL = query2.getResultList();
            if (!solL.isEmpty()) {
                sol = true;
                return sol;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return sol;
    }

    public void cancelarSol(Cliente cli) {
        try {
            Query query2 = em.createNativeQuery("DELETE FROM solicitud WHERE cliente_id_cliente = '"+cli.getIdCliente()+"'");
            query2.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}
