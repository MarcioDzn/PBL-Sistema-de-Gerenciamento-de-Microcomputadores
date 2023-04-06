package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.LinkedList;
import java.util.List;

public abstract class Servico {
    private int id;
    private double preco;
    private double custo;

    public Servico(double preco, double custo) {
        this.preco = preco;
        this.custo = custo;
    }

    public double getPreco() {
        return preco;
    }

    public double getCusto() {
        return custo;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
