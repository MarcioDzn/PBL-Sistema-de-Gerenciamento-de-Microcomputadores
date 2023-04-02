package com.example.pbl.dao.cliente;

import com.example.pbl.dao.CRUD;
import com.example.pbl.model.Cliente;

import java.util.List;

public interface ClienteDAO extends CRUD<Cliente> {
    public List<Cliente> buscarPorNome(String nome);
}
