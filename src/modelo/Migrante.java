/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Alex Velez
 */
public class Migrante {
    private String nombre;
    private String pasaporte;
    private String nacionalidad;
    private String provOrigen;
    private String cantOrigen;

    public Migrante(String nombre, String pasaporte, String nacionalidad, String provOrigen, String cantOrigen) {
        this.nombre = nombre;
        this.pasaporte = pasaporte;
        this.nacionalidad = nacionalidad;
        this.provOrigen = provOrigen;
        this.cantOrigen = cantOrigen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
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
    
}
