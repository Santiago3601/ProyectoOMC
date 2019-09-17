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
       int a=0;
        try {
            query=em.createNativeQuery("UPDATE horario SET hora_salida =?1, fecha_de_salida =?2  WHERE fecha_de_ingreso =?3 AND empleado_id_empleado =?4");
            query.setParameter(1, hor.getHoraSalida());
            query.setParameter(2, hor.getFechaDeSalida());
            query.setParameter(3, hor.getFechaDeSalida());
            query.setParameter(4,empleado.getIdEmpleado());
            a=query.executeUpdate();
             System.out.println("ERRORRR === "+ a);
        } catch (Exception e) {
        }
    }
}
