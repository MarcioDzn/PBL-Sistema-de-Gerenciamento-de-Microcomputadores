package com.example.pbl.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.HelloApplication;
import com.example.pbl.dao.DAO;
import com.example.pbl.model.OutroComponente;
import com.example.pbl.model.Peca;
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

public class CadastrarPecasExtrasWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtCusto;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtPreco;

    protected GerenciarMontagensWindow gerenciarMontagensController;
    private AlertWindow alertWindow;

    @FXML
    void cadastrarAction(ActionEvent event) {
        this.acionarAlert("AlertWindow.fxml", "Finalizar?");
        if (this.alertWindow.getConfirmacao()) {
            OutroComponente novaPecaExtra = this.criarPecaExtra();
            boolean pecaExtraValida = this.validarFormulario(novaPecaExtra);

            if (pecaExtraValida) {
                DAO.getOutroComponente().criar(novaPecaExtra);
                this.gerenciarMontagensController.listaPecasSelecionadas.add(novaPecaExtra);
                this.fecharJanela(event);
            }
        }
    }

    @FXML
    void cancelarAction(ActionEvent event) {
        this.acionarAlert("AlertWindow.fxml", "Cancelar?");
        if (this.alertWindow.getConfirmacao()) {
            this.fecharJanela(event);
        }
    }

    @FXML
    void initialize() {
        assert btnCadastrar != null : "fx:id=\"btnCadastrar\" was not injected: check your FXML file 'CadastrarPecasExtrasWindow.fxml'.";
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'CadastrarPecasExtrasWindow.fxml'.";
        assert txtCusto != null : "fx:id=\"txtCusto\" was not injected: check your FXML file 'CadastrarPecasExtrasWindow.fxml'.";
        assert txtDescricao != null : "fx:id=\"txtDescricao\" was not injected: check your FXML file 'CadastrarPecasExtrasWindow.fxml'.";
        assert txtPreco != null : "fx:id=\"txtPreco\" was not injected: check your FXML file 'CadastrarPecasExtrasWindow.fxml'.";

        this.carregarCSS();
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

    private boolean validarFormulario(OutroComponente novaPecaExtra){
        this.carregarCSS(); // Carrega o css padrão
        boolean pecaExtraValida = true;

        if (novaPecaExtra.getDescricao().equals("")){
            this.txtDescricao.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0;");
            this.txtDescricao.setPromptText("Nome inválido!");
            pecaExtraValida = false;
        }

        if (novaPecaExtra.getPreco() < 0){
            this.txtPreco.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0; ");
            this.txtPreco.setPromptText("Preço inválido!");
            pecaExtraValida = false;
        }

        if (novaPecaExtra.getCusto() < 0){
            this.txtCusto.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0; ");
            this.txtCusto.setPromptText("Custo inválido!");
            pecaExtraValida = false;
        }

        return pecaExtraValida;
    }

    private void carregarCSS(){
        txtDescricao.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtPreco.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtCusto.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
    }

    private OutroComponente criarPecaExtra(){
        String descricao = this.txtDescricao.getText();

        double preco;
        if (this.txtPreco.getText().equals("") || !this.verificarTipoTexto(this.txtPreco.getText()))
            preco = -1.0;
        else
            preco = Double.parseDouble(this.txtPreco.getText());

        double custo;
        if (this.txtCusto.getText().equals("") || !this.verificarTipoTexto(this.txtCusto.getText()))
            custo = -1.0;
        else
            custo = Double.parseDouble(this.txtCusto.getText());

        return new OutroComponente(custo, preco, descricao);
    }

    private boolean verificarTipoTexto(String valor){
        try{
            Double.parseDouble(valor);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    void fecharJanela(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        stage.close();
    }

}
