package com.example.controller;

import com.example.config.SessaoLogada;
import com.example.dao.candidatoDAO;
import com.example.model.Candidato;
import com.example.model.Candidatura;
import com.example.dao.CandidaturaDAO;
import com.example.dao.VagaDAO;
import com.example.model.Vaga;
import com.example.util.AlertaUtil;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    @FXML private TableColumn<Vaga, String> colunaDescricao;
    @FXML private TableColumn<Vaga, String> colunaDataPublicacao;
    @FXML private TableColumn<Vaga, Double> colunaSalario;
    
    @FXML private TableColumn<Candidatura, String> colunaTituloVaga;
    @FXML private TableColumn<Candidatura, String> colunaEmpresaCandidatura;
    @FXML private TableColumn<Candidatura, Double> colunaSalarioCandidatura;
    @FXML private TableColumn<Candidatura, String> colunaDataCandidatura;


    private candidatoDAO candidatoDAO = new candidatoDAO();
    private CandidaturaDAO candidaturaDAO = new CandidaturaDAO();
    private VagaDAO vagaDAO = new VagaDAO();

    @FXML
    public void initialize() {

        SessaoLogada.setIdLogado(1);
        idLogado = SessaoLogada.getIdLogado();

        if (idLogado <= 0) {
            return;
        }

        if (candidatoDAO.consultarCandidato(idLogado) == null) {
            System.out.println("Usuário não encontrado no sistema");
            return;
        }

            colunaVagas.setCellValueFactory(new PropertyValueFactory<>("Titulo"));
            colunaRequisitos.setCellValueFactory(new PropertyValueFactory<>("Requisitos"));
            colunaEmpresa.setCellValueFactory(new PropertyValueFactory<>("Empresa"));
            colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
            colunaDataPublicacao.setCellValueFactory(new PropertyValueFactory<>("DataPublicacao"));
            colunaSalario.setCellValueFactory(new PropertyValueFactory<>("Salario"));

            colunaTituloVaga.setCellValueFactory(celula -> new ReadOnlyStringWrapper(celula.getValue().getVaga().getTitulo()));
            colunaEmpresaCandidatura.setCellValueFactory(celula -> new ReadOnlyStringWrapper(celula.getValue().getVaga().getEmpresa()));
            colunaSalarioCandidatura.setCellValueFactory(celula -> new ReadOnlyObjectWrapper<>(celula.getValue().getVaga().getSalario()));
            colunaDataCandidatura.setCellValueFactory(new PropertyValueFactory<>("DataCandidatura"));


            carregarDadosPerfil();
            carregarDadosVagas();
            carregarDadosCandidatura();
    }

    @FXML 
    private void handleSalvar() {
        String nome = txtNome.getText();
        String email = txtEmail.getText().trim();
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

        if (nome.trim().isEmpty() || cpf.trim().isEmpty() || email.isEmpty() || telefone.trim().isEmpty() || data_nascimento.trim().isEmpty() || formacao.trim().isEmpty() || experiencia.trim().isEmpty()) {
            AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Atenção...", "Por favor, preencha os campos obrigatórios de forma correta! (Não deve deixar os campos vazios!)");
            return;
        } 

        if (!email.contains("@") || !email.contains(".")) {
            AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Atenção...", "Por favor, preencha os campos obrigatórios de forma correta! (Todo email deve conter '@' e '.')");
            return;
        }

            boolean atualizar = candidatoDAO.atualizarCandidato(candidato);

            if (atualizar) {
                AlertaUtil.mostrarAlerta(Alert.AlertType.INFORMATION, "Confirmando dados...", "Dados atualizados com sucesso!");
            } else {
                AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Não foi possível atualizar os dados do perfil! Tente novamente.");
            }
    } 

    @FXML 
    private void handleCandidatar() {

        Vaga vagaSelecionada = tabelaVagas.getSelectionModel().getSelectedItem();
        if (vagaSelecionada == null) {
            AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Seleção Vazia", "Por favor, escolha uma vaga na tabela para se candidatar!");
            return;
        }

        if (candidaturaDAO.verificarCandidaturaExistente(idLogado, vagaSelecionada.getIdVaga()) == true) {
            AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Erro ao selecionar vaga!", "Não é possível se inscrever na mesma vaga! Fique atento.");
            tabelaVagas.getSelectionModel().clearSelection();
            return;
        }

        boolean inscrever = candidaturaDAO.inscreverCandidatura(idLogado, vagaSelecionada.getIdVaga());
        if (inscrever) {
            AlertaUtil.mostrarAlerta(Alert.AlertType.INFORMATION, "Completando inscrição...", "Inscrição confirmada! Parabéns.");
            tabelaVagas.getSelectionModel().clearSelection();
            carregarDadosVagas();
            carregarDadosCandidatura();
        } else {
            AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Não foi possível realizar inscrição na candidatura selecionada! Tente novamente.");
        }
    }

    @FXML 
    private void handleCancelarCandidatura() {
        Candidatura candidaturaSelecionada = tabelaCandidatura.getSelectionModel().getSelectedItem();
        if (candidaturaSelecionada == null) {
            AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Seleção Vazia", "Por favor, escolha uma candidatura válida!");
            return;
        }

        boolean deletarCandidatura = candidaturaDAO.deletarCandidatura(idLogado, candidaturaSelecionada.getIdVaga());
        if (deletarCandidatura) {
            AlertaUtil.mostrarAlerta(Alert.AlertType.INFORMATION, "Confirmando exclusão...", "Exclusão confirmada e candidatura retirada.");
            carregarDadosCandidatura();
        } else {
            AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Não foi possível realizar exclusão da inscrição da candidatura selecionada! Tente novamente.");
        }
    }

    @FXML
    private void handleExcluirPerfil() {
        Alert alertaConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacao.setTitle("Excluir Conta");
        alertaConfirmacao.setHeaderText("Certeza que deseja excluir a conta?");
        alertaConfirmacao.setContentText("Esta ação não poderá ser desfeita.");

        java.util.Optional<javafx.scene.control.ButtonType> resultado = alertaConfirmacao.showAndWait();
        if (resultado.isPresent() && resultado.get() == javafx.scene.control.ButtonType.OK) {

            boolean deletarCandidato = candidatoDAO.deletarCandidato(idLogado);
            if (deletarCandidato) {
                AlertaUtil.mostrarAlerta(Alert.AlertType.INFORMATION, "Conta Excluída", "Sua conta foi excluída com sucesso!");
                Stage stageAtual = (Stage) txtNome.getScene().getWindow();
                stageAtual.close();
            } else {
                AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Não foi possível excluir o perfil! Tente novamente.");
            }
        }
    }

    public void carregarDadosPerfil() {

        Candidato candidatoLogado = candidatoDAO.consultarCandidato(idLogado);
        if (candidatoLogado != null) {
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
        } else {
            AlertaUtil.mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar os dados do perfil!");
        }
    }

    public void carregarDadosVagas() {                                                     
        List<Vaga> listaDeVagas = vagaDAO.consultarTodasVagas();    
        ObservableList<Vaga> dadosTabelaVaga = FXCollections.observableArrayList(listaDeVagas);
        tabelaVagas.setItems(dadosTabelaVaga);                                                 
    }                                                                                          

    public void carregarDadosCandidatura() {
        List<Candidatura> listaDeCandidaturas = candidaturaDAO.acompanharCandidatura(idLogado);
        ObservableList<Candidatura> dadosTabelaCandidatura = FXCollections.observableArrayList(listaDeCandidaturas);
        tabelaCandidatura.setItems(dadosTabelaCandidatura);
    }
}
