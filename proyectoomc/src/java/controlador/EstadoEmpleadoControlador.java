/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.EstadoEmpleado;
import facade.EstadoEmpleadoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Stephi
 */
@Named(value = "estadoEmpleado")
@SessionScoped
public class EstadoEmpleadoControlador implements Serializable {
    
     @EJB
     EstadoEmpleadoFacade estadofacade;
      private EstadoEmpleado estadoentidad;
    /**
     a Creates a new instance of EstadoEmpleado
     */
    public EstadoEmpleadoControlador() {
    }

    public EstadoEmpleado getEstadoentidad() {
        return estadoentidad;
    }

    public void setEstadoentidad(EstadoEmpleado estadoentidad) {
        this.estadoentidad = estadoentidad;
    }
    
    
    public List<EstadoEmpleado> consultarTodos() {
        return estadofacade.findAll();
    }
}
