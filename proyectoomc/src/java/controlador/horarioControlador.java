/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import facade.*;
import entidades.*;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Aprendiz
 */
@Named(value = "horarioControlador")
@SessionScoped
public class horarioControlador implements Serializable {

    @EJB
    private HorarioFacade horarioFacade;
    Horario horario;

    @EJB
    private EmpleadoFacade empleadoFacade;
    Empleado empleado;

    @PostConstruct
    public void init() {
        empleado = new Empleado();
        horario = new Horario();
       
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }


    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }


    //METODOS
    public List<Horario> consultarTodos() {
        return horarioFacade.findAll();
    }

    public void consultarID() {
        horario = horarioFacade.find(horario.getIdHorario());
    }

    public String registrarHorario() {
        horario.setEmpleadoIdEmpleado(empleadoFacade.find(empleado.getIdEmpleado()));
        horarioFacade.create(horario);
        horario = new Horario();
        return "registrarHorario";
    }
    
    public void eliminarHorario(Horario item) {
        this.horarioFacade.remove(item);
    }

    public String preActualizarHorario(Horario horarioAct) {
        horario = horarioAct;
        return "editarHorario";
    }

    public String actualizarHorario() {
        horario.setEmpleadoIdEmpleado(empleadoFacade.find(empleado.getIdEmpleado()));
        horarioFacade.edit(horario);
        horario = new Horario();
        return "editarHorario";
    }
}
