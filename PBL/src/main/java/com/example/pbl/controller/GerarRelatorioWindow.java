package com.example.pbl.controller;

import com.example.pbl.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class GerarRelatorioWindow {

    @FXML
    private Button btnCustos;

    @FXML
    private Button btnFaturamento;

    @FXML
    private Button btnSatisfacao;

    @FXML
    private Button btnTempoAtendimento;
    @FXML
    private BorderPane painelnformacoes;

    private Parent rootTempoMedio;

    @FXML
    void btnCustosAction(ActionEvent event) {
        this.openPage("CustosWindow.fxml");
    }

    @FXML
    void btnFaturamentoAction(ActionEvent event) {
        this.openPage("FaturamentoWindow.fxml");
        //FaturamentoWindow
    }

    @FXML
    void btnSatisfacaoAction(ActionEvent event) {
        this.openPage("SatisfacaoWindow.fxml");
        //SatisfacaoWindow
    }

    @FXML
    void btnTempoAtendimentoAction(ActionEvent event) {
        this.openPage("TempoMedioWindow.fxml");
    }

    private void openPage(String url) {
        Parent root = null;
        try{
            root = FXMLLoader.load(HelloApplication.class.getResource(url));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.painelnformacoes.setCenter(root);
    }

    @FXML
    void initialize() {
        assert btnCustos != null : "fx:id=\"btnCustos\" was not injected: check your FXML file 'GerarRelatorioWindow.fxml'.";
        assert btnFaturamento != null : "fx:id=\"btnFaturamento\" was not injected: check your FXML file 'GerarRelatorioWindow.fxml'.";
        assert btnSatisfacao != null : "fx:id=\"btnSatisfacao\" was not injected: check your FXML file 'GerarRelatorioWindow.fxml'.";
        assert btnTempoAtendimento != null : "fx:id=\"btnTempoAtendimento\" was not injected: check your FXML file 'GerarRelatorioWindow.fxml'.";
    }

}
