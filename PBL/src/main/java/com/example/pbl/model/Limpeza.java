package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.List;

public class Limpeza extends Servico{
    String limpeza;

    public Limpeza(double preco, double custo, String limpeza) {
        super(preco, custo);
        this.limpeza = limpeza;
    }

    public List<OrdemServico> getOrdensServico(){
        return DAO.getOrdemServico().buscarPorServico(super.getId(), "Limpeza");
    }

    public String getLimpeza() {
        return limpeza;
    }

    public void setLimpeza(String limpeza) {
        this.limpeza = limpeza;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Limpeza){
            Limpeza i = (Limpeza) obj;
            if (this.getId() == i.getId()){
                return true;
            }
        }
        return false;
    }
}
