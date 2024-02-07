package br.com.salvapets.domain;

import br.com.salvapets.dao.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

dsdsdsds

public class OperacoesCrud {

    public List<Pet> listarRegistros() {
        List<Pet> pets = new ArrayList<>();

        try (Connection conexao = Conexao.conectar()) {
            String sql = "SELECT id, nome, cor, historia, porteraca, sexo, idade, raca, imagem FROM pet";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Pet pet = new Pet();

                        pet.setId(rs.getInt("id"));
                        pet.setNome(rs.getString("nome"));
                        pet.setPorteRaca(rs.getString("porteRaca"));
                        pet.setCor(rs.getString("cor"));
                        pet.setSexo(rs.getString("sexo"));
                        pet.setIdade(rs.getString("idade"));
                        pet.setRaca(rs.getString("raca"));
                        pet.setHistoria(rs.getString("historia"));
                        pet.setImagem(rs.getBytes("imagem"));
 
                        pets.add(pet);
                    }
                }
            }
        } catch (SQLException e) {
            e.getMessage();
            throw new RuntimeException("Erro ao listar pets: " + e.getMessage());
        }

        return pets;
    }


    public void cadastrarRegistro(Pet pet) {
        try (Connection conexao = Conexao.conectar()) {
            String sql = "INSERT INTO pet (nome, raca, porteRaca, sexo, cor, idade, historia, imagem) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, pet.getNome());
                stmt.setString(2, pet.getRaca());
                stmt.setString(3, pet.getPorteRaca());
                stmt.setString(4, pet.getSexo());
                stmt.setString(5, pet.getCor());
                stmt.setString(6, pet.getIdade());
                stmt.setString(7, pet.getHistoria());
                Blob imagemBlob = conexao.createBlob();
                imagemBlob.setBytes(1, pet.getImagem());
                stmt.setBlob(8, imagemBlob);



                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar pet : " + e.getMessage());
        }
    }


    public void atualizarRegistro(Pet pet) {
        try (Connection conexao = Conexao.conectar()) {
            String sql = "UPDATE pet SET nome=?, raca=?, porteRaca=?, sexo=?, cor=?, idade=?, historia=?, imagem=? WHERE id="+ pet.getId();
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, pet.getNome());
                stmt.setString(2, pet.getRaca());
                stmt.setString(3, pet.getPorteRaca());
                stmt.setString(4, pet.getSexo());
                stmt.setString(5, pet.getCor());
                stmt.setString(6, pet.getIdade());
                stmt.setString(7, pet.getHistoria());  

                Blob imagemBlob = conexao.createBlob();
                imagemBlob.setBytes(1, pet.getImagem());
                stmt.setBlob(8, imagemBlob);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar pet : " + e.getMessage());
        }
    }

    public void excluirRegistro(int id) {
        try (Connection conexao = Conexao.conectar()) {
            String sql = "DELETE FROM pet WHERE id=?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir pet: " + e.getMessage());
        }
    }

}
