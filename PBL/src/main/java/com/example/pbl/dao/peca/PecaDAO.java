package com.example.pbl.dao.peca;

import com.example.pbl.dao.CRUD;
import com.example.pbl.model.Peca;

public interface PecaDAO extends CRUD<Peca> {
    public Peca buscarPorNome(String nome);
}
