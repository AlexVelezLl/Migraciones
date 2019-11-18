/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import javafx.application.Platform;
import javafx.event.EventType;
import javafx.geometry.Pos;
import utilities.Utilities;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import utilities.CONSTANTES;


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
    
    private void createRoot(){
        ImageView bg = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"BackGround.png"));
        /*bg.setFitHeight(CONSTANTES.HEIGHT);
        bg.setFitWidth(CONSTANTES.WIDTH);*/
        Label lbl = new Label("Migraciones");
        
        
        lbl.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 80));
        
        StackPane btnTurno = Utilities.boton("Button");
        Label lblTurnos = new Label("Sistema de Turnos");
        Font myFont = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 25);
        lblTurnos.setFont(myFont);
        lblTurnos.setTextFill(Color.WHITE);
        btnTurno.getChildren().add(lblTurnos);      
        
        
        btnTurno.setOnMouseClicked(e->{
            VistaTurnos vt = new VistaTurnos();
            Utilities.transition(root, vt.getRoot());
        });
        final String boton = "Button";
        StackPane btnRegistro = Utilities.boton(boton);
        Label lblRegistro = new Label("Registro Migratorio");
        lblRegistro.setFont(myFont);
        lblRegistro.setTextFill(Color.WHITE);
        btnRegistro.getChildren().add(lblRegistro);
        btnRegistro.setOnMouseClicked(e->{
            VistaRegistro vr = new VistaRegistro();
            Utilities.transition(root, vr.getRoot());
        });
        
        StackPane btnBusqueda = Utilities.boton(boton);
        Label lblBusqueda = new Label("Busqueda de Migrantes"); 
        lblBusqueda.setFont(myFont);
        lblBusqueda.setTextFill(Color.WHITE);
        btnBusqueda.getChildren().add(lblBusqueda);
        btnBusqueda.setOnMouseClicked(e->{
            VistaBusqueda vb = new VistaBusqueda();
            Utilities.transition(root, vb.getRoot());
        });
        
        StackPane btnVisualizacion = Utilities.boton(boton);
        Label lblVis = new Label("Visualizaciones");
        lblVis.setFont(myFont);
        lblVis.setTextFill(Color.WHITE);
        btnVisualizacion.getChildren().add(lblVis);
        btnVisualizacion.setOnMouseClicked(e->{
            VistaVisualizaciones vv = new VistaVisualizaciones();
            Utilities.transition(root, vv.getRoot());
        });
        VBox vb = new VBox();
        vb.setLayoutY(30);
        vb.setAlignment(Pos.CENTER);
        vb.setMinWidth(CONSTANTES.WIDTH);
        vb.setSpacing(18);
        HBox hbB1 = new HBox();
        hbB1.setSpacing(30);
        hbB1.setAlignment(Pos.CENTER);
        hbB1.getChildren().addAll(btnTurno,btnRegistro);
        HBox hbB2 = new HBox();
        hbB2.setSpacing(30);
        hbB2.getChildren().addAll(btnBusqueda,btnVisualizacion);
        hbB2.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(lbl,new Label(""),hbB1,hbB2);
        
        
        StackPane btnSalir = Utilities.boton("ButtonRed");
        Label lblSalir = new Label("Salir");
        lblSalir.setFont(myFont);
        lblSalir.setTextFill(Color.WHITE);
        btnSalir.getChildren().add(lblSalir);
        btnSalir.setLayoutX(60);
        btnSalir.setLayoutY(440);
        btnSalir.setOnMouseClicked(e->{
            Platform.exit();
        });
        /*TextField tx = new TextField();
        tx.setLayoutX(300);
        tx.setLayoutY(440);
        tx.focusedProperty().addListener((arg0,oldValue,newValue)->{
            System.out.println(arg0);
            if(!newValue){
                if(tx.getText().equals("Hola")){
                    System.out.println("Es un saludo! ");
                }
            }
        });*/
        
        
        root.getChildren().addAll(bg,vb,btnSalir);
    }
    public Pane getRoot(){
        return root;
    }
    
}
