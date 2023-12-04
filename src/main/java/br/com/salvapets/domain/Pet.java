package br.com.salvapets.domain;


public class Pet {
    private int id;
    private String nome;
    private String raca;
    private String porteRaca;
    private String sexo;
    private String cor;
    private String idade;
    private String historia;
    private byte[] imagem;

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPorteRaca() {
        return porteRaca;
    }

    public void setPorteRaca(String porteRaca) {
        this.porteRaca = porteRaca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

/*     @Override
    public String toString() {
        return "Pet [id=" + id + ", nome=" + nome + ", raca=" + raca + ", porteRaca=" + porteRaca + ", sexo=" + sexo
                + ", cor=" + cor + ", idade=" + idade + ", historia=" + historia + ", imagem=" + Arrays.toString(imagem)
                + "]";
    } */
}
