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

    public List<Object[]> historial(Cliente cli) {
        List<Object[]> list = null;
        try {
            Query query = em.createNativeQuery("SELECT solicitud.`tamanio_cilindro` AS solicitud_tamanio_cilindro, usuario.`nombre` AS usuario_nombre, usuario.`apellido` AS usuario_apellido, alquiler.`id_alquiler` AS alquiler_id_alquiler, alquiler.`fecha_de_entrega` AS alquiler_fecha_de_entrega, alquiler.`novedades` AS alquiler_novedades, alquiler.`solicitud_id_solicitud` AS alquiler_solicitud_id_solicitud, alquiler.`estado_alquiler_id_estado` AS alquiler_estado_alquiler_id_estado, estado_alquiler.`estado` AS estado_alquiler_estado, cilindro.`id_cilindro` AS cilindro_id_cilindro FROM `cliente` cliente INNER JOIN `solicitud` solicitud ON cliente.`id_cliente` = solicitud.`cliente_id_cliente` INNER JOIN `usuario` usuario ON cliente.`usuario_id` = usuario.`id` INNER JOIN `alquiler` alquiler ON solicitud.`id_solicitud` = alquiler.`solicitud_id_solicitud` INNER JOIN `estado_alquiler` estado_alquiler ON alquiler.`estado_alquiler_id_estado` = estado_alquiler.`id_estado` INNER JOIN `cilindro` cilindro ON alquiler.`cilindro_id_cilindro` = cilindro.`id_cilindro`  WHERE solicitud.cliente_id_cliente = ?1");
            query.setParameter(1, cli.getIdCliente());
            list = query.getResultList();
            if (!list.isEmpty()) {
                return list;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return list;

    }
    public List<Object[]> historialPrueba() {
        List<Object[]> list = null;
        try {
            Query query = em.createNativeQuery("SELECT alquiler.`id_alquiler` AS alquiler_id_alquiler, alquiler.`fecha_de_entrega` AS alquiler_fecha_de_entrega, alquiler.`novedades` AS alquiler_novedades, alquiler.`ruta_id_ruta` AS alquiler_ruta_id_ruta, alquiler.`cilindro_id_cilindro` AS alquiler_cilindro_id_cilindro, alquiler.`solicitud_id_solicitud` AS alquiler_solicitud_id_solicitud, alquiler.`estado_alquiler_id_estado` AS alquiler_estado_alquiler_id_estado FROM `solicitud` solicitud INNER JOIN `alquiler` alquiler ON solicitud.`id_solicitud` = alquiler.`solicitud_id_solicitud` INNER JOIN `cliente` cliente ON solicitud.`cliente_id_cliente` = cliente.`id_cliente` where solicitud.cliente_id_cliente = 9");
            list = query.getResultList();
            if (!list.isEmpty()) {
                return list;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return list;

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
}
