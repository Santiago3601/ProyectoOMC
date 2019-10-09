/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Agenda;
import entidades.Alquiler;
import entidades.Empleado;
import facade.AgendaFacade;
import facade.AlquilerFacade;
import facade.EmpleadoFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Aprendiz
 */
@Named(value = "agendaControlador")
@SessionScoped
public class agendaControlador implements Serializable {

    /**
     * Creates a new instance of agendaControlador
     */
    public agendaControlador() {

    }
    private Part file;
    private String nombre;
    private String pathReal;

    @EJB
    private AgendaFacade agendaFacade;
    Agenda agenda;

    @EJB
    private EmpleadoFacade empleadoFacade;
    Empleado empleado;

    @EJB
    private AlquilerFacade alquilerFacade;
    private Alquiler alquiler;

    @PostConstruct
    public void init() {
        agenda = new Agenda();
        empleado = new Empleado();
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Alquiler getAlquiler() {
        return alquiler;
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

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }

    public String registrarAgenda() {
        agenda.setEmpleadoIdEmpleado(empleadoFacade.find(empleado.getIdEmpleado()));
        agenda.setAlquilerIdAlqu(alquilerFacade.find(alquiler.getIdAlquiler()));
        agendaFacade.create(agenda);
        agenda = new Agenda();
        return "listaAgenda";

    }

    public void consultarID() {
        agenda = agendaFacade.find(agenda);
    }

    public List<Agenda> consultarTodos() {
        return agendaFacade.findAll();
    }

    public String eliminarAgenda(Agenda a) {
        this.agendaFacade.remove(a);
        return "listaAgenda";
    }

    public String preActualizarAgenda(Agenda agendaAct) {
        agenda = agendaAct;
        return "editarAgenda";
    }

    public String actualizarAgenda() {
        agenda.setEmpleadoIdEmpleado(empleadoFacade.find(empleado.getIdEmpleado()));
        agenda.setAlquilerIdAlqu(alquilerFacade.find(alquiler.getIdAlquiler()));
        agendaFacade.edit(agenda);
        agenda = new Agenda();
        return "listaAgenda";
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
        agenda.setAlquilerIdAlqu(getAlquiler());
        this.agenda.setFoto(pathReal);
        agendaFacade.edit(agenda);
        agenda = new Agenda();

        return "listaAgenda";

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
        agenda.setAlquilerIdAlqu(getAlquiler());
        this.agenda.setFoto(pathReal);
        agendaFacade.create(agenda);
        agenda = new Agenda();

        return "listaAgenda";

    }


}
