package com.example.pbl.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.pbl.dao.DAO;
import com.example.pbl.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CustosWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelCustos;

    private int custoTotal;

    private String totalCustos(){
        List<Instalacao> listaInstalacao = DAO.getInstalacao().buscarTodos();
        List<Limpeza> listaLimpeza = DAO.getLimpeza().buscarTodos();
        List<Montagem> listaMontagem = DAO.getMontagem().buscarTodos();

        for(Instalacao instalacao: listaInstalacao){
            this.custoTotal += instalacao.getCusto();
        }

        for(Limpeza limpeza: listaLimpeza){
            this.custoTotal += limpeza.getCusto();
        }

        for(Montagem montagem: listaMontagem){
            this.custoTotal += montagem.getCusto();
        }

        return String.valueOf(this.custoTotal);
    }

    @FXML
    void initialize() {
        assert labelCustos != null : "fx:id=\"labelCustos\" was not injected: check your FXML file 'CustosWindow.fxml'.";
        this.labelCustos.setText(totalCustos());

    }

}
