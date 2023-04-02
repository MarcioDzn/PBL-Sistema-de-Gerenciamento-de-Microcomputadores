package com.example.pbl.dao.tecnico;

import com.example.pbl.dao.CRUD;
import com.example.pbl.model.Tecnico;

public interface TecnicoDAO extends CRUD<Tecnico> {
    public Tecnico buscarPorNome(String nome);
}
