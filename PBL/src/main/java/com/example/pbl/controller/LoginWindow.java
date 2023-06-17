package com.example.pbl.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.HelloApplication;
import com.example.pbl.dao.DAO;
import com.example.pbl.model.LoginInfo;
import com.example.pbl.utils.LoginAtual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtUsuario;

    private AvisoWindow avisoWindow;

    @FXML
    void loginAction(ActionEvent event) {
        LoginInfo loginInfo = DAO.getLogin().buscarPorLogin(this.txtUsuario.getText(), this.txtSenha.getText());

        if (LoginAtual.idLogin == -2){
            this.fecharJanela(event);
            this.abrirPagina("MainWindow.fxml");

        } else if (loginInfo != null){
            LoginAtual.idLogin = loginInfo.getIdUsuario();
            this.fecharJanela(event);
            this.abrirPagina("MainWindow.fxml");

        } else{
            this.acionarAviso("AvisoWindow.fxml", "Login inválido!");
        }
    }

    @FXML
    void initialize() {
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert txtSenha != null : "fx:id=\"txtSenha\" was not injected: check your FXML file 'LoginWindow.fxml'.";
        assert txtUsuario != null : "fx:id=\"txtUsuario\" was not injected: check your FXML file 'LoginWindow.fxml'.";
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

    private void abrirPagina(String url){
        try {
            FXMLLoader loader = new FXMLLoader(); // Carrega o arquivo do scene builder
            URL xmlURL = HelloApplication.class.getResource(url); // Pega o XML e carrega pra ser utilizado
            loader.setLocation(xmlURL);

            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();

            stage.setTitle("Nome do Dialog");
            stage.setScene(scene);
            stage.centerOnScreen();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void fecharJanela(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        stage.close();
    }

}
