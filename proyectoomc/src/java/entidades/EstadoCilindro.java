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
@Table(name = "estado_cilindro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoCilindro.findAll", query = "SELECT e FROM EstadoCilindro e")
    , @NamedQuery(name = "EstadoCilindro.findByIdEstado", query = "SELECT e FROM EstadoCilindro e WHERE e.idEstado = :idEstado")
    , @NamedQuery(name = "EstadoCilindro.findByEstadoCol", query = "SELECT e FROM EstadoCilindro e WHERE e.estadoCol = :estadoCol")})
public class EstadoCilindro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_estado")
    private Integer idEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "estado_col")
    private String estadoCol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoIdEstado", fetch = FetchType.LAZY)
    private List<Cilindro> cilindroList;

    public EstadoCilindro() {
    }

    public EstadoCilindro(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public EstadoCilindro(Integer idEstado, String estadoCol) {
        this.idEstado = idEstado;
        this.estadoCol = estadoCol;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstadoCol() {
        return estadoCol;
    }

    public void setEstadoCol(String estadoCol) {
        this.estadoCol = estadoCol;
    }

    @XmlTransient
    @JsonIgnore
    public List<Cilindro> getCilindroList() {
        return cilindroList;
    }

    public void setCilindroList(List<Cilindro> cilindroList) {
        this.cilindroList = cilindroList;
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
        if (!(object instanceof EstadoCilindro)) {
            return false;
        }
        EstadoCilindro other = (EstadoCilindro) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EstadoCilindro[ idEstado=" + idEstado + " ]";
    }
    
}
