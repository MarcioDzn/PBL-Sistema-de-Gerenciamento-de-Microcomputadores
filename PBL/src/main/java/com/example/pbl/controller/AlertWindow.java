package com.example.pbl.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Label txtMensagem;

    private boolean confirmacao;
    @FXML
    void cancelarAction(ActionEvent event) {
        this.confirmacao = false;
        this.fecharJanela(event);
    }

    @FXML
    void confirmarAction(ActionEvent event) {
        this.confirmacao = true;
        this.fecharJanela(event);
    }

    @FXML
    void initialize() {
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'AlertWindow.fxml'.";
        assert btnConfirmar != null : "fx:id=\"btnConfirmar\" was not injected: check your FXML file 'AlertWindow.fxml'.";
        assert txtMensagem != null : "fx:id=\"txtMensagem\" was not injected: check your FXML file 'AlertWindow.fxml'.";

    }

    void fecharJanela(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        stage.close();
    }

    protected boolean getConfirmacao(){
        return this.confirmacao;
    }

}
