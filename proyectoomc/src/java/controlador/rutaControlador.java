/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Empleado;
import entidades.Ruta;
import entidades.Vehiculo;
import facade.EmpleadoFacade;
import facade.RutaFacade;
import facade.VehiculoFacade;
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
@Named(value = "rutaControlador")
@SessionScoped
public class rutaControlador implements Serializable {


        @EJB
        private RutaFacade rutaFacade;
        Ruta ruta;

        @EJB
        private VehiculoFacade vehiculoFacade;
        Vehiculo vehiculo;

        @EJB
        private EmpleadoFacade empleadoFacade;
        Empleado empleado;

        @PostConstruct
        public void init() {
            empleado = new Empleado();
            vehiculo = new Vehiculo();
            ruta = new Ruta();
        }

        public Ruta getRuta() {
            return ruta;
        }

        public void setRuta(Ruta ruta) {
            this.ruta = ruta;
        }

        public Vehiculo getVehiculo() {
            return vehiculo;
        }

        public void setVehiculo(Vehiculo vehiculo) {
            this.vehiculo = vehiculo;
        }

        public Empleado getEmpleado() {
            return empleado;
        }

        public void setEmpleado(Empleado empleado) {
            this.empleado = empleado;
        }

        public rutaControlador() {
        }

        //METODOS
        public List<Ruta> consultarTodos() {
            return rutaFacade.findAll();
        }

        public void consultarID() {
            ruta = rutaFacade.find(ruta.getIdRuta());
        }

        public void registrarRuta() {
            ruta.setEmpleadoIdEmpleado(empleadoFacade.find(empleado.getIdEmpleado()));
            ruta.setVehiculoIdVehiculo(vehiculoFacade.find(vehiculo.getIdVehiculo()));
            rutaFacade.create(ruta);
            ruta = new Ruta();

        }

        public void eliminarRuta(Ruta item) {
            this.rutaFacade.remove(item);
        }

        public String preActualizarRuta(Ruta rutaAct) {
            ruta = rutaAct;
            return "editarRuta";
        }

        public String actualizarRuta() {
            ruta.setEmpleadoIdEmpleado(empleadoFacade.find(empleado.getIdEmpleado()));
            ruta.setVehiculoIdVehiculo(vehiculoFacade.find(vehiculo.getIdVehiculo()));
            rutaFacade.edit(ruta);
            ruta = new Ruta();
            return "editarRuta";
        }
    }

