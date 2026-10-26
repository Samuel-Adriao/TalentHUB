package com.example.config;

public class SessaoLogada {

    private static int idCandidatoLogado;
    
    public SessaoLogada() {
    }

    public SessaoLogada(int idRecebido) {
        idCandidatoLogado = idRecebido;
    }

    public static int getIdLogado() {
        return idCandidatoLogado;
    }

    public static void setIdLogado(int idRecebido) {
        idCandidatoLogado = idRecebido;
    }
}
