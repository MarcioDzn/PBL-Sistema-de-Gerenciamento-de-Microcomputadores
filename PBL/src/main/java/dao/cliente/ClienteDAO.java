package dao.cliente;

import dao.CRUD;
import model.Cliente;

import java.util.List;

public interface ClienteDAO extends CRUD<Cliente> {
    public List<Cliente> buscarPorNome(String nome);
}
