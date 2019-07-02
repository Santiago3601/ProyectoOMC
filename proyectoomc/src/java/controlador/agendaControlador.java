/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Agenda;
import entidades.Empleado;
import facade.AgendaFacade;
import facade.EmpleadoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Aprendiz
 */
@Named(value = "agendaControlador")
@SessionScoped
public class agendaControlador implements Serializable {


    /**
     * Creates a new instance of agendaControlador
     */
    public agendaControlador() {
        
    }
    @EJB
    private AgendaFacade agendaFacade;
    Agenda agenda;
    
    @EJB
    private EmpleadoFacade empleadoFacade;
    Empleado empleado;
    
    @PostConstruct
    public void init(){
        agenda = new Agenda();
        empleado = new Empleado();
    }
    
    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    public String registrarAgenda(){
        agenda.setEmpleadoIdEmpleado(empleadoFacade.find(empleado.getIdEmpleado()));
        agendaFacade.create(agenda);
        agenda = new Agenda();
        return "listaAgenda";
        
    }
    
        public void consultarID() {
        agenda = agendaFacade.find(agenda);
    }
    public List<Agenda> consultarTodos(){
        return agendaFacade.findAll();
    }

 public String eliminarAgenda(Agenda a){
        this.agendaFacade.remove(a);
        return "listaAgenda";
    }

    
    public String preActualizarAgenda(Agenda agendaAct) {
       agenda = agendaAct;
       return "editarAgenda";
   }

   public String actualizarAgenda() {
       agenda.setEmpleadoIdEmpleado(empleadoFacade.find(empleado.getIdEmpleado()));
       agendaFacade.edit(agenda);
       agenda = new Agenda();
       return "listaAgenda";
   }




}

