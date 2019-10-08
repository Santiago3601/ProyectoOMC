/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.EstadoMantenimiento;
import facade.EstadoMantenimientoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author SENA
 */
@Named(value = "estadoMantenimiento")
@SessionScoped
public class estadoMantenimiento implements Serializable {

    /**
     * Creates a new instance of estadoMantenimiento
     */
    public estadoMantenimiento() {
    }
    @EJB
    private EstadoMantenimientoFacade estadoMantenimientoFacade;
    private EstadoMantenimiento estadoMantenimiento;
    
     @PostConstruct
    public void init() {
        estadoMantenimiento = new EstadoMantenimiento();
    }

    public EstadoMantenimiento getEstadoMantenimiento() {
        return estadoMantenimiento;
    }

    public void setEstadoMantenimiento(EstadoMantenimiento estadoMantenimiento) {
        this.estadoMantenimiento = estadoMantenimiento;
    }
    
    //Metodos
    
    public void consultarID(){
        estadoMantenimiento = estadoMantenimientoFacade.find(estadoMantenimiento.getIdEstado());
        
    }
    public String registrarMantenimiento(){
        estadoMantenimientoFacade.create(estadoMantenimiento);
        estadoMantenimiento = new EstadoMantenimiento();
        return "listaMantenimiento";
        
    }
    public void eliminarMantenimiento(EstadoMantenimiento es){
        this.estadoMantenimientoFacade.remove(es);
    }
    public String preActualizarMantenimiento(EstadoMantenimiento estadoMantenimientoAct){
        estadoMantenimiento = estadoMantenimientoAct;
        return "editarEstadoMantenimiento";
        
    }
    public String actualizarEstadoMantenimiento(){
        estadoMantenimientoFacade.edit(estadoMantenimiento);
        estadoMantenimiento = new EstadoMantenimiento();
        return "listaMantenimiento";
        
    }
    public List<EstadoMantenimiento> consultarTdos(){
        return estadoMantenimientoFacade.findAll();
    }
}
