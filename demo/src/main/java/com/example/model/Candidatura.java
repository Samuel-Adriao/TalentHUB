package com.example.model;

public class Candidatura {

    private int idCandidatura;
    private int idCandidato;
    private int idVaga;
    private String dataCandidatura;

    public Candidatura(){
    }

    public Candidatura(int idCandidatura, int idCandidato, int idVaga, String dataCandidatura) {
        this.idCandidatura = idCandidatura;
        this.idCandidato = idCandidato;
        this.idVaga = idVaga;
        this.dataCandidatura = dataCandidatura;
    }

    public int getIdCandidatura() {
        return idCandidatura;
    }
    public int getIdCandidato() {
        return idCandidato;
    }
    public int getIdVaga() {
        return idVaga;
    }
    public String getDataCandidatura() {
        return dataCandidatura;
    }
    public void setIdCandidatura(int idCandidatura) {
        this.idCandidatura = idCandidatura;
    }
    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }
    public void setIdVaga(int idVaga) {
        this.idVaga = idVaga;
    }
    public void setDataCandidatura(String dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }
}
