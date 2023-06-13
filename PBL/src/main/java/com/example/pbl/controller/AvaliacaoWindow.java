package com.example.pbl.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.exceptions.OrdemServicoException;
import com.example.pbl.model.OrdemServico;
import com.example.pbl.model.Tecnico;
import com.example.pbl.utils.LoginAtual;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AvaliacaoWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBom;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnFinalizar;

    @FXML
    private Button btnMediano;

    @FXML
    private Button btnOtimo;

    @FXML
    private Button btnPessimo;

    @FXML
    private Button btnRuim;

    private String avaliacao;

    @FXML
    void bomAction(ActionEvent event) {
        this.avaliacao = "Bom";
        this.colorirBotoes(this.btnBom);
    }

    @FXML
    void cancelarAction(ActionEvent event) {
        this.fecharJanela(event);
    }

    @FXML
    void finalizarAction(ActionEvent event) {
        try {
            if (this.avaliacao == null)
                this.avaliacao = "Indisponível";

            Tecnico tecnicoAtualizado = DAO.getTecnico().buscarPorId(LoginAtual.idLogin);

            OrdemServico ordem = tecnicoAtualizado.getOrdemServicoAtual();

            ordem.setAvaliacao(this.avaliacao);

            DAO.getOrdemServico().atualizar(ordem);


            this.fecharJanela(event);
        } catch (OrdemServicoException | ObjetoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void medianoAction(ActionEvent event) {
        this.avaliacao = "Mediano";
        this.colorirBotoes(this.btnMediano);
    }

    @FXML
    void otimoAction(ActionEvent event) {
        this.avaliacao = "Otimo";
        this.colorirBotoes(this.btnOtimo);
    }

    @FXML
    void pessimoAction(ActionEvent event) {
        this.avaliacao = "Pessimo";
        this.colorirBotoes(this.btnPessimo);
    }

    @FXML
    void ruimAction(ActionEvent event) {
        this.avaliacao = "Ruim";
        this.colorirBotoes(this.btnRuim);
    }

    @FXML
    void initialize() {
        assert btnBom != null : "fx:id=\"btnBom\" was not injected: check your FXML file 'AvaliacaoWindow.fxml'.";
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'AvaliacaoWindow.fxml'.";
        assert btnFinalizar != null : "fx:id=\"btnFinalizar\" was not injected: check your FXML file 'AvaliacaoWindow.fxml'.";
        assert btnMediano != null : "fx:id=\"btnMediano\" was not injected: check your FXML file 'AvaliacaoWindow.fxml'.";
        assert btnOtimo != null : "fx:id=\"btnOtimo\" was not injected: check your FXML file 'AvaliacaoWindow.fxml'.";
        assert btnPessimo != null : "fx:id=\"btnPessimo\" was not injected: check your FXML file 'AvaliacaoWindow.fxml'.";
        assert btnRuim != null : "fx:id=\"btnRuim\" was not injected: check your FXML file 'AvaliacaoWindow.fxml'.";

    }
    
    private void colorirBotoes(Button botao){
        if (botao != this.btnPessimo)
            this.btnPessimo.setStyle("-fx-background-color:  #282828; -fx-border-width: 1.5; -fx-border-color: white; -fx-border-radius: 8;");
        if (botao != this.btnRuim)
            this.btnRuim.setStyle("-fx-background-color:  #282828; -fx-border-width: 1.5; -fx-border-color: white; -fx-border-radius: 8;");
        if (botao != this.btnMediano)
            this.btnMediano.setStyle("-fx-background-color:  #282828; -fx-border-width: 1.5; -fx-border-color: white; -fx-border-radius: 8;");
        if (botao != this.btnBom)
            this.btnBom.setStyle("-fx-background-color:  #282828; -fx-border-width: 1.5; -fx-border-color: white; -fx-border-radius: 8;");
        if (botao != this.btnOtimo)
            this.btnOtimo.setStyle("-fx-background-color: #282828; -fx-border-width: 1.5; -fx-border-color: white; -fx-border-radius: 8;");

        botao.setStyle("-fx-background-color: #7A31E0; -fx-border-width: 1.5; -fx-border-color: white; -fx-border-radius: 8;");
    }

    void fecharJanela(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        stage.close();
    }

}
