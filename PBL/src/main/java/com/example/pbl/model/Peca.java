package com.example.pbl.model;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.QuantidadeException;

import java.util.List;

public class Peca extends Componente {
    private String nome;
    private String fabricante;
    private int quantidade;

    public Peca(int quantidade, double custo, double preco, String nome, String fabricante) {
        super(custo, preco);
        this.nome = nome;
        this.fabricante = fabricante;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) throws QuantidadeException {
        if (quantidade <= this.quantidade)
            this.quantidade = quantidade;
        else{
            throw new QuantidadeException(quantidade);
        }
    }

    public List<Montagem> getMontagens() {
        return DAO.getMontagem().buscarPorComponente(super.getId(), "Peca");
    }

}
