/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.*;
import facade.*;
import facade.TurnoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Stephi
 */
@Named(value = "turnoControlador")
@SessionScoped
public class TurnoControlador implements Serializable {


@EJB
   PuntoFacade puntoFacade;
    private Punto punto;

    @EJB
  EmpleadoFacade empleadoFacade;
   private Empleado empleado;

    @EJB
     TurnoFacade turnoFacade;
   private Turno turno;

    @PostConstruct
    public void init() {
        punto = new Punto();
        empleado = new Empleado();
        turno = new Turno();

    }


    public Punto getPunto() {
        return punto;
    }

    public void setPunto(Punto punto) {
        this.punto = punto;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public TurnoFacade getTurnoFacade() {
        return turnoFacade;
    }

    public void setTurnoFacade(TurnoFacade turnoFacade) {
        this.turnoFacade = turnoFacade;
    }

    /**
     * Creates a new instance of TurnoControlador
     */
    public TurnoControlador() {
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public String registrar() {
      turno.setPuntoIdPuntos(getPunto());
       turno.setIdEmpleado(getEmpleado());
         
        turnoFacade.create(turno);
        turno = new Turno();
        return "RegistrarTurno";
    }
    
    
    
    
    
    
    
    public List<Turno> consultarTodos() {
        return turnoFacade.findAll();
    }

    public void consultarID() {
        turno = turnoFacade.find(turno.getIdTurno());
    }
    public String eliminar(Turno turno) {
        this.turno = turno;
        turnoFacade.remove(turno);
        this.turno = new Turno();
        return "ListarTurno";
    }

    
    public String preActualizar(Turno turnoAct) {
       turno=turnoAct;
        return "ActualizarTurno";

    }
     public String actualizar(){
        turnoFacade.edit(turno);
        turno = new Turno();
        return "ListarTurno";
    }
     public void prueba()
     {
         System.out.println("Prueba de controlador");
     }
}
