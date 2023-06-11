package com.example.pbl.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.dao.DAO;
import com.example.pbl.model.Limpeza;
import com.example.pbl.model.OutroComponente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public class GerenciarLimpezasWindow {

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
    private ScrollPane scLimpezas;

    @FXML
    private TextField txtBuscarNome;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtCusto;
    @FXML
    private TextField txtPreco;

    @FXML
    void cadastrarAction(ActionEvent event) {
        Limpeza novaLimpeza = this.criarLimpeza();
        boolean limpezaValida = this.validarFormulario(novaLimpeza);

        if (limpezaValida){
            DAO.getLimpeza().criar(novaLimpeza);
            this.limparCampos();
            this.carregarCSS();
        }
    }

    @FXML
    void deletarAction(ActionEvent event) {
        this.limparCampos();
        this.carregarCSS();
    }

    @FXML
    void editarAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnCadastrar != null : "fx:id=\"btnCadastrar\" was not injected: check your FXML file 'GerenciarLimpezasWindow.fxml'.";
        assert btnDeletar != null : "fx:id=\"btnDeletar\" was not injected: check your FXML file 'GerenciarLimpezasWindow.fxml'.";
        assert btnEditar != null : "fx:id=\"btnEditar\" was not injected: check your FXML file 'GerenciarLimpezasWindow.fxml'.";
        assert scLimpezas != null : "fx:id=\"scLimpezas\" was not injected: check your FXML file 'GerenciarLimpezasWindow.fxml'.";
        assert txtBuscarNome != null : "fx:id=\"txtBuscarNome\" was not injected: check your FXML file 'GerenciarLimpezasWindow.fxml'.";
        assert txtCusto != null : "fx:id=\"txtCusto\" was not injected: check your FXML file 'GerenciarLimpezasWindow.fxml'.";
        assert txtDescricao != null : "fx:id=\"txtDescricao\" was not injected: check your FXML file 'GerenciarLimpezasWindow.fxml'.";
        assert txtPreco != null : "fx:id=\"txtPreco\" was not injected: check your FXML file 'GerenciarLimpezasWindow.fxml'.";

        this.carregarCSS();
    }

    void limparCampos(){
        this.txtDescricao.clear();
        this.txtDescricao.setPromptText("");

        this.txtPreco.clear();
        this.txtPreco.setPromptText("");

        this.txtCusto.clear();
        this.txtCusto.setPromptText("");
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
