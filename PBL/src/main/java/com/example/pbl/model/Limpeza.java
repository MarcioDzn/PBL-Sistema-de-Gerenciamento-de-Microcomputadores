package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.List;

public class Limpeza extends Servico{
    public Limpeza(double preco, double custo) {
        super(preco, custo);
    }

    public List<OrdemServico> getOrdensServico(){
        return DAO.getOrdemServico().buscarPorServico(super.getId(), "Limpeza");
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
