/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Cilindro;
import entidades.EstadoCilindro;
import entidades.EstadoMantenimiento;
import facade.CilindroFacade;
import facade.EstadoCilindroFacade;
import facade.EstadoMantenimientoFacade;
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
@Named(value = "cilindroControlador")
@SessionScoped
public class cilindroControlador implements Serializable {

    /**
     * Creates a new instance of empleadoControlador
     */
    @EJB
    private CilindroFacade cilindroFacade;
    Cilindro cilindro;
    @EJB
    private EstadoCilindroFacade estadocCilindroFacade;
    EstadoCilindro estadocilindro;

    @PostConstruct
    public void init() {
        cilindro = new Cilindro();
        estadocilindro = new EstadoCilindro();

    }

    public Cilindro getCilindro() {
        return cilindro;
    }

    public void setCilindro(Cilindro cilindro) {
        this.cilindro = cilindro;

    }

    public EstadoCilindro getEstadocilindro() {
        return estadocilindro;
    }

    public void setEstadocilindro(EstadoCilindro estadocilindro) {
        this.estadocilindro = estadocilindro;
    }


    //METODOS
    public String registrarCilindro() {
        cilindro.setEstadoIdEstado(estadocCilindroFacade.find(estadocilindro.getIdEstado()));
        cilindroFacade.create(cilindro);
        cilindro = new Cilindro();
        return "listaCilindro";

    }

    public List<Cilindro> consultarTodos() {
        return cilindroFacade.findAll();
    }

    public cilindroControlador() {

    }

    public String eliminarCilindro(Cilindro ci) {
        cilindroFacade.remove(ci);
        this.cilindro = new Cilindro();
        return "listarCilindro";
    }

    public String preActualizarCilindro(Cilindro cilindroAct) {
        cilindro = cilindroAct;
        return "editarCilindro";

    }

    public String actualizarCilindro() {
        cilindro.setEstadoIdEstado(estadocCilindroFacade.find(estadocilindro.getIdEstado()));
        cilindroFacade.edit(cilindro);
        cilindro = new Cilindro();
        return "listaCilindro";
    }

    public String cambiarEstado(Cilindro ci) {
        this.cilindro = ci;
        // this.estadocilindro.setEstadoCilindro();
        cilindroFacade.edit(getCilindro());
        cilindro = new Cilindro();
        return "listaCilindro";
    }

    public String consultaIndividual(Cilindro cili) {
        cilindro = cili;
        //cilindro = cili.getIdCilindro();

        return "listaIDCilindro?faces-redirect=true";
    }
}
