/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.*;
import facade.*;
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
@Named(value = "empleadoControlador")
@SessionScoped
public class EmpleadoControlador implements Serializable {

    @EJB
    private EmpleadoFacade empleadoFacade;
    Empleado empleado;
    @EJB
    private UsuarioFacade usuarioFacade;
    Usuario usuario;

    @EJB
    private TurnoFacade turnoFacade;
    Turno turno;

    @PostConstruct
    public void init() {
        empleado = new Empleado();
        usuario = new Usuario();
        turno = new Turno();

    }

    /**
     * Creates a new instance of EmpleadoControlador
     */
    public EmpleadoControlador() {
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
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

    //metodos
    public String registrar() {
       empleado.setUsuarioId(usuarioFacade.find(usuario.getId()));
       empleado.setTurnoIdTurno(turnoFacade.find(turno.getIdTurno()));
       empleadoFacade.create(empleado);
       empleado = new Empleado();
       return "listaEmpleado";
    }
    

    public List<Empleado> consultarTodos() {
        return empleadoFacade.findAll();
    }

    public void consultarID() {
        empleado = empleadoFacade.find(empleado.getIdEmpleado());
    }

    public String eliminarEmpleado(Empleado u) {
        this.empleadoFacade.remove(u);
        return "listaEmpleado";
    }

    public String preActualizarEmpleado(Empleado empleadoAct) {
        empleado = empleadoAct;
        return "ActualizarEmpleado";
    }

    public String actualizarEmpleado() {
        empleado.setUsuarioId(usuarioFacade.find(usuario.getId()));
        empleado.setTurnoIdTurno(turnoFacade.find(turno.getIdTurno()));
        empleadoFacade.edit(empleado);
        empleado = new Empleado();
        return "listaEmpleado";
    }
}
