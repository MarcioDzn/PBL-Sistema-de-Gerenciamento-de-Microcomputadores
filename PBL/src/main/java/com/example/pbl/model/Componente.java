package com.example.pbl.model;

public abstract class Componente {
    private int id;
    private double custo;
    private double preco;

    /***
     * Dados para gerar um objeto Componente.
     * @param custo
     * @param preco
     */
    public Componente(double custo, double preco) {
        this.custo = custo;
        this.preco = preco;
    }

    /***
     * Get custo
     * @return custo
     */
    public double getCusto() {
        return custo;
    }

    /***
     * Set custo
     * @param custo
     */
    public void setCusto(double custo) {
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
     * Set preço
     * @param preco
     */
    public void setPreco(double preco) {
        this.preco = preco;
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
     * Classe abstrata que deverá ser implementada nas classes filhas.
     * @param obj
     * @return
     */
    @Override
    public abstract boolean equals(Object obj);
}
