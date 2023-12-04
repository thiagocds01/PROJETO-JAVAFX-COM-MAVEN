package PI_Salva_Pets;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InserirPets {

    public static void inserir(Pets pets){
        try {
            Connection con = TestandoConexao.getTestandoConexao();
            try{
                //Parte da Imagem (tratamento)
                Blob imagemBlob = con.createBlob();
                imagemBlob.setBytes(1, pets.getImagem());

                //Aqui já é a inserçao)
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO pets (nome, raca, tamanho, sexo, cor, idade, historia, imagem) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ");
                pstmt.setString(1, pets.getNome());
                pstmt.setString(2, pets.getRaca());
                pstmt.setString(3, pets.getTamanho());
                pstmt.setString(4, pets.getSexo());
                pstmt.setString(5, pets.getCor());
                pstmt.setInt(6, pets.getIdade());
                pstmt.setString(7, pets.getHistoria());
                pstmt.setBlob(8, imagemBlob);
                pstmt.executeUpdate();

            } finally {
                con.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
