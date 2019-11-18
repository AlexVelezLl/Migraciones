/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
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
    private final List<PuestoAtencion> puestosAtencion;
    public Migraciones(){
        publicidades = new CircularLinkedList<>();
        colaAtencion = new PriorityQueue<>();
        registros = new ArrayList<>();
        puestosAtencion = new ArrayList<>();
    }
    public CircularLinkedList<Image> getPublicidades() {
        return publicidades;
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
            String line = null;
            while((line = bf.readLine())!=null)
                publicidades.addLast(new Image(CONSTANTES.RUTA_IMGS+line));
        
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
