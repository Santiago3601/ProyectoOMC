/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.EstadoCilindro;
import facade.EstadoCilindroFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author ASUS
 */
@Named(value = "estadoCilindro")
@SessionScoped
public class estadoCilindro implements Serializable {

    /**
     * Creates a new instance of estadoCilindro
     */
    public estadoCilindro() {
    }
    @EJB
    private EstadoCilindroFacade estadoCilindroFacade;
    private EstadoCilindro estadoCilindro;
    
     @PostConstruct
    public void init() {
        estadoCilindro = new EstadoCilindro();
    }
     public EstadoCilindro getEstadoCilindro() {
        return estadoCilindro;
    }

    public void setEstadoCilindro(EstadoCilindro estadoCilindro) {
        this.estadoCilindro = estadoCilindro;
    }


    //Metodos
    
    public void consultarID(){
        estadoCilindro = estadoCilindroFacade.find(estadoCilindro.getIdEstado());
        
    }
    public String registrarMantenimiento(){
        estadoCilindroFacade.create(estadoCilindro);
        estadoCilindro = new EstadoCilindro();
        return "listaMantenimiento";
        
    }
    public void eliminarMantenimiento(EstadoCilindro es){
        this.estadoCilindroFacade.remove(es);
    }
    public String preActualizarMantenimiento(EstadoCilindro estadoCilindroAct){
        estadoCilindro = estadoCilindroAct;
        return "editarEstadoCilindro";
        
    }
    public String actualizarEstadoCilindro(){
        estadoCilindroFacade.edit(estadoCilindro);
        estadoCilindro = new EstadoCilindro();
        return "listaMantenimiento";
        
    }
    public List<EstadoCilindro> consultarTdos(){
        return estadoCilindroFacade.findAll();
    }
}

   
