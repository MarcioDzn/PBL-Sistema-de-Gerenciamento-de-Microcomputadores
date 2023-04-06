package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.LinkedList;
import java.util.List;

public class Instalacao extends Servico{
    private List<String> programas;
    private String sistemaOperacional;

    public Instalacao(double preco, double custo) {
        super(preco, custo);
        this.programas = new LinkedList<String>();
    }

    public List<String> getProgramas() {
        return programas;
    }

    public void setProgramas(String programa) {
        this.programas.add(programa);
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public List<OrdemServico> getOrdensServico(){
        return DAO.getOrdemServico().buscarPorServico(super.getId(), "Instalacao");
    }
}
