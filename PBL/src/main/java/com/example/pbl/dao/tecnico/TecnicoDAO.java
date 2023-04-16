package com.example.pbl.dao.tecnico;

import com.example.pbl.dao.CRUD;
import com.example.pbl.model.Tecnico;

public interface TecnicoDAO extends CRUD<Tecnico> {
    /**
     * Busca pelo nome e retorna uma lista de objetos do tipo Tecnico
     * @param nome Nome referente ao tecnico
     * @return Lista de objetos do tipo Tecnico
     */
    public Tecnico buscarPorNome(String nome);
}
