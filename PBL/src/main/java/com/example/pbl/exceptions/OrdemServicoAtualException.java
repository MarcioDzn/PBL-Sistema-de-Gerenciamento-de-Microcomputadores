package com.example.pbl.exceptions;

public class OrdemServicoAtualException extends Exception{
    private Integer ordemServicoAtual;

    public OrdemServicoAtualException(Integer ordemServicoAtual){
        super("O técnico já tem uma ordem de serviço!");
        this.ordemServicoAtual = ordemServicoAtual;
    }

    public Integer getOrdemServicoAtual(){
        return this.ordemServicoAtual;
    }
}
