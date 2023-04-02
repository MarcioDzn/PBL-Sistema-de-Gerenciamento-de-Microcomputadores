package com.example.pbl.dao;

import java.util.List;

public interface CRUD<T> {
    public void criar(T objeto);

    public T buscarPorId(int id);

    public List<T> buscarTodos();

    public void atualizar(T objeto);

    public void remover(T objeto);

    public void  deletarTudo();
}
