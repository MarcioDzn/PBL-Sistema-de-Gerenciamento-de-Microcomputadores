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
import com.example.pbl.model.Instalacao;
import com.example.pbl.model.Limpeza;
import com.example.pbl.utils.componentesJavafx.ServicoCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GerenciarInstalacoesWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnEditar;

    @FXML
    private ScrollPane scInstalacoes;

    @FXML
    private TextField txtBuscarNome;

    @FXML
    private TextField txtCusto;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtPreco;

    private FlowPane flowPaneInstalacoes;
    private List<Instalacao> listaInstalacoes;
    private List<Button> listaBotoes;
    private AlertWindow alertWindow;
    private AvisoWindow avisoWindow;

    @FXML
    void cadastrarAction(ActionEvent event) {
        Instalacao novaInstalacao = this.criarInstalacao();
        boolean instalacaoValida = this.validarFormulario(novaInstalacao);

        if (instalacaoValida){
            this.acionarAlert("AlertWindow.fxml", "Cadastrar Instalação?");
            if (this.alertWindow.getConfirmacao()) {
                DAO.getInstalacao().criar(novaInstalacao);
                this.listaInstalacoes.add(novaInstalacao);

                this.carregarScrollPaneServico();
                this.limparCampos();
                this.carregarCSS();
            }
        }
    }

    @FXML
    void deletarAction(ActionEvent event) {

    }

    @FXML
    void editarAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnCadastrar != null : "fx:id=\"btnCadastrar\" was not injected: check your FXML file 'GerenciarInstalacoesWindow.fxml'.";
        assert btnDeletar != null : "fx:id=\"btnDeletar\" was not injected: check your FXML file 'GerenciarInstalacoesWindow.fxml'.";
        assert btnEditar != null : "fx:id=\"btnEditar\" was not injected: check your FXML file 'GerenciarInstalacoesWindow.fxml'.";
        assert scInstalacoes != null : "fx:id=\"scInstalacoes\" was not injected: check your FXML file 'GerenciarInstalacoesWindow.fxml'.";
        assert txtBuscarNome != null : "fx:id=\"txtBuscarNome\" was not injected: check your FXML file 'GerenciarInstalacoesWindow.fxml'.";
        assert txtCusto != null : "fx:id=\"txtCusto\" was not injected: check your FXML file 'GerenciarInstalacoesWindow.fxml'.";
        assert txtDescricao != null : "fx:id=\"txtDescricao\" was not injected: check your FXML file 'GerenciarInstalacoesWindow.fxml'.";
        assert txtPreco != null : "fx:id=\"txtPreco\" was not injected: check your FXML file 'GerenciarInstalacoesWindow.fxml'.";

        this.listaInstalacoes = new LinkedList<>();
        this.listaInstalacoes = DAO.getInstalacao().buscarTodos();
        this.listaBotoes = new LinkedList<>();

        this.carregarScrollPaneServico();
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

    private void carregarScrollPaneServico(){
        try {
            try {
                List<String> listaNomes = new LinkedList<>();
                listaNomes.add("Descricao");
                this.flowPaneInstalacoes = ServicoCard.criarTabelaMenor(this.listaInstalacoes, listaNomes, 405, 0);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            this.scInstalacoes.setContent(this.flowPaneInstalacoes);

            this.scInstalacoes.setPannable(true);

            this.scInstalacoes.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            this.scInstalacoes.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            this.selecionarBotoesServicos();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    void selecionarBotoesServicos(){
        HBox hboxMontagem;

        this.listaBotoes.clear();

        for (Node montagem : this.flowPaneInstalacoes.getChildren()){
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

                try {
                    Instalacao instalacao = DAO.getInstalacao().buscarPorId(index);

                    if (instalacao.getOrdensServico().size() == 0) {
                        DAO.getInstalacao().remover(this.listaInstalacoes.get(index));
                        this.listaBotoes.remove(index);
                        this.listaInstalacoes.remove(index);

                        this.carregarScrollPaneServico();
                    } else{
                        this.acionarAviso("AvisoWindow.fxml", "O serviço está associado a uma\nordem de serviço.\nNão é possível removê-lo!");
                    }
                } catch (ObjetoNaoEncontradoException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
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

    void limparCampos(){
        this.txtDescricao.clear();
        this.txtDescricao.setPromptText("");

        this.txtPreco.clear();
        this.txtPreco.setPromptText("");

        this.txtCusto.clear();
        this.txtCusto.setPromptText("");
    }
    private boolean validarFormulario(Instalacao novaInstalacao){
        this.carregarCSS(); // Carrega o css padrão
        boolean instalacaoValida = true;

        if (novaInstalacao.getDescricao().equals("")){
            this.txtDescricao.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0;");
            this.txtDescricao.setPromptText("Descricao inválida!");
            instalacaoValida = false;
        }

        if (novaInstalacao.getPreco() < 0){
            this.txtPreco.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0; ");
            this.txtPreco.setPromptText("Preço inválido!");
            instalacaoValida = false;
        }

        if (novaInstalacao.getCusto() < 0){
            this.txtCusto.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0; ");
            this.txtCusto.setPromptText("Custo inválido!");
            instalacaoValida = false;
        }

        return instalacaoValida;
    }

    private void carregarCSS(){
        txtDescricao.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtPreco.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtCusto.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
    }

    private Instalacao criarInstalacao(){
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

        return new Instalacao(custo, preco, descricao);
    }

    private boolean verificarTipoTexto(String valor){
        try{
            Double.parseDouble(valor);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
