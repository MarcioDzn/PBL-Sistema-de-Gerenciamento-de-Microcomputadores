package com.example.pbl.dao.cliente;

import com.example.pbl.dao.CRUD;
import com.example.pbl.model.Cliente;

import java.util.List;

public interface ClienteDAO extends CRUD<Cliente> {
    /**
     * Busca pelo nome e retorna uma lista de objetos do tipo Cliente
     * @param nome Nome referente ao cliente
     * @return Lista de objetos do tipo Cliente
     */
    public List<Cliente> buscarPorNome(String nome);
}
