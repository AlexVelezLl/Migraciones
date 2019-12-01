/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Alex Velez
 */
public final class CONSTANTES {
    private CONSTANTES(){}
    public static final double WIDTH =450;
    public static final double HEIGHT =600;
    public static final String RUTA_IMGS="/recursos/imagenes/";
    public static final String RUTA_TEXT="src/recursos/texto/";
    public static final Font MYFONT = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 25);
    public static final String GRADIENTE ="-fx-background-color:linear-gradient(from 0% 93% to 0% 100%, #0404B4 0%, #0404B4 100%),#0404B4,#0404B4,radial-gradient(center 50% 50%, radius 100%,#5882FA, #08088A)";

}
