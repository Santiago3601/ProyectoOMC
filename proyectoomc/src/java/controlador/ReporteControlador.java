/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Cilindro;
import facade.CilindroFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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
    }
    @EJB
    CilindroFacade cilindroFacade;
    
}
