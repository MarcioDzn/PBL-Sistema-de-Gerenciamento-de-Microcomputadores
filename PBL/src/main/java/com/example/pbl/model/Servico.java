package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.LinkedList;
import java.util.List;

public abstract class Servico {
    private int id;
    private double preco;
    private double custo;
    private List<Integer> ordensServicoId;

    public Servico(double preco, double custo) {
        this.preco = preco;
        this.custo = custo;
        this.ordensServicoId = new LinkedList<Integer>();
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

    public List<OrdemServico> getOrdensServico() {
        List<OrdemServico> lista = new LinkedList<OrdemServico>();

        for (Integer id : this.ordensServicoId){
            lista.add(DAO.getOrdemServico().buscarPorId(id));
        }

        return lista;
    }

    public void addOrdensServico(Integer id) {
        this.ordensServicoId.add(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
