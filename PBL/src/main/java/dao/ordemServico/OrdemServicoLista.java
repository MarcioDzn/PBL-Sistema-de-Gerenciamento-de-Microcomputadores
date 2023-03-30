package dao.ordemServico;

import dao.DAO;
import model.OrdemServico;

import java.util.LinkedList;
import java.util.List;

public class OrdemServicoLista implements OrdemServicoDAO{
    private List<OrdemServico> listaOrdensServico;
    private int proxId;

    public OrdemServicoLista() {
        this.listaOrdensServico = new LinkedList<OrdemServico>();
        this.proxId = 0;
    }

    @Override
    public void criar(OrdemServico objeto) {
        objeto.setId(this.proxId);
        listaOrdensServico.add(objeto);

        this.proxId++;
    }

    @Override
    public OrdemServico buscarPorId(int id) {
        for (OrdemServico ordemServico : listaOrdensServico){
            if (ordemServico.getId() == id)
                return ordemServico;
        }
        return null;
    }

    @Override
    public List<OrdemServico> buscarTodos() {
        List<OrdemServico> lista = new LinkedList<OrdemServico>();

        for (OrdemServico ordemServico : listaOrdensServico){
            lista.add(ordemServico);
        }

        return lista;
    }

    @Override
    public void atualizar(OrdemServico objeto) {
        for (int i = 0; i < this.listaOrdensServico.size(); i++){
            if (listaOrdensServico.get(i).getId() == objeto.getId()){
                this.listaOrdensServico.add(objeto.getId(), objeto);

                return; // Para o laço assim que entrar no if e atualizar a lista
            }
        }
    }

    @Override
    public void remover(OrdemServico objeto) {
        for (int i = 0; i < this.listaOrdensServico.size(); i++){
            if (listaOrdensServico.get(i).getId() == objeto.getId()){
                this.listaOrdensServico.remove(objeto);

                return;
            }
        }
    }

    @Override
    public void deletarTudo() {
        this.listaOrdensServico = new LinkedList<OrdemServico>();
        this.proxId = 0;
    }

    @Override
    public List<OrdemServico> buscarPorCliente(int id) {
        List<OrdemServico> lista = new LinkedList<OrdemServico>();

        for (OrdemServico ordemServico : DAO.getCliente().buscarPorId(id).getOrdensServico()){
            lista.add(ordemServico);
        }

        return lista;
    }

    @Override
    public List<OrdemServico> buscarPorTecnico(int id) {
        List<OrdemServico> lista = new LinkedList<OrdemServico>();

        for (OrdemServico ordemServico : DAO.getTecnico().buscarPorId(id).getOrdensServico()){
            lista.add(ordemServico);
        }

        return lista;
    }
}
