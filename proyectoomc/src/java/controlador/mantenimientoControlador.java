/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Agenda;
import entidades.Cilindro;
import entidades.EstadoMantenimiento;
import entidades.Mantenimiento;
import facade.AgendaFacade;
import facade.CilindroFacade;
import facade.EstadoMantenimientoFacade;
import facade.MantenimientoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Aprendiz
 */
@Named(value = "mantenimientoControlador")
@SessionScoped
public class mantenimientoControlador implements Serializable {

    /**
     * Creates a new instance of mantenimientoCilindro
     */
    public mantenimientoControlador() {
    }
    @EJB
    private MantenimientoFacade mantenimientoFacade;
    Mantenimiento mantenimiento;

    @EJB
    private AgendaFacade agendaFacade;
    Agenda agenda;

    @EJB
    private CilindroFacade cilindroFacade;
    Cilindro cilindro;
    @EJB
    private EstadoMantenimientoFacade estadoMantenimientoFacade;
    private EstadoMantenimiento estadoMantenimiento;

    @PostConstruct
    public void init() {
        mantenimiento = new Mantenimiento();
        agenda = new Agenda();
        cilindro = new Cilindro();
    }

    public Mantenimiento getMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(Mantenimiento mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Cilindro getCilindro() {
        return cilindro;
    }

    public void setCilindro(Cilindro cilindro) {
        this.cilindro = cilindro;
    }

    public EstadoMantenimiento getEstadoMantenimiento() {
        return estadoMantenimiento;
    }

    public void setEstadoMantenimiento(EstadoMantenimiento estadoMantenimiento) {
        this.estadoMantenimiento = estadoMantenimiento;
    }

//Metodos
public String registrarMantenimiento() {

        mantenimiento.setAgendaIdAgenda(agendaFacade.find(agenda.getIdAgenda()));
        mantenimiento.setCilindroIdCilindro(cilindroFacade.find(cilindro.getIdCilindro()));
        mantenimiento.setEstadoMantenimiento(estadoMantenimientoFacade.find(estadoMantenimiento.getIdEstado()));
        mantenimientoFacade.create(mantenimiento);
        mantenimiento = new Mantenimiento();
        return "listaMantenimiento";

    }

    public String pre_actualizar(Mantenimiento mantenimientoAct) {
        mantenimiento = mantenimientoAct;
        return "editarMantenimiento";
    }

    public String actualizar() {
        mantenimiento.setAgendaIdAgenda(agendaFacade.find(agenda.getIdAgenda()));
         mantenimiento.setCilindroIdCilindro(cilindroFacade.find(cilindro.getIdCilindro()));
        mantenimiento.setEstadoMantenimiento(estadoMantenimientoFacade.find(estadoMantenimiento.getIdEstado()));
        mantenimientoFacade.edit(mantenimiento);
        mantenimiento = new Mantenimiento();
        return "listaMantenimiento";
    }

    public void eliminar(Mantenimiento m) {
        mantenimientoFacade.remove(m);

    }

    public List<Mantenimiento> consultarTodos() {
        return mantenimientoFacade.findAll();
    }
}