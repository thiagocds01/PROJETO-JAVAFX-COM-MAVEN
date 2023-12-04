package br.com.salvapets.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection conectar() {
        try {
            String url = "jdbc:mysql://localhost:3306/pet";
            String usuario = "root";
            String senha = "Ramises229@";
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}