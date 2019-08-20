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
    private SolicitudFacade solicitudFacade;
    Solicitud solicitud;

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
}
