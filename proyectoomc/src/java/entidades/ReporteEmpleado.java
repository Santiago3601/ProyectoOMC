/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Objects;

/**
 *
 * @author Stephi
 */
public class ReporteEmpleado {
    String nombre;
    String apellido;
    long telefono;
    long cedula;
    String tipocedula;

    public ReporteEmpleado() {
    }

    public ReporteEmpleado(String nombre, String apellido, long telefono, long cedula, String tipocedula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.cedula = cedula;
        this.tipocedula = tipocedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getTipocedula() {
        return tipocedula;
    }

    public void setTipocedula(String tipocedula) {
        this.tipocedula = tipocedula;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.nombre);
        hash = 23 * hash + Objects.hashCode(this.apellido);
        hash = 23 * hash + (int) (this.telefono ^ (this.telefono >>> 32));
        hash = 23 * hash + (int) (this.cedula ^ (this.cedula >>> 32));
        hash = 23 * hash + Objects.hashCode(this.tipocedula);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReporteEmpleado other = (ReporteEmpleado) obj;
        if (this.telefono != other.telefono) {
            return false;
        }
        if (this.cedula != other.cedula) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        if (!Objects.equals(this.tipocedula, other.tipocedula)) {
            return false;
        }
        return true;
    }
    
    
}
