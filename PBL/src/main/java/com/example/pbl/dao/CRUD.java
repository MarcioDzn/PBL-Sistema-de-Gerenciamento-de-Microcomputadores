package com.example.pbl.dao;

import com.example.pbl.exceptions.ObjetoNaoEncontradoException;

import java.util.List;

public interface CRUD<T> {
    /**
     * Guarda um objeto de tipo qualquer em uma lista
     * @param objeto Objeto
     */
    public void criar(T objeto);

    /**
     * Busca pelo id e retorna um objeto de tipo qualquer
     * @param id Id referente ao objeto
     * @return Objeto ou null
     */
    public T buscarPorId(int id);

    /**
     * Retorna uma lista de todos os objetos de tipo qualquer
     * @return Lista de objetos
     */
    public List<T> buscarTodos();

    /**
     * Substitui um objeto de tipo qualquer por outro de mesmo id
     * @param objeto Objeto
     * @exception ObjetoNaoEncontradoException Se o id do objeto a ser atualizado não for encontrado
     */
    public void atualizar(T objeto) throws ObjetoNaoEncontradoException;

    /**
     * Remove um objeto de tipo qualquer da lista
     * @param objeto Objeto
     * @exception ObjetoNaoEncontradoException Se o id do objeto a ser removido não for encontrado
     */
    public void remover(T objeto) throws ObjetoNaoEncontradoException;

    /**
     * Esvazia a lista de objetos
     */
    public void deletarTudo();
}
