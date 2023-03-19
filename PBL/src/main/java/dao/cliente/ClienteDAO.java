package dao.cliente;

import dao.CRUD;
import model.Cliente;

public interface ClienteDAO extends CRUD<Cliente> {
    public Cliente buscarPorNome(String nome);
}
