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
import com.example.pbl.model.Montagem;
import com.example.pbl.model.Peca;
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
    private Button btnCadastrar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnEditar;

    @FXML
    private TextField txtBuscarNome;

    @FXML
    private ScrollPane scMontagens;
    @FXML
    private TextField txtDescricao;

    private EscolherPecasWindow escolherPecasWindow;
    private FlowPane flowPaneMontagens;
    private     List<Montagem> listaMontagens;
    private List<Button> listaBotoes;

    protected List<Peca> listaPecasSelecionadas;

    @FXML
    void addPecasAction(ActionEvent event) {
        this.abrirPagina("EscolherPecasWindow.fxml");

    }

    @FXML
    void cadastrarAction(ActionEvent event) {
        Montagem montagem = new Montagem();

        montagem.setDescricao(this.txtDescricao.getText());
        for (Peca pecaSelecionada : this.listaPecasSelecionadas)
            montagem.setComponente(pecaSelecionada, 1);

        DAO.getMontagem().criar(montagem);

        this.listaMontagens.add(montagem);
        System.out.println(montagem.getId());
        this.atualizarQuantidade();
    }

    @FXML
    void deletarAction(ActionEvent event) {
    }

    @FXML
    void editarAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnAddPecas != null : "fx:id=\"btnAddPecas\" was not injected: check your FXML file 'GerenciarMontagensWindow.fxml'.";
        assert btnCadastrar != null : "fx:id=\"btnCadastrar\" was not injected: check your FXML file 'GerenciarMontagensWindow.fxml'.";
        assert btnDeletar != null : "fx:id=\"btnDeletar\" was not injected: check your FXML file 'GerenciarMontagensWindow.fxml'.";
        assert btnEditar != null : "fx:id=\"btnEditar\" was not injected: check your FXML file 'GerenciarMontagensWindow.fxml'.";
        assert txtBuscarNome != null : "fx:id=\"txtBuscarNome\" was not injected: check your FXML file 'GerenciarMontagensWindow.fxml'.";
        assert txtDescricao != null : "fx:id=\"txtDescricao\" was not injected: check your FXML file 'GerenciarMontagensWindow.fxml'.";

        this.listaPecasSelecionadas = new LinkedList<>();
        this.listaMontagens = new LinkedList<>();
        this.listaMontagens.addAll(DAO.getMontagem().buscarTodos());

        this.listaBotoes = new LinkedList<>();

        this.carregarScrollPaneServico();
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

        this.listaBotoes.clear();

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
                                    this.listaBotoes.add(botao);
                                }
                            }
                        }
                    }
                }
            }
        }
        this.removerServico();
    }

    void removerServico(){
        for (Button botao : this.listaBotoes){
            botao.setOnAction(e -> {
                int index = this.listaBotoes.indexOf(botao);

                System.out.println(DAO.getMontagem().buscarTodos().get(0).getId());

                this.listaBotoes.remove(index);
                this.listaMontagens.remove(index);

                try {
                    DAO.getMontagem().remover(this.listaMontagens.get(index));
                } catch (ObjetoNaoEncontradoException ex) {
                    throw new RuntimeException(ex);
                }

                this.carregarScrollPaneServico();
                this.selecionarBotoesServicos();

            });
        }
    }

    private void atualizarQuantidade(){
            try {
                for (Peca peca : this.listaPecasSelecionadas) {
                    Peca pecaAtualizada = DAO.getPeca().buscarPorId(peca.getId());
                    pecaAtualizada.setQuantidade(pecaAtualizada.getQuantidade() - 1);

                    DAO.getPeca().atualizar(pecaAtualizada);
                }
            } catch (QuantidadeException | ObjetoNaoEncontradoException e) {
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

            this.escolherPecasWindow = loader.getController();
            this.setPecaController();

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

    public void setPecaController(){
        this.escolherPecasWindow.gerenciarMontagensController = this;
    }

}