package utilities;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
}
