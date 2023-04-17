package com.example.pbl.exceptions;
/***
 * Implementação da classe QuantidadeException.
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class QuantidadeException extends Exception{
    private int quantidade;

    /***
     * Exceção lançada quando o estoque de alguma peça acaba.
     * @param quantidade Quantidade do estoque.
     */
    public QuantidadeException(int quantidade){
        super("Quantidade indisponível!");
        this.quantidade = quantidade;
    }

    /***
     * Retorna quantidade disponível no estoque.
     * @return Quantidade no estoque.
     */
    public int getQuantidade(){
        return this.quantidade;
    }
}
