package com.example.pbl.controller;

import com.example.pbl.dao.DAO;
import com.example.pbl.model.OrdemServico;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.DecimalFormat;
import java.util.List;

public class TempoMedioWindow {

    @FXML
    private Label labelTempoMedio;

    @FXML
    private Label labelTotalOrdens;

    private long tempoTotal;

    private Double tempoTotalHoras;


    private String tempoMedio(){
        List<OrdemServico> lista = DAO.getOrdemServico().buscarTodos();
        DecimalFormat tempo = new DecimalFormat("#.##");

        // Tempo total em milissegundos.
        for(OrdemServico ordemServico: lista){
            if(ordemServico.isFinalizado() == true) {
                this.tempoTotal += ordemServico.getTempoTotal();
            }
        }
        Double tempoDouble = (double) this.tempoTotal;

        // Tempo total em horas

        this.tempoTotalHoras = tempoDouble/3600000;
        String tempoFinal = tempo.format(this.tempoTotalHoras);
        return tempoFinal;
    }

    @FXML
    void initialize() {
        assert labelTempoMedio != null : "fx:id=\"labelTempoMedio\" was not injected: check your FXML file 'TempoMedioWindow.fxml'.";
        assert labelTotalOrdens != null : "fx:id=\"labelTotalOrdens\" was not injected: check your FXML file 'TempoMedioWindow.fxml'.";

        this.labelTempoMedio.setText(tempoMedio());
        this.labelTotalOrdens.setText(String.valueOf(DAO.getOrdemServico().buscarTodos().size()));

    }

}
