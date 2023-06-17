package com.example.pbl.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

import com.example.pbl.HelloApplication;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.exceptions.QuantidadeException;
import com.example.pbl.model.Peca;
import com.example.pbl.utils.componentesJavafx.PecaCard;
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

public class EscolherPecasWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnFinalizar;

    @FXML
    private ScrollPane scPecas;

    private FlowPane flowPanePecas;

    private List<Peca> listaPecas;

    private List<Peca> listaPecasSelecionadas;

    private List<Button> listaBotoes;
    private List<TextField> listaTxtField;

    private boolean txtsValidos;

    protected GerenciarMontagensWindow gerenciarMontagensController;
    private AlertWindow alertWindow;
    private AvisoWindow avisoWindow;

    @FXML
    void cancelarAction(ActionEvent event) {
        this.acionarAlert("AlertWindow.fxml", "Cancelar?");
        if (this.alertWindow.getConfirmacao()) {
            this.fecharJanela(event);
        }
    }

    @FXML
    void finalizarAction(ActionEvent event) {
        this.validarCampos();

        if (this.txtsValidos){
            this.acionarAlert("AlertWindow.fxml", "Finalizar?");
            if (this.alertWindow.getConfirmacao()) {
                this.guardarSelecionados();
                this.gerenciarMontagensController.listaPecasSelecionadas.addAll(this.listaPecasSelecionadas);
                this.fecharJanela(event);
            }
        } else{
            this.acionarAviso("AvisoWindow.fxml", "Campos inválidos!");
        }

    }

    @FXML
    void initialize() {
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'EscolherPecasWindow.fxml'.";
        assert btnFinalizar != null : "fx:id=\"btnFinalizar\" was not injected: check your FXML file 'EscolherPecasWindow.fxml'.";
        assert scPecas != null : "fx:id=\"scPecas\" was not injected: check your FXML file 'EscolherPecasWindow.fxml'.";

        this.listaPecasSelecionadas = new LinkedList<>();

        this.listaPecas = new LinkedList<>();
        this.listaPecas.addAll(DAO.getPeca().buscarTodos());

        this.listaBotoes = new LinkedList<>();
        this.listaTxtField = new LinkedList<>();

        this.carregarScrollPanePeca();
        this.selecionarBotoesTxtFieldPecas();
        this.mudarStatusBotao();
        this.validarTxtField();
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

    void fecharJanela(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        stage.close();
    }

    private void validarTxtField(){
        for (int i = 0; i < this.listaTxtField.size(); i++){
            TextField txtQuantidade = this.listaTxtField.get(i);

            txtQuantidade.setOnKeyReleased(keyEvent -> {
                this.validarCampos();
            });
        }
    }
    private void validarCampos(){
        this.txtsValidos = true;

        boolean txtsValidosUnitario;
        for (int i = 0; i < this.listaTxtField.size(); i++){
            TextField txtQuantidade = this.listaTxtField.get(i);
            txtsValidosUnitario = true;

            if (verificarTipo(txtQuantidade.getText())){
                if (Integer.parseInt(txtQuantidade.getText()) < 0 ||
                        Integer.parseInt(txtQuantidade.getText()) > DAO.getPeca().buscarPorId(this.buscarIdPeca(txtQuantidade.getId())).getQuantidade())
                    txtsValidosUnitario = false;

            } else if (!txtQuantidade.getText().strip().equals("") && this.listaBotoes.get(i).getText().equals("Selecionado"))
                txtsValidosUnitario = false;

            if (!txtsValidosUnitario){
                txtQuantidade.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0; ");
                txtQuantidade.setPromptText("Valor inválido!");

                // Garante que apenas as peças selecionadas invalidas impeçam a finalização
                if (this.listaBotoes.get(i).getText().equals("Selecionado"))
                    this.txtsValidos = false;

            } else{
                txtQuantidade.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
                txtQuantidade.setPromptText("Quantidade");
            }
        }
    }

    private boolean verificarTipo(String texto){
        try{
            Integer.parseInt(texto);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private int buscarIdPeca(String id){
        int indexHifen = id.indexOf("-");

        return Integer.parseInt(id.substring(indexHifen + 1));
    }

    private void guardarSelecionados(){
        for (int i = 0; i < this.listaBotoes.size(); i++){
            Button botao = this.listaBotoes.get(i);

            if (botao.getText().equals("Selecionado")) {
                int idDAO = this.buscarIdPeca(botao.getId());

                String quantidadeSelecionadaTxt = this.listaTxtField.get(i).getText().strip();

                int quantidadeSelecionada;
                if (!quantidadeSelecionadaTxt.equals(""))
                    quantidadeSelecionada = Integer.parseInt(quantidadeSelecionadaTxt);
                else
                    quantidadeSelecionada = 0;

                Peca pecaSelecionada = DAO.getPeca().buscarPorId(idDAO);

                for (int j = 0; j < quantidadeSelecionada; j++) {
                    this.listaPecasSelecionadas.add(pecaSelecionada);
                }
            }
        }
    }

    private void carregarScrollPanePeca(){
        try {
            try {
                this.flowPanePecas = PecaCard.criarTabela(this.listaPecas, 195, 300);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            this.scPecas.setContent(this.flowPanePecas);

            this.scPecas.setPannable(true);
            this.scPecas.setFitToHeight(true);

            this.scPecas.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            this.scPecas.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    void selecionarBotoesTxtFieldPecas(){
        HBox hboxMontagem;

        for (Node montagem : this.flowPanePecas.getChildren()){
            hboxMontagem = (HBox) montagem;

            for (Node node : hboxMontagem.getChildren()){
                if (node instanceof VBox){
                    for (Node mostInnerNode : ((VBox) node).getChildren()){
                        if (mostInnerNode instanceof VBox){
                            for (Node innerNode: ((VBox) mostInnerNode).getChildren()){
                                if (innerNode instanceof TextField){
                                    this.listaTxtField.add((TextField) innerNode);
                                }

                                if (innerNode instanceof Button){
                                    this.listaBotoes.add((Button) innerNode);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void mudarStatusBotao(){
        for (int i = 0; i < this.listaBotoes.size(); i++){
            Button botao = this.listaBotoes.get(i);

            int finalI = i;
            botao.setOnAction(e -> {
                if (botao.getText().equals("Selecionar")){
                    botao.setText("Selecionado");
                    botao.setStyle("-fx-background-color: #bfbfbf;");
                    this.listaTxtField.get(finalI).setDisable(false);

                } else{
                    botao.setText("Selecionar");
                    botao.setStyle("-fx-background-color: white;");
                    this.listaTxtField.get(finalI).setDisable(true);
                }

                this.listaTxtField.get(finalI).clear();
                this.validarCampos();
            });
        }

    }

}
