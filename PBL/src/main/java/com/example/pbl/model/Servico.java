package com.example.pbl.model;

import java.util.List;

/**
 * <p>
 * Classe abstrata referente ao Servico.
 * </p>
 *
 * <p>
 * Um Servico possui id, preço e custo.
 * </p>
 *
 * <p>
 * Esta classe contém métodos para manipular seus atributos,
 * bem como para buscar todas as ordens de serviço da qual este Serviço
 * faz parte.
 * </p>
 *
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public abstract class Servico {
    private int id;
    private double preco;
    private double custo;

    /**
     * Dados para gerar um objeto do tipo Serviço.
     * @param preco Preço do Serviço
     * @param custo Custo do Serviço
     */
    public Servico(double preco, double custo) {
        this.preco = preco;
        this.custo = custo;
    }

    /**
     * Método para retornar o preço do Serviço
     * @return Preço do Serviço
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Método para definir o preço do Serviço
     * @param preco Novo preço do Serviço
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Método para retornar o custo do Serviço
     * @return Custo do Serviço
     */
    public double getCusto() {
        return custo;
    }

    /**
     * Método para definir o custo do Serviço
     * @param custo Novo custo do Serviço
     */
    public void setCusto(double custo) {
        this.custo = custo;
    }

    /**
     * Método para retornar o id do Serviço
     * @return Id do Serviço
     */
    public int getId() {
        return id;
    }

    /**
     * Método para definir o id do Serviço
     * @param id Novo id do serviço
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodo abstrato para retornar as ordens de serviços associadas a um determiando serviço
     */
    public abstract List<OrdemServico> getOrdensServico();

    /**
     * Método abstrato para comparar dois objetos pelo id
     * @param obj Objeto do tipo Servico ser comparado
     * @return Valor booleano true para id's iguais e false para id's diferentes
     */
    @Override
    public abstract boolean equals(Object obj);

}
