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
    private Utilities(){}
    
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
