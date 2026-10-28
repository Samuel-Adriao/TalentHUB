package com.example.controller;

import com.example.config.SessaoLogada;
import com.example.dao.candidatoDAO;
import com.example.model.Candidato;
import com.example.model.Candidatura;
import com.example.dao.CandidaturaDAO;
import com.example.dao.VagaDAO;
import com.example.model.Vaga;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

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

    @FXML private TableView<Vaga> tabelaVagas;
    @FXML private TableView<Candidatura> tabelaCandidatura;

    @FXML private TableColumn<Vaga, String> colunaVagas;
    @FXML private TableColumn<Vaga, String> colunaRequisitos;
    @FXML private TableColumn<Vaga, String> colunaEmpresa;

    private candidatoDAO candidatoDAO = new candidatoDAO();
    private CandidaturaDAO candidaturaDAO = new CandidaturaDAO();

    @FXML
    public void initialize() {

        idLogado = SessaoLogada.getIdLogado();

        if (idLogado > 0) {

            colunaVagas.setCellValueFactory(new PropertyValueFactory<>("titulo"));
            colunaRequisitos.setCellValueFactory(new PropertyValueFactory<>("requisitos"));
            colunaEmpresa.setCellValueFactory(new PropertyValueFactory<>("empresa"));

            carregarDadosPerfil();
            carregarDadosCandidatura();
            carregarDadosVagas();
        }
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

        if (txtNome.getText().isEmpty() || txtCpf.getText().isEmpty() || txtEmail.getText().isEmpty() || txtTelefone.getText().isEmpty() || txtDataNascimento.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Atenção...");
            alerta.setContentText("Por Favor, preencha os campos obrigatórios!");
            alerta.showAndWait();

        } else {
            candidatoDAO.atualizarCandidato(candidato);
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Confirmando Dados...");
            alerta.setContentText("Dados atualizados com sucesso!");
            alerta.showAndWait();
        }
    } 

    @FXML 
    private void handleCandidatar() {

        Vaga vagaSelecionada = tabelaVagas.getSelectionModel().getSelectedItem();
        if (vagaSelecionada == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Seleção Vazia");
            alerta.setContentText("Por Favor, escolha uma vaga na tabela para se candidatar!");
            alerta.showAndWait();
            return;
        }

        candidaturaDAO.inscreverCandidatura(idLogado, vagaSelecionada.getIdVaga());
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Completando inscrição...");
        alerta.setContentText("Inscrição confirmada! Parabéns.");
        alerta.showAndWait();
    }

    @FXML 
    private void handleCancelarCandidatura() {
        Candidatura candidaturaSelecionada = tabelaCandidatura.getSelectionModel().getSelectedItem();
        if (candidaturaSelecionada == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Seleção Vazia");
            alerta.setContentText("Por Favor, escolha uma candidatura válida!");
            alerta.showAndWait();
            return;
        }

        candidaturaDAO.deletarCandidatura(idLogado, candidaturaSelecionada.getIdVaga());
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Confirmando Exclusão...");
        alerta.setContentText("Exclusão confirmada e candidatura retirada.");
        alerta.showAndWait();

        carregarDadosCandidatura();
    }

    public void carregarDadosPerfil() {

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

    public void carregarDadosCandidatura() {

        CandidaturaDAO candidaturaDAO = new CandidaturaDAO();
        List<Candidatura> listaDeCandidaturas = candidaturaDAO.acompanharCandidatura(idLogado);

        ObservableList<Candidatura> dadosTabelaCandidatura = FXCollections.observableArrayList(listaDeCandidaturas);
        tabelaCandidatura.setItems(dadosTabelaCandidatura);
    }

    public void carregarDadosVagas() {

        VagaDAO vagaDAO = new VagaDAO();
        List<Vaga> listaDeVagas = vagaDAO.consultarTodasVagas();

        ObservableList<Vaga> dadosTabelaVaga = FXCollections.observableArrayList(listaDeVagas);
        tabelaVagas.setItems(dadosTabelaVaga);
    }
}
