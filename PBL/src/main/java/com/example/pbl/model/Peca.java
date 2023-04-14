package com.example.pbl.model;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.QuantidadeException;

import java.util.List;

public class Peca extends Componente {
    private String nome;
    private String fabricante;
    private int quantidade;

    /***
     * Dados para gerar um objeto peça.
     * @param quantidade
     * @param custo
     * @param preco
     * @param nome
     * @param fabricante
     */
    public Peca(int quantidade, double custo, double preco, String nome, String fabricante) {
        super(custo, preco);
        this.nome = nome;
        this.fabricante = fabricante;
        this.quantidade = quantidade;
    }

    /***
     * Get nome
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /***
     * Set nome
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /***
     * Set fabricante
     * @param fabricante
     */
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    /***
     * Get fabricante
     * @return fabrcante
     */
    public String getFabricante() {
        return fabricante;
    }
    /***
     * Get quantdade
     * @return quantdade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /***
     * Set quantdade
     * @param quantidade
     * @throws QuantidadeException
     */
    public void setQuantidade(int quantidade) throws QuantidadeException {
        if (quantidade >= 0)
            this.quantidade = quantidade;

        else{
            throw new QuantidadeException(quantidade);
        }
    }

    /***
     * Busca pelo DAO de todas as peças da ordem de serviço.
     * @return lista de peças
     */
    public List<Montagem> getMontagens() {
        return DAO.getMontagem().buscarPorComponente(super.getId(), "Peca");
    }

    /***
     * Compara dois objetos do tipo peca
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Peca){
            Peca i = (Peca) obj;
            if (this.getId() == i.getId()){
                return true;
            }
        }
        return false;
    }
}
