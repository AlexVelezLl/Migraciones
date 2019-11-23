/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author Alex Velez
 */
public class Registro {
    private Migrante migrante;
    private String tipoMov;
    private LocalDate fecha;
    private String paisProced;
    private String ciudadProced;
    private String viaTransporte;
    private String paisDestino;
    private String ciudadDestino;
    private String motiMov;

    public Registro(Migrante migrante, String tipoMov, String paisProced, String viaTransporte, String paisDestino, String ciudadDestino, String motiMov) {
        this.migrante = migrante;
        this.tipoMov = tipoMov;
        this.paisProced = paisProced;
        this.viaTransporte = viaTransporte;
        this.paisDestino = paisDestino;
        this.ciudadDestino = ciudadDestino;
        this.motiMov = motiMov;
        fecha = LocalDate.now();
    }

    public Migrante getMigrante() {
        return migrante;
    }

    public void setMigrante(Migrante migrante) {
        this.migrante = migrante;
    }

    public String getTipoMov() {
        return tipoMov;
    }

    public void setTipoMov(String tipoMov) {
        this.tipoMov = tipoMov;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getPaisProced() {
        return paisProced;
    }

    public void setPaisProced(String paisProced) {
        this.paisProced = paisProced;
    }

    public String getCiudadProced() {
        return ciudadProced;
    }

    public void setCiudadProced(String ciudadProced) {
        this.ciudadProced = ciudadProced;
    }

    public String getViaTransporte() {
        return viaTransporte;
    }

    public void setViaTransporte(String viaTransporte) {
        this.viaTransporte = viaTransporte;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public String getMotiMov() {
        return motiMov;
    }

    public void setMotiMov(String motiMov) {
        this.motiMov = motiMov;
    }
    
}
