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
import javax.faces.context.FacesContext;

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
    private ClienteFacade clienteFacade;
    Cliente cliente;
    @EJB
    private EstadoClienteFacade estadoClienteFacade;
    EstadoCliente estadoCliente;

    @EJB
    private RutaFacade rutaFacade;
    Ruta ruta;
    @EJB
    private UsuarioFacade usuariiFacade;
    Usuario usuario;

    @EJB
    private EstadoAlquilerFacade estadoAlquilerFacade;
    EstadoAlquiler estadoAlquiler;

    @EJB
    private SolicitudFacade solicitudFacade;
    Solicitud solicitud;

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
        cliente = new Cliente();
        estadoCliente = new EstadoCliente();
        usuario = new Usuario();
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EstadoCliente getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(EstadoCliente estadoCliente) {
        this.estadoCliente = estadoCliente;
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
        alquilerFacade.cambiarEstadoCliente(alquiler);
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

    public String cambiarEstado(Alquiler al) {
        this.alquiler = al;
        this.estadoAlquiler.setIdEstado(2);
        this.alquiler.setEstadoAlquilerIdEstado(getEstadoAlquiler());
        this.alquilerFacade.edit(getAlquiler());
        cilindro = new Cilindro();
        ruta = new Ruta();
        estadoAlquiler = new EstadoAlquiler();
        alquiler = new Alquiler();
        solicitud = new Solicitud();
        return "listaAlquiler";
    }

    public String entregado(Alquiler alquilerAct) {
        this.alquiler.setIdAlquiler(alquiler.getIdAlquiler());
        this.alquiler.setFechaDeEntrega(alquiler.getFechaDeEntrega());
        alquiler.setRutaIdRuta(rutaFacade.find(ruta.getIdRuta()));
        alquiler.setCilindroIdCilindro(cilindroFacade.find(cilindro.getIdCilindro()));

        this.estadoAlquiler.setIdEstado(2);
        alquiler.setSolicitudIdSolicitud(solicitudFacade.find(solicitud.getIdSolicitud()));
        alquilerFacade.edit(alquiler);
        return "listaAlquiler";
    }

    public String actualizarAlquiler() {
        alquiler.setRutaIdRuta(rutaFacade.find(ruta.getIdRuta()));
        alquiler.setCilindroIdCilindro(cilindroFacade.find(cilindro.getIdCilindro()));
        alquiler.setEstadoAlquilerIdEstado(estadoAlquilerFacade.find(estadoAlquiler.getIdEstado()));
        alquiler.setSolicitudIdSolicitud(solicitudFacade.find(solicitud.getIdSolicitud()));
        alquilerFacade.edit(alquiler);
        return "listaAlquiler";
    }

    public alquilerControlador() {
    }

}
