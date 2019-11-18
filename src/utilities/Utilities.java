package utilities;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alex Velez
 */
public final class Utilities {
    private static final Image imgBlock=new Image(CONSTANTES.RUTA_IMGS+"BG_Block.png");
    private Utilities(){}
    /**
     * Metodo que sirve para hacer una trancision entre dos roots pane en la misma escena
     * En la que se va opacando el primer root y luego va aclarandose el otro.
     * @param root1 Root a reemplazar
     * @param root2 Root que reemplaza
     */
    public static void transition(Pane root1,Pane root2){
        
        Thread t = new Thread(()->{
            ImageView block = new ImageView(imgBlock); 
            block.setOpacity(0);
            Platform.runLater(()->root1.getChildren().add(block));
            for(int i =1; i<=10;i++){
                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
                    Thread.currentThread().interrupt();
                }
                block.setOpacity(i*0.1);   
            }
            Platform.runLater(()->{
                root1.getChildren().remove(block);
                root2.getChildren().add(block);
                Controlador.getScene().setRoot(root2);
            });
                
                
            for(int i =10; i>=0;i--){
                try {
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
                    Thread.currentThread().interrupt();
                }
                block.setOpacity(i*0.1);
            }
            Platform.runLater(()->root2.getChildren().remove(block));
        });
        t.start();
    }
    
    public static StackPane boton(String nameImg){
        StackPane pBoton = new  StackPane();
        Image imgButton = new Image(CONSTANTES.RUTA_IMGS+nameImg+".png");
        Image imgButtonPressed = new Image(CONSTANTES.RUTA_IMGS+nameImg+"Pressed.png");
        ImageView imgVButton = new ImageView(imgButton);
        pBoton.setOnMousePressed(e->{
            imgVButton.setImage(imgButtonPressed);
            Media musicFile = new Media(new File("src/recursos/chestClick.mp3").toURI().toString());
            MediaPlayer mp = new MediaPlayer(musicFile);
            mp.play();
            
        });
        pBoton.setOnMouseReleased(e->imgVButton.setImage(imgButton));
        pBoton.getChildren().add(imgVButton);
        return pBoton;
    }
}
