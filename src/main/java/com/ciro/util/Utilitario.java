package com.ciro.util;

import javafx.scene.control.Alert;

public class Utilitario {
    public static final double ALTURA_600 = 600;
    public static final double ANCHO_1100 = 1100;

    public static final double ALTURA_400 = 400;
    public static final double ANCHO_600 = 600;

    public static final double ALTURA_250 = 250;
    public static final double ANCHO_250 = 250;

    public static final double ALTURA_200 = 200;

    public static void showMessageAlert(String titulo, String mensaje){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(mensaje);
        alert.showAndWait();
    }
}
