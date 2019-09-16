/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Empleado;
import entidades.Horario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Stephi
 */
@Stateless
public class HorarioFacade extends AbstractFacade<Horario> {

    @PersistenceContext(unitName = "proyectoOMCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HorarioFacade() {
        super(Horario.class);
    }
    public void registrarSalida(Horario hor,Empleado empleado){
       Query query;
        try {
            query=em.createNativeQuery("UPDATE horario SET hora_salida =:horaSalida, fecha_de_salida =:fechaSalida  WHERE fecha_de_ingreso =:fechaIngreso AND empleado_id_empleado =:idEmpleado");
            query.setParameter("horaSalida", hor.getHoraSalida());
            query.setParameter("fechaSalida", hor.getFechaDeSalida());
            query.setParameter("fechaIngreso", hor.getFechaDeSalida());
            query.setParameter("idEmpleado",empleado.getIdEmpleado());
            System.out.println("ERRORRR === "+query.getResultList());
        } catch (Exception e) {
        }
    }
}
