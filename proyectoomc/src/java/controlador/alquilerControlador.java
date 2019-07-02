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
 * @author ACER
 */
@Named(value = "alquilerControlador")
@SessionScoped
public class alquilerControlador implements Serializable {

    @EJB
    private AlquilerFacade alquilerFacade;
    Alquiler alquiler;

    @EJB
    private RutaFacade rutaFacade;
    Ruta ruta;

    @EJB
    private EstadoAlquilerFacade estadoAlquilerFacade;
    EstadoAlquiler estadoAlquiler ;

    @EJB
    private SolicitudFacade solicitudFacade;
    Solicitud solicitud ;

    @EJB
    private CilindroFacade cilindroFacade;
    Cilindro cilindro;

    @PostConstruct
    public void init() {
        cilindro = new Cilindro();
        ruta = new Ruta();
        estadoAlquiler = new EstadoAlquiler();
        alquiler = new Alquiler();
        solicitud = new Solicitud();
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public EstadoAlquiler getEstadoAlquiler() {
        return estadoAlquiler;
    }

    public void setEstadoAlquiler(EstadoAlquiler estadoAlquiler) {
        this.estadoAlquiler = estadoAlquiler;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Cilindro getCilindro() {
        return cilindro;
    }

    public void setCilindro(Cilindro cilindro) {
        this.cilindro = cilindro;
    }

  
    //Metodos
    public List<Alquiler> consultarTodos() {
        return alquilerFacade.findAll();
    }

    public void consultarID() {
        alquiler = alquilerFacade.find(alquiler.getIdAlquiler());
    }

    public String registrarAlquiler() {
        alquiler.setRutaIdRuta(rutaFacade.find(ruta.getIdRuta()));
        alquiler.setCilindroIdCilindro(cilindroFacade.find(cilindro.getIdCilindro()));
        alquiler.setEstadoAlquilerIdEstado(estadoAlquilerFacade.find(estadoAlquiler.getIdEstado()));
        alquiler.setSolicitudIdSolicitud(solicitudFacade.find(solicitud.getIdSolicitud()));
        alquilerFacade.create(alquiler);
        alquiler = new Alquiler();
        return "listaAlquiler";

    }

    public void eliminarAlquiler(Alquiler a) {
        this.alquilerFacade.remove(a);
    }

    public String preActualizarAlquiler(Alquiler alquilerAct) {
        alquiler = alquilerAct;
        return "editarAlquiler";
    }

    public String actualizarAlquiler() {
        alquiler.setRutaIdRuta(rutaFacade.find(ruta.getIdRuta()));
        alquiler.setCilindroIdCilindro(cilindroFacade.find(cilindro.getIdCilindro()));
        alquiler.setEstadoAlquilerIdEstado(estadoAlquilerFacade.find(estadoAlquiler.getIdEstado()));
        alquiler.setSolicitudIdSolicitud(solicitudFacade.find(solicitud.getIdSolicitud()));
        alquilerFacade.edit(alquiler);
        alquiler = new Alquiler();
        return "listaAlquiler";
    }

    public alquilerControlador() {
    }

}
