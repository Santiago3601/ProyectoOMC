/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.mysql.jdbc.PreparedStatement;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import facade.*;
import entidades.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Aprendiz
 */
@Named(value = "solicitudControlador")
@SessionScoped
public class solicitudControlador implements Serializable {

    private Part file;
    private String nombre;
    private String pathReal;
    private Usuario usuario;
    EstadoCliente estadoCliente;

    public EstadoCliente getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(EstadoCliente estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPathReal() {
        return pathReal;
    }

    public void setPathReal(String pathReal) {
        this.pathReal = pathReal;
    }

    /**
     * Creates a new instance of solicitud
     */
    public solicitudControlador() {
    }
    @EJB
    private ClienteFacade clienteFacade;
    Cliente cliente;

    @EJB
    private EstadoClienteFacade estadoClienteFacade;

    @EJB
    private SolicitudFacade solicitudFacade;
    Solicitud solicitud;

    @EJB
    private AlquilerFacade alquilerFacade;
    Alquiler alquiler;

    @PostConstruct
    public void init() {
        solicitud = new Solicitud();
        alquiler = new Alquiler();
        cliente = new Cliente();
        usuario = new Usuario();
        estadoCliente = new EstadoCliente();
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
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

    public String editUpload() {

        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\Archivos\\";
        try {
            this.nombre = file.getSubmittedFileName();
            pathReal = "/proyectoomc/archivos/" + nombre;
            path = path + this.nombre;
            InputStream in = file.getInputStream();

            byte[] data = new byte[in.available()];
            in.read(data);
            FileOutputStream out = new FileOutputStream(new File(path));
            out.write(data);
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        solicitud.setClienteIdCliente(getCliente());
        this.solicitud.setFormula(pathReal);
        solicitudFacade.edit(solicitud);
        solicitud = new Solicitud();

        return "listaSolicitud";

    }
    public String upload() {

        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\Archivos\\";
        try {
            this.nombre = file.getSubmittedFileName();
            pathReal = "/proyectoomc/archivos/" + nombre;
            path = path + this.nombre;
            InputStream in = file.getInputStream();

            byte[] data = new byte[in.available()];
            in.read(data);
            FileOutputStream out = new FileOutputStream(new File(path));
            out.write(data);
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        solicitud.setClienteIdCliente(getCliente());
        this.solicitud.setFormula(pathReal);
        solicitudFacade.create(solicitud);
        solicitud = new Solicitud();

        return "listaSolicitud";

    }

    public String uploadUsuario() {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesionLogin");
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\Archivos\\";
        try {
            this.nombre = file.getSubmittedFileName();
            pathReal = "/proyectoomc/archivos/" + usuario.getId() +  nombre;
            path = path + usuario.getId() + this.nombre;
            InputStream in = file.getInputStream();

            byte[] data = new byte[in.available()];
            in.read(data);
            FileOutputStream out = new FileOutputStream(new File(path));
            out.write(data);
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cliente = solicitudFacade.obtenerIdUsuario(usuario);
        solicitud.setIdSolicitud(1);
        solicitud.setClienteIdCliente(cliente);
        this.solicitud.setFormula(pathReal);
        solicitudFacade.create(solicitud);
        solicitud = new Solicitud();
        return "../mensajes/confirmacionCliente";

    }

    public boolean validarPedidoAgendado() {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesionLogin");
        this.cliente = solicitudFacade.obtenerIdUsuario(usuario);
        boolean estado;
        estado = solicitudFacade.pedidoAgendado(cliente);
        return estado;
    }

    public String cancelarSolicitud() {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesionLogin");
        this.cliente = solicitudFacade.obtenerIdUsuario(usuario);
        solicitudFacade.cancelarSol(cliente);
        return "../mensajes/confirmacionClienteCilindroCancelado";
    }

    public String redireccionar() {
        return "paginaDeBienvenidaCliente";
    }
}
