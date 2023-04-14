package com.example.pbl.exceptions;

public class ObjetoNaoEncontradoException extends Exception{

    public ObjetoNaoEncontradoException(String nomeClasse){
        super(nomeClasse + " não encontrado(a)!");
    }
}
