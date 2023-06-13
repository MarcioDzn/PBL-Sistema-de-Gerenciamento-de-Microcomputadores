package com.example.pbl.controller;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.exceptions.OrdemServicoAtualException;
import com.example.pbl.exceptions.OrdemServicoException;
import com.example.pbl.model.OrdemServico;
import com.example.pbl.model.Tecnico;
import com.example.pbl.utils.LoginAtual;
import com.example.pbl.utils.componentesJavafx.OrdensCard;
import com.example.pbl.utils.componentesJavafx.ServicoCard;
import com.example.pbl.utils.componentesJavafx.VazioCard;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuPrincipalWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane scOrdens;
    private FlowPane flowPaneOrdens;
    private List<OrdemServico> listaOrdens;
    private Map<Button, String> mapBotoes;
    @FXML
    void initialize() {
        assert scOrdens != null : "fx:id=\"scOrdens\" was not injected: check your FXML file 'MenuPrincipalWindow.fxml'.";

        this.listaOrdens = new LinkedList<>();
        this.listaOrdens.addAll(DAO.getOrdemServico().buscarOrdensEmAberto()); // Resolver pra ele só pegar as ordens em andamento

        this.mapBotoes = new HashMap<>();

        this.carregarScrollPaneOrdens();
        this.mudarStatusBotao();
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
                this.scOrdens.setContent(VazioCard.mensagemVazio("ordem de serviço em aberto", 900, 310));
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
                                    this.mapBotoes.put(botao, botao.getId());
                                }
                            }
                        }
                    }
                }
            }
            this.mudarStatusBotao();
        }
    }

    void mudarStatusBotao(){
        for (Button botao : this.mapBotoes.keySet()){
            botao.setOnAction(e -> {
                botao.setText("Selecionado");
                botao.setStyle("-fx-background-color: #bfbfbf;");

                String idBotao = this.mapBotoes.get(botao);
                int indexHifen = idBotao.indexOf("-");

                int idDAO = Integer.parseInt(idBotao.substring(indexHifen+1));

                try {
                    OrdemServico ordem = DAO.getOrdemServico().buscarPorId(idDAO);
                    ordem.colocarEmAndamento();
                    ordem.setTecnicoId(LoginAtual.idLogin);

                    DAO.getOrdemServico().atualizar(ordem);

                    Tecnico tecnicoAtualizado = DAO.getTecnico().buscarPorId(LoginAtual.idLogin);
                    tecnicoAtualizado.addOrdemServicoAtual(ordem.getId());
                    DAO.getTecnico().atualizar(tecnicoAtualizado);

                } catch (OrdemServicoException | ObjetoNaoEncontradoException | OrdemServicoAtualException ex) {
                    throw new RuntimeException(ex);
                }

                this.listaOrdens.clear();
                this.listaOrdens.addAll(DAO.getOrdemServico().buscarOrdensEmAberto());
                this.carregarScrollPaneOrdens();

                this.mapBotoes.remove(botao);
            });
        }

        this.carregarBotao();
    }

    void carregarBotao(){
        for (Button botao : this.mapBotoes.keySet()){
            if (DAO.getTecnico().buscarPorId(LoginAtual.idLogin) != null){
                System.out.println("a" + DAO.getTecnico().buscarPorId(LoginAtual.idLogin).getOrdemServicoAtual());
                if (DAO.getTecnico().buscarPorId(LoginAtual.idLogin).getOrdemServicoAtual() == null) {
                    botao.setStyle("-fx-brackground-color: white");
                    botao.setDisable(false);
                }
            }
        }
    }

}
