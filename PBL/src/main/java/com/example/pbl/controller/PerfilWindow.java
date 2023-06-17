package com.example.pbl.controller;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import com.example.pbl.dao.DAO;
import com.example.pbl.model.OrdemServico;
import com.example.pbl.utils.LoginAtual;
import com.example.pbl.utils.componentesJavafx.OrdensCard;
import com.example.pbl.utils.componentesJavafx.ServicoCard;
import com.example.pbl.utils.componentesJavafx.VazioCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class PerfilWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnFechar;

    @FXML
    private Label nomeUsuario;

    @FXML
    private ScrollPane scOrdens;

    @FXML
    private Label tipoCargo;
    private FlowPane flowPaneOrdens;

    @FXML
    void fecharAction(ActionEvent event) {
        this.fecharJanela(event);
    }

    @FXML
    void initialize() {
        assert btnFechar != null : "fx:id=\"btnFechar\" was not injected: check your FXML file 'PerfilWindow.fxml'.";
        assert nomeUsuario != null : "fx:id=\"nomeUsuario\" was not injected: check your FXML file 'PerfilWindow.fxml'.";
        assert scOrdens != null : "fx:id=\"scOrdens\" was not injected: check your FXML file 'PerfilWindow.fxml'.";
        assert tipoCargo != null : "fx:id=\"tipoCargo\" was not injected: check your FXML file 'PerfilWindow.fxml'.";

        this.carregarUsuario();

    }

    private void carregarUsuario(){
        if (LoginAtual.idLogin != -1) {
            this.carregarScrollPaneServico();
            this.nomeUsuario.setText(DAO.getTecnico().buscarPorId(LoginAtual.idLogin).getNome());
            this.tipoCargo.setText("Técnico");
        } else{
            this.scOrdens.setContent(VazioCard.mensagemVazio("ordem de serviço", 770, 370));
            this.scOrdens.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            this.scOrdens.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            this.nomeUsuario.setText("Admin");
            this.tipoCargo.setText("Administrador");
        }
    }

    private void carregarScrollPaneServico(){
        try {
            List<OrdemServico> ordensHistorico = DAO.getTecnico().buscarPorId(LoginAtual.idLogin).getOrdensServico();

            if (ordensHistorico.size() > 0) {
                Collections.reverse(ordensHistorico);

                this.flowPaneOrdens = OrdensCard.criarTabela(ordensHistorico, 240, 255, false);
                this.scOrdens.setContent(this.flowPaneOrdens);

            } else {
                this.scOrdens.setContent(this.flowPaneOrdens);
                this.scOrdens.setContent(VazioCard.mensagemVazio("ordem de serviço", 770, 322));

            }

            this.scOrdens.setPannable(true);
            this.scOrdens.setFitToHeight(true);

            this.scOrdens.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            this.scOrdens.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    void fecharJanela(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        stage.close();
    }
}


