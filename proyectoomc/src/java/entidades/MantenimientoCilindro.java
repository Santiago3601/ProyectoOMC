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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Stephi
 */
@Entity
@Table(name = "mantenimiento_cilindro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MantenimientoCilindro.findAll", query = "SELECT m FROM MantenimientoCilindro m")
    , @NamedQuery(name = "MantenimientoCilindro.findByIdMantenimientoCilindro", query = "SELECT m FROM MantenimientoCilindro m WHERE m.idMantenimientoCilindro = :idMantenimientoCilindro")
    , @NamedQuery(name = "MantenimientoCilindro.findByTipoMantenimiento", query = "SELECT m FROM MantenimientoCilindro m WHERE m.tipoMantenimiento = :tipoMantenimiento")})
public class MantenimientoCilindro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_mantenimiento_cilindro")
    private Integer idMantenimientoCilindro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_mantenimiento")
    private String tipoMantenimiento;
    @JoinColumn(name = "cilindro_id_cilindro", referencedColumnName = "id_cilindro")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cilindro cilindroIdCilindro;
    @JoinColumn(name = "mantenimiento_id_mantenimiento", referencedColumnName = "id_mantenimiento")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Mantenimiento mantenimientoIdMantenimiento;

    public MantenimientoCilindro() {
    }

    public MantenimientoCilindro(Integer idMantenimientoCilindro) {
        this.idMantenimientoCilindro = idMantenimientoCilindro;
    }

    public MantenimientoCilindro(Integer idMantenimientoCilindro, String tipoMantenimiento) {
        this.idMantenimientoCilindro = idMantenimientoCilindro;
        this.tipoMantenimiento = tipoMantenimiento;
    }

    public Integer getIdMantenimientoCilindro() {
        return idMantenimientoCilindro;
    }

    public void setIdMantenimientoCilindro(Integer idMantenimientoCilindro) {
        this.idMantenimientoCilindro = idMantenimientoCilindro;
    }

    public String getTipoMantenimiento() {
        return tipoMantenimiento;
    }

    public void setTipoMantenimiento(String tipoMantenimiento) {
        this.tipoMantenimiento = tipoMantenimiento;
    }

    public Cilindro getCilindroIdCilindro() {
        return cilindroIdCilindro;
    }

    public void setCilindroIdCilindro(Cilindro cilindroIdCilindro) {
        this.cilindroIdCilindro = cilindroIdCilindro;
    }

    public Mantenimiento getMantenimientoIdMantenimiento() {
        return mantenimientoIdMantenimiento;
    }

    public void setMantenimientoIdMantenimiento(Mantenimiento mantenimientoIdMantenimiento) {
        this.mantenimientoIdMantenimiento = mantenimientoIdMantenimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMantenimientoCilindro != null ? idMantenimientoCilindro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MantenimientoCilindro)) {
            return false;
        }
        MantenimientoCilindro other = (MantenimientoCilindro) object;
        if ((this.idMantenimientoCilindro == null && other.idMantenimientoCilindro != null) || (this.idMantenimientoCilindro != null && !this.idMantenimientoCilindro.equals(other.idMantenimientoCilindro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.MantenimientoCilindro[ idMantenimientoCilindro=" + idMantenimientoCilindro + " ]";
    }
    
}
