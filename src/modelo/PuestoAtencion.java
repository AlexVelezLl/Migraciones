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
    private Empleado empleado;
    private boolean disponible;
    private Ticket ticket;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    public PuestoAtencion(){
        disponible = true;
    }
    
    public PuestoAtencion(Empleado empleado){
        this.empleado = empleado;
        disponible = true;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado){
        this.empleado = empleado;
    }
    
    public boolean isDisponible() {
        return disponible;
    }
    
    public void setDisponible(boolean disponible){
        this.disponible = disponible;
    }
    
}
