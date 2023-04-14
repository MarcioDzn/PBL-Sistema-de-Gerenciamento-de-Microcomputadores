package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.List;

public class Limpeza extends Servico{
    String limpeza;

    /***
     * Dados para gerar um objeto limpeza.
     * @param preco
     * @param custo
     * @param limpeza
     */
    public Limpeza(double preco, double custo, String limpeza) {
        super(preco, custo);
        this.limpeza = limpeza;
    }

    /***
     * Busca pelo DAO de todos os serviços de limpeza.
     * @return lista de serviços do tipo limpeza
     */
    public List<OrdemServico> getOrdensServico(){
        return DAO.getOrdemServico().buscarPorServico(super.getId(), "Limpeza");
    }

    /***
     * Get limpeza
     * @return limpeza
     */
    public String getLimpeza() {
        return limpeza;
    }

    /***
     * Set limpeza
     * @param limpeza
     */
    public void setLimpeza(String limpeza) {
        this.limpeza = limpeza;
    }


    /***
     * Comparar se duas lmpezas são iguais
     * @param obj
     * @return booleano
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Limpeza){
            Limpeza i = (Limpeza) obj;
            if (this.getId() == i.getId()){
                return true;
            }
        }
        return false;
    }
}
