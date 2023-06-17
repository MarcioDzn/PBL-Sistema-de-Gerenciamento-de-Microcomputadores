package com.example.pbl.controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

import com.example.pbl.dao.DAO;
import com.example.pbl.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GerarRelatorioWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label idCustos;

    @FXML
    private Label idFaturamento;

    @FXML
    private Label idSatisfacao;

    @FXML
    private Label idTempo;

    private String tempoMedioHoras;

    public String calculoTempoMedio(){
        List<OrdemServico> lista = DAO.getOrdemServico().buscarTodos();
        DecimalFormat formato = new DecimalFormat("0.##");
        int totalOrdens = DAO.getOrdemServico().buscarTodos().size();
        long tempoTotalMiliseg = 0;

        for(OrdemServico ordemServico: lista){
            if(ordemServico.isFinalizado()){
                tempoTotalMiliseg += ordemServico.getTempoTotal();
            }
        }

        double tempoTotalDouble = (double) tempoTotalMiliseg;

        // Convertendo para horas
        this.tempoMedioHoras = formato.format(tempoTotalDouble/3600000);

        return this.tempoMedioHoras;
    }

    public String custosPecas(){
        List<Peca> listaPecas = DAO.getPeca().buscarTodos();
        List<OutroComponente> listaOutros = DAO.getOutroComponente().buscarTodos();
        int custoPecas = 0;
        int custoOutros = 0;
        DecimalFormat format = new DecimalFormat("0.##");

        for(Peca peca: listaPecas){
            custoPecas += peca.getCusto();
        }

        for(OutroComponente outroComponente: listaOutros){
            custoOutros += outroComponente.getCusto();
        }
        return format.format(custoPecas + custoOutros);
    }

    public String faturamento(){
        List<OrdemServico> listaOrdens = DAO.getOrdemServico().buscarTodos();
        double pagamentoTotal = 0;
        double custoTotal = 0;

        for(OrdemServico ordemServico: listaOrdens){
            if(ordemServico.isFinalizado() && ordemServico.getPreco() != 0) {
                pagamentoTotal += ordemServico.getPreco();
            }
        }

        List<Montagem> listaMontagem;
        List<Limpeza> listaLimpezas;
        List<Instalacao> listaInstalacao;
        for(OrdemServico ordemServico: listaOrdens){
            if(ordemServico.isFinalizado()) {
                listaMontagem = ordemServico.getMontagens();
                listaLimpezas = ordemServico.getLimpezas();
                listaInstalacao = ordemServico.getInstalacoes();

                for (Montagem montagem : listaMontagem) {
                    if(montagem != null) {
                        custoTotal += montagem.getCusto();
                    }
                }
                for (Limpeza limpeza : listaLimpezas) {
                    if(limpeza != null) {
                        custoTotal += limpeza.getCusto();
                    }
                }
                for (Instalacao instalacao : listaInstalacao) {
                    if(instalacao != null) {
                        custoTotal += instalacao.getCusto();
                    }
                }
            }
        }

        return String.valueOf(pagamentoTotal - custoTotal);
    }


    @FXML
    void initialize() {
        assert idCustos != null : "fx:id=\"idCustos\" was not injected: check your FXML file 'GerarRelatorioWindow.fxml'.";
        assert idFaturamento != null : "fx:id=\"idFaturamento\" was not injected: check your FXML file 'GerarRelatorioWindow.fxml'.";
        assert idSatisfacao != null : "fx:id=\"idSatisfacao\" was not injected: check your FXML file 'GerarRelatorioWindow.fxml'.";
        assert idTempo != null : "fx:id=\"idTempo\" was not injected: check your FXML file 'GerarRelatorioWindow.fxml'.";

        this.idTempo.setText(calculoTempoMedio()+" Horas");
        this.idCustos.setText(custosPecas()+" Reais");
        this.idFaturamento.setText(faturamento()+" Reais");
        //this.idSatisfacao.setText();

    }

}
