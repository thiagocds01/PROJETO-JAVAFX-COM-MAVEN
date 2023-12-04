package br.com.salvapets.controller;

import javafx.scene.layout.StackPane;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import br.com.salvapets.domain.OperacoesCrud;
import br.com.salvapets.domain.Pet;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
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
    private TextField campoDtNascimento;

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
    private TextField inputIdade;

    @FXML
    private TextField inputNome;

    @FXML
    private TextField inputRaca;

    @FXML
    private TextField inputSexo;

    @FXML
    private TextField inputTamanho;

    private StackPane seuPaneDeExibicao;


    private OperacoesCrud operacoesCRUD = new OperacoesCrud();
    private Pet pet = new Pet();

    @FXML
    void handleAtualizarImagemUploadButtonAction(ActionEvent event) {

    }

    @FXML
    void handlebuttonAtualizarLista(ActionEvent event) {

        List<Pet> pets = operacoesCRUD.listarRegistros();
        tabListaTudo.getItems().clear();
        tabListaTudo.getItems().addAll(pets);

    }

    @FXML
    void handleUploadButtonAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        // Abre o diálogo de seleção de arquivo
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            try {
                // Lê o arquivo para um array de bytes
                // byte[] bytesDaImagem = Files.readAllBytes(file.toPath());
                FileInputStream fileInputStream = new FileInputStream(file);
                Integer numBytes = fileInputStream.available();
                byte[] imagemByte = new byte[numBytes];
                int numBytesLidos = fileInputStream.read(imagemByte);
                pet.setImagem(imagemByte);

                fileInputStream.close();

                System.out.println(numBytesLidos);

            } catch (IOException e) {
                e.printStackTrace();
                // Lidar com a exceção, por exemplo, mostrar uma mensagem de erro
                System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            }
        }
    }
    //Botão Cancelar irá limpar os campos
    @FXML
    void handleButtonCancelar(ActionEvent event) {
        campoNome.setText("");
        campoRaca.setText("");
        campoPorteRaca.setText("");
        campoSexo.setValue("");
        campoCor.setText("");
        campoDtNascimento.setText("");
        campoHistoria.setText("");
    }



    @FXML
    void handleButtonSalvar(ActionEvent event) {

        pet.setNome(campoNome.getText());
        pet.setRaca(campoRaca.getText());
        pet.setPorteRaca(campoPorteRaca.getText());
        pet.setSexo(campoSexo.getValue());
        pet.setCor(campoCor.getText());
        pet.setIdade(campoDtNascimento.getText());
        pet.setHistoria(campoHistoria.getText());

        System.out.println(pet.getImagem());

        operacoesCRUD.cadastrarRegistro(pet);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Chame o método para popular o ComboBox
        popularComboBox();

        List<Pet> pets = operacoesCRUD.listarRegistros();

        TableColumn<Pet, Integer> colId = new TableColumn<>("ID");
        TableColumn<Pet, String> colNome = new TableColumn<>("Nome");
        TableColumn<Pet, String> colCor = new TableColumn<>("Cor");
        TableColumn<Pet, String> colHistoria = new TableColumn<>("Historia");
        TableColumn<Pet, String> colSexo = new TableColumn<>("Sexo");
        TableColumn<Pet, String> colIdade = new TableColumn<>("Idade");
        TableColumn<Pet, String> colRaca = new TableColumn<>("Raca");
        TableColumn<Pet, byte[]> colImagem = new TableColumn<>("Imagem");

        colId.setCellValueFactory(new PropertyValueFactory<Pet, Integer>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<Pet, String>("nome"));
        colCor.setCellValueFactory(new PropertyValueFactory<Pet, String>("cor"));
        colSexo.setCellValueFactory(new PropertyValueFactory<Pet, String>("sexo"));
        colIdade.setCellValueFactory(new PropertyValueFactory<Pet, String>("idade"));
        colRaca.setCellValueFactory(new PropertyValueFactory<Pet, String>("raca"));
        colImagem.setCellValueFactory(new PropertyValueFactory<Pet, byte[]>("imagem"));

        colNome.setCellFactory(TextFieldTableCell.forTableColumn());
        colCor.setCellFactory(TextFieldTableCell.forTableColumn());
        colSexo.setCellFactory(TextFieldTableCell.forTableColumn());
        colIdade.setCellFactory(TextFieldTableCell.forTableColumn());
        colRaca.setCellFactory(TextFieldTableCell.forTableColumn());
        colHistoria.setCellFactory(TextFieldTableCell.forTableColumn());

        tabListaTudo.getColumns().addAll(colId, colNome, colCor, colSexo, colIdade, colRaca, colImagem);

        tabListaTudo.getItems().addAll(pets);

        // Adicionar um EventHandler para lidar com o clique na linha
        tabListaTudo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    // Obter a linha selecionada
                    Pet petSelecionado = tabListaTudo.getSelectionModel().getSelectedItem();
                    inputID.setText(String.valueOf(petSelecionado.getId()));;
                    inputNome.setText(String.valueOf(petSelecionado.getNome()));
                    inputRaca.setText(String.valueOf(petSelecionado.getRaca()));
                    inputTamanho.setText(String.valueOf(petSelecionado.getPorteRaca()));
                    inputCor.setText(String.valueOf(petSelecionado.getCor()));
                    inputIdade.setText(String.valueOf(petSelecionado.getIdade()));
                    inputHistoria.setText(String.valueOf(petSelecionado.getHistoria()));
                    inputSexo.setText(String.valueOf(petSelecionado.getSexo()));

                    if (petSelecionado != null) {
                        // Exibir a imagem em um ImageView
                        exibirImagem(petSelecionado.getImagem());
                    }
                }
            }
        });

    }

    @FXML
    void atualizarEdit(ActionEvent event) {

    }

    private void exibirImagem(byte[] imagemBytes) {
        // Verificar se há bytes de imagem
        if (imagemBytes != null && imagemBytes.length > 0) {
            // Converter o array de bytes para um objeto Image
            Image imagem = new Image(new ByteArrayInputStream(imagemBytes));
    
            // Criar um novo ImageView
            ImageView imageView = new ImageView(imagem);
    
            // Adicionar o ImageView ao seu Pane de exibição (substitua seuPaneDeExibicao pelo seu Pane real)
            seuPaneDeExibicao.getChildren().setAll(imageView);
        }
    }

    @FXML
    void handlebuttonSalvarListar(ActionEvent event) {
        ObservableList<Pet> petsEditados = tabListaTudo.getItems();

        for (Pet pet : petsEditados) {
            // Chama o método atualizarRegistro para cada pet editado
            operacoesCRUD.atualizarRegistro(pet);
        }

        System.out.println("Executando a lógica de salvar alterações");
    }

    // Método para popular o ComboBox
    private void popularComboBox() {
        ObservableList<String> opcoes = FXCollections.observableArrayList("Macho", "Femea");
        campoSexo.setItems(opcoes);
    }

    @FXML
    void handleAtualizarUploadButtonAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        // Abre o diálogo de seleção de arquivo
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            try {
                // Lê o arquivo para um array de bytes
                // byte[] bytesDaImagem = Files.readAllBytes(file.toPath());
                FileInputStream fileInputStream = new FileInputStream(file);
                Integer numBytes = fileInputStream.available();
                byte[] imagemByte = new byte[numBytes];
                int numBytesLidos = fileInputStream.read(imagemByte);
                pet.setImagem(imagemByte);

                fileInputStream.close();

                System.out.println(numBytesLidos);

            } catch (IOException e) {
                e.printStackTrace();
                // Lidar com a exceção, por exemplo, mostrar uma mensagem de erro
                System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            }
        }

    }

    @FXML
    void handlebuttonExcluirListar(ActionEvent event) {
        Pet itemSelecionado = tabListaTudo.getSelectionModel().getSelectedItem();

        if (itemSelecionado != null) {
            int idParaExcluir = itemSelecionado.getId(); // Correção aqui
            operacoesCRUD.excluirRegistro(idParaExcluir);
            // Atualizar a TableView
            tabListaTudo.getItems().remove(itemSelecionado);
            System.out.println(idParaExcluir);
        }
    }

}