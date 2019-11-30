/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Objects;

/**
 *
 * @author Alex Velez
 */
public class Migrante {
    private String nombre;
    private String identificacion;
    private String nacionalidad;
    private String provOrigen;
    private String cantOrigen;
    private TipoPersona tipoPersona;
    public Migrante(String nombre, String identificacion, String nacionalidad, String provOrigen, String cantOrigen,TipoPersona tipoPersona) {
        this.nombre = nombre;
        this.identificacion =identificacion;
        this.nacionalidad = nacionalidad;
        this.provOrigen = provOrigen;
        this.cantOrigen = cantOrigen;
        this.tipoPersona = tipoPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identifiacion) {
        this.identificacion = identifiacion;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getProvOrigen() {
        return provOrigen;
    }

    public void setProvOrigen(String provOrigen) {
        this.provOrigen = provOrigen;
    }

    public String getCantOrigen() {
        return cantOrigen;
    }

    public void setCantOrigen(String cantOrigen) {
        this.cantOrigen = cantOrigen;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.identificacion);
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
        final Migrante other = (Migrante) obj;
        if (!Objects.equals(this.identificacion, other.identificacion)) {
            return false;
        }
        return true;
    }
    
    
}
