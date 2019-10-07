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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.exolab.castor.types.Date;

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

    public String registrarAlquiler() throws UnsupportedEncodingException {
        alquilerFacade.cambiarEstadoCliente(alquiler);
        alquiler.setIdAlquiler(1);
        alquiler.setRutaIdRuta(rutaFacade.find(ruta.getIdRuta()));
        alquiler.setCilindroIdCilindro(cilindroFacade.find(cilindro.getIdCilindro()));
        alquiler.setEstadoAlquilerIdEstado(estadoAlquilerFacade.find(estadoAlquiler.getIdEstado()));
        alquiler.setSolicitudIdSolicitud(solicitudFacade.find(solicitud.getIdSolicitud()));
        Mailer.alquilerProgramado(alquiler);
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

    public String consultaIndividual(Alquiler al) {
        alquiler = al;
        solicitud = al.getSolicitudIdSolicitud();

        return "listaIDAlquiler?faces-redirect=true";
    }

    public List<Alquiler> historial() {
        List<Object[]> histor = null;
        List<Alquiler> listaAlquiler = new ArrayList<>();
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesionLogin");
        cliente = alquilerFacade.obtenerIdUsuario(usuario);
        histor = alquilerFacade.historial(cliente);
        for (Object[] obj : histor) {
            Alquiler aL = new Alquiler();
            Ruta rT = new Ruta();
            Cilindro cil = new Cilindro();
            Solicitud sol = new Solicitud();
            Usuario us = new Usuario();
            EstadoAlquiler eT = new EstadoAlquiler();

            sol.setTamanioCilindro(Integer.parseInt(obj[0].toString()));
            us.setNombre(obj[1].toString());
            us.setApellido(obj[2].toString());
            aL.setIdAlquiler(Integer.parseInt(obj[3].toString()));
            aL.setFechaDeEntrega((java.util.Date) obj[4]);
            aL.setNovedades(obj[5].toString());

            sol.setIdSolicitud(Integer.parseInt(obj[6].toString()));
            aL.setSolicitudIdSolicitud(sol);

            eT.setIdEstado(Integer.parseInt(obj[7].toString()));
            aL.setEstadoAlquilerIdEstado(eT);

            eT.setEstado((obj[8].toString()));


            cil.setIdCilindro(Integer.parseInt(obj[9].toString()));
            aL.setCilindroIdCilindro(cil);

            listaAlquiler.add(aL);
        }
        return listaAlquiler;
    }
}
