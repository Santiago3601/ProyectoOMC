/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import facade.*;
import entidades.*;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Aprendiz
 */
@Named(value = "solicitudControlador")
@SessionScoped
public class solicitudControlador implements Serializable {

    /**
     * Creates a new instance of solicitud
     */
    public solicitudControlador() {
    }
     @EJB
    private ClienteFacade clienteFacade;
    Cliente cliente;

    @EJB
    private SolicitudFacade solicitudFacade;
    Solicitud solicitud ;


    @PostConstruct
    public void init() {
        solicitud = new Solicitud();
        cliente = new Cliente();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    

    //Metodos
    public List<Solicitud> consultarTodos() {
        return solicitudFacade.findAll();
    }

    public void consultarID() {
        solicitud = solicitudFacade.find(solicitud.getIdSolicitud());
    }

    public String registrarSolicitud() {
        solicitud.setClienteIdCliente(clienteFacade.find(cliente.getIdCliente()));
        solicitudFacade.create(solicitud);
        solicitud = new Solicitud();
        return "listaSolicitud";

    }

    public void eliminarSolicitud(Solicitud s) {
        this.solicitudFacade.remove(s);
    }

    public String preActualizarSolicitud(Solicitud solicitudAct) {
        solicitud = solicitudAct;
        return "editarSolicitud";
    }

    public String actualizarSolicitud() {
        solicitud.setClienteIdCliente(clienteFacade.find(cliente.getIdCliente()));
        solicitudFacade.edit(solicitud);
        solicitud = new Solicitud();
        return "listaSolicitud";
    }
   
}
