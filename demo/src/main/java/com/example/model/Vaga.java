package com.example.model;

public class Vaga {
     
    private int idVaga;
    private String titulo;
    private String descricao;
    private String empresa;
    private String requisitos;
    private double salario;
    private String data_publicacao;

    public Vaga() { 
    }

    public Vaga(int idVaga, String titulo, String descricao, String empresa, String requisitos, double salario, String data_publicacao) {
        this.idVaga = idVaga;
        this.titulo = titulo;
        this.descricao = descricao;
        this.empresa = empresa;
        this.requisitos = requisitos;
        this.salario = salario;
        this.data_publicacao = data_publicacao;
    }

    public int getIdVaga() {
        return idVaga;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getEmpresa() {
        return empresa;
    }
    public String getRequisitos() {
        return requisitos;
    }
    public double getSalario() {
        return salario;
    }
    public String getDataPublicacao() {
        return data_publicacao;
    }

    public void setIdVaga(int idVaga) {
        this.idVaga = idVaga;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    public void setDataPublicacao(String data_publicacao) {
        this.data_publicacao = data_publicacao;
    }
}
