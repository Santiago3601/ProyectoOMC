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
import javax.faces.context.FacesContext;

@Named(value = "horarioControlador")
@SessionScoped
public class horarioControlador implements Serializable {

    @EJB
    HorarioFacade horarioFacade;
    private Horario horario;
    @EJB
    UsuarioFacade usuarioFacade;
    Usuario usuario;

    @EJB
    EmpleadoFacade empleadoFacade;
    private Empleado empleado;

    private Date objDate;
    
     int id;

    @PostConstruct
    public void init() {
        empleado = new Empleado();
        usuario = new Usuario();
        horario = new Horario();
        objDate = new Date();
    }

    public Date getObjDate() {
        return objDate;
    }

    public void setObjDate(Date objDate) {
        this.objDate = objDate;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    //METODOS
    public List<Horario> consultarTodos() {
        return horarioFacade.findAll();
    }

    public void consultarID() {
        horario = horarioFacade.find(horario.getIdHorario());
    }

    public String validarLogin() {

        String redirecionar = "";

        return redirecionar;
    }

    public String registrarIngreso() {

        horario.setEmpleadoIdEmpleado(empleado);
        this.horario.setFechaDeIngreso(getObjDate());
        this.horario.setHoraIngreso("" + getObjDate().getHours() + ":" + getObjDate().getMinutes());
//        "+objDate.getHours()+":"+objDate.getMinutes());

        horarioFacade.create(horario);
        horario = new Horario();
        this.empleado = new Empleado();
        return "registrarHorario";
    }

    public String registrarSalida() {
        
        horario.setEmpleadoIdEmpleado(getEmpleado());
        this.horario.setFechaDeSalida(getObjDate());
        this.horario.setHoraSalida("" + getObjDate().getHours() + ":" + getObjDate().getMinutes());
        horarioFacade.registrarSalida(horario,empleado);
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

    public void fecha() {
        Date sisteFecha = new Date();
    }

}
