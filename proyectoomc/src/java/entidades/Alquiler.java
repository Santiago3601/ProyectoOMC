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
import javax.persistence.CascadeType;
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
 * @author Aprendiz
 */
@Entity
@Table(name = "alquiler")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alquiler.findAll", query = "SELECT a FROM Alquiler a")
    , @NamedQuery(name = "Alquiler.findByIdAlquiler", query = "SELECT a FROM Alquiler a WHERE a.idAlquiler = :idAlquiler")
    , @NamedQuery(name = "Alquiler.findByFechaDeEntrega", query = "SELECT a FROM Alquiler a WHERE a.fechaDeEntrega = :fechaDeEntrega")
    , @NamedQuery(name = "Alquiler.findByNovedades", query = "SELECT a FROM Alquiler a WHERE a.novedades = :novedades")})
public class Alquiler implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_alquiler")
    private Integer idAlquiler;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_de_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaDeEntrega;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "novedades")
    private String novedades;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alquilerIdAlquiler", fetch = FetchType.LAZY)
    private List<Contrato> contratoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alquilerIdAlqu", fetch = FetchType.LAZY)
    private List<Agenda> agendaList;
    @JoinColumn(name = "cilindro_id_cilindro", referencedColumnName = "id_cilindro")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cilindro cilindroIdCilindro;
    @JoinColumn(name = "ruta_id_ruta", referencedColumnName = "id_ruta")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ruta rutaIdRuta;
    @JoinColumn(name = "solicitud_id_solicitud", referencedColumnName = "id_solicitud")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Solicitud solicitudIdSolicitud;
    @JoinColumn(name = "estado_alquiler_id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoAlquiler estadoAlquilerIdEstado;

    public Alquiler() {
    }

    public Alquiler(Integer idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public Alquiler(Integer idAlquiler, Date fechaDeEntrega, String novedades) {
        this.idAlquiler = idAlquiler;
        this.fechaDeEntrega = fechaDeEntrega;
        this.novedades = novedades;
    }

    public Integer getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(Integer idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public Date getFechaDeEntrega() {
        return fechaDeEntrega;
    }

    public void setFechaDeEntrega(Date fechaDeEntrega) {
        this.fechaDeEntrega = fechaDeEntrega;
    }

    public String getNovedades() {
        return novedades;
    }

    public void setNovedades(String novedades) {
        this.novedades = novedades;
    }

    @XmlTransient
    @JsonIgnore
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Agenda> getAgendaList() {
        return agendaList;
    }

    public void setAgendaList(List<Agenda> agendaList) {
        this.agendaList = agendaList;
    }

    public Cilindro getCilindroIdCilindro() {
        return cilindroIdCilindro;
    }

    public void setCilindroIdCilindro(Cilindro cilindroIdCilindro) {
        this.cilindroIdCilindro = cilindroIdCilindro;
    }

    public Ruta getRutaIdRuta() {
        return rutaIdRuta;
    }

    public void setRutaIdRuta(Ruta rutaIdRuta) {
        this.rutaIdRuta = rutaIdRuta;
    }

    public Solicitud getSolicitudIdSolicitud() {
        return solicitudIdSolicitud;
    }

    public void setSolicitudIdSolicitud(Solicitud solicitudIdSolicitud) {
        this.solicitudIdSolicitud = solicitudIdSolicitud;
    }

    public EstadoAlquiler getEstadoAlquilerIdEstado() {
        return estadoAlquilerIdEstado;
    }

    public void setEstadoAlquilerIdEstado(EstadoAlquiler estadoAlquilerIdEstado) {
        this.estadoAlquilerIdEstado = estadoAlquilerIdEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlquiler != null ? idAlquiler.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alquiler)) {
            return false;
        }
        Alquiler other = (Alquiler) object;
        if ((this.idAlquiler == null && other.idAlquiler != null) || (this.idAlquiler != null && !this.idAlquiler.equals(other.idAlquiler))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Alquiler[ idAlquiler=" + idAlquiler + " ]";
    }
    
}
