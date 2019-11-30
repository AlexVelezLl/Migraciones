/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
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
public class Migraciones implements Serializable{
    
    /**
     * Cola de imágenes para publicidad
     */
    private final CircularLinkedList<String> publicidades;
    
    /**
     * Almacena y ordena según la prioridad de los tickets
     */
    public PriorityQueue<Ticket> colaAtencion;
    public List<Registro> registros;
    private final Set<Migrante> migrantes;
    private final List<PuestoAtencion> puestosAtencion;
    private final Set<Empleado> empleados;
    
    /**
     * Cuenta la cantidad de personas con capacidad especial
     */
    private int contCapEsp;
    private int contTerEd;
    private int contNorm;
    
    public Migraciones(){
        
        class colaSort implements Comparator<Ticket>, Serializable{

            @Override
            public int compare(Ticket o1, Ticket o2) {
                return o1.getTipoPersona().ordinal()-o2.getTipoPersona().ordinal();
            }
            
        }
        publicidades = new CircularLinkedList<>();
        colaAtencion = new PriorityQueue<>(new colaSort());
        registros = new ArrayList<>();
        puestosAtencion = new ArrayList<>();
        empleados = new HashSet<>();
        migrantes = new HashSet<>();
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
    
    
    public CircularLinkedList<String> getPublicidades() {
        return publicidades;
    }
    
    public Set<Migrante> getMigrantes(){
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
                publicidades.addLast(CONSTANTES.RUTA_IMGS+line);
        
        }catch(Exception e){
            Logger.getLogger(Migraciones.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
