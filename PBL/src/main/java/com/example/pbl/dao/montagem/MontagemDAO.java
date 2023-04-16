package com.example.pbl.dao.montagem;

import com.example.pbl.dao.CRUD;
import com.example.pbl.model.Montagem;

import java.util.List;

public interface MontagemDAO extends CRUD<Montagem> {
    /**
     * Busca pelo id e retorna uma lista de objetos do tipo Montagem dos quais um componente faz parte
     * @param id Id do componente
     * @return Lista de objetos do tipo Montagem
     */
    public List<Montagem> buscarPorComponente(int id, String tipoComponente);

}
