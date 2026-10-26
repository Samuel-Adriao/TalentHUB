package com.example.controller;

import com.example.config.SessaoLogada;
import com.example.dao.candidatoDAO;
import com.example.model.Candidato;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class candidatoController {

    @FXML private TextField txtNome;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTelefone;
    @FXML private TextField txtCpf;
    @FXML private TextField txtDataNascimento;
    @FXML private TextField txtCidade;
    @FXML private TextField txtBairro;
    @FXML private TextField txtEstado;
    @FXML private TextField txtCep;
    @FXML private TextField txtFormacao;
    @FXML private TextField txtExperiencia;
    private int idLogado;

    private candidatoDAO candidatoDAO = new candidatoDAO();

    @FXML
    public void initialize() {
        idLogado = SessaoLogada.getIdLogado();

        Candidato candidatoLogado = candidatoDAO.consultarCandidato(idLogado);
        txtNome.setText(candidatoLogado.getNome());
        txtEmail.setText(candidatoLogado.getEmail());
        txtTelefone.setText(candidatoLogado.getTelefone());
        txtCpf.setText(candidatoLogado.getCpf());
        txtDataNascimento.setText(candidatoLogado.getDataNascimento());
        txtCidade.setText(candidatoLogado.getCidade());
        txtBairro.setText(candidatoLogado.getBairro());
        txtEstado.setText(candidatoLogado.getEstado());
        txtCep.setText(candidatoLogado.getCep());
        txtFormacao.setText(candidatoLogado.getFormacao());
        txtExperiencia.setText(candidatoLogado.getExperiencia());

    }

    @FXML 
    private void handleSalvar() {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String telefone = txtTelefone.getText();
        String cpf = txtCpf.getText();
        String data_nascimento = txtDataNascimento.getText();
        String cidade = txtCidade.getText();
        String bairro = txtBairro.getText();
        String estado = txtEstado.getText();
        String cep = txtCep.getText();
        String formacao = txtFormacao.getText();
        String experiencia = txtExperiencia.getText();

        Candidato candidato = new Candidato();
        candidato.setId(idLogado);
        candidato.setNome(nome);
        candidato.setEmail(email);
        candidato.setTelefone(telefone);
        candidato.setCpf(cpf);
        candidato.setDataNascimento(data_nascimento);
        candidato.setCidade(cidade);
        candidato.setBairro(bairro);
        candidato.setEstado(estado);
        candidato.setCep(cep);
        candidato.setFormacao(formacao);
        candidato.setExperiencia(experiencia);

        candidatoDAO.atualizarCandidato(candidato);
    
    } 
}
