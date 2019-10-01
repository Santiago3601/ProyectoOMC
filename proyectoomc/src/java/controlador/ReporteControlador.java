/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Cilindro;
import facade.CilindroFacade;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
    private List<Cilindro> listaCilindro;
    private List<Object[]> listaTotal;
   
       
    JasperPrint jasperPrint;

    public List<Cilindro> getListaCilindro() {
        listaCilindro = cilindroFacade.findAll();
        return listaCilindro;
    }

    public void setListaCilindro(List<Cilindro> listaProducto) {
        this.listaCilindro = listaProducto;
    }

    public void init() throws JRException {

    }

    public void PDFC(ActionEvent actionEvent) throws JRException, IOException {
        getListaCilindro();
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaCilindro);
        String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("//reportes//");
        jasperPrint = JasperFillManager.fillReport(ruta + "//CilindroR.jasper", new HashMap(), beanCollectionDataSource);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();

    }

    public List<Object[]> getListaTotal() {
        listaTotal = cilindroFacade.calcularTotal();
        return listaTotal;
    }

    public void setListaTotal(List<Object[]> listaTotal) {

        this.listaTotal = listaTotal;
    }

    public void listaFinal() {
        cilindroFacade.calcularTotal();
    }
    
}
