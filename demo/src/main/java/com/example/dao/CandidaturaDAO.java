package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import com.example.model.Candidatura;


public class CandidaturaDAO {
    public void inscreverCandidatura(int id_candidato, int idVaga) {
        String sql = "INSERT INTO candidaturas (id_candidato, id_vaga) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_candidato);
            stmt.setInt(2, idVaga);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Informações gravadas com sucesso!");
            } else {
                System.out.println("Erro ao gravar informações no banco!");
            }

        } catch (SQLException  erro) {
            System.out.println("Erro ao acessar o banco de dados!" + erro.getMessage());
        }
    }
    
    public List<Candidatura> acompanharCandidatura(int id_candidato) {
        String sql = "SELECT * FROM candidatura WHERE id_candidato = ?";
        List<Candidatura> listaCandidaturas = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                
            stmt.setInt(1, id_candidato);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    Candidatura candidatura = new Candidatura();
                    candidatura.setIdCandidatura(rs.getInt("id_candidatura"));
                    candidatura.setIdCandidato(rs.getInt("id_candidato"));
                    candidatura.setIdVaga(rs.getInt("id_vaga"));
                    candidatura.setDataCandidatura(rs.getString("data_candidatura"));

                    listaCandidaturas.add(candidatura);      
                }
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao acessar o banco de dados!" + erro.getMessage());
        }
        return listaCandidaturas;
    }
}
