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

public class ManipularOrdensWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane scOrdens;
    private FlowPane flowPaneOrdens;
    private List<OrdemServico> listaOrdens;
    private List<Button> listaBotoesFinalizar;
    private List<Button> listaBotoesCancelar;
    private List<Button> listaBotoesEditar;
    private AlertWindow alertWindow;


    @FXML
    void initialize() {
        assert scOrdens != null : "fx:id=\"scOrdens\" was not injected: check your FXML file 'ManipularOrdensWindow.fxml'.";

        this.listaOrdens = new LinkedList<>();

        Tecnico tecnico = DAO.getTecnico().buscarPorId(LoginAtual.idLogin);

        if (tecnico != null){
            if (tecnico.getOrdemServicoAtual() != null)
                this.listaOrdens.add(tecnico.getOrdemServicoAtual());
        }

        this.listaBotoesFinalizar = new LinkedList<>();
        this.listaBotoesCancelar = new LinkedList<>();
        this.listaBotoesEditar = new LinkedList<>();

        this.carregarScrollPaneOrdem();
    }

    private void carregarScrollPaneOrdem() {
        try {

            if (this.listaOrdens.size() > 0) {
                try {
                    this.flowPaneOrdens = OrdensCard.criarTabelaOrdemAtual(this.listaOrdens, 220, 250);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                this.scOrdens.setContent(this.flowPaneOrdens);

                this.scOrdens.setPannable(true);
                this.scOrdens.setFitToHeight(true);

            } else {
                this.scOrdens.setContent(VazioCard.mensagemVazio("ordem de serviço em andamento", 900, 440));
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

                                    if (botao.getText().equals("Finalizar")){
                                        this.listaBotoesFinalizar.add(botao);

                                    } else if (botao.getText().equals("Cancelar")){
                                        this.listaBotoesCancelar.add(botao);

                                    } else if (botao.getText().equals("Editar")){
                                        this.listaBotoesEditar.add(botao);
                                    }

                                }
                            }
                        }
                    }
                }
            }
            this.mudarStatusBotaoFinalizar();
            this.mudarStatusBotaoCancelar();
        }
    }

    void mudarStatusBotaoFinalizar(){
        for (Button botao : this.listaBotoesFinalizar){
            botao.setOnAction(e -> {
                try {
                    this.acionarAlert("AlertWindow.fxml", "Finalizar ordem de serviço?");
                    if (this.alertWindow.getConfirmacao()) {
                        Tecnico tecnicoAtualizado = DAO.getTecnico().buscarPorId(LoginAtual.idLogin);

                        this.abrirPagina("AvaliacaoWindow.fxml");
                        OrdemServico ordem = tecnicoAtualizado.getOrdemServicoAtual();

                        if (ordem.getAvaliacao() != null) {
                            ordem.finalizar();
                            DAO.getOrdemServico().atualizar(ordem);
                            tecnicoAtualizado.addOrdemServicoAtual(-1);

                            DAO.getTecnico().atualizar(tecnicoAtualizado);

                            this.listaOrdens.clear();
                            this.carregarScrollPaneOrdem();
                        }
                    }
                } catch (OrdemServicoException | ObjetoNaoEncontradoException | OrdemServicoAtualException ex) {
                    throw new RuntimeException(ex);
                }

            });
        }
    }

    void mudarStatusBotaoCancelar(){
        for (Button botao : this.listaBotoesCancelar){
            botao.setOnAction(e -> {
                try {
                    this.acionarAlert("AlertWindow.fxml", "Cancelar ordem de serviço?");
                    if (this.alertWindow.getConfirmacao()) {
                        Tecnico tecnicoAtualizado = DAO.getTecnico().buscarPorId(LoginAtual.idLogin);

                        OrdemServico ordem = tecnicoAtualizado.getOrdemServicoAtual();
                        ordem.cancelar();
                        DAO.getOrdemServico().atualizar(ordem);

                        tecnicoAtualizado.addOrdemServicoAtual(-1);
                        DAO.getTecnico().atualizar(tecnicoAtualizado);

                        this.listaOrdens.clear();

                        this.carregarScrollPaneOrdem();
                    }
                } catch (OrdemServicoException | ObjetoNaoEncontradoException | OrdemServicoAtualException ex) {
                    throw new RuntimeException(ex);
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
            stage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
