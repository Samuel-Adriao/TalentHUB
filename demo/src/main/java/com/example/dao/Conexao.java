package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection conectar() throws SQLException {
        String url = System.getenv("DB_URL");
        String usuario = System.getenv("DB_USER");
        String senha = System.getenv("DB_PASS");

        return DriverManager.getConnection(url, usuario, senha);
    }
}