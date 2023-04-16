package com.example.pbl.model;

/**
 * <p>
 * Classe abstrata referente à Componente.
 * </p>
 *
 * <p>
 * Um Componente possui id, custo e preço.
 * </p>
 *
 * <p>
 * Esta classe contém métodos para manipular seus atributos.
 * </p>
 *
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public abstract class Componente {
    private int id;
    private double custo;
    private double preco;

    /**
     * Dados para gerar um objeto Componente.
     * @param custo Custo do componente
     * @param preco Preço do componente
     */
    public Componente(double custo, double preco) {
        this.custo = custo;
        this.preco = preco;
    }

    /**
     * Método que retorna o custo do componente
     * @return Custo do componente
     */
    public double getCusto() {
        return custo;
    }

    /**
     * Método que define o custo do componente
     * @param custo Novo custo do componente
     */
    public void setCusto(double custo) {
        this.custo = custo;
    }

    /**
     * Método que retorna o preço do componente
     * @return Preço do componente
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Método que define o preço do componente
     * @param preco Novo preço do componente
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Método que retorna o id do componente
     * @return Id do componente
     */
    public int getId() {
        return id;
    }

    /**
     * Método que define o id do componente
     * @param id Novo id do componente
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método para comparar o objeto de um Componente com este objeto pelo id
     * @param obj Objeto do tipo Componente a ser comparado
     * @return Valor booleano true para id's iguais e false para id's diferentes
     */
    @Override
    public abstract boolean equals(Object obj);
}
