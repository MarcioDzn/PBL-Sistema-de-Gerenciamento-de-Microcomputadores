package com.example.pbl.dao.montagem;

import com.example.pbl.dao.CRUD;
import com.example.pbl.model.Montagem;

import java.util.List;

public interface MontagemDAO extends CRUD<Montagem> {
    public List<Montagem> buscarPorComponente(int id, String tipoComponente);

}
