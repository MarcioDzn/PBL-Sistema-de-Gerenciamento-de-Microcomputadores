package com.example.pbl.model;

public class Limpeza extends Servico{
    private int id;
    public Limpeza(double preco, double custo) {
        super(preco, custo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
