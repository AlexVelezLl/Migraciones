/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utilities.CONSTANTES;
import utilities.Controlador;

/**
 *
 * @author Alex Velez
 */
public class Main extends Application{

    @Override
    public void start(Stage primaryStage){
        VistaInicio vi= new VistaInicio();
        Pane root = vi.getRoot();
        Controlador.setScene(new Scene(root));
        primaryStage.setScene(Controlador.getScene());
        primaryStage.setTitle("Migraciones");
        primaryStage.setHeight(CONSTANTES.HEIGHT);
        primaryStage.setWidth(CONSTANTES.WIDTH);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(CONSTANTES.RUTA_IMGS+"ICO_01.png")); 
        primaryStage.show();
    }
    
    public static void main(String[]args){
        launch(args);
    }
}
