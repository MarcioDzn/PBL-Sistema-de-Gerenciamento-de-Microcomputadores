package com.example.pbl.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.pbl.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
    private BorderPane mainActionPane;

    @FXML
    private HBox btnPageMontagens;

    private Parent rootCliente;
    private Parent rootOrdem;
    private Parent rootPecas;
    private Parent rootMenu;
    private Parent rootMontagens;
    private List<HBox> listaBotoes;

    @FXML
    void pageClientesAction(MouseEvent event) {
        try {
            this.rootCliente = FXMLLoader.load(HelloApplication.class.getResource("GerenciarClientesWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.mainActionPane.setCenter(this.rootCliente);


    }

    @FXML
    void pageEstoqueAction(MouseEvent event) {
        this.mainActionPane.setCenter(this.rootPecas);
    }

    @FXML
    void pageMenuAction(MouseEvent event) {
        try {
            this.rootMenu = FXMLLoader.load(HelloApplication.class.getResource("MenuPrincipalWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.mainActionPane.setCenter((this.rootMenu));
    }

    @FXML
    void pageOrdensAction(MouseEvent event) {
        this.mainActionPane.setCenter(this.rootOrdem);
    }

    @FXML
    void pageRelatorioAction(MouseEvent event) {

    }

    // MUDAR DEPOIS PRA A PAGINA CERTA
    // MUDAR DEPOIS PRA A PAGINA CERTA
    // MUDAR DEPOIS PRA A PAGINA CERTA
    // MUDAR DEPOIS PRA A PAGINA CERTA
    // MUDAR DEPOIS PRA A PAGINA CERTA
    // MUDAR DEPOIS PRA A PAGINA CERTA


    @FXML
    void pageTecnicosAction(MouseEvent event) {

    }

    @FXML
    void pageMontagensAction(MouseEvent event) {
        try {
            this.rootMontagens = FXMLLoader.load(HelloApplication.class.getResource("GerenciarMontagensWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mainActionPane.setCenter((this.rootMontagens));
    }

    @FXML
    void sairAction(ActionEvent event) {

    }

    void openPage(String url){
        Parent root = null;

        try {
            root = FXMLLoader.load(HelloApplication.class.getResource(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mainActionPane.setCenter(root);
    }

    @FXML
    void initialize() {
        assert btnPageClientes != null : "fx:id=\"btnPageClientes\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert btnPageEstoque != null : "fx:id=\"btnPageEstoque\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert btnPageMenu != null : "fx:id=\"btnPageMenu\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert btnPageMontagens != null : "fx:id=\"btnPageMontagens\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert btnPageOrdens != null : "fx:id=\"btnPageOrdens\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert btnPageRelatorio != null : "fx:id=\"btnPageRelatorio\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert btnPageTecnicos != null : "fx:id=\"btnPageTecnicos\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert btnSair != null : "fx:id=\"btnSair\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert mainActionPane != null : "fx:id=\"mainActionPane\" was not injected: check your FXML file 'MainWindow.fxml'.";


        try {
            this.rootCliente = FXMLLoader.load(HelloApplication.class.getResource("GerenciarClientesWindow.fxml"));
            this.rootOrdem = FXMLLoader.load(HelloApplication.class.getResource("GerenciarOrdensWindow.fxml"));
            this.rootPecas = FXMLLoader.load(HelloApplication.class.getResource("GerenciarPecasWindow.fxml"));
            this.rootMenu = FXMLLoader.load(HelloApplication.class.getResource("MenuPrincipalWindow.fxml"));
            this.rootMontagens = FXMLLoader.load(HelloApplication.class.getResource("GerenciarMontagensWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mainActionPane.setCenter(this.rootMenu);
    }


}
