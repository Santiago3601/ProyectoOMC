/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Permiso;
import entidades.Rol;
import facade.PermisoFacade;
import facade.RolFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Aprendiz
 */



/*
@Named(value = "rolTienePermisoControlador")
@SessionScoped
public class rolTienePermisoControlador implements Serializable {

    @EJB
    private PermisoFacade permisoFacade;
    Permiso permiso;

    @EJB
    private RolFacade rolFacade;
    Rol rol;

    @EJB
    private RolTienePermisoFacade rolTienePermisoFacade;
    RolTienePermiso rolTienePermiso;

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public RolTienePermiso getRolTienePermiso() {
        return rolTienePermiso;
    }

    public void setRolTienePermiso(RolTienePermiso rolTienePermiso) {
        this.rolTienePermiso = rolTienePermiso;
    }

    //Metodos
    public List<RolTienePermiso> consultarTodos() {
        return rolTienePermisoFacade.findAll();
    }

    public void consultarID() {
        rolTienePermiso = rolTienePermisoFacade.find(rolTienePermiso.getIdRolTienePermiso());
    }

    public void registrarRolTienePermiso() {
        rolTienePermiso.setPermisoIdPermiso(permisoFacade.find(permiso.getIdPermiso()));
        rolTienePermiso.setRolidRol(rolFacade.find(rol.getIdRol()));
        rolTienePermisoFacade.create(rolTienePermiso);
        rolTienePermiso = new RolTienePermiso();
    }

    public void eliminarRolTienePermiso(RolTienePermiso c) {
        this.rolTienePermisoFacade.remove(c);
    }

    public String preActualizarRolTienePermiso(RolTienePermiso rolTienePermisoAct) {
        rolTienePermiso = rolTienePermisoAct;
        return "crearRolTienePermiso";
    }

    public String actualizarUsuario() {
        rolTienePermiso.setPermisoIdPermiso(permisoFacade.find(permiso.getIdPermiso()));
        rolTienePermiso.setRolidRol(rolFacade.find(rol.getIdRol()));
        rolTienePermisoFacade.edit(rolTienePermiso);
        rolTienePermiso = new RolTienePermiso();
        return "editarRolTienePermiso";
    }

    public rolTienePermisoControlador() {
}
}
*/


