package PI_Salva_Pets;

import java.io.FileInputStream;
import java.io.IOException;

public class Pets {
    private String nome;
    private String raca;
    private String tamanho;
    private String sexo;
    private String cor;
    private int id;
    private int idade;
    private String historia;
    private byte [] imagem;

    public Pets(String nome,String raca, String tamanho, String sexo, String cor, int idade, String historia, String imagemPath){
        this.nome= nome;
        this.raca = raca;
        this.tamanho = tamanho;
        this.sexo = sexo;
        this.cor = cor;
        this.idade = idade;
        this.historia = historia;
        this.imagem = carregarImagem(imagemPath);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }


    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    //Parte da IMAGEM!!

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    private byte[] carregarImagem(String imagemPath) {
        try (FileInputStream fileInputStream = new FileInputStream(imagemPath)) {
            byte[] imageData = new byte[fileInputStream.available()];
            fileInputStream.read(imageData);
            return imageData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
