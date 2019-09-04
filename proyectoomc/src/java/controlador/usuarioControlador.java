/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Permiso;
import entidades.Rol;
//import entidades.RolTienePermiso;
import entidades.*;
import facade.*;
import facade.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
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
    Rol rol;

    @EJB
    private UsuarioFacade usuarioFacade;
    Usuario usuario;

    @EJB
    private TipoidFacade tipoIdFacade;
    Tipoid tipoId;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        rol = new Rol();
        tipoId = new Tipoid();

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        Locale idiomaUsuario = ec.getRequestLocale();
        boolean support = false;
        for (Locale l : getSupportLanguages()) {
            if (l.getLanguage().equals(idiomaUsuario.getLanguage())) {
                support = true;
                break;
            }
        }
        languageSelected = (support) ? idiomaUsuario : new Locale("es");

    }

    public Tipoid getTipoId() {
        return tipoId;
    }

    public void setTipoId(Tipoid tipoId) {
        this.tipoId = tipoId;
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

    public String registrarUsuario() {
        usuario.setRolidRol(rolFacade.find(rol.getIdRol()));
        usuario.setTpId(tipoIdFacade.find(tipoId.getIdTipoID()));
        usuarioFacade.create(usuario);
        usuario = new Usuario();
        return "listaUsuario";

    }

    public void eliminarUsuario(Usuario u) {
        this.usuarioFacade.remove(u);
    }

    public String preActualizarUsuario(Usuario usuarioAct) {
        usuario = usuarioAct;
        return "editarUsuario";
    }

    public String actualizarUsuario() {
        usuario.setRolidRol(rolFacade.find(rol.getIdRol()));
        usuario.setTpId(tipoIdFacade.find(tipoId.getIdTipoID()));
        usuarioFacade.edit(usuario);
        usuario = new Usuario();
        return "listaUsuario";
    }

    public String validarLogin() {

        String redirecionar = "";

        try {
            Usuario usuarioLogueado = usuarioFacade.login(usuario);
            if (usuarioLogueado != null) {
                rol = usuarioLogueado.getRolidRol();

                for (Permiso permiso : usuarioLogueado.getRolidRol().getPermisoList()) {

                    System.out.println("Permisos " + permiso.getNombre());

                }

                System.out.println("usuarioLogeado " + usuarioLogueado.getNombre());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sesionLogin", usuarioLogueado);
                switch (usuarioLogueado.getRolidRol().getIdRol()) {
                    case 1:
                        redirecionar = "dashboard/SI/1admin/index?faces-redirect=true";
                        break;
                    case 2:
                        redirecionar = "dashboard/SI/2cliente/index?faces-redirect=true";
                        break;
                    case 3:
                        //       redirecionar = "dashboard/SI/3jefe_planta/index";
                        redirecionar = "dashboard/SI/3jefe_planta/index?faces-redirect=true";
                        break;
                    case 4:
                        redirecionar = "dashboard/SI/4tecnico/index?faces-redirect=true";
                        break;

                    default:
                        throw new AssertionError();
                }

            } else {
                redirecionar = "index_1";

            }

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

        return redirecionar;
    }

    public String obtenerMenu() {
        String strHTML = "";
        for (Permiso p : rol.getPermisoList()) {
            strHTML += "<li>";
            strHTML += "<a>" + p.getNombre() + "</a>";
            strHTML += "</li>";

        }
        return strHTML;
    }

    private Locale languageSelected = new Locale("es");

    /**
     * Creates a new instance of SessionController
     */
    public Locale getLanguageSelected() {
        return languageSelected;
    }

    public void setLanguageSelected(Locale languageSelected) {
        this.languageSelected = languageSelected;
    }

    public List<Locale> getSupportLanguages() {
        List<Locale> idiomas = new ArrayList<>();
        Iterator<Locale> it = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
        while (it.hasNext()) {
            idiomas.add(it.next());
        }
        return idiomas;
    }

    public String cambiarIdioma(Locale idioma) {
        if (idioma != null) {
            this.languageSelected = idioma;
            FacesContext.getCurrentInstance().getViewRoot().setLocale(languageSelected);
        }
        return "";
    }

    public String recuperarContrasena() throws UnsupportedEncodingException{
        Usuario usa;
        usa=usuarioFacade.find(getUsuario().getId());
        if(usa!=null){
            usa.setContrasenia(GeneradorContraseñas.getPassword(GeneradorContraseñas.MINUSCULAS+GeneradorContraseñas.MAYUSCULAS,10));
            usuarioFacade.edit(usa);
            Mailer.send(usa);
                    
        }
        
        return null;
    }
    
    
}
