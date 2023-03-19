package dao;

import java.util.List;

public interface CRUD<T> {
    public void adicionarNaLista(T objeto);

    public T buscarPorId(int id);

    public List<T> buscarTodos();

    public void atualizar(T objeto);

    public void remover(T objeto);
}
