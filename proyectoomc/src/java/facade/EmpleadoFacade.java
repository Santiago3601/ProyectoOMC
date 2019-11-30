/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Empleado;
import entidades.Usuario;
import java.util.ArrayList;
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
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "proyectoOMCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }
        public Empleado bucarE(Empleado empleado) {
        Empleado empleadoEncotrado = null;
        try {
            Query query = em.createQuery("select e from Empleado e where e.usuarioId =?1");
            query.setParameter(1, empleado.getUsuarioId());
            List<Empleado> list = query.getResultList();
            if (!list.isEmpty()) {
                empleadoEncotrado=list.get(0);
            }
            
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return empleadoEncotrado;

    }
        
        
        public List<Object[]> traerEmpleado(Usuario us){
           Query query;
           List<Object[]> listaConsulta=null;
            try {
                query=em.createNativeQuery("SELECT usuario.`nombre` AS usuario_nombre, usuario.`apellido` AS usuario_apellido, usuario.`telefono` AS usuario_telefono, usuario.`id` AS usuario_id, tipoid.`tipoID` AS tipoid_tipoID FROM `tipoid` tipoid INNER JOIN `usuario` usuario ON tipoid.`idTipoID` = usuario.`tp_id` WHERE id = ?1");
                query.setParameter(1, us.getId());
                listaConsulta=query.getResultList();
                if (!listaConsulta.isEmpty()) {
                    return listaConsulta;
                }
            } catch (Exception e) {
            }
            return null;
        }
        public List<Usuario> traerUsuarionSinEmpleado(){
           Query query;
           List<Usuario> listaDeUsuario= new ArrayList<>();
           try{
           query=em.createQuery("SELECT ");
           }catch (Exception e) {
            }
            return null;
        }
        
}
