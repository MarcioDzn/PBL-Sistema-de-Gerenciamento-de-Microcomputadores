package com.example.pbl.exceptions;

/***
 * Implementação da classe OrdemServicoAtualException.
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class OrdemServicoAtualException extends Exception{
    private Integer ordemServicoAtual;

    /***
     * Exceção lançada quando um técnico ainda tem uma ordem de serviço em aberto.
     * @param ordemServicoAtual Ordem de serviço atual.
     */
    public OrdemServicoAtualException(Integer ordemServicoAtual){
        super("O técnico ainda tem uma ordem se serviço em aberto!");
        this.ordemServicoAtual = ordemServicoAtual;
    }

    /***
     * Retorna a ordem de serviço atual.
     * @return Ordem de serviço atual.
     */
    public Integer getOrdemServicoAtual(){
        return this.ordemServicoAtual;
    }
}
