/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import modelo.Migrante;
import modelo.PuestoAtencion;
import modelo.Registro;
import modelo.Ticket;
import utilities.CONSTANTES;
import utilities.Utilities;

/**
 *
 * @author Alex Velez
 */
public class VistaRegistro {

    private final String nomBot = "Button";
    private Migrante migr = null;
    private Pane root;
    public final String redButton = "ButtonRed";
    private TextField paisproced;
    private TextField paisdestino;
    private PuestoAtencion pt;
    private VistaAtencion vt;
    public VistaRegistro(PuestoAtencion pt,VistaAtencion vt) {
        this.vt = vt;
        this.pt = pt;
        Ticket t = pt.getTicket();
        root = new Pane();
        createrootregistro(t);
    }

    public Pane getRoot() {
        return root;
    }

    private void createrootregistro(Ticket t) {
        if(t!=null){
            for (Migrante s : (Main.getMigraciones().getMigrantes())){
                if (s.getIdentificacion().equals(t.getIdentificacion())) {
                migr = s;
            }
        }
        TextField nombre = new TextField();
        TextField pasaporte = new TextField();
        TextField nacionalidad = new TextField();
        TextField provorigen = new TextField();
        TextField cantonorigen = new TextField();
        ComboBox tipomov = new ComboBox(FXCollections.observableArrayList("Entrada", "Salida"));
        paisproced = new TextField();
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
        if (t != null) {
            pasaporte.setText(t.getIdentificacion());
            pasaporte.setEditable(false);
        }
        if (migr
                != null) {
            nombre.setText(migr.getNombre());
            nacionalidad.setText(migr.getNacionalidad());
            provorigen.setText(migr.getProvOrigen());
            cantonorigen.setText(migr.getCantOrigen());
        }

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
                .addAll(tipomovl, tipomov,  paisprocedl, paisproced, motivoMovl, motivoMov);
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
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        }
        );
        StackPane btnRegistrar = Utilities.boton(nomBot);
        ImageView im = (ImageView) btnRegistrar.getChildren().get(0);

        im.setFitHeight(
                50);
        im.setFitWidth(
                200);
        Label lblPuestos = new Label("Regitrar");

        lblPuestos.setFont(CONSTANTES.MYFONT);

        lblPuestos.setTextFill(Color.WHITE);

        btnRegistrar.getChildren()
                .add(lblPuestos);
        btnRegistrar.setOnMouseClicked(e
                -> {
            if(nombre.getText().equals("")||pasaporte.getText().equals("")||nacionalidad.getText().equals("")
                    || provorigen.getText().equals("")||cantonorigen.getText().equals("")||tipomov.getValue()==null
                    ||paisproced.getText().equals("")||viatransporte.getText().equals("")||paisdestino.getText().equals("")
                    ||ciudaddestino.getText().equals("")||motivoMov.getText().equals("")){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Rellenar Campos");
                alerta.setContentText("Alguno de los campos no ha sido llenado, por favor rellenelo y vuelva a intentarlo");
                alerta.showAndWait();
            }else{
                if (migr == null) {
                    migr = new Migrante(nombre.getText(), pasaporte.getText(), nacionalidad.getText(), provorigen.getText(), cantonorigen.getText(),t.getTipoPersona());
                    if(!(Main.getMigraciones().getMigrantes().contains(migr)))
                        Main.getMigraciones().getMigrantes().add(migr);
                }else{
                    migr.setTipoPersona(t.getTipoPersona());
                }
                Registro reg = new Registro(migr, (String) tipomov.getValue(), paisproced.getText(), viatransporte.getText(), paisdestino.getText(), ciudaddestino.getText(), motivoMov.getText());
                Main.getMigraciones().getRegistros().add(reg);
                
                if(!Main.getMigraciones().getColaAtencion().isEmpty()){
                    pt.setTicket(Main.getMigraciones().getColaAtencion().poll());
                    int i = 1;
                    for(PuestoAtencion puest : Main.getMigraciones().getPuestosAtencion()){
                        if(puest == pt){
                            pt.getTicket().setPuesto(i);
                        }
                        i++;
                    }
                }else{
                    pt.setTicket(null);
                    pt.setDisponible(true);
                }
                vt.actualizar();
                Stage stage = (Stage) root.getScene().getWindow();
                stage.close();
            }
                      
        });
        btnRegistrar.setLayoutX(
                20);
        btnRegistrar.setLayoutY(
                480);
        root.getChildren()
                .addAll(rc, onroot, btnSalir, btnRegistrar);

    }
    }}
