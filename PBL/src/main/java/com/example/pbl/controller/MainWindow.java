package com.example.pbl.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MainWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox btnPageClientes;

    @FXML
    private HBox btnPageEstoque;

    @FXML
    private HBox btnPageMenu;

    @FXML
    private HBox btnPageOrdens;

    @FXML
    private HBox btnPageRelatorio;

    @FXML
    private HBox btnPageTecnicos;

    @FXML
    private Button btnSair;

    @FXML
    private Pane mainActionPane;

    @FXML
    void pageClientesAction(MouseEvent event) {

    }

    @FXML
    void pageEstoqueAction(MouseEvent event) {

    }

    @FXML
    void pageMenuAction(MouseEvent event) {

    }

    @FXML
    void pageOrdensAction(MouseEvent event) {

    }

    @FXML
    void pageRelatorioAction(MouseEvent event) {

    }

    @FXML
    void pageTecnicosAction(MouseEvent event) {

    }

    @FXML
    void sairAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnPageClientes != null : "fx:id=\"btnPageClientes\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnPageEstoque != null : "fx:id=\"btnPageEstoque\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnPageMenu != null : "fx:id=\"btnPageMenu\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnPageOrdens != null : "fx:id=\"btnPageOrdens\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnPageRelatorio != null : "fx:id=\"btnPageRelatorio\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnPageTecnicos != null : "fx:id=\"btnPageTecnicos\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert btnSair != null : "fx:id=\"btnSair\" was not injected: check your FXML file 'mainWindow.fxml'.";
        assert mainActionPane != null : "fx:id=\"mainActionPane\" was not injected: check your FXML file 'mainWindow.fxml'.";

    }

}
