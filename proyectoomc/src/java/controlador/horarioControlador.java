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
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;


@Named(value = "horarioControlador")
@SessionScoped
public class horarioControlador implements Serializable {

    @EJB
    HorarioFacade horarioFacade;
    private Horario horario;

    @EJB
    EmpleadoFacade empleadoFacade;
    private Empleado empleado;
    
    private Fecha fecha;
    
    @PostConstruct
    public void init() {
        empleado = new Empleado();
        horario = new Horario();
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
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

    public String registrarIngreso() {
        horario.setEmpleadoIdEmpleado(getEmpleado());
        this.horario.setFechaDeIngreso(getFecha().getObjDate());
        //this.horario.setHoraIngreso(getFecha().getObjDate().getTime().);
        horarioFacade.create(horario);
        horario = new Horario();
        this.empleado = new Empleado();
        return "registrarHorario";
    }
    
    public String registrarSalida(){
        horario.setEmpleadoIdEmpleado(getEmpleado());
        this.horario.setFechaDeSalida(getFecha().getObjDate());
        //this.horario.setHoraSalida(getFecha().getSoyLaHora());
        horarioFacade.edit(horario);
        horario = new Horario();
        this.empleado = new Empleado();
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
    
    public void fecha(){
       Date sisteFecha = new Date(); 
    }
    
   
}
