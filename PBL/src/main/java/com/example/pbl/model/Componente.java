package com.example.pbl.model;

public abstract class Componente {
    private int id;
    private double custo;
    private double preco;

    public Componente(double custo, double preco) {
        this.custo = custo;
        this.preco = preco;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public abstract boolean equals(Object obj);
}
