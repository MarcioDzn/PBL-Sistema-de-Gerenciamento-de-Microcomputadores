package com.example.pbl.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.HelloApplication;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Cliente;
import com.example.pbl.model.LoginInfo;
import com.example.pbl.model.Tecnico;
import com.example.pbl.utils.LoginAtual;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GerenciarTecnicosWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnEditar;

    @FXML
    private TableView<Tecnico> tblTecnicos;

    @FXML
    private TextField txtBuscarNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtUsuario;

    private ObservableList<Tecnico> listaTecnicos;
    private AlertWindow alertWindow;
    private AvisoWindow avisoWindow;

    @FXML
    void cadastrarAction(ActionEvent event) {
        Tecnico novoTecnico = this.criarTecnico();
        LoginInfo novoLogin = this.criarLogin();

        boolean tecnicoValido = this.validarFormulario(novoTecnico);
        boolean loginValido = this.validarLogin(novoLogin);

        if (tecnicoValido && loginValido){

            this.acionarAlert("AlertWindow.fxml", "Cadastrar Tecnico?");
            if (this.alertWindow.getConfirmacao()) {
                DAO.getTecnico().criar(novoTecnico);

                novoLogin.setIdUsuario(novoTecnico.getId());
                DAO.getLogin().criar(novoLogin);

                this.listaTecnicos.clear();
                this.listaTecnicos.addAll(DAO.getTecnico().buscarTodos());
                this.txtBuscarNome.setText("");

                this.limparCampos();
            }
        }
    }

    @FXML
    void deletarAction(ActionEvent event) {
        Tecnico tecnicoRemovido = this.tblTecnicos.getSelectionModel().getSelectedItem();

        if (tecnicoRemovido != null) {
            if (tecnicoRemovido.getOrdensServico().size() == 0) {
                this.acionarAlert("AlertWindow.fxml", "Remover Tecnico?");
                if (this.alertWindow.getConfirmacao()) {
                    try {
                        DAO.getTecnico().remover(tecnicoRemovido);
                    } catch (ObjetoNaoEncontradoException e) {
                        throw new RuntimeException(e);
                    }

                    this.listaTecnicos.clear();
                    this.listaTecnicos.addAll(DAO.getTecnico().buscarTodos());

                    this.carregarCSS();
                    this.limparCampos();
                }
            } else {
                this.acionarAviso("AvisoWindow.fxml", "O técnico está associado a uma\nordem de serviço.\nNão é possível removê-lo!");
            }
        } else{
            this.acionarAviso("AvisoWindow.fxml", "Selecione um técnico!");
        }
    }

    @FXML
    void editarAction(ActionEvent event) {
        Tecnico tecnico = this.tblTecnicos.getSelectionModel().getSelectedItem();

        if (tecnico != null){
            Tecnico tecnicoEditado = this.editarTecnico(tecnico, this.criarTecnico());
            LoginInfo loginEditado = this.editarLogin();

            this.acionarAlert("AlertWindow.fxml", "Editar Tecnico?");
            if (this.alertWindow.getConfirmacao()) {
                try {
                    DAO.getTecnico().atualizar(tecnicoEditado);
                    DAO.getLogin().atualizar(loginEditado);
                } catch (ObjetoNaoEncontradoException e) {
                    throw new RuntimeException(e);
                }

                int index = this.listaTecnicos.indexOf(tecnico);
                this.listaTecnicos.set(index, tecnicoEditado);

                this.carregarCSS();
                this.limparCampos();
            }
        } else{
            this.acionarAviso("AvisoWindow.fxml", "Selecione um cliente!");
        }
    }

    @FXML
    void initialize() {
        assert btnCadastrar != null : "fx:id=\"btnCadastrar\" was not injected: check your FXML file 'GerenciarTecnicosWindow.fxml'.";
        assert btnDeletar != null : "fx:id=\"btnDeletar\" was not injected: check your FXML file 'GerenciarTecnicosWindow.fxml'.";
        assert btnEditar != null : "fx:id=\"btnEditar\" was not injected: check your FXML file 'GerenciarTecnicosWindow.fxml'.";
        assert tblTecnicos != null : "fx:id=\"tblTecnicos\" was not injected: check your FXML file 'GerenciarTecnicosWindow.fxml'.";
        assert txtBuscarNome != null : "fx:id=\"txtBuscarNome\" was not injected: check your FXML file 'GerenciarTecnicosWindow.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'GerenciarTecnicosWindow.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'GerenciarTecnicosWindow.fxml'.";
        assert txtSenha != null : "fx:id=\"txtSenha\" was not injected: check your FXML file 'GerenciarTecnicosWindow.fxml'.";
        assert txtUsuario != null : "fx:id=\"txtUsuario\" was not injected: check your FXML file 'GerenciarTecnicosWindow.fxml'.";

        this.listaTecnicos = FXCollections.observableArrayList();
        this.listaTecnicos.setAll(DAO.getTecnico().buscarTodos());

        this.carregarCSS();
        this.carregarTabela();
    }

    private Tecnico editarTecnico(Tecnico tecnicoAntigo, Tecnico tecnicoNovo){
        LoginInfo login = DAO.getLogin().buscarPorId(LoginAtual.idLogin);

        if (!tecnicoNovo.getNome().equals("")){
            tecnicoAntigo.setNome(tecnicoNovo.getNome());
        }

        if (!tecnicoNovo.getEmail().equals("")){
            tecnicoAntigo.setEmail(tecnicoNovo.getEmail());
        }

        return tecnicoAntigo;
    }

    private LoginInfo editarLogin(){
        LoginInfo login = DAO.getLogin().buscarPorId(LoginAtual.idLogin);

        if (!this.txtUsuario.getText().equals("")){
            login.setUsuario(this.txtUsuario.getText());
        }

        if (!this.txtSenha.getText().equals("")){
            login.setSenha(this.txtSenha.getText());
        }

        return login;
    }

    private void acionarAlert(String url, String texto){
        try {
            FXMLLoader loader = new FXMLLoader(); // Carrega o arquivo do scene builder
            URL xmlURL = HelloApplication.class.getResource(url); // Pega o XML e carrega pra ser utilizado
            loader.setLocation(xmlURL);

            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();

            this.alertWindow = loader.getController();

            stage.setTitle("Nome do Dialog");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);

            this.alertWindow.setTexto(texto);
            stage.showAndWait();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void acionarAviso(String url, String texto){
        try {
            FXMLLoader loader = new FXMLLoader(); // Carrega o arquivo do scene builder
            URL xmlURL = HelloApplication.class.getResource(url); // Pega o XML e carrega pra ser utilizado
            loader.setLocation(xmlURL);

            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();

            this.avisoWindow = loader.getController();

            stage.setTitle("Nome do Dialog");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);

            this.avisoWindow.setTexto(texto);

            stage.showAndWait();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Cria o tecnico
    private Tecnico criarTecnico(){
        String nome = this.txtNome.getText();
        String email = this.txtEmail.getText();

        Tecnico novoTecnico = new Tecnico(nome, email);

        return novoTecnico;

    }

    private LoginInfo criarLogin(){
        String usuario = this.txtUsuario.getText();
        String senha = this.txtSenha.getText();

        LoginInfo novoLogin = new LoginInfo(usuario, senha, "Tecnico");

        return novoLogin;
    }

    // Verifica se algum textField ficou vazio
    private boolean validarFormulario(Tecnico novoTecnico){
        this.carregarCSS(); // Carrega o css padrão
        boolean tecnicoValido = true;

        if (novoTecnico.getNome().equals("")){
            this.txtNome.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0;");
            this.txtNome.setPromptText("Nome inválido!");
            tecnicoValido = false;
        }

        if (this.txtUsuario.getText().equals("")){
            this.txtUsuario.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0; ");
            this.txtUsuario.setPromptText("Usuário inválido!");
            tecnicoValido = false;
        }

        if (this.txtSenha.getText().equals("")){
            this.txtSenha.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0; ");
            this.txtSenha.setPromptText("Senha inválida!");
            tecnicoValido = false;
        }

        if (novoTecnico.getEmail().equals("")){
            this.txtEmail.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0; ");
            this.txtEmail.setPromptText("Email inválido!");
            tecnicoValido = false;
        }

        return tecnicoValido;
    }

    private boolean validarLogin(LoginInfo login){
        return DAO.getLogin().buscarPorLogin(login.getUsuario(), login.getSenha()) == null;
    }

    void limparCampos(){
        this.txtNome.clear();
        this.txtEmail.clear();
        this.txtUsuario.clear();
        this.txtSenha.clear();
        this.txtBuscarNome.clear();

        this.txtNome.setPromptText("");
        this.txtEmail.setPromptText("");
        this.txtUsuario.setPromptText("");
        this.txtSenha.setPromptText("");
    }

    private void carregarCSS(){
        txtNome.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtEmail.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtUsuario.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtSenha.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtBuscarNome.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
    }

    private void carregarTabela(){
        TableColumn nomeCol = new TableColumn("Nome");
        TableColumn emailCol = new TableColumn("Email");

        nomeCol.setCellValueFactory(new PropertyValueFactory<Tecnico, String>("nome"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Tecnico, String>("email"));


        this.tblTecnicos.getColumns().addAll(nomeCol, emailCol);
        this.tblTecnicos.setItems(this.listaTecnicos);
    }

}
