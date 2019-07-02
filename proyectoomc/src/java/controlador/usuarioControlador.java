/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Rol;
import entidades.Usuario;
import facade.RolFacade;
import facade.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author Aprendiz
 */
@Named(value = "usuarioControlador")
@SessionScoped
public class usuarioControlador implements Serializable {

    @EJB
    private RolFacade rolFacade;
    Rol rol ;

    @EJB
    private UsuarioFacade usuarioFacade;
    Usuario usuario ;

    @PostConstruct
    public void init(){
       usuario = new Usuario() ;
       rol = new Rol();
    }
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public usuarioControlador() {
    }
//METODOS
    public List<Usuario> consultarTodos() {
        return usuarioFacade.findAll();
    }

    public void consultarID() {
        usuario = usuarioFacade.find(usuario.getId());
    }

    public void registrarUsuario(){
       usuario.setRolidRol(rolFacade.find(rol.getIdRol()));
       usuarioFacade.create(usuario);
       usuario = new Usuario();
       
    }
    
    
    public void eliminarUsuario(Usuario u){
        this.usuarioFacade.remove(u);
    }

    
    public String preActualizarUsuario(Usuario usuarioAct) {
       usuario = usuarioAct;
       return "editarUsuario";
   }

   public String actualizarUsuario() {
       usuario.setRolidRol(rolFacade.find(rol.getIdRol()));
       usuarioFacade.edit(usuario);
       usuario = new Usuario();
       return "listaUsuario";
   }


    public String validarLogin() {

        String redirecionar = "";

        try {
            Usuario usuarioLogueado = usuarioFacade.login(usuario);
            if (usuarioLogueado != null) {

                System.out.println("usuarioLogeado " + usuarioLogueado.getNombre());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sesionLogin", usuarioLogueado);
                switch (usuarioLogueado.getRolidRol().getIdRol()) {
                    case 1:
                        redirecionar ="dashboard/SI/1admin/index";
                        break;
                    case 2:
                        redirecionar ="dashboard/SI/2cliente/index";
                        break;
                    case 3:
                        redirecionar ="dashboard/SI/3jefe_planta/index";
                        break;
                    case 4:
                        redirecionar ="dashboard/SI/4tecnico/index";
                        break;
                   
                    default:
                        throw new AssertionError();
                }
            }else{
                redirecionar ="index_1";
                
            }

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

        return redirecionar;
    }

}
