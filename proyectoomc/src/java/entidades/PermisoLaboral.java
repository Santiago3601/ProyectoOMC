/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aprendiz
 */
@Entity
@Table(name = "permiso_laboral")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PermisoLaboral.findAll", query = "SELECT p FROM PermisoLaboral p")
    , @NamedQuery(name = "PermisoLaboral.findByIdPermisoLaboral", query = "SELECT p FROM PermisoLaboral p WHERE p.idPermisoLaboral = :idPermisoLaboral")
    , @NamedQuery(name = "PermisoLaboral.findByHoraPermiso", query = "SELECT p FROM PermisoLaboral p WHERE p.horaPermiso = :horaPermiso")
    , @NamedQuery(name = "PermisoLaboral.findByFechaPermiso", query = "SELECT p FROM PermisoLaboral p WHERE p.fechaPermiso = :fechaPermiso")
    , @NamedQuery(name = "PermisoLaboral.findByObvservaciones", query = "SELECT p FROM PermisoLaboral p WHERE p.obvservaciones = :obvservaciones")})
public class PermisoLaboral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_permiso_laboral")
    private Integer idPermisoLaboral;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_permiso")
    @Temporal(TemporalType.TIME)
    private Date horaPermiso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_permiso")
    @Temporal(TemporalType.DATE)
    private Date fechaPermiso;
    @Size(max = 200)
    @Column(name = "obvservaciones")
    private String obvservaciones;
    @JoinColumn(name = "empleado_id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empleado empleadoIdEmpleado;

    public PermisoLaboral() {
    }

    public PermisoLaboral(Integer idPermisoLaboral) {
        this.idPermisoLaboral = idPermisoLaboral;
    }

    public PermisoLaboral(Integer idPermisoLaboral, Date horaPermiso, Date fechaPermiso) {
        this.idPermisoLaboral = idPermisoLaboral;
        this.horaPermiso = horaPermiso;
        this.fechaPermiso = fechaPermiso;
    }

    public Integer getIdPermisoLaboral() {
        return idPermisoLaboral;
    }

    public void setIdPermisoLaboral(Integer idPermisoLaboral) {
        this.idPermisoLaboral = idPermisoLaboral;
    }

    public Date getHoraPermiso() {
        return horaPermiso;
    }

    public void setHoraPermiso(Date horaPermiso) {
        this.horaPermiso = horaPermiso;
    }

    public Date getFechaPermiso() {
        return fechaPermiso;
    }

    public void setFechaPermiso(Date fechaPermiso) {
        this.fechaPermiso = fechaPermiso;
    }

    public String getObvservaciones() {
        return obvservaciones;
    }

    public void setObvservaciones(String obvservaciones) {
        this.obvservaciones = obvservaciones;
    }

    public Empleado getEmpleadoIdEmpleado() {
        return empleadoIdEmpleado;
    }

    public void setEmpleadoIdEmpleado(Empleado empleadoIdEmpleado) {
        this.empleadoIdEmpleado = empleadoIdEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermisoLaboral != null ? idPermisoLaboral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermisoLaboral)) {
            return false;
        }
        PermisoLaboral other = (PermisoLaboral) object;
        if ((this.idPermisoLaboral == null && other.idPermisoLaboral != null) || (this.idPermisoLaboral != null && !this.idPermisoLaboral.equals(other.idPermisoLaboral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.PermisoLaboral[ idPermisoLaboral=" + idPermisoLaboral + " ]";
    }
    
}
