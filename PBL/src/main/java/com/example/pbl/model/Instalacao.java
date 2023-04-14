package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.LinkedList;
import java.util.List;

public class Instalacao extends Servico{
    private String instalacao; // Instalação de programas ou formatação


    public Instalacao(double preco, double custo, String instalacao) {
        super(preco, custo);
        this.instalacao = instalacao;
    }

    public String getInstalacao() {
        return instalacao;
    }

    public void setInstalacao(String tipoInstalacao) {
        this.instalacao = tipoInstalacao;
    }

    public List<OrdemServico> getOrdensServico(){
        return DAO.getOrdemServico().buscarPorServico(super.getId(), "Instalacao");
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Instalacao){
            Instalacao i = (Instalacao) obj;
            if (this.getId() == i.getId()){
                return true;
            }
        }
        return false;
    }
}
