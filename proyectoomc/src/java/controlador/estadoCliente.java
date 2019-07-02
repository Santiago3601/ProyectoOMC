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
@Named(value = "estadoClienteControlador")
@SessionScoped
public class estadoCliente implements Serializable {

    @EJB
    private EstadoClienteFacade estadoClienteFacade;
    EstadoCliente estadoCliente;

    @PostConstruct
    public void init() {
        estadoCliente = new EstadoCliente();

    }

    public EstadoCliente getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(EstadoCliente estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    //Metodos
    public List<EstadoCliente> consultarTodos() {
        return estadoClienteFacade.findAll();
    }

    public void consultarID() {
        estadoCliente = estadoClienteFacade.find(estadoCliente.getIdEstado());
    }

    public String registrarCliente() {
        //cliente.setUsuarioId(usuarioFacade.find(usuario.getId()));
        estadoClienteFacade.create(estadoCliente);
        estadoCliente = new EstadoCliente();
        return "listaClientes";
    }

    public void eliminarCliente(EstadoCliente ec) {
        this.estadoClienteFacade.remove(ec);
    }

    public String preActualizarCliente(EstadoCliente estadoClienteAct) {
        estadoCliente = estadoClienteAct;
        return "editarEstadoCliente";
    }

    public String actualizarEstadoCliente() {
        estadoClienteFacade.edit(estadoCliente);
        estadoCliente = new EstadoCliente();
        return "listaEstadoCliente";
    }

    public estadoCliente() {
    }

}
