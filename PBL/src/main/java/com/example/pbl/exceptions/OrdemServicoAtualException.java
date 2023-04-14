package com.example.pbl.exceptions;

public class OrdemServicoAtualException extends Exception{
    private Integer ordemServicoAtual;

    public OrdemServicoAtualException(Integer ordemServicoAtual){
        super("O técnico ainda tem uma ordem se serviço em aberto!");
        this.ordemServicoAtual = ordemServicoAtual;
    }

    public Integer getOrdemServicoAtual(){
        return this.ordemServicoAtual;
    }
}
