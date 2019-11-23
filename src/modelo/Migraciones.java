/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import utilities.CONSTANTES;
import utilities.CircularLinkedList;



/**
 *
 * @author Alex Velez
 */
public class Migraciones {
    private final CircularLinkedList<Image> publicidades;
    private final PriorityQueue<Ticket> colaAtencion;
    private final List<Registro> registros;
    private final List<Migrante> migrantes;
    private final List<PuestoAtencion> puestosAtencion;
    private final Set<Empleado> empleados;
    private int contCapEsp;
    private int contTerEd;
    private int contNorm;
    
    public Migraciones(){
        publicidades = new CircularLinkedList<>();
        colaAtencion = new PriorityQueue<>((Ticket t1,Ticket t2)->t1.getTipoPersona().ordinal()-t2.getTipoPersona().ordinal());
        registros = new ArrayList<>();
        puestosAtencion = new ArrayList<>();
        empleados = new HashSet<>();
        migrantes = new ArrayList<>();
        contCapEsp = 0;
        contTerEd = 0;
        contNorm = 0;
    }

    public int getContCapEsp() {
        return contCapEsp;
    }

    public void setContCapEsp(int contCapEsp) {
        this.contCapEsp = contCapEsp;
    }

    public int getContTerEd() {
        return contTerEd;
    }

    public void setContTerEd(int contTerEd) {
        this.contTerEd = contTerEd;
    }

    public int getContNorm() {
        return contNorm;
    }

    public void setContNorm(int contNorm) {
        this.contNorm = contNorm;
    }
    
    
    public CircularLinkedList<Image> getPublicidades() {
        return publicidades;
    }
    
    public List<Migrante> getMigrantes(){
        return migrantes;
    }
    
    public Set<Empleado> getEmpleados(){
        return empleados;
    }
    
    public PriorityQueue<Ticket> getColaAtencion() {
        return colaAtencion;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public List<PuestoAtencion> getPuestosAtencion() {
        return puestosAtencion;
    }
    
    public void cargarPublicidad(){
        try(BufferedReader bf = new BufferedReader(new FileReader(CONSTANTES.RUTA_TEXT+"publicidades.txt"))){
            String line;
            while((line = bf.readLine())!=null)
                publicidades.addLast(new Image(CONSTANTES.RUTA_IMGS+line));
        
        }catch(Exception e){
            Logger.getLogger(Migraciones.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
