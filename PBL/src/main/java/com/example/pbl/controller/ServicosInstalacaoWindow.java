package com.example.pbl.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

import com.example.pbl.HelloApplication;
import com.example.pbl.dao.DAO;
import com.example.pbl.model.Servico;
import com.example.pbl.utils.componentesJavafx.ServicoCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ServicosInstalacaoWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnFinalizar;

    @FXML
    private ScrollPane scServicos;

    private FlowPane flowPaneServicos;
    private List<Servico> listaServicos;
    private List<Servico> listaServicosSelecionados;
    private Map<Button, String> mapBotoes;

    GerenciarOrdensWindow gerenciarOrdensController;
    private AlertWindow alertWindow;

    @FXML
    void cancelarAction(ActionEvent event) {
        this.acionarAlert("AlertWindow.fxml", "Cancelar?");
        if (this.alertWindow.getConfirmacao()) {
            this.fecharJanela(event);
        }
    }

    @FXML
    void finalizarAction(ActionEvent event) {
        this.acionarAlert("AlertWindow.fxml", "Finalizar?");
        if (this.alertWindow.getConfirmacao()) {
            this.listaServicosSelecionados.clear();

            for (Button botao : this.mapBotoes.keySet()) {
                if (botao.getText().equals("Selecionado")) {
                    String idBotao = this.mapBotoes.get(botao);
                    int indexHifen = idBotao.indexOf("-");

                    String tipoItem = idBotao.substring(0, indexHifen);
                    int idDAO = Integer.parseInt(idBotao.substring(indexHifen + 1));

                    switch (tipoItem) {
                        case "Limpeza" -> this.listaServicosSelecionados.add(DAO.getLimpeza().buscarPorId(idDAO));
                        case "Montagem" -> this.listaServicosSelecionados.add(DAO.getMontagem().buscarPorId(idDAO));
                        case "Instalacao" -> this.listaServicosSelecionados.add(DAO.getInstalacao().buscarPorId(idDAO));
                    }
                }
            }

            this.gerenciarOrdensController.listaInstalacoesSelecionadas = this.listaServicosSelecionados;
            this.fecharJanela(event);
        }
    }

    @FXML
    void initialize() {
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'ServicosWindow.fxml'.";
        assert btnFinalizar != null : "fx:id=\"btnFinalizar\" was not injected: check your FXML file 'ServicosWindow.fxml'.";
        assert scServicos != null : "fx:id=\"scServicos\" was not injected: check your FXML file 'ServicosWindow.fxml'.";

        this.listaServicosSelecionados = new LinkedList<>();

        this.listaServicos = new LinkedList<>();
        this.listaServicos.addAll(DAO.getInstalacao().buscarTodos());

        this.mapBotoes = new HashMap<>();

        this.carregarScrollPaneServico();
        this.selecionarBotoesServicos();
        this.mudarStatusBotao();
    }

    void fecharJanela(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        stage.close();
    }


    private void carregarScrollPaneServico(){
        try {
            try {
                this.flowPaneServicos = ServicoCard.criarTabela(this.listaServicos, 196, 255);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            this.scServicos.setContent(this.flowPaneServicos);

            this.scServicos.setPannable(true);
            this.scServicos.setFitToHeight(true);

            this.scServicos.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            this.scServicos.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    void selecionarBotoesServicos(){
        HBox hboxMontagem;

        for (Node montagem : this.flowPaneServicos.getChildren()){
            Button botao;
            hboxMontagem = (HBox) montagem;

            for (Node node : hboxMontagem.getChildren()){
                if (node instanceof VBox){
                    for (Node mostInnerNode : ((VBox) node).getChildren()){
                        if (mostInnerNode instanceof VBox){
                            for (Node innerNode: ((VBox) mostInnerNode).getChildren()){
                                if (innerNode instanceof Button){
                                    botao = (Button) innerNode;
                                    this.mapBotoes.put(botao, botao.getId());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void mudarStatusBotao(){
        for (Button botao : this.mapBotoes.keySet()){
            botao.setOnAction(e -> {
                if (botao.getText().equals("Selecionar")){
                    botao.setText("Selecionado");
                    botao.setStyle("-fx-background-color: #bfbfbf;");

                } else{
                    botao.setText("Selecionar");
                    botao.setStyle("-fx-background-color: white;");
                }
            });
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
}

