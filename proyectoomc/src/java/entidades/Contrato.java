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
@Table(name = "contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c")
    , @NamedQuery(name = "Contrato.findByIdContrato", query = "SELECT c FROM Contrato c WHERE c.idContrato = :idContrato")
    , @NamedQuery(name = "Contrato.findByTipoContrato", query = "SELECT c FROM Contrato c WHERE c.tipoContrato = :tipoContrato")
    , @NamedQuery(name = "Contrato.findByFechaInicioContrato", query = "SELECT c FROM Contrato c WHERE c.fechaInicioContrato = :fechaInicioContrato")
    , @NamedQuery(name = "Contrato.findByFechaFinalContrato", query = "SELECT c FROM Contrato c WHERE c.fechaFinalContrato = :fechaFinalContrato")})
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contrato")
    private Integer idContrato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "tipo_contrato")
    private String tipoContrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio_contrato")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioContrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_final_contrato")
    @Temporal(TemporalType.DATE)
    private Date fechaFinalContrato;
    @JoinColumn(name = "alquiler_id_alquiler", referencedColumnName = "id_alquiler")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Alquiler alquilerIdAlquiler;

    public Contrato() {
    }

    public Contrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Contrato(Integer idContrato, String tipoContrato, Date fechaInicioContrato, Date fechaFinalContrato) {
        this.idContrato = idContrato;
        this.tipoContrato = tipoContrato;
        this.fechaInicioContrato = fechaInicioContrato;
        this.fechaFinalContrato = fechaFinalContrato;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Date getFechaInicioContrato() {
        return fechaInicioContrato;
    }

    public void setFechaInicioContrato(Date fechaInicioContrato) {
        this.fechaInicioContrato = fechaInicioContrato;
    }

    public Date getFechaFinalContrato() {
        return fechaFinalContrato;
    }

    public void setFechaFinalContrato(Date fechaFinalContrato) {
        this.fechaFinalContrato = fechaFinalContrato;
    }

    public Alquiler getAlquilerIdAlquiler() {
        return alquilerIdAlquiler;
    }

    public void setAlquilerIdAlquiler(Alquiler alquilerIdAlquiler) {
        this.alquilerIdAlquiler = alquilerIdAlquiler;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContrato != null ? idContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.idContrato == null && other.idContrato != null) || (this.idContrato != null && !this.idContrato.equals(other.idContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Contrato[ idContrato=" + idContrato + " ]";
    }
    
}
