/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Cilindro;
import facade.CilindroFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author ACER
 */
@Named(value = "cilindroControlador")
@SessionScoped
public class cilindroControlador implements Serializable {

    /**
     * Creates a new instance of empleadoControlador
     */
    @EJB
    private CilindroFacade cilindroFacade;
    Cilindro cilindro;
    
        @PostConstruct
    public void init() {
        cilindro = new Cilindro();
    }

    public Cilindro getCilindro() {
        return cilindro;
    }

    public void setCilindro(Cilindro cilindro) {
        this.cilindro = cilindro;
    }

    
    
    //METODOS
    public String registrarCilindro(){
        
        cilindroFacade.create(cilindro);
        cilindro = new Cilindro();
        return "listaCilindro";
        
    }
    
    public List<Cilindro> consultarTodos() {
        return cilindroFacade.findAll();
    }

    public cilindroControlador() {

    }

    public String eliminarCilindro(Cilindro ci) {
        cilindroFacade.remove(ci);
        this.cilindro = new Cilindro();
        return "listarCilindro";
    }

    
    public String preActualizarCilindro(Cilindro cilindroAct) {
       cilindro = cilindroAct;
        return "editarCilindro";

    }
     public String actualizarCilindro(){
        cilindroFacade.edit(cilindro);
        cilindro = new Cilindro();
        return "listaCilindro";
    
    
}
}

