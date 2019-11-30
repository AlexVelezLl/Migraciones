/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.util.LinkedList;
import java.util.ListIterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
import modelo.Registro;
import utilities.CONSTANTES;
import utilities.Utilities;

/**
 *
 * @author Alex Velez
 */
public class VistaBusqueda {

    private final Pane root;
    private final String nomBot = "Button";
    private final String icono = "ICO_01.png";
    public final String redButton = "ButtonRed";
    private Pane pnEmp;
    private Pane pnPuest;

    DatePicker fecha = new DatePicker();
    DatePicker fecha2 = new DatePicker();
    TextField provorigen = new TextField();
    TextField cantonorigen = new TextField();
    TextField paisdestino = new TextField();
    TextField ciudaddestino = new TextField();

    public VistaBusqueda() {
        root = new Pane();
        createRoot();
    }

    public Pane getRoot() {
        return root;
    }

    public void createRoot() {
        Label busqueda = new Label("Busqueda");
        busqueda.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 60));
        Label labelcons = new Label("Ingrese los datos a consultar:");
        labelcons.setFont(CONSTANTES.MYFONT);
        StackPane buscar = utilities.Utilities.boton(nomBot);
        Label buscarlabel = new Label("Buscar");
        buscarlabel.setFont(CONSTANTES.MYFONT);
        buscarlabel.setTextFill(Color.WHITE);
        buscar.getChildren().add(buscarlabel);

        Label provorigenl = new Label("Provincia Origen:");
        Label cantonorigenl = new Label("Canton Origen:");
        Label fechal1 = new Label("Desde:");
        Label fechal2 = new Label("Hasta:");
        Label paisdestinol = new Label("Pais Destino:");
        Label ciudaddestinol = new Label("Ciudad Destino:");
        VBox vbright = new VBox();
        VBox vbleft = new VBox();
        vbright.getChildren().addAll(fechal2, fecha2, provorigenl, provorigen, cantonorigenl, cantonorigen);
        vbleft.getChildren().addAll(fechal1, fecha, paisdestinol, paisdestino, ciudaddestinol, ciudaddestino);
        vbright.setAlignment(Pos.CENTER);
        vbleft.setAlignment(Pos.CENTER);
        HBox rooty = new HBox();
        rooty.getChildren().addAll(vbleft, vbright);
        rooty.setSpacing(10);
        rooty.setLayoutX(200);
        rooty.setLayoutY(40);

        buscar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if (fecha.getValue() == null && fecha2.getValue() == null && provorigen.getText().equals("")&& cantonorigen.getText().equals("")
                        && paisdestino.getText().equals("")&& ciudaddestino.getText().equals("")){
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Casilleros en Blanco");
                    alerta.setContentText("Llene al menos uno de los campos");
                    alerta.showAndWait();
                } else {
                    Stage stGerente = new Stage();
                    Scene scTurnos = new Scene(createBusqueda());
                    stGerente.setScene(scTurnos);
                    stGerente.setWidth(450);
                    stGerente.setHeight(800);
                    stGerente.setResizable(false);
                    stGerente.getIcons().add(new Image(CONSTANTES.RUTA_IMGS + "ICO_01.png"));
                    stGerente.setTitle("Vista Registro");
                    stGerente.show();
                }
            }
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
        hbButtons.getChildren().addAll(buscar);
        onRoot.getChildren().addAll(busqueda, labelcons, rooty, buscar);
        Rectangle rc = new Rectangle(750, 450);
        rc.setFill(Color.WHITE);
        root.getChildren().addAll(rc, onRoot, btnSalir);
    }

    public Pane createBusqueda() {
        ListIterator<Registro> lit = Main.getMigraciones().getRegistros().listIterator();
        LinkedList<Registro> link = new LinkedList<>();
        while (lit.hasNext()) {
            Registro reg = lit.next();
            if ((fecha.getValue() == null || reg.getFecha().isAfter(fecha.getValue()))
                    && (fecha2.getValue() == null || reg.getFecha().isBefore(fecha2.getValue())) && (provorigen.getText().equals("") || provorigen.getText().equals(reg.getMigrante().getProvOrigen()))
                    && (cantonorigen.getText().equals("") || cantonorigen.getText().equals(reg.getMigrante().getCantOrigen())) && (paisdestino.getText().equals("") || paisdestino.getText().equals(reg.getPaisDestino()))
                    && (ciudaddestino.getText().equals("") || ciudaddestino.getText().equals(reg.getCiudadDestino()))) {
                link.add(reg);
                System.out.println("true");
                System.out.println(reg);
            }
        }
        TableColumn column = new TableColumn("Registro");
        column.setCellValueFactory(new PropertyValueFactory<Registro, String>("CiudadDestino"));


      
        TableView<Registro> comb = new TableView<>();
          comb.getColumns().add(column);
          comb.setItems(FXCollections.observableList(link));
        VBox onroot = new VBox();
        onroot.getChildren().add(comb);
        Pane pane = new Pane();
        pane.getChildren().addAll(onroot);

        return pane;
    }
}
