/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.*;
import facade.*;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
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
 * @author Stephi
 */
@Named(value = "empleadoControlador")
@SessionScoped
public class EmpleadoControlador implements Serializable {

    @EJB
     EmpleadoFacade empleadoFacade;
    private Empleado empleado;
    @EJB
     UsuarioFacade usuarioFacade;
   private Usuario usuario;

    @EJB
     TurnoFacade turnoFacade;
   private Turno turno;

    private JasperPrint jasperPrint;
    private List<ReporteEmpleado> listareporte;
    private List<Object[]> listaFacade;

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }
    
    @PostConstruct
    public void init() {
        empleado = new Empleado();
        usuario = new Usuario();
        turno = new Turno();
        listareporte = new ArrayList<>();
        

    }

    public List<ReporteEmpleado> getListareporte() {
        return listareporte;
    }

    public void setListareporte(List<ReporteEmpleado> listareporte) {
        this.listareporte = listareporte;
    }

    public List<Object[]> getListaFacade() {
        return listaFacade;
    }

    public void setListaFacade(List<Object[]> listaFacade) {
        this.listaFacade = listaFacade;
    }

    /**
     * Creates a new instance of EmpleadoControlador
     */
    public EmpleadoControlador() {
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    //metodos
    public String registrar() {
       empleado.setUsuarioId(usuarioFacade.find(usuario.getId()));
       empleadoFacade.create(empleado);
       empleado = new Empleado();
       return "listarEmpleado";
    }
    

    public List<Empleado> consultarTodos() {
        return empleadoFacade.findAll();
    }

    public void consultarID() {
        empleado = empleadoFacade.find(empleado.getIdEmpleado());
    }

    public String eliminarEmpleado(Empleado u) {
        this.empleadoFacade.remove(u);
        return "listarEmpleado";
    }

    public String preActualizarEmpleado(Empleado empleadoAct) {
        empleado = empleadoAct;
        return "ActualizarEmpleado";
    }

    public String actualizarEmpleado() {
        empleado.setUsuarioId(usuarioFacade.find(usuario.getId()));
        empleadoFacade.edit(empleado);
        empleado = new Empleado();
        return "listarEmpleado";
    }
    public void certificadoLaboral() throws IOException, JRException{
        listaFacade=empleadoFacade.traerEmpleado((Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sesionLogin")); 
        if (!listaFacade.isEmpty()) {
            
            for (Object[] obj : listaFacade) {
                ReporteEmpleado rE = new ReporteEmpleado();
                rE.setNombre(obj[0].toString());
                rE.setApellido(obj[1].toString());
                rE.setTelefono(Long.parseLong(obj[2].toString()));
                rE.setCedula(Long.parseLong(obj[3].toString()));
                rE.setTipocedula(obj[4].toString());
                listareporte.add(rE);
            }
            
           JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(this.listareporte);
            String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("//reportes//");
            jasperPrint = JasperFillManager.fillReport(ruta + "//CertificadoLaboral.jasper", new HashMap(), beanCollectionDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=CertificadoLaboral.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete(); 
        }
        
    }
    
}
