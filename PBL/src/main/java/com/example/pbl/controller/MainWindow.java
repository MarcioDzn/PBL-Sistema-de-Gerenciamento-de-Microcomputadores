package com.example.pbl.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.pbl.HelloApplication;
import com.example.pbl.dao.DAO;
import com.example.pbl.model.Tecnico;
import com.example.pbl.utils.LoginAtual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label txtNomeUsuario;

    @FXML
    private HBox btnPageClientes;

    @FXML
    private HBox btnPageEstoque;

    @FXML
    private HBox btnPageMenu;

    @FXML
    private HBox btnPageOrdens;

    @FXML
    private HBox btnPageManipularOrdens;

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
    @FXML
    private HBox btnPageLimpezas;
    @FXML
    private HBox btnPageInstalacoes;



    private Parent rootCliente;
    private Parent rootOrdem;
    private Parent rootPecas;
    private Parent rootMenu;
    private Parent rootMontagens;
    private Parent rootLimpezas;
    private Parent rootInstalacoes;
    private Parent rootTecnicos;
    private Parent rootManipularOrdens;
    private Parent rootRelatorio;
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
    void pageManipularOrdensAction(MouseEvent event) {
        try {
            this.rootManipularOrdens = FXMLLoader.load(HelloApplication.class.getResource("ManipularOrdensWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mainActionPane.setCenter((this.rootManipularOrdens));
    }

    @FXML
    void pageRelatorioAction(MouseEvent event) {
        try {
            this.rootRelatorio = FXMLLoader.load(HelloApplication.class.getResource("GerarRelatorioWindow.fxml"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mainActionPane.setCenter((this.rootRelatorio));
    }

    @FXML
    void pageTecnicosAction(MouseEvent event) {
        try {
            this.rootTecnicos = FXMLLoader.load(HelloApplication.class.getResource("GerenciarTecnicosWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mainActionPane.setCenter((this.rootTecnicos));
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
    void pageInstalacoesAction(MouseEvent event) {
        try {
            this.rootInstalacoes = FXMLLoader.load(HelloApplication.class.getResource("GerenciarInstalacoesWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mainActionPane.setCenter((this.rootInstalacoes));
    }

    @FXML
    void pageLimpezasAction(MouseEvent event) {
        try {
            this.rootLimpezas = FXMLLoader.load(HelloApplication.class.getResource("GerenciarLimpezasWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mainActionPane.setCenter((this.rootLimpezas));
    }

    @FXML
    void sairAction(ActionEvent event) {
        this.abrirPagina("LoginWindow.fxml");
        this.fecharJanela(event);
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
            stage.setResizable(false);
            stage.centerOnScreen();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
            this.rootLimpezas = FXMLLoader.load(HelloApplication.class.getResource("GerenciarLimpezasWindow.fxml"));
            this.rootInstalacoes = FXMLLoader.load(HelloApplication.class.getResource("GerenciarInstalacoesWindow.fxml"));
            this.rootTecnicos = FXMLLoader.load(HelloApplication.class.getResource("GerenciarTecnicosWindow.fxml"));
            this.rootManipularOrdens = FXMLLoader.load(HelloApplication.class.getResource("ManipularOrdensWindow.fxml"));
            this.rootRelatorio = FXMLLoader.load(HelloApplication.class.getResource("GerarRelatorioWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mainActionPane.setCenter(this.rootMenu);
        this.carregarNomeUsuario();
    }

    void carregarNomeUsuario(){
        Tecnico tecnico = DAO.getTecnico().buscarPorId(LoginAtual.idLogin);
        if (tecnico != null){
            this.txtNomeUsuario.setText(tecnico.getNome());

        }

    }

    void fecharJanela(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        stage.close();
    }


}
