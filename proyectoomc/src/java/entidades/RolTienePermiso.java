/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aprendiz
 */
@Entity
@Table(name = "rol_tiene_permiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolTienePermiso.findAll", query = "SELECT r FROM RolTienePermiso r")
    , @NamedQuery(name = "RolTienePermiso.findByIdRolTienePermiso", query = "SELECT r FROM RolTienePermiso r WHERE r.idRolTienePermiso = :idRolTienePermiso")})
public class RolTienePermiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rol_tiene_permiso")
    private Integer idRolTienePermiso;
    @JoinColumn(name = "permiso_id_permiso", referencedColumnName = "id_permiso")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Permiso permisoIdPermiso;
    @JoinColumn(name = "rol_idRol", referencedColumnName = "idRol")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol rolidRol;

    public RolTienePermiso() {
    }

    public RolTienePermiso(Integer idRolTienePermiso) {
        this.idRolTienePermiso = idRolTienePermiso;
    }

    public Integer getIdRolTienePermiso() {
        return idRolTienePermiso;
    }

    public void setIdRolTienePermiso(Integer idRolTienePermiso) {
        this.idRolTienePermiso = idRolTienePermiso;
    }

    public Permiso getPermisoIdPermiso() {
        return permisoIdPermiso;
    }

    public void setPermisoIdPermiso(Permiso permisoIdPermiso) {
        this.permisoIdPermiso = permisoIdPermiso;
    }

    public Rol getRolidRol() {
        return rolidRol;
    }

    public void setRolidRol(Rol rolidRol) {
        this.rolidRol = rolidRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRolTienePermiso != null ? idRolTienePermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolTienePermiso)) {
            return false;
        }
        RolTienePermiso other = (RolTienePermiso) object;
        if ((this.idRolTienePermiso == null && other.idRolTienePermiso != null) || (this.idRolTienePermiso != null && !this.idRolTienePermiso.equals(other.idRolTienePermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.RolTienePermiso[ idRolTienePermiso=" + idRolTienePermiso + " ]";
    }
    
}
