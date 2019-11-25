/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Empleado;
import modelo.PuestoAtencion;
import modelo.Ticket;
import utilities.CONSTANTES;
import utilities.Utilities;

/**
 *
 * @author Alex Velez
 */
public class VistaGerente {
    private final Pane root;
    private final String nomBot = "Button";
    private final String icono = "ICO_01.png";
    public final String redButton = "ButtonRed";
    private Pane pnEmp;
    private Pane pnPuest;
    public VistaGerente(){
        root = new Pane();
        createRoot();
    }
    
    public Pane getRoot(){
        return root;
    }
    
    private void createRoot(){
        Label lblBienvenida = new Label("Bienvenido Gerente!");
        lblBienvenida.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 60));
        
        Label pregunta = new Label("Que deseas ver?");
        pregunta.setFont(CONSTANTES.MYFONT);
        StackPane btnEmpleados= Utilities.boton(nomBot);
        Label lblEmpleados = new  Label("Empleados");
        lblEmpleados.setFont(CONSTANTES.MYFONT);
        lblEmpleados.setTextFill(Color.WHITE);
        btnEmpleados.getChildren().add(lblEmpleados);
        btnEmpleados.setOnMouseClicked(e->{
            Stage stEmp = new Stage();
            Scene scEmp = new Scene(createRootEmpl(),900,500);
            stEmp.setScene(scEmp);
            stEmp.setResizable(false);
            stEmp.getIcons().add(new Image(CONSTANTES.RUTA_IMGS+icono));
            stEmp.setTitle("Empleados");
            stEmp.show();
        });
        
        
        StackPane btnPuestos= Utilities.boton(nomBot);
        Label lblPuestos = new  Label("Puestos de Atencion");
        lblPuestos.setFont(CONSTANTES.MYFONT);
        lblPuestos.setTextFill(Color.WHITE);
        btnPuestos.getChildren().add(lblPuestos);
        btnPuestos.setOnMouseClicked(e->{
            
            Scene sc = new Scene(createRootPuestos(),900,500);
            Stage stPuestos = new Stage();
            stPuestos.setScene(sc);
            stPuestos.setResizable(false);
            stPuestos.getIcons().add(new Image(CONSTANTES.RUTA_IMGS+icono));
            stPuestos.setTitle("Puestos de atencion");
            stPuestos.show();
            
        });
        
        
        StackPane btnVis= Utilities.boton(nomBot);
        Label lblVis = new  Label("Visualizaciones");
        lblVis.setFont(CONSTANTES.MYFONT);
        lblVis.setTextFill(Color.WHITE);
        btnVis.getChildren().add(lblVis);
        
        btnVis.setOnMouseClicked(e->{
            VistaVisualizaciones vs = new VistaVisualizaciones();
            Stage stVis = new Stage();
            Pane p = vs.getRoot();
            Scene scVis = new Scene(p,p.getWidth(),p.getHeight());
            stVis.setScene(scVis);
            stVis.show();
        });
        StackPane btnSalir = Utilities.boton(redButton);
        Label lblSalir = new Label("Salir");
        lblSalir.setFont(CONSTANTES.MYFONT);
        lblSalir.setTextFill(Color.WHITE);
        btnSalir.getChildren().add(lblSalir);
        btnSalir.setLayoutX(30);
        btnSalir.setLayoutY(340);
        btnSalir.setOnMouseClicked(e->{
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
        hbButtons.getChildren().addAll(btnEmpleados,btnPuestos);
        onRoot.getChildren().addAll(lblBienvenida,pregunta,hbButtons,btnVis);
        
        
        Rectangle rc = new Rectangle(750,450);
        rc.setFill(Color.WHITE);
        root.getChildren().addAll(rc,onRoot,btnSalir);
    }
    
    private ScrollPane createRootEmpl(){
        pnEmp = new Pane();
        
        pnEmp.setLayoutY(15);
        
        //Mostrando empleados
        FlowPane fpEmpleados = new FlowPane();
        
        Set<Empleado> empleados = Main.getMigraciones().getEmpleados();
        Iterator<Empleado> it = empleados.iterator();
        while(it.hasNext()){
            Empleado emp = it.next();
            VBox vbEmp = new VBox();
            Font font = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 16);
            Label lblNombre = new Label(emp.getNombre());
            lblNombre.setFont(font);
            Label lblCedula = new Label(emp.getCedula());
            lblCedula.setFont(font);
            ImageView img;
            
            if(emp.getGenero().equals("Hombre")) img = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Empleado.png"));
            else img = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Empleada.png"));
            img.setFitWidth(175);
            img.setFitHeight(175);
            vbEmp.setAlignment(Pos.CENTER);
            vbEmp.getChildren().addAll(img,lblNombre,lblCedula);
            
            vbEmp.setOnMouseClicked(e->{
                StackPane btnMod =Utilities.boton(nomBot);
                StackPane btnBor =Utilities.boton(nomBot);
                Label lblModify = new Label("Modificar Empleado");
                lblModify.setFont(CONSTANTES.MYFONT);
                lblModify.setTextFill(Color.WHITE);
                Label lblErase = new Label("Eliminar Empleado");
                lblErase.setFont(CONSTANTES.MYFONT);
                lblErase.setTextFill(Color.WHITE);
                btnMod.getChildren().add(lblModify);
                btnBor.getChildren().add(lblErase);                
                            
                VBox vbOpciones = new VBox();
                vbOpciones.setLayoutX(30);
                vbOpciones.setLayoutY(20);
                vbOpciones.setSpacing(20);
                vbOpciones.getChildren().addAll(btnMod,btnBor);
                
                Rectangle rc1 = new  Rectangle(380,240);
                rc1.setFill(Color.WHITE);
                Pane rootOpc = new Pane();
                rootOpc.getChildren().addAll(rc1,vbOpciones);
                
                btnMod.setOnMouseClicked(e1->{
                    ImageView imaEmp = new ImageView();
                    imaEmp.setFitHeight(175);
                    imaEmp.setFitWidth(175);
                    if(emp.getGenero().equals("Hombre"))imaEmp.setImage(new Image(CONSTANTES.RUTA_IMGS+"Empleado.png"));
                    else imaEmp.setImage(new Image(CONSTANTES.RUTA_IMGS+"Empleada.png"));
                    Font theFont = Font.font("Arial", 14);
                    TextField txtNom = new TextField(emp.getNombre());
                    txtNom.setFont(theFont);
                    TextField txtCed = new TextField(emp.getCedula());
                    txtNom.setFont(theFont);
                    
                    StackPane btnOk = Utilities.boton(redButton);
                    Label lblOk = new Label("Guardar");
                    lblOk.setFont(CONSTANTES.MYFONT);
                    lblOk.setTextFill(Color.WHITE);
                    btnOk.getChildren().add(lblOk);
                    
                    
                    
                    VBox vbRootMod = new VBox();
                    
                    vbRootMod.setAlignment(Pos.CENTER);
                    vbRootMod.setLayoutX(45);
                    vbRootMod.setLayoutY(20);
                    
                    vbRootMod.setSpacing(20);
                    vbRootMod.getChildren().addAll(imaEmp,txtNom,txtCed,btnOk);
                    Pane pnRootMod = new Pane();
                    Rectangle r3 = new Rectangle(300,450);
                    r3.setFill(Color.WHITE);
                    
                    pnRootMod.getChildren().addAll(r3,vbRootMod);
                    Stage stMod = new Stage();
                    
                    btnOk.setOnMouseClicked(e3->{
                        
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        if(txtNom.getText().equals("")||txtCed.getText().length()!=10){
                            
                            alerta.setTitle("Campos Incorrectos");
                            alerta.setContentText("POR FAVOR INGRESE TODOS LOS CAMPOS CORRECTAMENTE");
                            alerta.showAndWait();
                        }else{
                            Empleado empAnt = new Empleado(emp.getNombre(),emp.getCedula(),emp.getGenero());
                            Main.getMigraciones().getEmpleados().remove(emp);
                            emp.setNombre(txtNom.getText());
                            emp.setCedula(txtCed.getText());
                            if(!Main.getMigraciones().getEmpleados().add(emp)){
                                alerta.setTitle("Empleado repetido");
                                alerta.setContentText("El numero de cedula que usted ingreso ya se encuentra registrado");
                                alerta.showAndWait();
                                
                                Main.getMigraciones().getEmpleados().add(empAnt);
                                emp.setNombre(empAnt.getNombre());
                                emp.setCedula(empAnt.getCedula());
                                
                            }else{
                                pnEmp.getScene().setRoot(createRootEmpl());
                                stMod.close();
                                Stage stOpc = (Stage) rootOpc.getScene().getWindow();
                                stOpc.close();
                            }
                        }
                        
                    });
                    
                    
                    Scene scMod = new Scene(pnRootMod,250,400);
                    stMod.setScene(scMod);
                    stMod.setResizable(false);
                    stMod.setTitle("Modificar Empleado");
                    stMod.getIcons().add(new Image(CONSTANTES.RUTA_IMGS+icono));
                    stMod.initModality(Modality.WINDOW_MODAL);
                    stMod.initOwner(rootOpc.getScene().getWindow());
                    stMod.show();
                    
                });
                
                btnBor.setOnMouseClicked(e2->{
                    if(emp.getPuesto()==null || emp.getPuesto().isDisponible()){
                        Label lblPreg = new Label("Estas seguro que deseas eliminar al empleado "+emp.getNombre()+"?");
                        lblPreg.setMinWidth(500);
                        lblPreg.setAlignment(Pos.CENTER);
                        lblPreg.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 18));
                        Rectangle rc2 = new Rectangle(550,150);
                        rc2.setFill(Color.WHITE);
                        Button btnSi = new Button("Si");
                        btnSi.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                    
                        Button btnNo = new Button("No");
                        btnNo.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                    
                        VBox vbPreg = new VBox();
                        vbPreg.setLayoutX(10);
                        vbPreg.setLayoutY(10);
                        vbPreg.setSpacing(20);
                        vbPreg.setAlignment(Pos.CENTER);
                        HBox hbOp = new HBox();
                        hbOp.setAlignment(Pos.CENTER);
                        hbOp.setSpacing(20);
                        hbOp.getChildren().addAll(btnSi,btnNo);
                        vbPreg.getChildren().addAll(lblPreg, hbOp);
                        Pane rootPane = new Pane();
                        rootPane.getChildren().addAll(rc2,vbPreg);
                                      
                        Scene sc = new Scene(rootPane,500,100);
                        Stage stPreg = new Stage();
                    
                        btnSi.setOnMouseClicked(e3->{
                            if(emp.getPuesto()!=null) emp.getPuesto().setEmpleado(null);
                            Main.getMigraciones().getEmpleados().remove(emp);
                            Stage st2 = (Stage)rootOpc.getScene().getWindow();
                            st2.close();
                            stPreg.close();
                            pnEmp.getScene().setRoot(createRootEmpl());
                        }); 
                    
                        btnNo.setOnMouseClicked(e3->{
                            stPreg.close();
                        });
                    
                        stPreg.setResizable(false);
                        stPreg.getIcons().add(new Image(CONSTANTES.RUTA_IMGS+icono));
                        stPreg.initModality(Modality.WINDOW_MODAL);
                        stPreg.initOwner(rootOpc.getScene().getWindow());
                        stPreg.setTitle("Confirmacion");
                         stPreg.show();
                        stPreg.setScene(sc);
                      
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Empleado ocupado");
                        alert.setContentText("No puede eliminar a este empleado, porque en estos momentos se encuentra ocupado"
                                + ". Por favor espere a que se desocupe y vuelva a intentarlo.");
                        alert.showAndWait();
                    }
                    
                });
                
                Stage stOpc = new Stage();
                stOpc.setTitle("Que desea hacer?");
                stOpc.setResizable(false);
                Scene sc = new Scene(rootOpc,360,220);
                stOpc.initModality(Modality.WINDOW_MODAL);
                stOpc.initOwner(pnEmp.getScene().getWindow());
                stOpc.setScene(sc);
                stOpc.show();
                
            });
            
            fpEmpleados.getChildren().add(vbEmp);
        }
        
        
        //Agregando botones de administracion de empleados
        StackPane addEmp = Utilities.boton(nomBot);
        Label lblAddEmp = new Label("Agregar empleado");
        lblAddEmp.setFont(CONSTANTES.MYFONT);
        lblAddEmp.setTextFill(Color.WHITE);
        addEmp.getChildren().add(lblAddEmp);

        
        Label lblSalir = new Label("Salir");
        lblSalir.setFont(CONSTANTES.MYFONT);
        lblSalir.setTextFill(Color.WHITE);
        StackPane btnSalir = Utilities.boton(redButton);
        btnSalir.getChildren().add(lblSalir);
        
        
        VBox vbEmpl = new VBox();
        vbEmpl.setSpacing(20);
        vbEmpl.setMinWidth(900);
        vbEmpl.setLayoutY(15);
        vbEmpl.setAlignment(Pos.CENTER);
        vbEmpl.getChildren().addAll(fpEmpleados,addEmp,btnSalir);
        

        
        Rectangle rc = new Rectangle(1000,1000);
        rc.setFill(Color.WHITE);
        pnEmp.getChildren().addAll(rc,vbEmpl);
        
        btnSalir.setOnMouseClicked(e->{
            Stage st = (Stage)vbEmpl.getScene().getWindow();
            st.close();
        });
        
        addEmp.setOnMouseClicked(e->{
            Stage stNewEmp = new Stage();
            Scene scTurnos = new Scene(createRootNewEmpl());
            stNewEmp.setScene(scTurnos);
            stNewEmp.setWidth(500);
            stNewEmp.setHeight(500);
            stNewEmp.setResizable(false);
            stNewEmp.getIcons().add(new Image(CONSTANTES.RUTA_IMGS+icono));
            stNewEmp.setTitle("Agregar Empleado");
            stNewEmp.initModality(Modality.WINDOW_MODAL);
            stNewEmp.initOwner(pnEmp.getScene().getWindow());
            stNewEmp.show();
        });
        ScrollPane sc = new ScrollPane();
        sc.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sc.setContent(pnEmp);
        
        return sc;
    }
    
    private Pane createRootNewEmpl(){
        ImageView ima = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"EmpleadoDesconocido.png"));
        ima.setFitHeight(175);
        ima.setFitWidth(175);
        ObservableList<String> items=FXCollections.observableArrayList();
        items.addAll("Hombre","Mujer");
        ComboBox cbGen=new ComboBox(items);
        cbGen.getSelectionModel().selectedItemProperty().addListener((obs,oldVal,newVal)->{
            if(newVal =="Hombre"){
                ima.setImage(new Image(CONSTANTES.RUTA_IMGS+"Empleado.png"));
            }else{
                ima.setImage(new Image(CONSTANTES.RUTA_IMGS+"Empleada.png"));
            }
        });
        Font font = Font.font("Arial", FontWeight.LIGHT, FontPosture.REGULAR, 18);
        
        Label lblGen = new Label("Genero: ");
        lblGen.setFont(font);
        Label lblNom = new Label("Nombre:");
        lblNom.setFont(font);
        Label lblCed = new Label("Cedula: ");
        lblCed.setFont(font);
        TextField txtNom = new  TextField();
        txtNom.setFont(font);
        TextField txtCed = new TextField();
        txtCed.setFont(font);
        
        GridPane gpForm = new GridPane();
        gpForm.add(lblGen, 0, 0);
        gpForm.add(lblNom, 0, 1);
        gpForm.add(lblCed, 0, 2);
        gpForm.add(cbGen, 1, 0);
        gpForm.add(txtNom, 1, 1);
        gpForm.add(txtCed, 1, 2);
        gpForm.setVgap(20);
        gpForm.setHgap(20);
        gpForm.setTranslateX(40);
        StackPane btnAgg = Utilities.boton(redButton);
        Label lblAdd = new Label("Agregar");
        lblAdd.setFont(CONSTANTES.MYFONT);
        lblAdd.setTextFill(Color.WHITE);
        btnAgg.getChildren().add(lblAdd);
        Pane createEmp = new Pane();
        btnAgg.setOnMouseClicked(e->{
            if(cbGen.getSelectionModel().getSelectedItem()==null || txtNom.getText().equals("") || txtCed.getText().length()!=10){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("WOOPS!");
                alert.setContentText("POR FAVOR INGRESE TODOS LOS CAMPOS CORRECTAMENTE");
                alert.showAndWait();
            }else{
                Empleado empl = new Empleado(txtNom.getText(),txtCed.getText(),(String)cbGen.getSelectionModel().getSelectedItem());
                if(!Main.getMigraciones().getEmpleados().add(empl)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Empleado repetido");
                    alert.setContentText("Ya existe un empleado con el mismo numero de cedula");
                    alert.showAndWait();
                }else{
                    pnEmp.getScene().setRoot(createRootEmpl());
                    Stage st = (Stage)createEmp.getScene().getWindow();
                    st.close();
                }
            }
        });
        VBox vbCreateEmp = new VBox();
        vbCreateEmp.setTranslateY(20);
        vbCreateEmp.setMinWidth(500);
        vbCreateEmp.setAlignment(Pos.CENTER);
        vbCreateEmp.setSpacing(20);
        vbCreateEmp.getChildren().addAll(ima,gpForm,btnAgg);
        
        Rectangle rc = new Rectangle(500,500);
        rc.setFill(Color.WHITE);
        createEmp.getChildren().addAll(rc,vbCreateEmp);
        return createEmp;
    }
    
    private ScrollPane createRootPuestos(){
        pnPuest = new Pane();
        
        pnPuest.setLayoutY(15);
        
        //Mostrando Puestos
        FlowPane fpPuestos = new FlowPane();
        fpPuestos.setHgap(20);
        fpPuestos.setVgap(20);
        List<PuestoAtencion> puestos = Main.getMigraciones().getPuestosAtencion();
        Iterator<PuestoAtencion> it = puestos.iterator();
        int i =0;
        while(it.hasNext()){
            i++;
            PuestoAtencion pt = it.next();
            VBox vbPuest = new VBox();
            Font font = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 16);
            Label lblNum = new Label("Puesto N."+i);
            lblNum.setFont(font);
            String ocu = pt.isDisponible()? "Disponible":"Ocupado";
            String disp = pt.getEmpleado()==null? "No disponible":ocu;
            Label lblDisp = new Label(disp);
            lblDisp.setFont(font);
            ImageView img = new ImageView();
            if(pt.getEmpleado()==null) img.setImage(new Image(CONSTANTES.RUTA_IMGS+"PuestoAtencion.png"));
            else if(pt.isDisponible()) img.setImage(new Image(CONSTANTES.RUTA_IMGS+"PuestoLibre.png"));
            else img.setImage(new Image(CONSTANTES.RUTA_IMGS+"PuestoOcupado.png"));
            img.setFitWidth(175);
            img.setFitHeight(175);
            vbPuest.setAlignment(Pos.CENTER);
            vbPuest.getChildren().addAll(img,lblNum,lblDisp);
            
            vbPuest.setOnMouseClicked(e->{
                StackPane btnModify =Utilities.boton(nomBot);
                StackPane btnBorrar =Utilities.boton(nomBot);
                Label lblModify = new Label("Modificar Puesto");
                lblModify.setFont(CONSTANTES.MYFONT);
                lblModify.setTextFill(Color.WHITE);
                Label lblErase = new Label("Eliminar Puesto");
                lblErase.setTextFill(Color.WHITE);
                lblErase.setFont(CONSTANTES.MYFONT);
                btnModify.getChildren().add(lblModify);
                btnBorrar.getChildren().add(lblErase);                
                            
                VBox vbOpc = new VBox();
                vbOpc.setSpacing(20);
                vbOpc.setLayoutX(30);
                vbOpc.setLayoutY(20);
                
                vbOpc.getChildren().addAll(btnModify,btnBorrar);
                
                Rectangle rc1 = new  Rectangle(380,240);
                rc1.setFill(Color.WHITE);
                Pane rootOpc = new Pane();
                rootOpc.getChildren().addAll(rc1,vbOpc);
                
                btnModify.setOnMouseClicked(f->{
                    
                    if(pt.isDisponible()||pt.getEmpleado()==null){
                        ImageView imaPuest = new ImageView();
                        imaPuest.setFitHeight(175);
                        imaPuest.setFitWidth(175);
                    
                        if(pt.getEmpleado()==null) imaPuest.setImage(new Image(CONSTANTES.RUTA_IMGS+"PuestoAtencion.png"));
                        else imaPuest.setImage(new Image(CONSTANTES.RUTA_IMGS+"PuestoLibre.png"));
                    
                    
                        Label lblEmp = new Label("Empleado: ");
                        lblEmp.setFont(font);
                        
                        ObservableList<Empleado> empleados=FXCollections.observableArrayList();
                        Main.getMigraciones().getEmpleados().stream().filter((emp) -> (emp.getPuesto()==null)).forEachOrdered((emp) -> {
                            empleados.add(emp);
                        });
                        ComboBox cbEmp=new ComboBox(empleados);
                        
                        StackPane btnCancelar = Utilities.boton(redButton);
                        Label lblCanc = new Label("Cancelar");
                        lblCanc.setFont(CONSTANTES.MYFONT);
                        lblCanc.setTextFill(Color.WHITE);
                        btnCancelar.getChildren().add(lblCanc);
                        
                        StackPane btnGuard = Utilities.boton(nomBot);
                        ImageView ima = (ImageView)btnGuard.getChildren().get(0);
                        ima.setFitHeight(59);
                        ima.setFitWidth(177);
                        Label lblGuard = new Label("Guardar");
                        lblGuard.setFont(CONSTANTES.MYFONT);
                        lblGuard.setTextFill(Color.WHITE);
                        btnGuard.getChildren().add(lblGuard);
                        
                        HBox hbModPuest = new HBox();
                        hbModPuest.setSpacing(20);
                        hbModPuest.getChildren().addAll(lblEmp, cbEmp);
                        VBox vbRootMod = new VBox();
                    
                        vbRootMod.setAlignment(Pos.CENTER);
                        vbRootMod.setLayoutX(45);
                        vbRootMod.setLayoutY(20);
                    
                        vbRootMod.setSpacing(20);
                        vbRootMod.getChildren().addAll(imaPuest,hbModPuest,btnGuard,btnCancelar);
                    
                        Pane pnRootMod = new Pane();
                        Rectangle r1 = new Rectangle(410,410);
                        r1.setFill(Color.WHITE);
                    
                        pnRootMod.getChildren().addAll(r1,vbRootMod);
                        Stage stMod = new Stage();
                        
                        btnCancelar.setOnMouseClicked(e3->{
                            stMod.close();
                        });
                        btnGuard.setOnMouseClicked(e3->{
                            Alert alerta = new Alert(Alert.AlertType.ERROR);
                            if(cbEmp.getSelectionModel().getSelectedItem()==null){
                            
                                alerta.setTitle("Campos Incorrectos");
                                alerta.setContentText("Por favor escoja a un empleado valido");
                                alerta.showAndWait();
                            }else{
                                Empleado emp = (Empleado) cbEmp.getSelectionModel().getSelectedItem();
                                emp.setPuesto(pt);
                                if(pt.getEmpleado()!=null) pt.getEmpleado().setPuesto(null);
                                pt.setEmpleado(emp);
                                PriorityQueue<Ticket> cola = Main.getMigraciones().getColaAtencion();
                                
                                if(!cola.isEmpty()){
                                    Ticket t = cola.poll();
                                    List<PuestoAtencion> puestosAt = Main.getMigraciones().getPuestosAtencion();
                                    int cont =1;
                                    int index=0;
                                    for(PuestoAtencion pts: puestosAt){
                                        if(pts.equals(pt)) index = cont;
                                        cont++;
                                    }
                                    t.setPuesto(index);
                                    pt.setTicket(t);
                                    pt.setDisponible(false);
                                }
                                pnPuest.getScene().setRoot(createRootPuestos());
                                stMod.close();
                                Stage stOpc = (Stage) rootOpc.getScene().getWindow();
                                stOpc.close();  
                            }
                            
                        });
                    
                        
                        Scene scMod = new Scene(pnRootMod,375,400);
                        stMod.setScene(scMod);
                        stMod.setResizable(false);
                        stMod.setTitle("Modificar Empleado");
                        stMod.getIcons().add(new Image(CONSTANTES.RUTA_IMGS+icono));
                        stMod.initModality(Modality.WINDOW_MODAL);
                        stMod.initOwner(rootOpc.getScene().getWindow());
                        stMod.show();    
                        
                    }else{
                        Alert alerta = new Alert(Alert.AlertType.ERROR); 
                        alerta.setTitle("Empleado ocupado");
                        alerta.setContentText("El puesto que desea modificar en este momento esta ocupado. "
                                + "Por favor espere a que se desocupe y vuelva a intentarlo.");
                        alerta.showAndWait();
                    }                   
                });
                
                btnBorrar.setOnMouseClicked(e2->{
                    if(pt.getEmpleado()==null||pt.isDisponible()){
                        Label lblPreg = new Label("Estas seguro que deseas eliminar este puesto?");
                        lblPreg.setMinWidth(500);
                        lblPreg.setAlignment(Pos.CENTER);
                        lblPreg.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 18));
                        Rectangle rc2 = new Rectangle(550,150);
                        rc2.setFill(Color.WHITE);
                        Button btnSi = new Button("Si");
                        btnSi.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                    
                        Button btnNo = new Button("No");
                        btnNo.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                    
                        VBox vbPreg = new VBox();
                        vbPreg.setLayoutX(10);
                        vbPreg.setLayoutY(10);
                        vbPreg.setSpacing(20);
                        vbPreg.setAlignment(Pos.CENTER);
                        HBox hbOp = new HBox();
                        hbOp.setAlignment(Pos.CENTER);
                        hbOp.setSpacing(20);
                        hbOp.getChildren().addAll(btnSi,btnNo);
                        vbPreg.getChildren().addAll(lblPreg, hbOp);
                        Pane rootPane = new Pane();
                        rootPane.getChildren().addAll(rc2,vbPreg);
                                      
                        Scene sc = new Scene(rootPane,500,100);
                        Stage stPreg = new Stage();
                    
                        btnSi.setOnMouseClicked(e3->{
                            Main.getMigraciones().getPuestosAtencion().remove(pt);
                            if(pt.getEmpleado()!=null)pt.getEmpleado().setPuesto(null);
                            Stage st2 = (Stage)rootOpc.getScene().getWindow();
                            st2.close();
                            stPreg.close();
                            pnPuest.getScene().setRoot(createRootPuestos());
                        }); 
                    
                        btnNo.setOnMouseClicked(e3->{
                            stPreg.close();
                        });
                    
                        stPreg.setResizable(false);
                        stPreg.getIcons().add(new Image(CONSTANTES.RUTA_IMGS+icono));
                        stPreg.initModality(Modality.WINDOW_MODAL);
                        stPreg.initOwner(rootOpc.getScene().getWindow());
                        stPreg.setTitle("Confirmacion");
                        stPreg.setScene(sc);
                        stPreg.show();
                    }else{
                        
                        Alert alertaOc = new Alert(Alert.AlertType.ERROR); 
                        alertaOc.setTitle("Empleado ocupado");
                        alertaOc.setContentText("El puesto que desea eliminar en este momento esta ocupado. "
                                + "Por favor espere a que se desocupe y vuelva a intentarlo.");
                        alertaOc.showAndWait();
                    }
                    
                });
                
                Stage stOpc = new Stage();
                stOpc.setTitle("Que desea hacer?");
                stOpc.setResizable(false);
                Scene sc = new Scene(rootOpc,360,220);
                stOpc.initModality(Modality.WINDOW_MODAL);
                stOpc.initOwner(pnPuest.getScene().getWindow());
                stOpc.setScene(sc);
                stOpc.show();
                
            });
            
            fpPuestos.getChildren().add(vbPuest);
        }
        
        
        //Agregando botones de administracion de Puestos
        StackPane addPuest= Utilities.boton(nomBot);
        Label lblAddPuest = new Label("Agregar Puesto");
        lblAddPuest.setFont(CONSTANTES.MYFONT);
        lblAddPuest.setTextFill(Color.WHITE);
        addPuest.getChildren().add(lblAddPuest);

        Label lblSalir = new Label("Salir");
        lblSalir.setFont(CONSTANTES.MYFONT);
        lblSalir.setTextFill(Color.WHITE);
        StackPane btnSalir = Utilities.boton(redButton);
        btnSalir.getChildren().add(lblSalir);
        
        
        VBox vbPuest = new VBox();
        vbPuest.setSpacing(20);
        vbPuest.setMinWidth(900);
        vbPuest.setLayoutY(15);
        vbPuest.setLayoutX(15);
        vbPuest.setAlignment(Pos.CENTER);
        vbPuest.getChildren().addAll(fpPuestos,addPuest,btnSalir);
        

        
        Rectangle rc = new Rectangle(1000,1000);
        rc.setFill(Color.WHITE);
        pnPuest.getChildren().addAll(rc,vbPuest);
        
        btnSalir.setOnMouseClicked(e->{
            Stage st = (Stage)vbPuest.getScene().getWindow();
            st.close();
        });
        
        addPuest.setOnMouseClicked(e->{
            PuestoAtencion pt = new PuestoAtencion();
            Main.getMigraciones().getPuestosAtencion().add(pt);
            pnPuest.getScene().setRoot(createRootPuestos());
        });
        ScrollPane sc = new ScrollPane();
        sc.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sc.setContent(pnPuest);
        
        return sc;
        
    }
}
