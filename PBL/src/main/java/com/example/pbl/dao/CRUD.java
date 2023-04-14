package com.example.pbl.dao;

import com.example.pbl.exceptions.ObjetoNaoEncontradoException;

import java.util.List;

public interface CRUD<T> {
    public void criar(T objeto);

    public T buscarPorId(int id);

    public List<T> buscarTodos();

    public void atualizar(T objeto) throws ObjetoNaoEncontradoException;

    public void remover(T objeto) throws ObjetoNaoEncontradoException;

    public void  deletarTudo();
}
