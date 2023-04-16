package com.example.pbl.dao.peca;

import com.example.pbl.dao.CRUD;
import com.example.pbl.model.Peca;

public interface PecaDAO extends CRUD<Peca> {
    /**
     * Busca pelo nome e retorna uma lista de objetos do tipo Peca
     * @param nome Nome referente à paça
     * @return Lista de objetos do tipo Peca
     */
    public Peca buscarPorNome(String nome);
}
