/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Alex Velez
 */
public class Empleado implements Serializable{
    private String nombre;
    private String cedula;
    private final String genero;
    private PuestoAtencion puesto;
    public Empleado(String nombre, String cedula,String genero) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.genero=genero;
    }
    
    public PuestoAtencion getPuesto(){
        return puesto;
    }
    
    public void setPuesto(PuestoAtencion puesto){
        this.puesto = puesto;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getGenero(){
        return genero;
    }
    
    public String getCedula() {
        return cedula;
    }
    
    public void setCedula(String cedula){
        this.cedula = cedula;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(cedula);
        return hash;
    }
    
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Empleado)) return false;
        Empleado e = (Empleado) o;
        return e.cedula.equals(cedula);
    }
    
    public String toString(){
        return nombre+" ("+cedula+")";
    }
}
