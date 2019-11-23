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
public class Ticket {
    private String identificacion;
    private TipoPersona tipoPersona;
    private String turno;
    private int puesto;

    public Ticket(String identificacion, TipoPersona tipoPersona) {
        this.identificacion = identificacion;
        this.tipoPersona = tipoPersona;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setNombre(String identificacion) {
        this.identificacion = identificacion;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }
    
}
