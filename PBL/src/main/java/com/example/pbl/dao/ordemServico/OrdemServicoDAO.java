package com.example.pbl.dao.ordemServico;

import com.example.pbl.dao.CRUD;
import com.example.pbl.model.OrdemServico;

import java.util.List;

public interface OrdemServicoDAO extends CRUD<OrdemServico> {
    public List<OrdemServico> buscarPorCliente(int id);

    public List<OrdemServico> buscarPorTecnico(int id);

    public List<OrdemServico> buscarPorServico(int id, String tipoServico);
}
