package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import com.example.model.Vaga;

public class VagaDAO {
    public Vaga consultarVagas(int idVaga) {
        String sql = "SELECT * FROM vagas WHERE id_vaga = ?";
        Vaga vaga = null;

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVaga);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vaga = new Vaga();
                    vaga.setIdVaga(rs.getInt("id_vaga"));
                    vaga.setTitulo(rs.getString("titulo"));
                    vaga.setDescricao(rs.getString("descricao"));
                    vaga.setEmpresa(rs.getString("empresa"));
                    vaga.setRequisitos(rs.getString("requisitos"));
                    vaga.setSalario(rs.getDouble("salario"));
                    vaga.setDataPublicacao(rs.getString("data_publicacao"));
                }
            }
            
        } catch (SQLException erro) {
            System.out.println("Não foi possivel acessar o banco de dados!" + erro.getMessage());
        }
        return vaga;
    }

    public List<Vaga> consultarTodasVagas() {
        String sql = "SELECT * FROM vagas";
        List<Vaga> listaVagas = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql); 
            ResultSet rs = stmt.executeQuery()) {

            while(rs.next()) {
                Vaga vaga = new Vaga();
                vaga.setIdVaga(rs.getInt("id_vaga"));
                vaga.setTitulo(rs.getString("titulo"));
                vaga.setDescricao(rs.getString("descricao"));
                vaga.setEmpresa(rs.getString("empresa"));
                vaga.setRequisitos((rs.getString("requisitos")));
                vaga.setSalario(rs.getDouble("salario"));
                vaga.setDataPublicacao(rs.getString("data_publicacao"));

                listaVagas.add(vaga);
            }

        } catch (SQLException erro) {
            System.out.println("Não foi possivel acessar o banco de dados!" + erro.getMessage());
        }
        return listaVagas;
    }
}
