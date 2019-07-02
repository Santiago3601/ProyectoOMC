/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Agenda;
import entidades.Mantenimiento;
import facade.AgendaFacade;
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

    @PostConstruct
    public void init() {
        mantenimiento = new Mantenimiento();
        agenda = new Agenda();
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

    public String registrarMantenimiento() {

        mantenimiento.setAgendaIdAgenda(agendaFacade.find(agenda.getIdAgenda()));
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
