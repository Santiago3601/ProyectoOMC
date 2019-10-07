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
 * @author Aprendiz
 */
@Entity
@Table(name = "estado_permiso_laboral")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoPermisoLaboral.findAll", query = "SELECT e FROM EstadoPermisoLaboral e")
    , @NamedQuery(name = "EstadoPermisoLaboral.findByIdEstado", query = "SELECT e FROM EstadoPermisoLaboral e WHERE e.idEstado = :idEstado")
    , @NamedQuery(name = "EstadoPermisoLaboral.findByEstadoPermiso", query = "SELECT e FROM EstadoPermisoLaboral e WHERE e.estadoPermiso = :estadoPermiso")})
public class EstadoPermisoLaboral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_estado")
    private Integer idEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "estado_permiso")
    private String estadoPermiso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado", fetch = FetchType.LAZY)
    private List<PermisoLaboral> permisoLaboralList;

    public EstadoPermisoLaboral() {
    }

    public EstadoPermisoLaboral(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public EstadoPermisoLaboral(Integer idEstado, String estadoPermiso) {
        this.idEstado = idEstado;
        this.estadoPermiso = estadoPermiso;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstadoPermiso() {
        return estadoPermiso;
    }

    public void setEstadoPermiso(String estadoPermiso) {
        this.estadoPermiso = estadoPermiso;
    }

    @XmlTransient
    @JsonIgnore
    public List<PermisoLaboral> getPermisoLaboralList() {
        return permisoLaboralList;
    }

    public void setPermisoLaboralList(List<PermisoLaboral> permisoLaboralList) {
        this.permisoLaboralList = permisoLaboralList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoPermisoLaboral)) {
            return false;
        }
        EstadoPermisoLaboral other = (EstadoPermisoLaboral) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EstadoPermisoLaboral[ idEstado=" + idEstado + " ]";
    }
    
}
