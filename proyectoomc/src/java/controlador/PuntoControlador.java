/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Punto;
import facade.PuntoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Stephi
 */
@Named(value = "puntoControlador")
@SessionScoped
public class PuntoControlador implements Serializable {

    @EJB
    private PuntoFacade puntoFacade;
    Punto punto = new Punto();
    @PostConstruct
    public void init(){
        punto = new Punto();
    }
    /**
     * Creates a new instance of PuntoControlador
     */
    public PuntoControlador() {
    }

    public Punto getPunto() {
        return punto;
    }

    public void setPunto(Punto punto) {
        this.punto = punto;
    }

    //METODOS
    public String registrar() {
        puntoFacade.create(punto);
        punto = new Punto();
        return "RegistrarPunto";
    }

    public List<Punto> consultarTodos() {
        return puntoFacade.findAll();
    }

    public void consultarID() {
        punto = puntoFacade.find(punto.getIdPunto());
    }

    public String eliminar(Punto punto) {
        this.punto = punto;
        puntoFacade.remove(punto);
        this.punto = new Punto();
        return "ListaPunto";
    }

    public String preActualizar(Punto puntoAct) {
        punto = puntoAct;
        return "ActualizarPunto";
    }
    public String actualizar(){
        puntoFacade.edit(punto);
        punto = new Punto();
        return "ListaPunto";
    }
}
