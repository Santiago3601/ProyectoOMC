/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.PermisoLaboral;
import facade.PermisoLaboralFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author SENA
 */
@Named(value = "reporteControlador")
@SessionScoped
public class ReporteControlador implements Serializable {

    /**
     * Creates a new instance of ReporteControlador
     */
    public ReporteControlador() {
        this.permisoLaboral = permisoLaboral;
    }
    @EJB
    PermisoLaboralFacade permisoLaboralFacade;
    private PermisoLaboral permisoLaboral;
            

    public PermisoLaboral getPermisoLaboral() {
        return permisoLaboral;
    }

    public void setPermisoLaboral(PermisoLaboral permisoLaboral) {
        this.permisoLaboral = permisoLaboral;
    }
    //Metodos
    
 /*   public void reporte(){
        List<Object[]> listaPermisoLaboral = this.permisoLaboralFacade.reportePermisoLaboral();
        List<ReportePermisoLaboral> listaReporte = new ArrayList<>(); 
        for (Object[] ob: listaPermisoLaboral) {
            
        }
    }*/
    
}
