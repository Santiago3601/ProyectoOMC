/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;

/**
 *
 * @author ASUS
 */
@Named(value = "cilindro")
@ViewScoped
public class cilindroBean {

    /**
     * Creates a new instance of cilindro
     */
    public cilindroBean() {
    }
   
    public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
       
        //Instancia hacia la clase reporteClientes        
        reporteCilindro reporteci = new reporteCilindro();
       
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/cilindro.jasper");
        reporteci.getReporte(ruta);
        FacesContext.getCurrentInstance().responseComplete();              
    }
}