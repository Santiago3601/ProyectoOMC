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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Santiago
 */
@Entity
@Table(name = "auditoria_mantenimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuditoriaMantenimiento.findAll", query = "SELECT a FROM AuditoriaMantenimiento a")
    , @NamedQuery(name = "AuditoriaMantenimiento.findByIdAudMant", query = "SELECT a FROM AuditoriaMantenimiento a WHERE a.idAudMant = :idAudMant")
    , @NamedQuery(name = "AuditoriaMantenimiento.findByAntiguafechaInicioMantenimiento", query = "SELECT a FROM AuditoriaMantenimiento a WHERE a.antiguafechaInicioMantenimiento = :antiguafechaInicioMantenimiento")
    , @NamedQuery(name = "AuditoriaMantenimiento.findByAntiguafechaFinalMantenimiento", query = "SELECT a FROM AuditoriaMantenimiento a WHERE a.antiguafechaFinalMantenimiento = :antiguafechaFinalMantenimiento")
    , @NamedQuery(name = "AuditoriaMantenimiento.findByAntiguaTipoMantenimiento", query = "SELECT a FROM AuditoriaMantenimiento a WHERE a.antiguaTipoMantenimiento = :antiguaTipoMantenimiento")
    , @NamedQuery(name = "AuditoriaMantenimiento.findByUsuarioAuditor", query = "SELECT a FROM AuditoriaMantenimiento a WHERE a.usuarioAuditor = :usuarioAuditor")
    , @NamedQuery(name = "AuditoriaMantenimiento.findByFechaAuditoria", query = "SELECT a FROM AuditoriaMantenimiento a WHERE a.fechaAuditoria = :fechaAuditoria")})
public class AuditoriaMantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_AudMant")
    private Integer idAudMant;
    @Column(name = "Antigua_fechaInicioMantenimiento")
    @Temporal(TemporalType.DATE)
    private Date antiguafechaInicioMantenimiento;
    @Column(name = "Antigua_fechaFinalMantenimiento")
    @Temporal(TemporalType.DATE)
    private Date antiguafechaFinalMantenimiento;
    @Size(max = 50)
    @Column(name = "Antigua_TipoMantenimiento")
    private String antiguaTipoMantenimiento;
    @Size(max = 45)
    @Column(name = "UsuarioAuditor")
    private String usuarioAuditor;
    @Column(name = "FechaAuditoria")
    @Temporal(TemporalType.DATE)
    private Date fechaAuditoria;
    @JoinColumn(name = "id_mantenimiento", referencedColumnName = "id_mantenimiento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Mantenimiento idMantenimiento;
    @JoinColumn(name = "id_agenda", referencedColumnName = "id_agenda")
    @ManyToOne(fetch = FetchType.LAZY)
    private Agenda idAgenda;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(fetch = FetchType.LAZY)
    private EstadoMantenimiento idEstado;
    @JoinColumn(name = "id_cilindro", referencedColumnName = "id_cilindro")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cilindro idCilindro;

    public AuditoriaMantenimiento() {
    }

    public AuditoriaMantenimiento(Integer idAudMant) {
        this.idAudMant = idAudMant;
    }

    public Integer getIdAudMant() {
        return idAudMant;
    }

    public void setIdAudMant(Integer idAudMant) {
        this.idAudMant = idAudMant;
    }

    public Date getAntiguafechaInicioMantenimiento() {
        return antiguafechaInicioMantenimiento;
    }

    public void setAntiguafechaInicioMantenimiento(Date antiguafechaInicioMantenimiento) {
        this.antiguafechaInicioMantenimiento = antiguafechaInicioMantenimiento;
    }

    public Date getAntiguafechaFinalMantenimiento() {
        return antiguafechaFinalMantenimiento;
    }

    public void setAntiguafechaFinalMantenimiento(Date antiguafechaFinalMantenimiento) {
        this.antiguafechaFinalMantenimiento = antiguafechaFinalMantenimiento;
    }

    public String getAntiguaTipoMantenimiento() {
        return antiguaTipoMantenimiento;
    }

    public void setAntiguaTipoMantenimiento(String antiguaTipoMantenimiento) {
        this.antiguaTipoMantenimiento = antiguaTipoMantenimiento;
    }

    public String getUsuarioAuditor() {
        return usuarioAuditor;
    }

    public void setUsuarioAuditor(String usuarioAuditor) {
        this.usuarioAuditor = usuarioAuditor;
    }

    public Date getFechaAuditoria() {
        return fechaAuditoria;
    }

    public void setFechaAuditoria(Date fechaAuditoria) {
        this.fechaAuditoria = fechaAuditoria;
    }

    public Mantenimiento getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(Mantenimiento idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public Agenda getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Agenda idAgenda) {
        this.idAgenda = idAgenda;
    }

    public EstadoMantenimiento getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadoMantenimiento idEstado) {
        this.idEstado = idEstado;
    }

    public Cilindro getIdCilindro() {
        return idCilindro;
    }

    public void setIdCilindro(Cilindro idCilindro) {
        this.idCilindro = idCilindro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAudMant != null ? idAudMant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditoriaMantenimiento)) {
            return false;
        }
        AuditoriaMantenimiento other = (AuditoriaMantenimiento) object;
        if ((this.idAudMant == null && other.idAudMant != null) || (this.idAudMant != null && !this.idAudMant.equals(other.idAudMant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.AuditoriaMantenimiento[ idAudMant=" + idAudMant + " ]";
    }
    
}
