package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.List;

public class OutroComponente extends Componente{
    private String descricao;

    /***
     * Dados para gerar um objeto outroComponente.
     * @param custo
     * @param preco
     * @param descricao
     */
    public OutroComponente(double custo, double preco, String descricao) {
        super(custo, preco);
        this.descricao = descricao;
    }

    /***
     * Get descrição
     * @return descrição
     */
    public String getDescricao() {
        return descricao;
    }

    /***
     * Set descrição
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /***
     * Get lista de montagem
     * @return lista de montagem
     */
    public List<Montagem> getMontagens() {
        return DAO.getMontagem().buscarPorComponente(super.getId(), "OutroComponente");
    }

    /***
     * Compara dois objetos do tipo outroComponente
     * @param obj
     * @return booleano
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof OutroComponente){
            OutroComponente i = (OutroComponente) obj;
            if (this.getId() == i.getId()){
                return true;
            }
        }
        return false;
    }

}
