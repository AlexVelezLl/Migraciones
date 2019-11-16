/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import javafx.scene.Scene;

/**
 *
 * @author Alex Velez
 */
public final class Controlador {
    private static Scene sc;
    public static void setScene(Scene sc){
        Controlador.sc=sc;
    }
    
    public static Scene getScene(){
        return sc;
    }
}
