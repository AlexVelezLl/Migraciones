/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import modelo.Migraciones;
import modelo.PuestoAtencion;
import modelo.Ticket;
import modelo.TipoPersona;
import utilities.CONSTANTES;
import utilities.CircularLinkedList;
import utilities.Utilities;

/**
 *
 * @author Alex Velez
 */
public class VistaTurnos {
    private Pane root;
    private static boolean activo;
    public VistaTurnos(){
        activo = true;
        root = createRoot();
    }
    public Pane getRoot(){
        return root;
    }
    public static void setActivo(boolean activo){
        VistaTurnos.activo=activo;
    }
    private Pane createRoot(){
        Pane rootC = new Pane();
        VBox onRoot = new VBox();
        onRoot.setSpacing(20);
        onRoot.setLayoutX(15);
        onRoot.setLayoutY(15);
        ImageView mig = new ImageView(new Image(CONSTANTES.RUTA_IMGS+"Migraciones.png"));//Image con logo de migraciones
        HBox hbEncab = new HBox();
        Label lHora = new Label();
        lHora.setFont(CONSTANTES.MYFONT);
        lHora.setTextFill(Color.web("6297de"));
        lHora.setTranslateX(630);
        Thread t = new Thread(()->{
            while(activo){
                
                LocalDateTime nowTime = LocalDateTime.now();
                String [] time = nowTime.toString().split("T")[1].split(":");
                String segundo;
                if(nowTime.getSecond()<10) segundo = "0"+nowTime.getSecond();
                else segundo = String.valueOf(nowTime.getSecond());
                Platform.runLater(()->lHora.setText(time[0]+":"+time[1]+":"+segundo));
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VistaTurnos.class.getName()).log(Level.SEVERE, null, ex);
                    Thread.currentThread().interrupt();
                }
                
            }
            
        });
        t.start();
        hbEncab.getChildren().addAll(mig,lHora);
        Rectangle bg = new  Rectangle(1000,600);
        bg.setFill(Color.WHITE);
        
        Pane pPub = ponerPublicidad();
        
        StackPane btnCogerTurno = Utilities.boton("ButtonRed");
        btnCogerTurno.setTranslateY(30);
        Label lblCogerTurno = new Label("Coger Turno");
        lblCogerTurno.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 25));
        lblCogerTurno.setTextFill(Color.WHITE);
        btnCogerTurno.getChildren().add(lblCogerTurno);
        btnCogerTurno.setAlignment(Pos.CENTER);
        btnCogerTurno.setOnMouseClicked(e->{
            
            VBox onRootTicket = createRootTicket();
            Pane rootTicket = new Pane();
            Rectangle rc = new Rectangle(500,250);
            rc.setFill(Color.WHITE);
            rootTicket.getChildren().addAll(rc,onRootTicket);
            Scene sc = new Scene(rootTicket);
            
            Stage stTake = new Stage();
            stTake.getIcons().add(new Image(CONSTANTES.RUTA_IMGS+"ICO_01.png"));
            stTake.setTitle("Tomar Turno");
            stTake.setWidth(500);
            stTake.setHeight(250);
            stTake.initModality(Modality.WINDOW_MODAL);
            stTake.initOwner(root.getScene().getWindow());
            stTake.setScene(sc);
            stTake.show();
        });
        
        onRoot.setMinWidth(1000);
        GridPane gpTurnos = new GridPane();
        gpTurnos.setHgap(5);
        gpTurnos.setVgap(5);
        Rectangle rc2 = new Rectangle(225,60);
        rc2.setFill(Color.DEEPSKYBLUE);
        Rectangle rc1 = new Rectangle(225,60);
        rc1.setFill(Color.DEEPSKYBLUE);
        StackPane stPuestos = new StackPane();
        StackPane stTurnos = new StackPane();
        Label lblPuestos = new Label("Puesto");
        Label lblTurnos = new Label("Turno");
        Font theFont = Font.font("Arial", FontWeight.BOLD, 18);
        lblPuestos.setFont(theFont);
        lblTurnos.setFont(theFont);
        lblPuestos.setTextFill(Color.WHITE);
        lblTurnos.setTextFill(Color.WHITE);  
        stPuestos.getChildren().addAll(rc2,lblPuestos);
        stTurnos.getChildren().addAll(rc1,lblTurnos);
        gpTurnos.add(stTurnos,0,0);
        gpTurnos.add(stPuestos, 1, 0);
        Iterator<PuestoAtencion> it = Main.getMigraciones().getPuestosAtencion().iterator();
        int i=1;
        while(it.hasNext()){
            PuestoAtencion pt = it.next();
            if(!pt.isDisponible()){
                Rectangle rc3 = new Rectangle(225,60);
                rc3.setFill(Color.DEEPSKYBLUE);
                Rectangle rc4 = new Rectangle(225,60);
                rc4.setFill(Color.DEEPSKYBLUE);
                StackPane stPuesto = new StackPane();
                StackPane stTurno = new StackPane();
                String puesto = pt.getTicket().getPuesto()<10? "0"+String.valueOf(pt.getTicket().getPuesto()):String.valueOf(pt.getTicket().getPuesto());
                Label lblPuesto = new Label(puesto);
                Label lblTurno = new Label(pt.getTicket().getTurno());
                lblPuesto.setFont(theFont);
                lblTurno.setFont(theFont);
                lblPuesto.setTextFill(Color.WHITE);
                lblTurno.setTextFill(Color.WHITE);
                
                stPuesto.getChildren().addAll(rc3,lblPuesto);
                stTurno.getChildren().addAll(rc4,lblTurno);                
                gpTurnos.add(stTurno, 0, i);
                gpTurnos.add(stPuesto, 1, i);
                i++;
                
            }
        }
        Pane p = new Pane();
        p.setMinSize(450, 300);
        Rectangle r = new Rectangle(455, 300);
        r.setFill(Color.WHITE);
        p.getChildren().addAll(r,gpTurnos);
        ScrollPane sc = new ScrollPane();
        
        sc.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sc.setContent(p);
                
        HBox hbPubPuest = new HBox();
        hbPubPuest.setSpacing(10);
        hbPubPuest.getChildren().addAll(pPub,sc);
        
        StackPane salir = Utilities.boton("ButtonRed");
        Label lblSalir = new Label("Salir");
        lblSalir.setFont(CONSTANTES.MYFONT);
        lblSalir.setTextFill(Color.WHITE);
        salir.getChildren().add(lblSalir);
        salir.setTranslateY(30);
        salir.setOnMouseClicked(e->{
            Stage st = (Stage) root.getScene().getWindow();
            st.close();
        });
        
        HBox hbTale = new HBox();
        hbTale.setAlignment(Pos.CENTER);
        hbTale.getChildren().addAll(btnCogerTurno,salir);
        hbTale.setSpacing(40);
        onRoot.getChildren().addAll(hbEncab,hbPubPuest,hbTale);
        rootC.getChildren().addAll(bg,onRoot);
        return rootC;
    }
    
    private Pane ponerPublicidad(){
        Main.getMigraciones().cargarPublicidad();
        CircularLinkedList publicidades = Main.getMigraciones().getPublicidades();
        Pane pnPublicidad = new Pane();
        pnPublicidad.setMinHeight(300);
        pnPublicidad.setMinWidth(500);
        final ImageView imPub1 = new ImageView();
        
        pnPublicidad.getChildren().addAll(imPub1);
        Thread t = new Thread(()->{
            Iterator<Image> it = publicidades.iterator();
            while(it.hasNext()&&activo){
                imPub1.setImage(it.next());
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VistaTurnos.class.getName()).log(Level.SEVERE, null, ex);
                    Thread.currentThread().interrupt();
                }              
            }  
                        
        });
        t.start();
        return pnPublicidad;
    }
    
    private VBox createRootTicket(){
        Font font = Font.font("Arial", FontWeight.LIGHT, FontPosture.REGULAR, 20);
        Label lblId = new Label("Identificador: ");
        lblId.setFont(font);
        TextField txtId = new TextField();
        txtId.setFont(font);
        Label lblTipoPersona = new Label("Tipo de persona: ");
        lblTipoPersona.setFont(font);
        ObservableList<TipoPersona> items=FXCollections.observableArrayList();
        items.addAll(TipoPersona.CAPACIDADESESPECIALES,TipoPersona.NORMAL,TipoPersona.TERCERAEDAD);
        
        ComboBox tipo=new ComboBox(items);
        
        GridPane gp = new GridPane();
        gp.setTranslateX(20);
        gp.setTranslateY(20);
        gp.setVgap(20);
        gp.setHgap(20);
        gp.add(lblId, 0, 0);
        gp.add(txtId, 1, 0);
        gp.add(lblTipoPersona, 0, 1);
        gp.add(tipo, 1, 1);
        
        
        VBox rootTicket = new VBox();
        StackPane btnOk = Utilities.boton("ButtonRed");
        Label lblOk = new Label("Ok");
        lblOk.setFont(CONSTANTES.MYFONT);
        lblOk.setTextFill(Color.WHITE);
        btnOk.getChildren().add(lblOk);
        btnOk.setTranslateY(25);
        btnOk.setOnMouseClicked(e->{
            if(tipo.getSelectionModel().getSelectedItem()!=null && !txtId.getText().equals("")){
                TipoPersona selected = (TipoPersona)tipo.getSelectionModel().getSelectedItem();
                Migraciones mig = Main.getMigraciones();
                Ticket ticket = new Ticket(txtId.getText(),selected);
                String turno;
                switch (selected) {
                    case CAPACIDADESESPECIALES:
                        mig.setContCapEsp(mig.getContCapEsp()+1);
                        turno = "A" + String.valueOf(mig.getContCapEsp()%100);
                        break;
                    case TERCERAEDAD:
                        mig.setContTerEd(mig.getContTerEd()+1);
                        turno = "B"+String.valueOf(mig.getContTerEd()%100);
                        break;
                    default:
                        mig.setContNorm(mig.getContNorm()+1);
                        turno = "C"+String.valueOf(mig.getContNorm()%100);
                        break;
                }
                ticket.setTurno(turno);
            
                Iterator<PuestoAtencion> itPuestos = mig.getPuestosAtencion().iterator();
                int i = 1;
                while(itPuestos.hasNext()&&ticket.getPuesto()==0){
                    PuestoAtencion puesto = itPuestos.next();
                    if(puesto.getEmpleado()!=null && puesto.isDisponible()){
                        puesto.setDisponible(false);
                        puesto.setTicket(ticket);
                        ticket.setPuesto(i);
                    }
                    i++;
                }
                if(ticket.getPuesto()==0) mig.getColaAtencion().offer(ticket);
                Pane p = createRoot();
                root.getScene().setRoot(p);
                root = p;
                Stage st = (Stage) rootTicket.getScene().getWindow();
                st.close();
            }else{
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Datos insuficientes");
                alerta.setContentText("Por favor rellene todos los campos del ticket");
                alerta.showAndWait();
            }
            
        });
        rootTicket.setSpacing(20);
        rootTicket.getChildren().addAll(gp,btnOk);
        return rootTicket;
    }
    
}
