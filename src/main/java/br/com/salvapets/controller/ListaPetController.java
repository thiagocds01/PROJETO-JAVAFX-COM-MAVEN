package br.com.salvapets.controller;

import br.com.salvapets.domain.OperacoesCrud;
import br.com.salvapets.domain.Pet;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ListaPetController implements Initializable {
    @FXML
    private Button bttnSubmit;

    @FXML
    private Button atualizarUploadButton;

    @FXML
    private Button buttonExcluirListar;

    @FXML
    private Button buttonSalvarListar;

    @FXML
    private Button bootonExcluir;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button buttonSalvar;

    @FXML
    private ImageView imgListar;

    @FXML
    private TextField campoCor;

    @FXML
    private TextArea campoHistoria;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoPorteRaca;

    @FXML
    private TextField campoRaca;

    @FXML
    private ComboBox<String> campoSexo;

    @FXML
    private ComboBox<String> campoDtNascimento;

    @FXML
    private TableColumn<Pet, String> colCor;

    @FXML
    private TableColumn<Pet, Integer> colId;

    @FXML
    private TableColumn<Pet, String> colIdade;

    @FXML
    private TableColumn<Pet, String> colNome;

    @FXML
    private TableColumn<Pet, String> colRaca;

    @FXML
    private TableColumn<Pet, String> colSexo;

    @FXML
    private TableColumn<Pet, byte[]> colImagem;

    @FXML
    private Label labelCor;

    @FXML
    private Label labelDtNascimento;

    @FXML
    private Label labelHistoria;

    @FXML
    private Label labelImagem;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelPorteRaca;

    @FXML
    private Label labelRaca;

    @FXML
    private Label labelSexo;

    @FXML
    private Label msgStatusFalha;

    @FXML
    private Label msgStatusOk;

    @FXML
    private Tab tabCadastro;

    @FXML
    private TableView<Pet> tabListaTudo;

    @FXML
    private Tab tabListar;

    @FXML
    private Text tituloPagina;

    @FXML
    private Button uploadButton;

    @FXML
    private ImageView imgTelaListar;

    @FXML
    private Button uploadImagemListarButton;

    @FXML
    private Button atualizarImagemUploadButton;

    @FXML
    private Button buttonAtualizarLista;

    @FXML
    private TextField inputCor;

    @FXML
    private TextField inputHistoria;

    @FXML
    private TextField inputID;

    @FXML
    private ComboBox<String> inputIdade;

    @FXML
    private TextField inputNome;

    @FXML
    private TextField inputRaca;

    @FXML
    private ComboBox<String> inputSexo;

    @FXML
    private TextField inputTamanho;

    @FXML
    private ImageView imgMostrar;

    private OperacoesCrud operacoesCRUD = new OperacoesCrud();
    private Pet pet = new Pet();

    @FXML
    void handleUploadButtonAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                Integer numBytes = fileInputStream.available();
                byte[] imagemByte = new byte[numBytes];
                int numBytesLidos = fileInputStream.read(imagemByte);
                pet.setImagem(imagemByte);

                fileInputStream.close();

                System.out.println(numBytesLidos);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            }
        }
    }

    @FXML
    void handleButtonCancelar(ActionEvent event) {
        campoNome.setText("");
        campoRaca.setText("");
        campoPorteRaca.setText("");
        campoSexo.setValue("");
        campoCor.setText("");
        campoDtNascimento.setValue("");
        campoHistoria.setText("");
    }

    @FXML
    void handleButtonSalvar(ActionEvent event) {
        String nome = campoNome.getText();
        String raca = campoRaca.getText();
        String porteRaca = campoPorteRaca.getText();
        String sexo = campoSexo.getValue();
        String cor = campoCor.getText();
        String idade = campoDtNascimento.getValue();
        String historia = campoHistoria.getText();

        if (nome.trim().isEmpty() ||
                raca.trim().isEmpty() ||
                porteRaca.trim().isEmpty() ||
                sexo == null || sexo.trim().isEmpty() ||
                cor.trim().isEmpty() ||
                idade.trim().isEmpty() ||
                historia.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios e não podem estar em branco.");
            return;
        }

        pet.setNome(nome);
        pet.setRaca(raca);
        pet.setPorteRaca(porteRaca);
        pet.setSexo(sexo);
        pet.setCor(cor);
        pet.setIdade(idade);
        pet.setHistoria(historia);

        System.out.println(pet.getImagem());

        JOptionPane.showMessageDialog(null, "Cadastrado!");
        operacoesCRUD.cadastrarRegistro(pet);
    }

    @FXML
    void handlebuttonAtualizarLista(ActionEvent event) {

        List<Pet> pets = operacoesCRUD.listarRegistros();
        tabListaTudo.getItems().clear();
        tabListaTudo.getItems().addAll(pets);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        popularComboBoxSexo();
        popularComboBoxDtNascimento();

        List<Pet> pets = operacoesCRUD.listarRegistros();

        TableColumn<Pet, String> colNome = new TableColumn<>("Nome");
        TableColumn<Pet, String> colCor = new TableColumn<>("Cor");
        TableColumn<Pet, String> colHistoria = new TableColumn<>("Historia");
        TableColumn<Pet, String> colSexo = new TableColumn<>("Sexo");
        TableColumn<Pet, String> colIdade = new TableColumn<>("Idade");
        TableColumn<Pet, String> colRaca = new TableColumn<>("Raca");
        TableColumn<Pet, byte[]> colImagem = new TableColumn<>("Imagem");

        colNome.setCellValueFactory(new PropertyValueFactory<Pet, String>("nome"));
        colCor.setCellValueFactory(new PropertyValueFactory<Pet, String>("cor"));
        colSexo.setCellValueFactory(new PropertyValueFactory<Pet, String>("sexo"));
        colIdade.setCellValueFactory(new PropertyValueFactory<Pet, String>("idade"));
        colRaca.setCellValueFactory(new PropertyValueFactory<Pet, String>("raca"));
        colHistoria.setCellValueFactory(new PropertyValueFactory<Pet, String>("historia"));
        colImagem.setCellValueFactory(new PropertyValueFactory<Pet, byte[]>("imagem"));

        tabListaTudo.getColumns().addAll(colNome, colCor, colSexo, colIdade, colRaca, colHistoria);

        tabListaTudo.getItems().addAll(pets);

        tabListaTudo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    Pet petSelecionado = tabListaTudo.getSelectionModel().getSelectedItem();
                    inputNome.setText(String.valueOf(petSelecionado.getNome()));
                    inputRaca.setText(String.valueOf(petSelecionado.getRaca()));
                    inputTamanho.setText(String.valueOf(petSelecionado.getPorteRaca()));
                    inputCor.setText(String.valueOf(petSelecionado.getCor()));
                    inputIdade.setValue(String.valueOf(petSelecionado.getIdade()));
                    inputHistoria.setText(String.valueOf(petSelecionado.getHistoria()));
                    inputSexo.setValue(String.valueOf(petSelecionado.getSexo()));

                }

                if (event.getClickCount() == 1) {
                    Pet petSelecionado = tabListaTudo.getSelectionModel().getSelectedItem();

                    Image imagem = convertByteArrayToImage(petSelecionado.getImagem());

                    imgMostrar.setImage(imagem);

                }
            }
        });

    }

    private Image convertByteArrayToImage(byte[] byteArray) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
            Image imagem = new Image(bis);

            bis.close();

            return imagem;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @FXML
    void handleAtualizarUploadButtonAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                Integer numBytes = fileInputStream.available();
                byte[] imagemByte = new byte[numBytes];
                int numBytesLidos = fileInputStream.read(imagemByte);
                pet.setImagem(imagemByte);

                fileInputStream.close();

                System.out.println(numBytesLidos);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            }
        }

    }

    @FXML
    void atualizarEdit(ActionEvent event) {
        Pet itemSelecionado = tabListaTudo.getSelectionModel().getSelectedItem();
        if (itemSelecionado != null) {
            int idEdit = itemSelecionado.getId();

            String nome = inputNome.getText();
            String raca = inputRaca.getText();
            String porteRaca = inputTamanho.getText();
            String sexo = inputSexo.getValue();
            String cor = inputCor.getText();
            String idade = inputIdade.getValue();
            String historia = inputHistoria.getText();

            if (nome.trim().isEmpty() ||
                    raca.trim().isEmpty() ||
                    porteRaca.trim().isEmpty() ||
                    sexo.trim().isEmpty() ||
                    cor.trim().isEmpty() ||
                    idade.trim().isEmpty() ||
                    historia.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios e não podem estar em branco.");
                return;
            }

            pet.setId(idEdit);
            pet.setNome(nome);
            pet.setRaca(raca);
            pet.setPorteRaca(porteRaca);
            pet.setSexo(sexo);
            pet.setCor(cor);
            pet.setIdade(idade);
            pet.setHistoria(historia);

            operacoesCRUD.atualizarRegistro(pet);
            JOptionPane.showMessageDialog(null, "Atualizado!");

            System.out.println("id" + idEdit);
        }
    }

    private void popularComboBoxSexo() {
        ObservableList<String> opcoes = FXCollections.observableArrayList("Macho", "Femea");
        campoSexo.setItems(opcoes);
        inputSexo.setItems(opcoes);
    }

    private void popularComboBoxDtNascimento() {
        ObservableList<String> opcoes = FXCollections.observableArrayList("1 a 6 Meses", "6 a 12 Meses", "1 a 2 Anos",
                "2 a 4 Anos", "Acima de 5 Anos");
        campoDtNascimento.setItems(opcoes);
        inputIdade.setItems(opcoes);
    }

    @FXML
    void handlebuttonExcluirListar(ActionEvent event) {
        Pet itemSelecionado = tabListaTudo.getSelectionModel().getSelectedItem();

        if (itemSelecionado != null) {
            int idParaExcluir = itemSelecionado.getId();
            operacoesCRUD.excluirRegistro(idParaExcluir);
            tabListaTudo.getItems().remove(itemSelecionado);
            System.out.println(idParaExcluir);
        }
    }

}