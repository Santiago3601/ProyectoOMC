/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Empleado;
import entidades.PermisoLaboral;

import facade.EmpleadoFacade;
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
    @EJB
    private EmpleadoFacade empleadoFacade;
    Empleado empleado;

    @PostConstruct
    public void init() {
        permisoLaboral = new PermisoLaboral();
        empleado = new Empleado();

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

    //METODOS
    public String registrarp() {

        permisoLaboral.setEmpleadoIdEmpleado(empleadoFacade.find(empleado.getIdEmpleado()));
        permisoLaboralFacade.create(permisoLaboral);
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
        return "ListarPermisoLaboral";
    }

    public String preActualizar(PermisoLaboral permisoAct) {
        permisoLaboral = permisoAct;
        return "ActualizarPermisoLaboral";

    }

    public String actualizar() {
        permisoLaboral.setEmpleadoIdEmpleado(empleadoFacade.find(empleado.getIdEmpleado()));
        permisoLaboralFacade.edit(permisoLaboral);
        permisoLaboral = new PermisoLaboral();
        return "ListarPermisoLaboral";
    }


}
