package com.ciro.util;

import javafx.scene.control.Alert;

public class Utilitario {
    public static final double ALTURA_PANTALLA_GRANDE = 600;
    public static final double ANCHO_PANTALLA_GRANDE = 1100;

    public static final double ALTURA_PANTALLA_PEQUENIA = 400;
    public static final double ANCHO_PANTALLA_PEQUENIA = 600;

    public static final double ALTURA_CREAR_PROYECTO = 250;
    public static final double ANCHO_CREAR_PROYECTO = 250;

    public static void showMessageAlert(String titulo, String mensaje){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(mensaje);
        alert.showAndWait();
    }
}
