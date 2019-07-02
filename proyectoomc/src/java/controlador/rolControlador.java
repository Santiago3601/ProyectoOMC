/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Rol;
import facade.RolFacade;
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
@Named(value = "rolControlador")
@SessionScoped
public class rolControlador implements Serializable {

  @EJB
    private RolFacade rolFacade;
    Rol rol = new Rol();

    @PostConstruct
    public void init() {
        rol = new Rol();
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void registrarRol() {
        rolFacade.create(rol);
        rol = new Rol();
    }

    public List<Rol> consultarTodos() {
        return rolFacade.findAll();
    }

    public void consultarID() {
        rol = rolFacade.find(rol.getIdRol());
    }

    public void eliminarRol(Rol r) {
        this.rolFacade.remove(r);
    }

    public String preActualizarRol(Rol rolAct) {
        rol = rolAct;
        return "editarRol";
    }

    public String actualizarRol() {

        rolFacade.edit(rol);
        rol = new Rol();
        return "listaRoles";
    }

    public rolControlador() {
    }

}
