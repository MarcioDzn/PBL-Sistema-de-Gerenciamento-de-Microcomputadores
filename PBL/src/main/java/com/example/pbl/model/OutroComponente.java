package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.List;

public class OutroComponente extends Componente{
    private String descricao;

    public OutroComponente(double custo, double preco, String descricao) {
        super(custo, preco);
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Montagem> getMontagens() {
        return DAO.getMontagem().buscarPorComponente(super.getId(), "OutroComponente");
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof OutroComponente){
            OutroComponente i = (OutroComponente) obj;
            if (this.getId() == i.getId()){
                return true;
            }
        }
        return false;
    }

}
