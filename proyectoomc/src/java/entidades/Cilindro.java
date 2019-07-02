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
import javax.persistence.Id;
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

/**
 *
 * @author Aprendiz
 */
@Entity
@Table(name = "cilindro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cilindro.findAll", query = "SELECT c FROM Cilindro c")
    , @NamedQuery(name = "Cilindro.findByIdCilindro", query = "SELECT c FROM Cilindro c WHERE c.idCilindro = :idCilindro")
    , @NamedQuery(name = "Cilindro.findByEstado", query = "SELECT c FROM Cilindro c WHERE c.estado = :estado")
    , @NamedQuery(name = "Cilindro.findByTamanio", query = "SELECT c FROM Cilindro c WHERE c.tamanio = :tamanio")
    , @NamedQuery(name = "Cilindro.findByLote", query = "SELECT c FROM Cilindro c WHERE c.lote = :lote")
    , @NamedQuery(name = "Cilindro.findByFechaDeCreacion", query = "SELECT c FROM Cilindro c WHERE c.fechaDeCreacion = :fechaDeCreacion")})
public class Cilindro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cilindro")
    private Integer idCilindro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tamanio")
    private int tamanio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lote")
    private int lote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_de_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaDeCreacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cilindroIdCilindro", fetch = FetchType.LAZY)
    private List<Alquiler> alquilerList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cilindroIdCilindro", fetch = FetchType.LAZY)
    private List<MantenimientoCilindro> mantenimientoCilindroList;

    public Cilindro() {
    }

    public Cilindro(Integer idCilindro) {
        this.idCilindro = idCilindro;
    }

    public Cilindro(Integer idCilindro, String estado, int tamanio, int lote, Date fechaDeCreacion) {
        this.idCilindro = idCilindro;
        this.estado = estado;
        this.tamanio = tamanio;
        this.lote = lote;
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public Integer getIdCilindro() {
        return idCilindro;
    }

    public void setIdCilindro(Integer idCilindro) {
        this.idCilindro = idCilindro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getLote() {
        return lote;
    }

    public void setLote(int lote) {
        this.lote = lote;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    @XmlTransient
    public List<Alquiler> getAlquilerList() {
        return alquilerList;
    }

    public void setAlquilerList(List<Alquiler> alquilerList) {
        this.alquilerList = alquilerList;
    }

    @XmlTransient
    public List<MantenimientoCilindro> getMantenimientoCilindroList() {
        return mantenimientoCilindroList;
    }

    public void setMantenimientoCilindroList(List<MantenimientoCilindro> mantenimientoCilindroList) {
        this.mantenimientoCilindroList = mantenimientoCilindroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCilindro != null ? idCilindro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cilindro)) {
            return false;
        }
        Cilindro other = (Cilindro) object;
        if ((this.idCilindro == null && other.idCilindro != null) || (this.idCilindro != null && !this.idCilindro.equals(other.idCilindro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cilindro[ idCilindro=" + idCilindro + " ]";
    }
    
}
