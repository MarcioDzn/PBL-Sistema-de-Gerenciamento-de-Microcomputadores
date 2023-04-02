package com.example.pbl.model;

import java.util.LinkedList;
import java.util.List;

public abstract class Componente {
    private int id;
    private double custo;
    private double preco;
    private List<Montagem> montagensAssociadas;

    public Componente(double custo, double preco) {
        this.custo = custo;
        this.preco = preco;
        this.montagensAssociadas = new LinkedList<Montagem>();
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

    public List<Montagem> getMontagensAssociadas() {
        return montagensAssociadas;
    }

    public void setMontagensAssociadas(Montagem montagem) {
        this.montagensAssociadas.add(montagem);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
