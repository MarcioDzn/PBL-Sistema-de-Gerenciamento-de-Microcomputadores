package com.example.pbl.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

import com.example.pbl.HelloApplication;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.exceptions.OrdemServicoAtualException;
import com.example.pbl.exceptions.OrdemServicoException;
import com.example.pbl.model.OrdemServico;
import com.example.pbl.model.Tecnico;
import com.example.pbl.utils.LoginAtual;
import com.example.pbl.utils.componentesJavafx.OrdensCard;
import com.example.pbl.utils.componentesJavafx.VazioCard;
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

public class MenuPrincipalWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane scOrdens;
    private FlowPane flowPaneOrdens;
    private List<OrdemServico> listaOrdens;
    private List<Button> listaBotoesSelecionar;
    private List<Button> listaBotoesRemover;
    private AlertWindow alertWindow;
    @FXML
    void initialize() {
        assert scOrdens != null : "fx:id=\"scOrdens\" was not injected: check your FXML file 'MenuPrincipalWindow.fxml'.";

        this.listaOrdens = new LinkedList<>();
        this.listaOrdens.addAll(DAO.getOrdemServico().buscarOrdensEmAberto()); // Resolver pra ele só pegar as ordens em andamento

        this.listaBotoesSelecionar = new LinkedList<>();
        this.listaBotoesRemover = new LinkedList<>();

        this.carregarScrollPaneOrdens();
        this.mudarStatusBotaoSelecionar();
    }

    private void carregarScrollPaneOrdens(){
        try {

            if (this.listaOrdens.size() > 0) {
                try {
                    this.flowPaneOrdens = OrdensCard.criarTabela(this.listaOrdens, 196, 250);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                this.scOrdens.setContent(this.flowPaneOrdens);

                this.scOrdens.setPannable(true);
                this.scOrdens.setFitToHeight(true);



                this.carregarBotao();
            } else {
                this.scOrdens.setContent(VazioCard.mensagemVazio("ordem de serviço em aberto", 900, 450));
            }

            this.scOrdens.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            this.scOrdens.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        this.selecionarBotoesOrdens();
    }

    void selecionarBotoesOrdens(){
        Button botao;
        
        if (this.listaOrdens.size() > 0){
            HBox hboxMontagem = (HBox) this.flowPaneOrdens.getChildren().get(0);

            for (Node node : hboxMontagem.getChildren()){
                if (node instanceof VBox){
                    for (Node mostInnerNode : ((VBox) node).getChildren()){
                        if (mostInnerNode instanceof VBox){
                            for (Node innerNode: ((VBox) mostInnerNode).getChildren()){
                                if (innerNode instanceof Button){
                                    botao = (Button) innerNode;

                                    if (botao.getText().equals("Selecionar")){
                                        this.listaBotoesSelecionar.add(botao);

                                    } else if (botao.getText().equals("Remover")) {
                                        this.listaBotoesRemover.add(botao);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            this.mudarStatusBotaoSelecionar();
            this.mudarStatusBotarRemover();
        }
    }

    void mudarStatusBotaoSelecionar(){
        for (Button botao : this.listaBotoesSelecionar){
            botao.setOnAction(e -> {
                this.acionarAlert("AlertWindow.fxml", "Pegar ordem de serviço?");
                if (this.alertWindow.getConfirmacao()) {
                    botao.setText("Selecionado");
                    botao.setStyle("-fx-background-color: #bfbfbf;");

                    String idBotao = botao.getId();
                    int indexHifen = idBotao.indexOf("-");

                    int idDAO = Integer.parseInt(idBotao.substring(indexHifen + 1));

                    try {
                        OrdemServico ordem = DAO.getOrdemServico().buscarPorId(idDAO);
                        ordem.colocarEmAndamento();
                        ordem.setTecnicoId(LoginAtual.idLogin);

                        DAO.getOrdemServico().atualizar(ordem);

                        Tecnico tecnicoAtualizado = DAO.getTecnico().buscarPorId(LoginAtual.idLogin);
                        tecnicoAtualizado.addOrdemServicoAtual(ordem.getId());
                        DAO.getTecnico().atualizar(tecnicoAtualizado);

                        this.listaOrdens.clear();
                        this.listaOrdens.addAll(DAO.getOrdemServico().buscarOrdensEmAberto());
                        this.carregarScrollPaneOrdens();

                        this.listaBotoesSelecionar.remove(botao);

                    } catch (OrdemServicoException | ObjetoNaoEncontradoException | OrdemServicoAtualException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }

        this.carregarBotao();
    }

    void mudarStatusBotarRemover(){
        for (Button botao : this.listaBotoesRemover){
            botao.setOnAction(e -> {
                try {
                    String idBotao = botao.getId();
                    int indexHifen = idBotao.indexOf("-");

                    int idDAO = Integer.parseInt(idBotao.substring(indexHifen + 1));

                    this.acionarAlert("AlertWindow.fxml", "Remover ordem de serviço?");
                    if (this.alertWindow.getConfirmacao()) {
                        OrdemServico ordem = DAO.getOrdemServico().buscarPorId(idDAO);
                        DAO.getOrdemServico().remover(ordem);

                        this.listaOrdens.clear();
                        this.listaOrdens.addAll(DAO.getOrdemServico().buscarOrdensEmAberto());
                        this.carregarScrollPaneOrdens();

                        this.listaBotoesRemover.remove(botao);
                    }


                } catch (ObjetoNaoEncontradoException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }

        this.carregarBotao();
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

    void carregarBotao(){
        for (Button botao : this.listaBotoesSelecionar){
            if (DAO.getTecnico().buscarPorId(LoginAtual.idLogin) != null){
                if (DAO.getTecnico().buscarPorId(LoginAtual.idLogin).getOrdemServicoAtual() == null) {
                    botao.setStyle("-fx-brackground-color: white");
                    botao.setDisable(false);
                }
            }
        }
    }

}
