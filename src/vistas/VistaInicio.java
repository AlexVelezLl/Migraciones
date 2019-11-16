/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import utilities.Utilities;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


/**
 *
 * @author AlexVelez
 */
public class VistaInicio{
    private Pane root;
    public VistaInicio(){
        root = new Pane();
        createRoot();
    }
    
    public void createRoot(){
        Label lbl = new Label("Bienvenido!");
        lbl.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 40));
        Button btnTurnos = new Button("Sistema de Turnos");
        btnTurnos.setOnMouseClicked(e->{
            VistaTurnos vt = new VistaTurnos();
            Utilities.transition(root, vt.getRoot());
        });
        
        Button btnRegistro = new Button("Registrar de Migrante");
        btnRegistro.setOnMouseClicked(e->{
            System.out.println("Hola");
            VistaRegistro vr = new VistaRegistro();
            Utilities.transition(root, vr.getRoot());
            System.out.println("Hola2");
        });
        
        Button btnBusqueda = new Button("Busqueda de migrantes");
        btnBusqueda.setOnMouseClicked(e->{
            VistaBusqueda vb = new VistaBusqueda();
            Utilities.transition(root, vb.getRoot());
        });
        
        Button btnVisualizacion = new Button("Visualizacion");
        btnVisualizacion.setOnMouseClicked(e->{
            VistaVisualizaciones vv = new VistaVisualizaciones();
            Utilities.transition(root, vv.getRoot());
        });
        VBox vb = new VBox();
        vb.getChildren().addAll(lbl,btnTurnos,btnRegistro,btnBusqueda,btnVisualizacion);
        root.getChildren().add(vb);
        
    }
    public Pane getRoot(){
        return root;
    }
    
}
