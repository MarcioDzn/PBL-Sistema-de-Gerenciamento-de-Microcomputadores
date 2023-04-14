package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.LinkedList;
import java.util.List;

public class Instalacao extends Servico{
    private String instalacao; // Instalação de programas ou formatação

    /***
     * Dados para gerar um objeto Instalação.
     * @param preco
     * @param custo
     * @param instalacao
     */
    public Instalacao(double preco, double custo, String instalacao) {
        super(preco, custo);
        this.instalacao = instalacao;
    }

    /***
     * Get instalação
     * @return instalação
     */
    public String getInstalacao() {
        return instalacao;
    }

    /***
     * Set tipoInstalação
     * @param tipoInstalacao
     */
    public void setInstalacao(String tipoInstalacao) {
        this.instalacao = tipoInstalacao;
    }

    /***
     * Busca pelo DAO de todas as ordens de serviço relacionadas a uma determinada instalação.
     * @return lista de programas instalados
     */
    public List<OrdemServico> getOrdensServico(){
        return DAO.getOrdemServico().buscarPorServico(super.getId(), "Instalacao");
    }

    /***
     * Compara duas instalações pelo id.
     * @param obj
     * @return booleano
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Instalacao){
            Instalacao i = (Instalacao) obj;
            if (this.getId() == i.getId()){
                return true;
            }
        }
        return false;
    }
}
