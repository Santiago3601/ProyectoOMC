/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Cilindro;
import entidades.Mantenimiento;
import entidades.MantenimientoCilindro;
import facade.CilindroFacade;
import facade.MantenimientoCilindroFacade;
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
@Named(value = "mantenimientoCilindroControlador")
@SessionScoped
public class mantenimientoCilindroControlador implements Serializable {

  /**
     * Creates a new instance of mantenimientoCilindroControlador
     */
    public mantenimientoCilindroControlador() {
    }
    @EJB
    private MantenimientoCilindroFacade mantenimientoCilindroFacade;
    MantenimientoCilindro mantenimientoCilindro;
    
    @EJB
    private CilindroFacade cilindroFacade;
    Cilindro cilindro;
    
    @EJB
    private MantenimientoFacade mantenimientoFacade;
    Mantenimiento mantenimiento;

    
        @PostConstruct
    public void init(){
        mantenimientoCilindro = new MantenimientoCilindro();
        cilindro = new Cilindro();
        mantenimiento = new Mantenimiento();
    }
    public MantenimientoCilindro getMantenimientoCilindro() {
        return mantenimientoCilindro;
    }

    public void setMantenimientoCilindro(MantenimientoCilindro mantenimientoCilindro) {
        this.mantenimientoCilindro = mantenimientoCilindro;
    }

    public Cilindro getCilindro() {
        return cilindro;
    }

    public void setCilindro(Cilindro cilindro) {
        this.cilindro = cilindro;
    }

    public Mantenimiento getMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(Mantenimiento mantenimiento) {
        this.mantenimiento = mantenimiento;
    }
    
    public String registrarMantenimientoCilindro(){
        mantenimientoCilindro.setIdMantenimientoCilindro(mantenimientoCilindro.getIdMantenimientoCilindro());
        mantenimientoCilindro.setMantenimientoIdMantenimiento(mantenimientoFacade.find(mantenimiento.getIdMantenimiento()));
        mantenimientoCilindro.setCilindroIdCilindro(cilindroFacade.find(cilindro.getIdCilindro()));
        mantenimientoCilindroFacade.create(mantenimientoCilindro);
        return "listaMantenimientoCilindro";
    }
    public String pre_actualizarMantenimientoCilindro(MantenimientoCilindro mantenimientcCilindrooAct){
        mantenimientoCilindro = mantenimientcCilindrooAct;
        return "editarMantenimientoCilindro";
        
    }
    public String actualizarMantenimientoCilindro(){
        mantenimientoCilindro.setIdMantenimientoCilindro(mantenimientoCilindro.getIdMantenimientoCilindro());
        mantenimientoCilindro.setMantenimientoIdMantenimiento(mantenimientoFacade.find(mantenimiento.getIdMantenimiento()));
        mantenimientoCilindro.setCilindroIdCilindro(cilindroFacade.find(cilindro.getIdCilindro()));
        mantenimientoCilindroFacade.edit(mantenimientoCilindro);
        return "listaMantenimientoCilindro";
   
    }
    public void eliminarMantenimientoCilindro(MantenimientoCilindro mantenimientoCilindroC){
        mantenimientoCilindroFacade.remove(mantenimientoCilindro);
        
    }
    public List<MantenimientoCilindro> consultarTodos(){
        return mantenimientoCilindroFacade.findAll();
        
    }
}

