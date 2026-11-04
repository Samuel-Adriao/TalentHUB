package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import com.example.model.Candidatura;
import com.example.model.Vaga;

public class CandidaturaDAO {

    // Essa função serve para o candidato se inscrever em alguma candidatura disponível no sistema, por meio do id candidato e id vaga
    public boolean inscreverCandidatura(int id_candidato, int idVaga) {
        String sql = "INSERT INTO candidatura (id_candidato, id_vaga) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_candidato);
            stmt.setInt(2, idVaga);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Informacoes da candidatura gravadas com sucesso!");
                return true;
            } else {
                System.out.println("Erro ao gravar informacoes da candidatura no banco!");
                return false;
            }

        } catch (SQLException  erro) {
            System.out.println("Erro ao realizar inscrição da candidatura no banco de dados!" + erro.getMessage());
            return false;
        }
    }
    
    // Essa função é uma lista, que busca todas as candidaturas que o candidato realizou alguma inscrição, retornando no final a lista completa, ela funciona através do id do candidato
    public List<Candidatura> acompanharCandidatura(int id_candidato) {
        String sql = "SELECT candidatura.id_candidatura, candidatura.id_vaga, vaga.titulo, vaga.empresa, vaga.salario, candidatura.dataCandidatura FROM vaga INNER JOIN candidatura ON vaga.id_vaga = candidatura.id_vaga WHERE id_candidato = ?";
        List<Candidatura> listaCandidaturas = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                
            stmt.setInt(1, id_candidato);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    Candidatura candidatura = new Candidatura();
                    Vaga vaga = new Vaga();
                    candidatura.setIdCandidatura(rs.getInt("id_candidatura"));
                    candidatura.setIdVaga(rs.getInt("id_vaga"));
                    vaga.setTitulo(rs.getString("titulo"));
                    vaga.setEmpresa(rs.getString("empresa"));
                    vaga.setSalario(rs.getDouble("salario"));
                    candidatura.setDataCandidatura(rs.getString("dataCandidatura"));
                    candidatura.setVaga(vaga);

                    listaCandidaturas.add(candidatura);      
                }
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao acessar o banco de dados!" + erro.getMessage());
        }
        return listaCandidaturas;
    }

    // Essa função é responsável por deletar a candidatura escolhida pelo candidato, por meio do id do candidato e id vaga
    public boolean deletarCandidatura(int id_candidato, int idVaga) {
        String sql = "DELETE FROM candidatura WHERE id_candidato = ? AND id_vaga = ?";

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id_candidato);
                stmt.setInt(2, idVaga);

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Informacoes deletadas com sucesso!");
                    return true;
                } else {
                    System.out.println("Erro ao deletar informacoes da candidatura!");
                    return false;
                }
            
        } catch (SQLException erro) {
            System.out.println("Erro ao realizar exclusão da candidatura no banco de dados!" + erro.getMessage());
            return false;
        }

    }

    // Método responsável por verificar se o candidato se inscreveu em alguma candidatura, verificando id candidato e id vaga, e vendo se a candidatura possui ambos id's
    // Serve para não permitir o candidato de se inscrever na mesma vaga mais de uma vez
    public boolean verificarCandidaturaExistente(int id_candidato, int idVaga) {
        String sql = "SELECT COUNT(id_candidatura) AS total FROM candidatura WHERE id_candidato = ? AND id_vaga = ?";

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id_candidato);
                stmt.setInt(2, idVaga);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int total = rs.getInt("total");
                        return total > 0;
                    }
                }

        } catch (SQLException erro) {
            erro.printStackTrace();
            return true;
        }
        return false;
    }
}
