package com.example.pbl.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AvisoWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Label txtMensagem;

    @FXML
    void confirmarAction(ActionEvent event) {
        this.fecharJanela(event);
    }

    @FXML
    void initialize() {
        assert btnConfirmar != null : "fx:id=\"btnConfirmar\" was not injected: check your FXML file 'AvisoWindow.fxml'.";
        assert txtMensagem != null : "fx:id=\"txtMensagem\" was not injected: check your FXML file 'AvisoWindow.fxml'.";

    }

    void fecharJanela(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        stage.close();
    }

    protected void setTexto(String texto){
        this.txtMensagem.setText(texto);
    }

}
