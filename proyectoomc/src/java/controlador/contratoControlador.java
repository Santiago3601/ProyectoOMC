/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.*;
import facade.AlquilerFacade;
import facade.ContratoFacade;
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
@Named(value = "contratoControlador")
@SessionScoped
public class contratoControlador implements Serializable {
    
    @EJB
    private ContratoFacade contratoFacade;
    Contrato contrato;
    
    @EJB
    private AlquilerFacade alquilerFacade;
    Alquiler alquiler;
    
    @PostConstruct
    public void init() {
        alquiler = new Alquiler();
        contrato = new Contrato();
    }
    
    public Contrato getContrato() {
        return contrato;
    }
    
    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
    
    public Alquiler getAlquiler() {
        return alquiler;
    }
    
    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }

//METODOS
    public List<Contrato> consultarTodos() {
        return contratoFacade.findAll();
    }
    
    public void consultarID() {
        contrato = contratoFacade.find(contrato.getIdContrato());
    }
    
    public String registrarContrato() {
        contrato.setAlquilerIdAlquiler(alquilerFacade.find(alquiler.getIdAlquiler()));
        contratoFacade.create(contrato);
        contrato = new Contrato();
        return "listaContrato";        
        
    }
    
    public void eliminarContrato(Contrato u) {
        this.contratoFacade.remove(u);
    }
    
    public String preActualizarContrato(Contrato contratoAct) {
        contrato = contratoAct;
        return "editarContrato";
    }
    
    public String actualizarContrato() {
        contrato.setAlquilerIdAlquiler(alquilerFacade.find(alquiler.getIdAlquiler()));
        contratoFacade.edit(contrato);
        contrato = new Contrato();
        return "listaContrato";
    }
    
    public contratoControlador() {
    }
    
}
