/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import modelo.PuestoAtencion;
import utilities.CONSTANTES;
import utilities.Utilities;

/**
 *
 * @author dylan
 */
public class VistaAtencion {

    private final Pane root;
    private final String nomBot = "Button";
    private final String icono = "ICO_01.png";
    public final String redButton = "ButtonRed";
    private Pane pnEmp;
    private Pane pnPuest;

    public VistaAtencion() {
        root = new Pane();
        createRoot();
    }

    public Pane getRoot() {
        return root;
    }

    private void createRoot() {
        Label lblBienvenida = new Label("Bienvenido!");
        lblBienvenida.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 60));
        ComboBox<PuestoAtencion> ticketscombo = new ComboBox<>(FXCollections.observableList(Main.getMigraciones().getPuestosAtencion()));
        StackPane btnregistro = Utilities.boton(nomBot);
        Label lblregistro = new Label("Registrar");
        lblregistro.setFont(CONSTANTES.MYFONT);
        lblregistro.setTextFill(Color.WHITE);
        btnregistro.getChildren().add(lblregistro);
        btnregistro.setOnMouseClicked(e -> {
            if (ticketscombo.getValue() != null) {
                Stage stGerente = new Stage();
                VistaRegistro vb = new VistaRegistro(ticketscombo.getValue().getTicket());
                Scene scTurnos = new Scene(vb.getRoot());
                stGerente.setScene(scTurnos);
                stGerente.setWidth(450);
                stGerente.setHeight(800);
                stGerente.setResizable(false);
                stGerente.getIcons().add(new Image(CONSTANTES.RUTA_IMGS+"ICO_01.png"));
                stGerente.setTitle("Vista Registro");
                stGerente.show();
                ticketscombo.getValue().setTicket(null);

            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Puesto no seleccionado");
                alerta.setContentText("Seleccione su puesto por favor.");
                alerta.showAndWait();
            }

        });
        StackPane btnPuestos = Utilities.boton(nomBot);
        Label lblPuestos = new Label("Buscar");
        lblPuestos.setFont(CONSTANTES.MYFONT);
        lblPuestos.setTextFill(Color.WHITE);
        btnPuestos.getChildren().add(lblPuestos);
        btnPuestos.setOnMouseClicked(e -> {
            Stage stGerente = new Stage();
            VistaBusqueda vg = new VistaBusqueda();
            Scene scTurnos = new Scene(vg.getRoot());
            stGerente.setScene(scTurnos);
            stGerente.setWidth(750);
            stGerente.setHeight(450);
            stGerente.setResizable(false);
            stGerente.getIcons().add(new Image(CONSTANTES.RUTA_IMGS + "ICO_01.png"));
            stGerente.setTitle("Vista Busqueda");
            stGerente.show();

        });

        StackPane btnSalir = Utilities.boton(redButton);
        Label lblSalir = new Label("Salir");
        lblSalir.setFont(CONSTANTES.MYFONT);
        lblSalir.setTextFill(Color.WHITE);
        btnSalir.getChildren().add(lblSalir);
        btnSalir.setLayoutX(30);
        btnSalir.setLayoutY(340);
        btnSalir.setOnMouseClicked(e -> {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        });

        VBox onRoot = new VBox();
        onRoot.setMinWidth(750);
        HBox hbButtons = new HBox();
        hbButtons.setSpacing(20);
        hbButtons.setAlignment(Pos.CENTER);
        onRoot.setSpacing(20);
        onRoot.setAlignment(Pos.CENTER);
        hbButtons.getChildren().addAll(btnPuestos, btnregistro);
        onRoot.getChildren().addAll(lblBienvenida, ticketscombo, hbButtons);

        Rectangle rc = new Rectangle(750, 450);
        rc.setFill(Color.WHITE);
        root.getChildren().addAll(rc, onRoot, btnSalir);
    }
}
