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
import com.example.pbl.model.Montagem;
import com.example.pbl.model.OutroComponente;
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

public class GerenciarLimpezasWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ScrollPane scLimpezas;

    @FXML
    private TextField txtBuscarNome;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtCusto;
    @FXML
    private TextField txtPreco;

    private FlowPane flowPaneLimpezas;
    private List<Limpeza> listaLimpezas;
    private List<Button> listaBotoes;
    private AlertWindow alertWindow;
    private AvisoWindow avisoWindow;

    @FXML
    void cadastrarAction(ActionEvent event) {
        Limpeza novaLimpeza = this.criarLimpeza();
        boolean limpezaValida = this.validarFormulario(novaLimpeza);

        if (limpezaValida){
            this.acionarAlert("AlertWindow.fxml", "Cadastrar Limpeza?");
            if (this.alertWindow.getConfirmacao()) {
                DAO.getLimpeza().criar(novaLimpeza);
                this.listaLimpezas.add(novaLimpeza);

                this.carregarScrollPaneServico();
                this.limparCampos();
                this.carregarCSS();
            }
        }
    }

    @FXML
    void cancelarAction(ActionEvent event) {
        this.limparCampos();
        this.carregarCSS();
    }

    @FXML
    void initialize() {
        assert btnCadastrar != null : "fx:id=\"btnCadastrar\" was not injected: check your FXML file 'GerenciarLimpezasWindow.fxml'.";
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'GerenciarLimpezasWindow.fxml'.";
        assert scLimpezas != null : "fx:id=\"scLimpezas\" was not injected: check your FXML file 'GerenciarLimpezasWindow.fxml'.";
        assert txtBuscarNome != null : "fx:id=\"txtBuscarNome\" was not injected: check your FXML file 'GerenciarLimpezasWindow.fxml'.";
        assert txtCusto != null : "fx:id=\"txtCusto\" was not injected: check your FXML file 'GerenciarLimpezasWindow.fxml'.";
        assert txtDescricao != null : "fx:id=\"txtDescricao\" was not injected: check your FXML file 'GerenciarLimpezasWindow.fxml'.";
        assert txtPreco != null : "fx:id=\"txtPreco\" was not injected: check your FXML file 'GerenciarLimpezasWindow.fxml'.";

        this.listaLimpezas = new LinkedList<>();
        this.listaLimpezas = DAO.getLimpeza().buscarTodos();
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
                this.flowPaneLimpezas = ServicoCard.criarTabelaMenor(this.listaLimpezas, listaNomes, 405, 0);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            this.scLimpezas.setContent(this.flowPaneLimpezas);

            this.scLimpezas.setPannable(true);

            this.scLimpezas.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            this.scLimpezas.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            this.selecionarBotoesServicos();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    void selecionarBotoesServicos(){
        HBox hboxMontagem;

        this.listaBotoes.clear();

        for (Node montagem : this.flowPaneLimpezas.getChildren()){
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
                    Limpeza limpeza = DAO.getLimpeza().buscarPorId(index);

                    if (limpeza.getOrdensServico().size() == 0) {
                        DAO.getLimpeza().remover(this.listaLimpezas.get(index));
                        this.listaBotoes.remove(index);
                        this.listaLimpezas.remove(index);

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

    void limparCampos(){
        this.txtDescricao.clear();
        this.txtDescricao.setPromptText("");

        this.txtPreco.clear();
        this.txtPreco.setPromptText("");

        this.txtCusto.clear();
        this.txtCusto.setPromptText("");
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

    private boolean validarFormulario(Limpeza novaLimpeza){
        this.carregarCSS(); // Carrega o css padrão
        boolean limpezaValida = true;

        if (novaLimpeza.getDescricao().equals("")){
            this.txtDescricao.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0;");
            this.txtDescricao.setPromptText("Descricao inválida!");
            limpezaValida = false;
        }

        if (novaLimpeza.getPreco() < 0){
            this.txtPreco.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0; ");
            this.txtPreco.setPromptText("Preço inválido!");
            limpezaValida = false;
        }

        if (novaLimpeza.getCusto() < 0){
            this.txtCusto.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0; ");
            this.txtCusto.setPromptText("Custo inválido!");
            limpezaValida = false;
        }

        return limpezaValida;
    }

    private void carregarCSS(){
        txtDescricao.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtPreco.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtCusto.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
    }



    private Limpeza criarLimpeza(){
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

        return new Limpeza(custo, preco, descricao);
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
