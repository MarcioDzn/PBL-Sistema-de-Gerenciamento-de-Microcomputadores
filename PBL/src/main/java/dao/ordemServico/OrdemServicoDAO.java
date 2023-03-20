package dao.ordemServico;

import dao.CRUD;
import model.OrdemServico;
import model.Tecnico;

import java.util.List;

public interface OrdemServicoDAO extends CRUD<OrdemServico> {
    public List<OrdemServico> buscarPorCliente(int id);

    public List<OrdemServico> buscarPorTecnico(int id);
}
