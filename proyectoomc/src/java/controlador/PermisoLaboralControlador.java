/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Empleado;
import entidades.EstadoPermisoLaboral;
import entidades.PermisoLaboral;

import facade.EmpleadoFacade;
import facade.EstadoPermisoLaboralFacade;
import facade.PermisoLaboralFacade;

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
@Named(value = "permisoLaboralControlador")
@SessionScoped
public class PermisoLaboralControlador implements Serializable {

    @EJB
    private PermisoLaboralFacade permisoLaboralFacade;
    PermisoLaboral permisoLaboral;
    Empleado empleado;

    @EJB
    private EstadoPermisoLaboralFacade estadoPermisoLaboralFacade;
    EstadoPermisoLaboral estadoPermisoLaboral;

    @PostConstruct
    public void init() {
        permisoLaboral = new PermisoLaboral();
        empleado = new Empleado();
        estadoPermisoLaboral = new EstadoPermisoLaboral();

    }

    /**
     * Creates a new instance of PermisoLaboralControlador
     */
    public PermisoLaboralControlador() {
    }

    public PermisoLaboral getPermisoLaboral() {
        return permisoLaboral;
    }

    public void setPermisoLaboral(PermisoLaboral permisoLaboral) {
        this.permisoLaboral = permisoLaboral;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public EstadoPermisoLaboral getEstadoPermisoLaboral() {
        return estadoPermisoLaboral;
    }

    public void setEstadoPermisoLaboral(EstadoPermisoLaboral estadoPermisoLaboral) {
        this.estadoPermisoLaboral = estadoPermisoLaboral;
    }

    //METODOS
    public String registrarp() {

        this.permisoLaboral.setEmpleadoIdEmpleado(getEmpleado());
        this.estadoPermisoLaboral.setIdEstado(1);
        this.permisoLaboral.setEstado(getEstadoPermisoLaboral());
        permisoLaboralFacade.create(getPermisoLaboral());
        permisoLaboral = new PermisoLaboral();
        return "RegistrarPermisoLaboral";
    }

    public List<PermisoLaboral> consultarTodos() {
        return permisoLaboralFacade.findAll();
    }

    public void consultarID() {
        permisoLaboral = permisoLaboralFacade.find(permisoLaboral.getIdPermisoLaboral());
    }

    public String eliminar(PermisoLaboral p) {
        permisoLaboralFacade.remove(p);
        permisoLaboral = new PermisoLaboral();
        return "ListarPermisoLaboral";
    }

    public String preActualizar(PermisoLaboral permisoAct) {
        permisoLaboral = permisoAct;
        return "ActualizarPermisoLaboral";

    }

    public String actualizar() {
        permisoLaboral.setEmpleadoIdEmpleado(getEmpleado());
        permisoLaboralFacade.edit(permisoLaboral);
        permisoLaboral = new PermisoLaboral();
        return "ListarPermisoLaboral";
    }

    public String CambiarEstado(PermisoLaboral pl) {
        this.permisoLaboral = pl;
        this.estadoPermisoLaboral.setIdEstado(2);
        this.permisoLaboral.setEstado(getEstadoPermisoLaboral());
        permisoLaboralFacade.edit(getPermisoLaboral());
        permisoLaboral = new PermisoLaboral();
        empleado = new Empleado();
        estadoPermisoLaboral = new EstadoPermisoLaboral();
        return "ListarPermisoLaboral";
    }
    
    public String CambiarEstado1(PermisoLaboral pl) {
        this.permisoLaboral = pl;
        this.estadoPermisoLaboral.setIdEstado(3);
        this.permisoLaboral.setEstado(getEstadoPermisoLaboral());
        permisoLaboralFacade.edit(getPermisoLaboral());
        permisoLaboral = new PermisoLaboral();
        empleado = new Empleado();
        estadoPermisoLaboral = new EstadoPermisoLaboral();
        return "ListarPermisoLaboral";
    }
}
