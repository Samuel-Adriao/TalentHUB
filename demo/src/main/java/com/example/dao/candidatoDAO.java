package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.example.model.Candidato;
 
public class candidatoDAO {
    public void cadastrarCandidato(String nome, String email, String telefone, String cpf, String data_nascimento, String cidade, String bairro, String estado, String cep, String formacao, String experiencia) {
        String sql = "INSERT INTO candidatos (nome, email, telefone, cpf, data_nascimento, cidade, bairro, estado, cep, formacao, experiencia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, nome);
                stmt.setString(2, email);
                stmt.setString(3, telefone);
                stmt.setString(4, cpf);
                stmt.setString(5, data_nascimento);
                stmt.setString(6, cidade);
                stmt.setString(7, bairro);
                stmt.setString(8, estado);
                stmt.setString(9, cep);
                stmt.setString(10, formacao);
                stmt.setString(11, experiencia);


                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Informações gravadas com sucesso!");
                } else {
                    System.out.println("Erro ao gravar informações no banco!");
                }
                
        } catch (SQLException erro) {
            System.out.println("Erro ao acessar o banco de dados!" + erro.getMessage());
        }
    }

    public void atualizarCandidato(Candidato candidato) {
        String sql = "UPDATE candidatos SET nome = ?, email = ?, telefone = ?, cpf = ?, data_nascimento = ?, cidade = ?, bairro = ?, estado = ?, cep = ?, formacao = ?, experiencia = ? WHERE id_candidato = ?";

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, candidato.getNome());
                stmt.setString(2, candidato.getEmail());
                stmt.setString(3, candidato.getTelefone());
                stmt.setString(4, candidato.getCpf());
                stmt.setString(5, candidato.getDataNascimento());
                stmt.setString(6, candidato.getCidade());
                stmt.setString(7, candidato.getBairro());
                stmt.setString(8, candidato.getEstado());
                stmt.setString(9, candidato.getCep());
                stmt.setString(10, candidato.getFormacao());
                stmt.setString(11, candidato.getExperiencia());
                stmt.setInt(12, candidato.getId());

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Informações gravadas com sucesso!");
                } else {
                    System.out.println("Erro ao gravar informações no banco!");
                }

        } catch (SQLException erro) {
            System.out.println("Erro ao acessar banco de dados!" + erro.getMessage());
        }
    }

    public void deletarCandidato(Candidato candidato) {
        String sql = "DELETE FROM candidatos WHERE id_candidato = ?";

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, candidato.getId());

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Informações deletadas com sucesso!");
                } else {
                    System.out.println("Erro ao deletar informações!");
                }


        } catch (SQLException erro) {
                System.out.println("Erro ao acessar o banco de dados!" + erro.getMessage());
        }
    }

    public Candidato consultarCandidato(int id) {
        String sql = "SELECT * FROM candidatos WHERE id_candidato = ?";
        Candidato candidato = null;

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); 

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    candidato = new Candidato();
                    candidato.setId(rs.getInt("id_candidato"));
                    candidato.setNome(rs.getString("nome"));
                    candidato.setEmail(rs.getString("email"));
                    candidato.setTelefone(rs.getString("telefone"));
                }                
            }     
                
        } catch (SQLException erro) {
            System.out.println("Erro ao acessar banco de dados!" + erro.getMessage());
        }
        return candidato;
    }
}