package com.example.pbl.model;

import java.util.List;

public abstract class Servico {
    private int id;
    private double preco;
    private double custo;

    /***
     * Dados para gerar um objeto serviço.
     * @param preco
     * @param custo
     */
    public Servico(double preco, double custo) {
        this.preco = preco;
        this.custo = custo;
    }

    /***
     * Get preço
     * @return preço
     */
    public double getPreco() {
        return preco;
    }
    /***
     * Get custo
     * @return custo
     */
    public double getCusto() {
        return custo;
    }

    /***
     * Set preço
     * @param preco
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }
    /***
     * Set custo
     * @param custo
     */
    public void setCusto(double custo) {
        this.custo = custo;
    }
    /***
     * Get id
     * @return id
     */
    public int getId() {
        return id;
    }
    /***
     * Set id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /***
     * Metodo abstrato que deverá ser implementada nas classes filhas.
     */
    public abstract List<OrdemServico> getOrdensServico();

    /***
     * @param obj
     * @return
     */
    @Override
    public abstract boolean equals(Object obj);

}
