/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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

    public void cambiarEstado(Alquiler al) {
        Query query;
        try {
            query = em.createNativeQuery("UPDATE alquiler SET estado_alquiler_id_estado = 2 WHERE id_alquiler =:id");
            query.setParameter("id", al.getIdAlquiler());

        } catch (Exception e) {
        }
    }

    public void cambiarEstadoCliente(Alquiler al) {
        Alquiler alquiler = null;
        Solicitud sol = null;
        try {
            Query query = em.createQuery("select a from Alquiler a where a.solicitudIdSolicitud =?1 and a.idAlquiler =?2");
            query.setParameter(1, al.getSolicitudIdSolicitud());
            query.setParameter(2, al.getIdAlquiler());
            List<Alquiler> list = query.getResultList();
            if (!list.isEmpty()) {
                alquiler = list.get(0);
                Query query2 = em.createNativeQuery("select s from Solicitud s where s.idSolicitud =" + al.getSolicitudIdSolicitud());
                List<Solicitud> solL = query2.getResultList();
                if (!solL.isEmpty()) {
                    sol = solL.get(0);
                }

                Query query3 = em.createNativeQuery("UPDATE cliente SET estado_id_estado = 3 WHERE (id_cliente = " + sol + ")");
                query3.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

    }
}
