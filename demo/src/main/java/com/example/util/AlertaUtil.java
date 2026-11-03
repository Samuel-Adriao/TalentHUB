package com.example.util;

import javafx.scene.control.Alert;

public class AlertaUtil {

    public static void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    public static void mostrarAlerta(Alert.AlertType tipo, String titulo, String cabecalho, String mensagem) {
        Alert alerta = new Alert(tipo); 
        alerta.setTitle(titulo);        
        alerta.setHeaderText(cabecalho);     
        alerta.setContentText(mensagem);
        alerta.showAndWait();           
    }
}
