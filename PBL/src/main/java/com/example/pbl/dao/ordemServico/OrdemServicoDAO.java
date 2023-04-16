package com.example.pbl.dao.ordemServico;

import com.example.pbl.dao.CRUD;
import com.example.pbl.model.OrdemServico;

import java.util.List;

public interface OrdemServicoDAO extends CRUD<OrdemServico> {
    /**
     * Busca pelo id e retorna uma lista de objetos do tipo OrdemServico dos quais um cliente faz parte
     * @param id Id do cliente
     * @return Lista de objetos do tipo OrdemServico
     */
    public List<OrdemServico> buscarPorCliente(int id);

    /**
     * Busca pelo id e retorna uma lista de objetos do tipo OrdemServico dos quais um tecnico faz parte
     * @param id Id do tecnico
     * @return Lista de objetos do tipo OrdemServico
     */
    public List<OrdemServico> buscarPorTecnico(int id);

    /**
     * Busca pelo id e retorna uma lista de objetos do tipo OrdemServico dos quais um serviço faz parte
     * @param id Id do serviço
     * @return Lista de objetos do tipo OrdemServico
     */
    public List<OrdemServico> buscarPorServico(int id, String tipoServico);
}
