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
public class PuestoAtencion {
    private String nomEncargado;
    private String cedEncargado;
    private boolean disponible;
    
    public PuestoAtencion(){
        
    }
    
    public PuestoAtencion(String nomEncargado, String cedEncargado) {
        this.nomEncargado = nomEncargado;
        this.cedEncargado = cedEncargado;
        disponible = true;
    }

    public String getNomEncargado() {
        return nomEncargado;
    }

    public String getCedEncargado() {
        return cedEncargado;
    }

    public boolean isDisponible() {
        return disponible;
    }
    
    public void setEmpleado(String nomEncargado, String cedEncargado){
        this.nomEncargado = nomEncargado;
        this.cedEncargado = cedEncargado;
        disponible = true;
    }
    
    public void quitarEmpleado(){
        nomEncargado = null;
        cedEncargado = null;
        disponible = false;
    }
    
}
