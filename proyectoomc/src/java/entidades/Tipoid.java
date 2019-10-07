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
@Table(name = "tipoid")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoid.findAll", query = "SELECT t FROM Tipoid t")
    , @NamedQuery(name = "Tipoid.findByIdTipoID", query = "SELECT t FROM Tipoid t WHERE t.idTipoID = :idTipoID")
    , @NamedQuery(name = "Tipoid.findByTipoID", query = "SELECT t FROM Tipoid t WHERE t.tipoID = :tipoID")})
public class Tipoid implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTipoID")
    private Integer idTipoID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "tipoID")
    private String tipoID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tpId", fetch = FetchType.LAZY)
    private List<Usuario> usuarioList;

    public Tipoid() {
    }

    public Tipoid(Integer idTipoID) {
        this.idTipoID = idTipoID;
    }

    public Tipoid(Integer idTipoID, String tipoID) {
        this.idTipoID = idTipoID;
        this.tipoID = tipoID;
    }

    public Integer getIdTipoID() {
        return idTipoID;
    }

    public void setIdTipoID(Integer idTipoID) {
        this.idTipoID = idTipoID;
    }

    public String getTipoID() {
        return tipoID;
    }

    public void setTipoID(String tipoID) {
        this.tipoID = tipoID;
    }

    @XmlTransient
    @JsonIgnore
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoID != null ? idTipoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoid)) {
            return false;
        }
        Tipoid other = (Tipoid) object;
        if ((this.idTipoID == null && other.idTipoID != null) || (this.idTipoID != null && !this.idTipoID.equals(other.idTipoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Tipoid[ idTipoID=" + idTipoID + " ]";
    }
    
}
