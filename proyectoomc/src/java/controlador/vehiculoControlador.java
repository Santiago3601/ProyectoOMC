/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Vehiculo;
import facade.VehiculoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Aprendiz
 */
@Named(value = "vehiculoControlador")
@SessionScoped
public class vehiculoControlador implements Serializable {

    @EJB
    private VehiculoFacade vehiculoFacade;
    Vehiculo vehiculo = new Vehiculo();


    public vehiculoControlador() {
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    //METODOS
    public List<Vehiculo> consultarTodos() {
        return vehiculoFacade.findAll();
    }

    public void consultarID() {
        vehiculo = vehiculoFacade.find(vehiculo.getIdVehiculo());
    }

    
        public void eliminarVehiculo(Vehiculo u){
        this.vehiculoFacade.remove(u);
    }

    
    public String preActualizarVehiculo(Vehiculo vehiculoAct) {
       vehiculo = vehiculoAct;
       return "editarVehiculo";
   }

   public String actualizarVehiculo() {
       
       vehiculoFacade.edit(vehiculo);
       vehiculo = new Vehiculo();
       return "listaVehiculos";
   }
    
    public void registrarVehiculo(){
     
       vehiculoFacade.create(vehiculo);
       vehiculo = new Vehiculo();
       
    }
    
    
    
}
