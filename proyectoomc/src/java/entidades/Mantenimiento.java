/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "mantenimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mantenimiento.findAll", query = "SELECT m FROM Mantenimiento m")
    , @NamedQuery(name = "Mantenimiento.findByIdMantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.idMantenimiento = :idMantenimiento")
    , @NamedQuery(name = "Mantenimiento.findByFechaInicioMantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.fechaInicioMantenimiento = :fechaInicioMantenimiento")
    , @NamedQuery(name = "Mantenimiento.findByFechaFinalMantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.fechaFinalMantenimiento = :fechaFinalMantenimiento")
    , @NamedQuery(name = "Mantenimiento.findByTipoMantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.tipoMantenimiento = :tipoMantenimiento")})
public class Mantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mantenimiento")
    private Integer idMantenimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio_mantenimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioMantenimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_final_mantenimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaFinalMantenimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo_mantenimiento")
    private String tipoMantenimiento;
    @OneToMany(mappedBy = "idMantenimiento", fetch = FetchType.LAZY)
    private List<AuditoriaMantenimiento> auditoriaMantenimientoList;
    @JoinColumn(name = "cilindro_id_cilindro", referencedColumnName = "id_cilindro")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cilindro cilindroIdCilindro;
    @JoinColumn(name = "estado_mantenimiento", referencedColumnName = "id_estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoMantenimiento estadoMantenimiento;
    @JoinColumn(name = "agenda_id_agenda", referencedColumnName = "id_agenda")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Agenda agendaIdAgenda;

    public Mantenimiento() {
    }

    public Mantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public Mantenimiento(Integer idMantenimiento, Date fechaInicioMantenimiento, Date fechaFinalMantenimiento, String tipoMantenimiento) {
        this.idMantenimiento = idMantenimiento;
        this.fechaInicioMantenimiento = fechaInicioMantenimiento;
        this.fechaFinalMantenimiento = fechaFinalMantenimiento;
        this.tipoMantenimiento = tipoMantenimiento;
    }

    public Integer getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public Date getFechaInicioMantenimiento() {
        return fechaInicioMantenimiento;
    }

    public void setFechaInicioMantenimiento(Date fechaInicioMantenimiento) {
        this.fechaInicioMantenimiento = fechaInicioMantenimiento;
    }

    public Date getFechaFinalMantenimiento() {
        return fechaFinalMantenimiento;
    }

    public void setFechaFinalMantenimiento(Date fechaFinalMantenimiento) {
        this.fechaFinalMantenimiento = fechaFinalMantenimiento;
    }

    public String getTipoMantenimiento() {
        return tipoMantenimiento;
    }

    public void setTipoMantenimiento(String tipoMantenimiento) {
        this.tipoMantenimiento = tipoMantenimiento;
    }

    @XmlTransient
    @JsonIgnore
    public List<AuditoriaMantenimiento> getAuditoriaMantenimientoList() {
        return auditoriaMantenimientoList;
    }

    public void setAuditoriaMantenimientoList(List<AuditoriaMantenimiento> auditoriaMantenimientoList) {
        this.auditoriaMantenimientoList = auditoriaMantenimientoList;
    }

    public Cilindro getCilindroIdCilindro() {
        return cilindroIdCilindro;
    }

    public void setCilindroIdCilindro(Cilindro cilindroIdCilindro) {
        this.cilindroIdCilindro = cilindroIdCilindro;
    }

    public EstadoMantenimiento getEstadoMantenimiento() {
        return estadoMantenimiento;
    }

    public void setEstadoMantenimiento(EstadoMantenimiento estadoMantenimiento) {
        this.estadoMantenimiento = estadoMantenimiento;
    }

    public Agenda getAgendaIdAgenda() {
        return agendaIdAgenda;
    }

    public void setAgendaIdAgenda(Agenda agendaIdAgenda) {
        this.agendaIdAgenda = agendaIdAgenda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMantenimiento != null ? idMantenimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mantenimiento)) {
            return false;
        }
        Mantenimiento other = (Mantenimiento) object;
        if ((this.idMantenimiento == null && other.idMantenimiento != null) || (this.idMantenimiento != null && !this.idMantenimiento.equals(other.idMantenimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Mantenimiento[ idMantenimiento=" + idMantenimiento + " ]";
    }
    
}
