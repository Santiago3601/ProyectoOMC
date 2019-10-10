/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Santiago
 */
@Entity
@Table(name = "estado_empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoEmpleado.findAll", query = "SELECT e FROM EstadoEmpleado e")
    , @NamedQuery(name = "EstadoEmpleado.findByIdEstadoEmpleado", query = "SELECT e FROM EstadoEmpleado e WHERE e.idEstadoEmpleado = :idEstadoEmpleado")
    , @NamedQuery(name = "EstadoEmpleado.findByEstado", query = "SELECT e FROM EstadoEmpleado e WHERE e.estado = :estado")})
public class EstadoEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_estado_empleado")
    private Integer idEstadoEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEstado", fetch = FetchType.LAZY)
    private List<Empleado> empleadoList;

    public EstadoEmpleado() {
    }

    public EstadoEmpleado(Integer idEstadoEmpleado) {
        this.idEstadoEmpleado = idEstadoEmpleado;
    }

    public EstadoEmpleado(Integer idEstadoEmpleado, String estado) {
        this.idEstadoEmpleado = idEstadoEmpleado;
        this.estado = estado;
    }

    public Integer getIdEstadoEmpleado() {
        return idEstadoEmpleado;
    }

    public void setIdEstadoEmpleado(Integer idEstadoEmpleado) {
        this.idEstadoEmpleado = idEstadoEmpleado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    @JsonIgnore
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoEmpleado != null ? idEstadoEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoEmpleado)) {
            return false;
        }
        EstadoEmpleado other = (EstadoEmpleado) object;
        if ((this.idEstadoEmpleado == null && other.idEstadoEmpleado != null) || (this.idEstadoEmpleado != null && !this.idEstadoEmpleado.equals(other.idEstadoEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EstadoEmpleado[ idEstadoEmpleado=" + idEstadoEmpleado + " ]";
    }
    
}
