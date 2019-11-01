/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;

/**
 *
 * @author ASUS
 */
@Named(value = "graficaB")
@ViewScoped
public class graficaB {

    /**
     * Creates a new instance of graficaB
     */
    public graficaB() {
    }
    public void verReporte()throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        reporteGrafica grafica = new reporteGrafica();
        
        FacesContext facesContext= FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/graficaTipoMantenimiento.jasper");
         
        grafica.getReporte(ruta);
        FacesContext.getCurrentInstance().responseComplete();
    }
}
