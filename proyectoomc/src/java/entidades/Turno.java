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
 * @author Stephi
 */
@Entity
@Table(name = "turno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Turno.findAll", query = "SELECT t FROM Turno t")
    , @NamedQuery(name = "Turno.findByIdTurno", query = "SELECT t FROM Turno t WHERE t.idTurno = :idTurno")
    , @NamedQuery(name = "Turno.findByProgramacion", query = "SELECT t FROM Turno t WHERE t.programacion = :programacion")
    , @NamedQuery(name = "Turno.findByHoraIngreso", query = "SELECT t FROM Turno t WHERE t.horaIngreso = :horaIngreso")
    , @NamedQuery(name = "Turno.findByHorasSalida", query = "SELECT t FROM Turno t WHERE t.horasSalida = :horasSalida")
    , @NamedQuery(name = "Turno.findByFechaInicial", query = "SELECT t FROM Turno t WHERE t.fechaInicial = :fechaInicial")
    , @NamedQuery(name = "Turno.findByFechaFinal", query = "SELECT t FROM Turno t WHERE t.fechaFinal = :fechaFinal")
    , @NamedQuery(name = "Turno.findByObservaciones", query = "SELECT t FROM Turno t WHERE t.observaciones = :observaciones")})
public class Turno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_turno")
    private Integer idTurno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "programacion")
    private String programacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_ingreso")
    @Temporal(TemporalType.TIME)
    private Date horaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horas_salida")
    @Temporal(TemporalType.TIME)
    private Date horasSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "punto_id_puntos", referencedColumnName = "id_punto")
    @ManyToOne(optional = false)
    private Punto puntoIdPuntos;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne
    private Empleado idEmpleado;

    public Turno() {
    }

    public Turno(Integer idTurno) {
        this.idTurno = idTurno;
    }

    public Turno(Integer idTurno, String programacion, Date horaIngreso, Date horasSalida, Date fechaInicial, Date fechaFinal, String observaciones) {
        this.idTurno = idTurno;
        this.programacion = programacion;
        this.horaIngreso = horaIngreso;
        this.horasSalida = horasSalida;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.observaciones = observaciones;
    }

    public Integer getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Integer idTurno) {
        this.idTurno = idTurno;
    }

    public String getProgramacion() {
        return programacion;
    }

    public void setProgramacion(String programacion) {
        this.programacion = programacion;
    }

    public Date getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(Date horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public Date getHorasSalida() {
        return horasSalida;
    }

    public void setHorasSalida(Date horasSalida) {
        this.horasSalida = horasSalida;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Punto getPuntoIdPuntos() {
        return puntoIdPuntos;
    }

    public void setPuntoIdPuntos(Punto puntoIdPuntos) {
        this.puntoIdPuntos = puntoIdPuntos;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTurno != null ? idTurno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turno)) {
            return false;
        }
        Turno other = (Turno) object;
        if ((this.idTurno == null && other.idTurno != null) || (this.idTurno != null && !this.idTurno.equals(other.idTurno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Turno[ idTurno=" + idTurno + " ]";
    }
    
}
