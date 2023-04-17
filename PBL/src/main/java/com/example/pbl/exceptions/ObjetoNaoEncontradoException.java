package com.example.pbl.exceptions;
/***
 * Implementação da classe ObjetoNaoEncontradoException.
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class ObjetoNaoEncontradoException extends Exception{
    /***
     * Exceção lançada quando um objeto não é encontrado na busca.
     * @param nomeClasse Nome da classe do objeto.
     */
    public ObjetoNaoEncontradoException(String nomeClasse){
        super(nomeClasse + " não encontrado(a)!");
    }
}
