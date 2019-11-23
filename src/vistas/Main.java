/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Empleado;
import modelo.Migraciones;
import modelo.PuestoAtencion;
import utilities.CONSTANTES;

/**
 *
 * @author Alex Velez
 */
public class Main extends Application{
    private static final Migraciones MIGRAC = new Migraciones(); 
    @Override
    public void start(Stage primaryStage){
        Empleado e1 = new Empleado("Juan Perez","0979050609","Hombre");
        Empleado e2 = new Empleado("Rosita Rosas","0979050610","Mujer");
        PuestoAtencion p1 = new PuestoAtencion(e1);
        PuestoAtencion p2 = new PuestoAtencion(e2);
        e1.setPuesto(p1);
        e2.setPuesto(p2);
        MIGRAC.getEmpleados().add(e1);
        MIGRAC.getEmpleados().add(e2);
        MIGRAC.getPuestosAtencion().add(p1);
        MIGRAC.getPuestosAtencion().add(p2);
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
        VistaTurnos.setActivo(false);
    }
    
    public static Migraciones getMigraciones(){
        return MIGRAC;
    }
}
