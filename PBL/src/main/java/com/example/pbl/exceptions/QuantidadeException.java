package com.example.pbl.exceptions;

public class QuantidadeException extends Exception{
    private int quantidade;

    public QuantidadeException(int quantidade){
        super("Quantidade indisponível!");
        this.quantidade = quantidade;
    }

    public int getQuantidade(){
        return this.quantidade;
    }
}
