package com.example.util;

import javafx.scene.control.Alert;

//Essa classe serve como estrutura de alertas, possuindo 2 métodos principais
//Os métodos são responsáveis por apresentar as telas de alerta, por exemplo um erro presente, ou uma informação crucial no sistema
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
