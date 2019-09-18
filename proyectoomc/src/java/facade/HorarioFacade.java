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
           query=em.createNativeQuery("call insertar_fecha_salida(?1,?2,?3)");
           query.setParameter(1, hor.getHoraSalida());
           query.setParameter(2, hor.getFechaDeSalida());
           
           query.setParameter(3,empleado.getIdEmpleado());
           a=query.executeUpdate();
            System.out.println("ERRORRR === "+ a);
       } catch (Exception e) {
           System.out.println("error"+ e.getMessage());
       }
   }
}
