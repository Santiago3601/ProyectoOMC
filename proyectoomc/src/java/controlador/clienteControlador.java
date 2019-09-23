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
import javax.faces.context.FacesContext;

/**
 *
 * @author ACER
 */
@Named(value = "clienteControlador")
@SessionScoped
public class clienteControlador implements Serializable {

    @EJB
    private ClienteFacade clienteFacade;
    Cliente cliente;

    @EJB
    private UsuarioFacade usuarioFacade;
    Usuario usuario;

    @EJB
    private EstadoClienteFacade estadoClienteFacade;
    EstadoCliente estadoCliente;

    @EJB
    private SolicitudFacade solicitudFacade;
    Solicitud solicitud;

    @PostConstruct
    public void init() {
        cliente = new Cliente();
        usuario = new Usuario();
        estadoCliente = new EstadoCliente();
        solicitud = new Solicitud();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EstadoCliente getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(EstadoCliente estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    //Metodos
    public List<Cliente> consultarTodos() {
        return clienteFacade.findAll();
    }

    public void consultarID() {
        cliente = clienteFacade.find(cliente.getIdCliente());
    }

    public String registrarCliente() {
        //cliente.setUsuarioId(usuarioFacade.find(usuario.getId()));
        cliente.setUsuarioId(usuarioFacade.find(usuario.getId()));
        cliente.setEstadoIdEstado(estadoClienteFacade.find(estadoCliente.getIdEstado()));
        clienteFacade.create(cliente);
        cliente = new Cliente();
        return "listaClientes";
    }

    public void eliminarCliente(Cliente c) {
        this.clienteFacade.remove(c);
    }

    public String preActualizarCliente(Cliente clienteAct) {
        cliente = clienteAct;
        return "editarCliente";
    }

    public String actualizarCliente() {
        cliente.setUsuarioId(usuarioFacade.find(usuario.getId()));
        cliente.setEstadoIdEstado(estadoClienteFacade.find(estadoCliente.getIdEstado()));
        clienteFacade.edit(cliente);
        cliente = new Cliente();
        return "listaClientes";
    }

    public clienteControlador() {
    }

    public String estado() {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesionLogin");
        this.cliente = solicitudFacade.obtenerIdUsuario(usuario);

        if (this.cliente.getEstadoIdEstado().getIdEstado() == 1) {
            return "confirmacionClienteCilindroAgendado";
        }

        return "confirmacionClienteCilindroNoAgendado";
    }
}
