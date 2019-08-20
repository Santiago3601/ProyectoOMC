/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.*;
import facade.*;
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
@Named(value = "tipoIdControlador")
@SessionScoped
public class tipoIdControlador implements Serializable {

    @EJB
    private TipoidFacade tipoIdFacade;
    Tipoid tipoId;

    @PostConstruct
    public void init() {
        tipoId = new Tipoid();

    }

    public Tipoid getTipoid() {
        return tipoId;
    }

    public void setTipoid(Tipoid tipoId) {
        this.tipoId = tipoId;
    }

    //Metodos
    public List<Tipoid> consultarTodos() {
        return tipoIdFacade.findAll();
    }

    public void consultarID() {
        tipoId = tipoIdFacade.find(tipoId.getIdTipoID());
    }

    public String registrarTipoId() {
        tipoIdFacade.create(tipoId);
        tipoId = new Tipoid();
        return "listaTipoId";
    }

    public void eliminarTipoId(Tipoid ec) {
        this.tipoIdFacade.remove(ec);
    }

    public String preActualizarTipoId(Tipoid tipoIdAct) {
        tipoId = tipoIdAct;
        return "editarTipoid";
    }

    public String actualizarTipoid() {
        tipoIdFacade.edit(tipoId);
        tipoId = new Tipoid();
        return "listaTipoid";
    }

    public tipoIdControlador() {
    }

}
