/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.util.PriorityQueue;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import utilities.Utilities;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import modelo.Empleado;
import modelo.Migraciones;
import modelo.Ticket;
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
        Label lbl = new Label("Bienvenido!");
        
        
        lbl.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 60));
        final String boton = "Button";
        StackPane btnTurno = Utilities.boton(boton);
        Label lblTurnos = new Label("Sistema de Turnos");
        
        lblTurnos.setFont(CONSTANTES.MYFONT);
        lblTurnos.setTextFill(Color.WHITE);
        btnTurno.getChildren().add(lblTurnos);      
        
        btnTurno.setOnMouseClicked(e->{
            Stage stTurnos = new Stage();
            VistaTurnos vt = new VistaTurnos();
            Scene scTurnos = new Scene(vt.getRoot());
            stTurnos.setScene(scTurnos);
            stTurnos.setWidth(1000);
            stTurnos.setHeight(600);
            stTurnos.setResizable(false);
            stTurnos.getIcons().add(new Image(CONSTANTES.RUTA_IMGS+"ICO_01.png"));
            stTurnos.setTitle("Sistema de turnos");
            stTurnos.show();  
            
        });
        
        StackPane btnVistaAtencion = Utilities.boton(boton);
        Label lblVistaAtencion = new  Label("Vista Atencion");
        lblVistaAtencion.setFont(CONSTANTES.MYFONT);
        lblVistaAtencion.setTextFill(Color.WHITE);
        btnVistaAtencion.getChildren().add(lblVistaAtencion);
        btnVistaAtencion.setOnMouseClicked(e->{
            //Hacer nuevo stage donde muestre si desea registrarse, o hacer una busqueda
        });
        
        StackPane btnVistaGerente = Utilities.boton(boton);
        Label lblGerente = new Label("Vista Gerente");
        lblGerente.setFont(CONSTANTES.MYFONT);
        lblGerente.setTextFill(Color.WHITE);
        btnVistaGerente.getChildren().add(lblGerente);      
        btnVistaGerente.setOnMouseClicked(e->{
            Stage stGerente = new Stage();
            VistaGerente vg = new VistaGerente();
            Scene scTurnos = new Scene(vg.getRoot());
            stGerente.setScene(scTurnos);
            stGerente.setWidth(750);
            stGerente.setHeight(450);
            stGerente.setResizable(false);
            stGerente.getIcons().add(new Image(CONSTANTES.RUTA_IMGS+"ICO_01.png"));
            stGerente.setTitle("Vista Gerente");
            stGerente.show();
        
        });
        
        //Vbox para mostrar opciones
        VBox vb = new VBox();
        vb.setLayoutY(25);
        vb.setAlignment(Pos.CENTER);
        vb.setMinWidth(CONSTANTES.WIDTH);
        vb.setSpacing(18);
        vb.getChildren().addAll(lbl,new Label(""),btnTurno,btnVistaAtencion,btnVistaGerente);
        
        //Botón salir
        StackPane btnSalir = Utilities.boton("ButtonRed");
        Label lblSalir = new Label("Salir");
        lblSalir.setFont(CONSTANTES.MYFONT);
        lblSalir.setTextFill(Color.WHITE);
        btnSalir.getChildren().add(lblSalir);
        btnSalir.setLayoutX(30);
        btnSalir.setLayoutY(480);
        btnSalir.setOnMouseClicked(e->{
            Platform.exit();
        });

        
        root.getChildren().addAll(bg,vb,btnSalir);
    }
    public Pane getRoot(){
        return root;
    }
    
}
