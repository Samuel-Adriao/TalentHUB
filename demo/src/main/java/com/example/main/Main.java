package com.example.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    // Basicamente esse main ele vai carregar a primeira tela do sistema, e montar automaticamente o que vier a seguir
    @Override 
    public void start(Stage primaryStage) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/com/example/view/painelCandidato.fxml"));

            primaryStage.setTitle("Perfil do Candidato");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
