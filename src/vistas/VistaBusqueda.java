/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.util.LinkedList;
import java.util.ListIterator;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.image.ImageView;
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
import modelo.Migrante;
import modelo.PuestoAtencion;
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
                if (fecha.getValue() == null && fecha2.getValue() == null && provorigen.getText().equals("") && cantonorigen.getText().equals("")
                        && paisdestino.getText().equals("") && ciudaddestino.getText().equals("")) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Casilleros en Blanco");
                    alerta.setContentText("Llene al menos uno de los campos");
                    alerta.showAndWait();
                } else {
                    Stage stGerente = new Stage();
                    Scene scTurnos = new Scene(createBusqueda());
                    stGerente.setScene(scTurnos);
                    stGerente.setWidth(415);
                    stGerente.setHeight(600);
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
        onRoot.setLayoutX(20);
        onRoot.setAlignment(Pos.CENTER);
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
            }
        }
        TableColumn column = new TableColumn("Migrante");
        column.setCellValueFactory(new PropertyValueFactory<Registro, String>("migrante"));
        TableColumn column2 = new TableColumn("Fecha");
        column2.setCellValueFactory(new PropertyValueFactory<Registro, String>("fecha"));
        TableColumn column3 = new TableColumn("CantonOrigen");
        column.setCellValueFactory(new PropertyValueFactory<Registro, String>("paisProced"));
        TableColumn column5 = new TableColumn("PaisDestino");
        column.setCellValueFactory(new PropertyValueFactory<Registro, String>("paisDestino"));
        TableColumn column6 = new TableColumn("CiudadDestino");
        column.setCellValueFactory(new PropertyValueFactory<Registro, String>("ciudadDestino"));
        
        TableView<Registro> comb = new TableView<>();
        comb.getColumns().addAll(column, column2, column3, column5, column6);
        comb.setItems(FXCollections.observableList(link));
        VBox onroot = new VBox();
        onroot.getChildren().add(comb);
        Pane pane = new Pane();
        comb.setLayoutX(9);
        
        StackPane btnSalir = Utilities.boton(redButton);
        Label lblSalir = new Label("Salir");
        lblSalir.setFont(CONSTANTES.MYFONT);
        lblSalir.setTextFill(Color.WHITE);
        btnSalir.getChildren().add(lblSalir);
        btnSalir.setLayoutX(9);
        btnSalir.setLayoutY(500);
        btnSalir.setOnMouseClicked(e -> {
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
        });
        StackPane modificarbtn = utilities.Utilities.boton(nomBot);
        Label buscarlabel = new Label("Modificar");
        buscarlabel.setFont(CONSTANTES.MYFONT);
        buscarlabel.setTextFill(Color.WHITE);
        modificarbtn.getChildren().add(buscarlabel);
        ImageView modificar = (ImageView) modificarbtn.getChildren().get(0);
        modificar.setFitHeight(40);
        modificar.setFitWidth(120);
        StackPane buscar = utilities.Utilities.boton(nomBot);
        Label buscarlabel1 = new Label("Eliminar");
        buscarlabel1.setFont(CONSTANTES.MYFONT);
        buscarlabel1.setTextFill(Color.WHITE);
        buscar.getChildren().add(buscarlabel1);
        ImageView eliminar = (ImageView) buscar.getChildren().get(0);
        eliminar.setFitHeight(40);
        eliminar.setFitWidth(120);
        
        HBox btns = new HBox();
        btns.getChildren().addAll(modificarbtn, buscar);
        btns.setSpacing(2);
        btns.setLayoutX(9);
        btns.setLayoutY(430);
        
        pane.getChildren().addAll(onroot, btnSalir, btns);
        
        modificarbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if (comb.getSelectionModel() == null) {
                    
                } else {
                    Registro reg = comb.getItems().get(comb.getSelectionModel().getSelectedIndex());
                    Stage stGerente = new Stage();
                    Scene scTurnos = new Scene(rootmodificar(reg));
                    stGerente.setScene(scTurnos);
                    stGerente.setWidth(450);
                    stGerente.setHeight(700);
                    stGerente.setResizable(false);
                    stGerente.getIcons().add(new Image(CONSTANTES.RUTA_IMGS + "ICO_01.png"));
                    stGerente.setTitle("Vista Modificar");
                    stGerente.show();
                    Thread th = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    ListIterator<Registro> lit = Main.getMigraciones().getRegistros().listIterator();
                                    LinkedList<Registro> link = new LinkedList<>();
                                    while (lit.hasNext()) {
                                        Registro reg = lit.next();
                                        if ((fecha.getValue() == null || reg.getFecha().isAfter(fecha.getValue()))
                                                && (fecha2.getValue() == null || reg.getFecha().isBefore(fecha2.getValue())) && (provorigen.getText().equals("") || provorigen.getText().equals(reg.getMigrante().getProvOrigen()))
                                                && (cantonorigen.getText().equals("") || cantonorigen.getText().equals(reg.getMigrante().getCantOrigen())) && (paisdestino.getText().equals("") || paisdestino.getText().equals(reg.getPaisDestino()))
                                                && (ciudaddestino.getText().equals("") || ciudaddestino.getText().equals(reg.getCiudadDestino()))) {
                                            link.add(reg);
                                        }
                                    }
                                    comb.getItems().clear();
                                    comb.setItems(FXCollections.observableList(link));
                                }
                            });
                        }
                    });
                    th.start();
                    
                }
            }
        });
        buscar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if (comb.getSelectionModel() == null) {
                } else {
                    Registro reg = comb.getItems().get(comb.getSelectionModel().getSelectedIndex());
                    Main.getMigraciones().getRegistros().remove(comb.getItems().get(comb.getSelectionModel().getSelectedIndex()));
                    Thread th = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    ListIterator<Registro> lit = Main.getMigraciones().getRegistros().listIterator();
                                    LinkedList<Registro> link = new LinkedList<>();
                                    while (lit.hasNext()) {
                                        Registro reg = lit.next();
                                        if ((fecha.getValue() == null || reg.getFecha().isAfter(fecha.getValue()))
                                                && (fecha2.getValue() == null || reg.getFecha().isBefore(fecha2.getValue())) && (provorigen.getText().equals("") || provorigen.getText().equals(reg.getMigrante().getProvOrigen()))
                                                && (cantonorigen.getText().equals("") || cantonorigen.getText().equals(reg.getMigrante().getCantOrigen())) && (paisdestino.getText().equals("") || paisdestino.getText().equals(reg.getPaisDestino()))
                                                && (ciudaddestino.getText().equals("") || ciudaddestino.getText().equals(reg.getCiudadDestino()))) {
                                            link.add(reg);
                                        }
                                    }
                                    comb.getItems().clear();
                                    comb.setItems(FXCollections.observableList(link));
                                }
                            });
                        }
                    });
                    th.start();
                }
            }
        });
        
        return pane;
    }
    
    private Pane rootmodificar(Registro r) {
        Pane root1 = new Pane();
        TextField nombre = new TextField();
        TextField pasaporte = new TextField();
        TextField nacionalidad = new TextField();
        TextField provorigen = new TextField();
        TextField cantonorigen = new TextField();
        ComboBox tipomov = new ComboBox(FXCollections.observableArrayList("Entrada", "Salida"));
        TextField paisproced = new TextField();
        TextField viatransporte = new TextField();
        paisdestino = new TextField();
        TextField ciudaddestino = new TextField();
        TextField motivoMov = new TextField();
        Label nombrel = new Label("Nombre:");
        nombrel.setFont(CONSTANTES.MYFONT);
        Label pasaportel = new Label("Identificacion:");
        pasaportel.setFont(CONSTANTES.MYFONT);
        Label nacionalidadl = new Label("Nacionalidad:");
        nacionalidadl.setFont(CONSTANTES.MYFONT);
        Label provorigenl = new Label("Provincia Origen:");
        provorigenl.setFont(CONSTANTES.MYFONT);
        Label cantonorigenl = new Label("Canton Origen:");
        cantonorigenl.setFont(CONSTANTES.MYFONT);
        Label tipomovl = new Label("Tipo movimiento:");
        tipomovl.setFont(CONSTANTES.MYFONT);
        Label paisprocedl = new Label("Pais Procedencia:");
        paisprocedl.setFont(CONSTANTES.MYFONT);
        Label viatransportel = new Label("Via transporte:");
        viatransportel.setFont(CONSTANTES.MYFONT);
        Label paisdestinol = new Label("Pais Destino:");
        paisdestinol.setFont(CONSTANTES.MYFONT);
        Label ciudaddestinol = new Label("Ciudad Destino:");
        ciudaddestinol.setFont(CONSTANTES.MYFONT);
        Label motivoMovl = new Label("Motivo:");
        motivoMovl.setFont(CONSTANTES.MYFONT);
        pasaporte.setText(r.getMigrante().getIdentificacion());
        pasaporte.setEditable(false);
        tipomov.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                if (tipomov.getValue().equals("Entrada")) {
                    Thread th = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    paisdestino.setText("Ecuador");
                                    paisdestino.setEditable(false);
                                    paisproced.setEditable(true);
                                }
                            });
                        }
                    });
                    th.start();
                } else if (tipomov.getValue().equals("Salida")) {
                    Thread th = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    paisproced.setText("Ecuador");
                                    paisproced.setEditable(false);
                                    paisdestino.setEditable(true);
                                }
                            });
                        }
                    });
                    th.start();
                }
            }
        });
        
        nombre.setText(r.getMigrante().getNombre());
        nacionalidad.setText(r.getMigrante().getNacionalidad());
        provorigen.setText(r.getMigrante().getProvOrigen());
        cantonorigen.setText(r.getMigrante().getCantOrigen());
        if (r.getTipoMov().equals("Entrada")) {
            tipomov.getSelectionModel().select(0);
        } else {
            tipomov.getSelectionModel().select(1);
        }
        paisproced.setText(r.getPaisProced());
        viatransporte.setText(r.getViaTransporte());
        paisproced.setText(r.getPaisDestino());
        ciudaddestino.setText(r.getCiudadDestino());
        motivoMov.setText(r.getMotiMov());
        
        HBox hboxmigr = new HBox();
        VBox vbleft = new VBox();
        
        vbleft.getChildren()
                .addAll(nombrel, nombre, pasaportel, pasaporte, nacionalidadl, nacionalidad);
        VBox vbright = new VBox();
        
        vbright.getChildren()
                .addAll(provorigenl, provorigen, cantonorigenl, cantonorigen);
        hboxmigr.getChildren()
                .addAll(vbleft, vbright);
        VBox vbleft2 = new VBox();
        
        vbleft2.getChildren()
                .addAll(tipomovl, tipomov, paisprocedl, paisproced, motivoMovl, motivoMov);
        VBox vbright2 = new VBox();
        
        vbright2.getChildren()
                .addAll(viatransportel, viatransporte, paisdestinol, paisdestino, ciudaddestinol, ciudaddestino);
        HBox hboxmigrac = new HBox();
        
        hboxmigrac.getChildren()
                .addAll(vbleft2, vbright2);
        vbleft.setSpacing(
                2);
        vbleft.setAlignment(Pos.CENTER);
        
        vbleft2.setSpacing(
                2);
        vbleft2.setAlignment(Pos.CENTER);
        
        vbright.setSpacing(
                2);
        vbright.setAlignment(Pos.CENTER);
        
        vbright2.setSpacing(
                2);
        vbright2.setAlignment(Pos.CENTER);
        
        hboxmigr.setSpacing(
                20);
        hboxmigr.setAlignment(Pos.CENTER);
        
        hboxmigrac.setSpacing(
                20);
        hboxmigrac.setAlignment(Pos.CENTER);
        VBox onroot = new VBox();
        
        onroot.getChildren()
                .addAll(hboxmigr, hboxmigrac);
        onroot.setAlignment(Pos.CENTER);
        
        onroot.setLayoutX(
                23);
        onroot.setLayoutY(
                30);
        Rectangle rc = new Rectangle(450, 800);
        
        rc.setFill(Color.WHITE);
        StackPane btnSalir = Utilities.boton(redButton);
        Label lblSalir = new Label("Salir");
        
        lblSalir.setFont(CONSTANTES.MYFONT);
        
        lblSalir.setTextFill(Color.WHITE);
        
        btnSalir.getChildren()
                .add(lblSalir);
        btnSalir.setLayoutX(
                20);
        btnSalir.setLayoutY(
                550);
        btnSalir.setOnMouseClicked(e
                -> {
            Stage stage = (Stage) root1.getScene().getWindow();
            stage.close();
        }
        );
        StackPane btnRegistrar = Utilities.boton(nomBot);
        ImageView im = (ImageView) btnRegistrar.getChildren().get(0);
        
        im.setFitHeight(
                50);
        im.setFitWidth(
                200);
        Label lblPuestos = new Label("Modificar");
        lblPuestos.setFont(CONSTANTES.MYFONT);
        lblPuestos.setTextFill(Color.WHITE);
        btnRegistrar.getChildren()
                .add(lblPuestos);
        btnRegistrar.setOnMouseClicked(e
                -> {
            if (nombre.getText().equals("") || pasaporte.getText().equals("") || nacionalidad.getText().equals("")
                    || provorigen.getText().equals("") || cantonorigen.getText().equals("") || tipomov.getValue() == null
                    || paisproced.getText().equals("") || viatransporte.getText().equals("") || paisdestino.getText().equals("")
                    || ciudaddestino.getText().equals("") || motivoMov.getText().equals("")) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Rellenar Campos");
                alerta.setContentText("Alguno de los campos no ha sido llenado, por favor rellenelo y vuelva a intentarlo");
                alerta.showAndWait();
            } else {
                r.getMigrante().setCantOrigen(cantonorigen.getText());
                r.getMigrante().setNombre(nombre.getText());
                r.getMigrante().setProvOrigen(provorigen.getText());
                r.getMigrante().setNacionalidad(nacionalidad.getText());
                r.setTipoMov(tipomov.getValue().toString());
                r.setPaisProced(paisproced.getText());
                r.setViaTransporte(viatransporte.getText());
                r.setPaisDestino(paisproced.getText());
                r.setCiudadDestino(ciudaddestino.getText());
                r.setMotiMov(motivoMov.getText());
                Stage stage = (Stage) root1.getScene().getWindow();
                stage.close();
            }
            
        });
        btnRegistrar.setLayoutX(
                20);
        btnRegistrar.setLayoutY(
                480);
        root1.getChildren()
                .addAll(rc, onroot, btnSalir, btnRegistrar);
        return root1;
        
    }
    
}
