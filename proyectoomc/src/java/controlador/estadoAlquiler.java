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
 * @author ACER
 */
@Named(value = "estadoAlquilerControlador")
@SessionScoped
public class estadoAlquiler implements Serializable {

    @EJB
    private EstadoAlquilerFacade estadoAlquilerFacade;
    EstadoAlquiler estadoAlquiler;

    @PostConstruct
    public void init() {
        estadoAlquiler = new EstadoAlquiler();

    }

    public EstadoAlquiler getEstadoAlquiler() {
        return estadoAlquiler;
    }

    public void setEstadoAlquiler(EstadoAlquiler estadoAlquiler) {
        this.estadoAlquiler = estadoAlquiler;
    }

    //Metodos
    public List<EstadoAlquiler> consultarTodos() {
        return estadoAlquilerFacade.findAll();
    }

    public void consultarID() {
        estadoAlquiler = estadoAlquilerFacade.find(estadoAlquiler.getIdEstado());
    }

    public String registrarAlquiler() {
        //cliente.setUsuarioId(usuarioFacade.find(usuario.getId()));
        estadoAlquilerFacade.create(estadoAlquiler);
        estadoAlquiler = new EstadoAlquiler();
        return "listaAlquilers";
    }

    public void eliminarAlquiler(EstadoAlquiler ec) {
        this.estadoAlquilerFacade.remove(ec);
    }

    public String preActualizarAlquiler(EstadoAlquiler estadoAlquilerAct) {
        estadoAlquiler = estadoAlquilerAct;
        return "editarEstadoAlquiler";
    }

    public String actualizarEstadoAlquiler() {
        estadoAlquilerFacade.edit(estadoAlquiler);
        estadoAlquiler = new EstadoAlquiler();
        return "listaEstadoAlquiler";
    }

    public estadoAlquiler() {
    }

}
