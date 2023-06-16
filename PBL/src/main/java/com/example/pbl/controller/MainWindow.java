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
import javafx.scene.layout.AnchorPane;
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
    private Button btnPerfil;


    @FXML
    private BorderPane mainActionPane;

    @FXML
    private HBox btnPageMontagens;
    @FXML
    private HBox btnPageLimpezas;
    @FXML
    private HBox btnPageInstalacoes;

    @FXML
    private AnchorPane telaAnchor;



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
    private Label txtTag;

    @FXML
    void pageClientesAction(MouseEvent event) {
        this.colorirBotoes(this.btnPageClientes);
        try {
            this.rootCliente = FXMLLoader.load(HelloApplication.class.getResource("GerenciarClientesWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.mainActionPane.setCenter(this.rootCliente);


    }

    @FXML
    void pageEstoqueAction(MouseEvent event) {
        this.colorirBotoes(this.btnPageEstoque);
        try {
            this.rootPecas = FXMLLoader.load(HelloApplication.class.getResource("GerenciarPecasWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mainActionPane.setCenter(this.rootPecas);
    }

    @FXML
    void pageMenuAction(MouseEvent event) {
        this.colorirBotoes(this.btnPageMenu);
        try {
            this.rootMenu = FXMLLoader.load(HelloApplication.class.getResource("MenuPrincipalWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.mainActionPane.setCenter((this.rootMenu));
    }

    @FXML
    void pageOrdensAction(MouseEvent event) {
        this.colorirBotoes(this.btnPageOrdens);
        try {
            this.rootOrdem = FXMLLoader.load(HelloApplication.class.getResource("GerenciarOrdensWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mainActionPane.setCenter(this.rootOrdem);
    }

    @FXML
    void pageManipularOrdensAction(MouseEvent event) {
        this.colorirBotoes(this.btnPageManipularOrdens);
        try {
            this.rootManipularOrdens = FXMLLoader.load(HelloApplication.class.getResource("ManipularOrdensWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mainActionPane.setCenter((this.rootManipularOrdens));
    }

    @FXML
    void pageRelatorioAction(MouseEvent event) {
        this.colorirBotoes(this.btnPageRelatorio);
        try {
            this.rootRelatorio = FXMLLoader.load(HelloApplication.class.getResource("GerarRelatorioWindow.fxml"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mainActionPane.setCenter((this.rootRelatorio));
    }

    @FXML
    void pageTecnicosAction(MouseEvent event) {
        this.colorirBotoes(this.btnPageTecnicos);
        try {
            this.rootTecnicos = FXMLLoader.load(HelloApplication.class.getResource("GerenciarTecnicosWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mainActionPane.setCenter((this.rootTecnicos));
    }

    @FXML
    void pageMontagensAction(MouseEvent event) {
        this.colorirBotoes(this.btnPageMontagens);
        try {
            this.rootMontagens = FXMLLoader.load(HelloApplication.class.getResource("GerenciarMontagensWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mainActionPane.setCenter((this.rootMontagens));
    }

    @FXML
    void pageInstalacoesAction(MouseEvent event) {
        this.colorirBotoes(this.btnPageInstalacoes);
        try {
            this.rootInstalacoes = FXMLLoader.load(HelloApplication.class.getResource("GerenciarInstalacoesWindow.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mainActionPane.setCenter((this.rootInstalacoes));
    }

    @FXML
    void pageLimpezasAction(MouseEvent event) {
        this.colorirBotoes(this.btnPageLimpezas);
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

    @FXML
    void perfilAction(ActionEvent event) {
        this.abrirPagina("PerfilWindow.fxml");
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
        assert telaAnchor != null : "fx:id=\"telaAnchor\" was not injected: check your FXML file 'MainWindow.fxml'.";

        this.carregarPaginas();
        this.mainActionPane.setCenter(this.rootMenu);
        this.carregarNomeUsuario();
    }

    private void carregarPaginas(){
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
    }

    void carregarNomeUsuario(){
        Tecnico tecnico = DAO.getTecnico().buscarPorId(LoginAtual.idLogin);
        if (tecnico != null){
            this.txtNomeUsuario.setText(tecnico.getNome());

        } else{
            this.txtNomeUsuario.setText("Admin");
            this.txtTag.setText("Administrador");
        }

    }

    void fecharJanela(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        stage.close();
    }

    private void colorirBotoes(HBox botao){
        if (botao != this.btnPageClientes) {
            this.btnPageClientes.setStyle("-fx-background-radius: 10; -fx-background-color: #2C2C2C");
            this.btnPageClientes.setOnMouseEntered(e -> this.btnPageClientes.setStyle("-fx-background-color: #7A31E0; -fx-background-radius: 10;"));
            this.btnPageClientes.setOnMouseExited(e -> this.btnPageClientes.setStyle("-fx-background-color: #2C2C2C; -fx-background-radius: 10;"));
        }

        if (botao != this.btnPageTecnicos) {
            this.btnPageTecnicos.setStyle("-fx-background-radius: 10; -fx-background-color: #2C2C2C");
            this.btnPageTecnicos.setOnMouseEntered(e -> this.btnPageTecnicos.setStyle("-fx-background-color: #7A31E0; -fx-background-radius: 10;"));
            this.btnPageTecnicos.setOnMouseExited(e -> this.btnPageTecnicos.setStyle("-fx-background-color: #2C2C2C; -fx-background-radius: 10;"));
        }

        if (botao != this.btnPageEstoque) {
            this.btnPageEstoque.setStyle("-fx-background-radius: 10; -fx-background-color: #2C2C2C");
            this.btnPageEstoque.setOnMouseEntered(e -> this.btnPageEstoque.setStyle("-fx-background-color: #7A31E0; -fx-background-radius: 10;"));
            this.btnPageEstoque.setOnMouseExited(e -> this.btnPageEstoque.setStyle("-fx-background-color: #2C2C2C; -fx-background-radius: 10;"));
        }
        if (botao != this.btnPageInstalacoes) {
            this.btnPageInstalacoes.setStyle("-fx-background-radius: 10; -fx-background-color: #2C2C2C");
            this.btnPageInstalacoes.setOnMouseEntered(e -> this.btnPageInstalacoes.setStyle("-fx-background-color: #7A31E0; -fx-background-radius: 10; "));
            this.btnPageInstalacoes.setOnMouseExited(e -> this.btnPageInstalacoes.setStyle("-fx-background-color: #2C2C2C; -fx-background-radius: 10;"));
        }
        if (botao != this.btnPageLimpezas) {
            this.btnPageLimpezas.setStyle("-fx-background-radius: 10; -fx-background-color: #2C2C2C");
            this.btnPageLimpezas.setOnMouseEntered(e -> this.btnPageLimpezas.setStyle("-fx-background-color: #7A31E0; -fx-background-radius: 10;"));
            this.btnPageLimpezas.setOnMouseExited(e -> this.btnPageLimpezas.setStyle("-fx-background-color: #2C2C2C; -fx-background-radius: 10;"));
        }

        if (botao != this.btnPageMontagens) {
            this.btnPageMontagens.setStyle("-fx-background-radius: 10; -fx-background-color: #2C2C2C");
            this.btnPageMontagens.setOnMouseEntered(e -> this.btnPageMontagens.setStyle("-fx-background-color: #7A31E0; -fx-background-radius: 10;"));
            this.btnPageMontagens.setOnMouseExited(e -> this.btnPageMontagens.setStyle("-fx-background-color: #2C2C2C; -fx-background-radius: 10;"));
        }

        if (botao != this.btnPageManipularOrdens) {
            this.btnPageManipularOrdens.setStyle("-fx-background-radius: 10; -fx-background-color: #2C2C2C");
            this.btnPageManipularOrdens.setOnMouseEntered(e -> this.btnPageManipularOrdens.setStyle("-fx-background-color: #7A31E0; -fx-background-radius: 10;"));
            this.btnPageManipularOrdens.setOnMouseExited(e -> this.btnPageManipularOrdens.setStyle("-fx-background-color: #2C2C2C; -fx-background-radius: 10;"));
        }

        if (botao != this.btnPageMenu) {
            this.btnPageMenu.setStyle("-fx-background-radius: 10; -fx-background-color: #2C2C2C");
            this.btnPageMenu.setOnMouseEntered(e -> this.btnPageMenu.setStyle("-fx-background-color: #7A31E0; -fx-background-radius: 10;"));
            this.btnPageMenu.setOnMouseExited(e -> this.btnPageMenu.setStyle("-fx-background-color: #2C2C2C; -fx-background-radius: 10;"));
        }

        if (botao != this.btnPageOrdens) {
            this.btnPageOrdens.setStyle("-fx-background-radius: 10; -fx-background-color: #2C2C2C");
            this.btnPageOrdens.setOnMouseEntered(e -> this.btnPageOrdens.setStyle("-fx-background-color: #7A31E0; -fx-background-radius: 10;"));
            this.btnPageOrdens.setOnMouseExited(e -> this.btnPageOrdens.setStyle("-fx-background-color: #2C2C2C; -fx-background-radius: 10;"));
        }

        if (botao != this.btnPageEstoque) {
            this.btnPageEstoque.setStyle("-fx-background-radius: 10; -fx-background-color: #2C2C2C");
            this.btnPageEstoque.setOnMouseEntered(e -> this.btnPageEstoque.setStyle("-fx-background-color: #7A31E0; -fx-background-radius: 10;"));
            this.btnPageEstoque.setOnMouseExited(e -> this.btnPageEstoque.setStyle("-fx-background-color: #2C2C2C; -fx-background-radius: 10;"));
        }

        if (botao != this.btnPageRelatorio) {
            this.btnPageRelatorio.setStyle("-fx-background-radius: 10; -fx-background-color: #2C2C2C");
            this.btnPageRelatorio.setOnMouseEntered(e -> this.btnPageRelatorio.setStyle("-fx-background-color: #7A31E0; -fx-background-radius: 10;"));
            this.btnPageRelatorio.setOnMouseExited(e -> this.btnPageRelatorio.setStyle("-fx-background-color: #2C2C2C; -fx-background-radius: 10;"));
        }

        botao.setStyle("-fx-background-color: #7A31E0; -fx-border-radius: 10; -fx-background-radius: 10;");
        botao.setOnMouseExited(e -> botao.setStyle("-fx-background-color: #7A31E0; -fx-background-radius: 10;"));
    }


}
