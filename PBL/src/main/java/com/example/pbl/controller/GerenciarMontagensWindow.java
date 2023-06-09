package com.example.pbl.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.pbl.HelloApplication;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.exceptions.QuantidadeException;
import com.example.pbl.model.*;
import com.example.pbl.utils.componentesJavafx.ServicoCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GerenciarMontagensWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAddPecas;

    @FXML
    private Button btnAddPecaExtra;

    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtBuscarNome;

    @FXML
    private ScrollPane scMontagens;
    @FXML
    private TextField txtDescricao;

    private EscolherPecasWindow escolherPecasWindow;
    private CadastrarPecasExtrasWindow cadastrarPecasExtrasWindow;
    private FlowPane flowPaneMontagens;
    private List<Montagem> listaMontagens;
    private List<Button> listaBotoesRemover;

    protected List<Componente> listaPecasSelecionadas;
    private AlertWindow alertWindow;
    private AvisoWindow avisoWindow;

    @FXML
    void addPecasAction(ActionEvent event) {
        this.abrirPagina("EscolherPecasWindow.fxml", "Peca");

    }


    @FXML
    void cancelarAction(ActionEvent event) {
        this.limparCampos();
        this.carregarCSS();
    }

    @FXML
    void addPecasExtraAction(ActionEvent event) {
        this.abrirPagina("CadastrarPecasExtrasWindow.fxml", "PecaExtra");
    }

    @FXML
    void cadastrarAction(ActionEvent event) {
        Montagem montagem = new Montagem();

        if (this.listaPecasSelecionadas.size() > 0) {
            this.acionarAlert("AlertWindow.fxml", "Cadastrar Montagem?");
            if (this.alertWindow.getConfirmacao()) {
                montagem.setDescricao(this.txtDescricao.getText());
                for (Componente pecaSelecionada : this.listaPecasSelecionadas)
                    montagem.setComponente(pecaSelecionada, 1);

                DAO.getMontagem().criar(montagem);

                this.listaMontagens.add(montagem);

                this.txtDescricao.setText("");
                this.carregarScrollPaneServico();
            }
        } else{
            this.acionarAviso("AvisoWindow.fxml", "Selecione pelo menos \num componente!");
        }

    }

    @FXML
    void initialize() {
        assert btnAddPecas != null : "fx:id=\"btnAddPecas\" was not injected: check your FXML file 'GerenciarMontagensWindow.fxml'.";
        assert btnCadastrar != null : "fx:id=\"btnCadastrar\" was not injected: check your FXML file 'GerenciarMontagensWindow.fxml'.";
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'GerenciarMontagensWindow.fxml'.";
        assert txtBuscarNome != null : "fx:id=\"txtBuscarNome\" was not injected: check your FXML file 'GerenciarMontagensWindow.fxml'.";
        assert txtDescricao != null : "fx:id=\"txtDescricao\" was not injected: check your FXML file 'GerenciarMontagensWindow.fxml'.";

        this.listaPecasSelecionadas = new LinkedList<>();
        this.listaMontagens = new LinkedList<>();
        this.listaMontagens.addAll(DAO.getMontagem().buscarTodos());

        this.listaBotoesRemover = new LinkedList<>();

        this.carregarScrollPaneServico();
        this.carregarCSS();
    }

    void limparCampos(){
        this.txtDescricao.clear();
        this.txtDescricao.setPromptText("");

        this.listaPecasSelecionadas.clear();
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

    private void carregarScrollPaneServico(){
        try {
            try {
                List<String> listaNomes = new LinkedList<>();
                listaNomes.add("Descricao");
                this.flowPaneMontagens = ServicoCard.criarTabelaMenor(this.listaMontagens, listaNomes, 405, 0);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            this.scMontagens.setContent(this.flowPaneMontagens);

            this.scMontagens.setPannable(true);

            this.scMontagens.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            this.scMontagens.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            this.selecionarBotoesServicos();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    void selecionarBotoesServicos(){
        HBox hboxMontagem;

        this.listaBotoesRemover.clear();

        for (Node montagem : this.flowPaneMontagens.getChildren()){
            Button botao;
            hboxMontagem = (HBox) montagem;

            for (Node node : hboxMontagem.getChildren()){
                if (node instanceof VBox){
                    for (Node mostInnerNode : ((VBox) node).getChildren()){
                        if (mostInnerNode instanceof VBox){
                            for (Node innerNode: ((VBox) mostInnerNode).getChildren()){
                                if (innerNode instanceof Button){
                                    botao = (Button) innerNode;
                                    this.listaBotoesRemover.add(botao);
                                }
                            }
                        }
                    }
                }
            }
        }
        this.removerServico();
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

    void removerServico(){
        for (Button botao : this.listaBotoesRemover){
            botao.setOnAction(e -> {
                int index = this.listaBotoesRemover.indexOf(botao);

                try {
                    Montagem montagem = DAO.getMontagem().buscarPorId(index);

                    if (montagem.getOrdensServico().size() == 0) {
                        DAO.getMontagem().remover(this.listaMontagens.get(index));
                        this.listaBotoesRemover.remove(index);
                        this.listaMontagens.remove(index);

                        this.carregarScrollPaneServico();
                    } else{
                        this.acionarAviso("AvisoWindow.fxml", "O serviço está associado a uma\nOrdem de Serviço.\nNão é possível removê-lo!");
                    }


                } catch (ObjetoNaoEncontradoException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
    }
    private void abrirPagina(String url, String tipo){
        try {
            FXMLLoader loader = new FXMLLoader(); // Carrega o arquivo do scene builder
            URL xmlURL = HelloApplication.class.getResource(url); // Pega o XML e carrega pra ser utilizado
            loader.setLocation(xmlURL);

            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();

            if (tipo.equals("Peca")){
                this.escolherPecasWindow = loader.getController();
                this.setPecaController();

            } else if (tipo.equals("PecaExtra")){
                this.cadastrarPecasExtrasWindow = loader.getController();
                this.setPecaExtraController();
            }

            stage.setTitle("Nome do Dialog");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void carregarCSS(){
        txtDescricao.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
    }

    public void setPecaController(){
        this.escolherPecasWindow.gerenciarMontagensController = this;
    }
    public void setPecaExtraController(){
        this.cadastrarPecasExtrasWindow.gerenciarMontagensController = this;
    }

}
