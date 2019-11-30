/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.PriorityQueue;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Empleado;
import modelo.Migraciones;
import modelo.PuestoAtencion;
import modelo.Registro;
import modelo.Ticket;
import utilities.CONSTANTES;

/**
 *
 * @author Alex Velez
 */
public class Main extends Application{
    private static Migraciones MIGRAC; 
    @Override
    public void start(Stage primaryStage){
        
        MIGRAC = new Migraciones();
        
        try(ObjectInputStream oos = new ObjectInputStream(new FileInputStream("src/data/src.dat"))){
            MIGRAC = (Migraciones) oos.readObject();
        }catch(Exception ex){
            
            
        }
        VistaInicio vi= new VistaInicio();
        Pane root = vi.getRoot();
        Scene sc = new Scene(root);
        
        primaryStage.setScene(sc);
        primaryStage.setTitle("Migraciones");
        primaryStage.setHeight(CONSTANTES.HEIGHT);
        primaryStage.setWidth(CONSTANTES.WIDTH);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(CONSTANTES.RUTA_IMGS+"ICO_01.png")); 
        primaryStage.setOnCloseRequest(e-> Platform.exit());
        primaryStage.show();
    }
    
    public static void main(String[]args){
        launch(args);
    }
    
    @Override
    public void stop(){
        VistaTurnos.setActivo(false);//Cerrando hilos
        
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/data/src.dat"))){
            oos.writeObject(MIGRAC);
        }catch(IOException ex){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("El programa se cerro de una forma inseperada, por favor informar a los administradores acerca de "
                    + "este error. \n\nCodigo de error: "+ex);
            alerta.setTitle("ERROR!");
            alerta.showAndWait();
        }
    }
    
    public static Migraciones getMigraciones(){
        return MIGRAC;
    }
}
