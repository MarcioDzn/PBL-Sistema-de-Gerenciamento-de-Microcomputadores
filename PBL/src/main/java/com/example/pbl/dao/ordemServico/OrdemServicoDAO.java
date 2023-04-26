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

    /**
     * Busca uma lista de ordens em aberto armazenadas no DAO
     * @return Lista de objetos do tipo OrdemServico
     */
    public List<OrdemServico> buscarOrdensEmAberto();

    /**
     * Busca a primeira ordem de serviço em aberto armazenada no DAO
     * @return Objeto do tipo OrdemServico
     */
    public OrdemServico buscarPrimeiraOrdem();

}
