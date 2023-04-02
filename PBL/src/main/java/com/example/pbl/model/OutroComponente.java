package com.example.pbl.model;

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
}
