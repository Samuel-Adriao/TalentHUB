package com.example.model;

public class Candidato {    

    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String data_nascimento;
    private String cidade;
    private String bairro;
    private String estado;
    private String cep;
    private String formacao;
    private String experiencia;

    public Candidato() {
    }
    
    public Candidato(int id, String nome, String email, String telefone, String cpf, String data_nascimento, String cidade, String bairro, String estado, String cep, String formacao, String experiencia) {
         this.id = id;
         this.nome = nome;
         this.email = email;
         this.telefone = telefone;
         this.cpf = cpf;
         this.data_nascimento = data_nascimento;
         this.cidade = cidade;
         this.bairro = bairro;
         this.estado = estado;
         this.cep = cep;
         this.formacao = formacao;
         this.experiencia = experiencia;
    }

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getCpf() {
        return cpf;
    }
    public String getDataNascimento() {
        return data_nascimento;
    }
    public String getCidade() {
        return cidade;
    }
    public String getBairro() {
        return bairro;
    }
    public String getEstado() {
        return estado;
    }
    public String getCep() {
        return cep;
    }
    public String getFormacao() {
        return formacao;
    }
    public String getExperiencia() {
        return experiencia;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setDataNascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }
    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
}
