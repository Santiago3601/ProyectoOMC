/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Vehiculo;
import facade.VehiculoFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

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

    
    private Part file;
    private String nombre;
    private String pathReal;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPathReal() {
        return pathReal;
    }

    public void setPathReal(String pathReal) {
        this.pathReal = pathReal;
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
    
    public String registrarVehiculo(){
     
       vehiculoFacade.create(vehiculo);
       vehiculo = new Vehiculo();
              return "listaVehiculos";
    }
    
    
        public String upload(String tabla) {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("Archivos");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\Archivos\\";
        try {
            this.nombre = file.getSubmittedFileName();
            pathReal = "/proyectoomc/archivos/" + nombre;
            path = path + this.nombre;
            InputStream in = file.getInputStream();

            byte[] data = new byte[in.available()];
            in.read(data);
            FileOutputStream out = new FileOutputStream(new File(path));
            out.write(data);
            //this.archivo.setUrl(path);
            in.close();
            out.close();

            path = path.replace("\\", "\\\\");

            vehiculoFacade.cargaArchivos(path, tabla);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //this.archivo.setEmpleadocedula(getEmpleado());
//        this.archivo.setUrl(pathReal);
//        this.archivoFacade.create(archivo);
//        this.empleado = new Empleado();
//        this.archivo = new Archivo();
        return "listaVehiculos";
    }

}