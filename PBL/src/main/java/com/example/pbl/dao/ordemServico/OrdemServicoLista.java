package com.example.pbl.dao.ordemServico;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementação em lista encadeada do DAO da OrdemServico
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class OrdemServicoLista implements OrdemServicoDAO{
    private List<OrdemServico> listaOrdensServico;
    private int proxId;

    public OrdemServicoLista() {
        this.listaOrdensServico = new LinkedList<OrdemServico>();
        this.proxId = 0;
    }

    /**
     * Guarda um objeto do tipo OrdemServico em uma lista
     * Cada objeto tem um id único.
     * @param objeto Objeto do tipoOrdemServico
     */
    @Override
    public void criar(OrdemServico objeto) {
        objeto.setId(this.proxId);
        listaOrdensServico.add(objeto);

        this.proxId++;
    }

    /**
     * Busca pelo id e retorna um objeto do tipo OrdemServico
     * @param id Id referente à ordem de serviço
     * @return Objeto do tipo OrdemServico ou null
     */
    @Override
    public OrdemServico buscarPorId(int id) {
        for (OrdemServico ordemServico : listaOrdensServico){
            if (ordemServico.getId() == id)
                return ordemServico;
        }
        return null;
    }

    /**
     * Retorna uma lista de todos os objetos do tipo OrdemServico
     * @return Lista de objetos do tipo OrdemServico
     */
    @Override
    public List<OrdemServico> buscarTodos() {
        List<OrdemServico> lista = new LinkedList<OrdemServico>();

        for (OrdemServico ordemServico : listaOrdensServico){
            lista.add(ordemServico);
        }

        return lista;
    }

    /**
     * Substitui um objeto do tipo OrdemServico por outro de mesmo id
     * @param objeto Objeto do tipo OrdemServico
     * @exception ObjetoNaoEncontradoException Se o id da ordem de serviço a ser atualizada não for encontrado
     */
    @Override
    public void atualizar(OrdemServico objeto) throws ObjetoNaoEncontradoException {
        for (int i = 0; i < this.listaOrdensServico.size(); i++){
            if (listaOrdensServico.get(i).getId() == objeto.getId()){
                this.listaOrdensServico.set(i, objeto);

                return;
            }
        }
        throw new ObjetoNaoEncontradoException("OrdemServico");
    }

    /**
     * Remove um objeto do tipo OrdemServico da lista
     * @param objeto Objeto do tipo OrdemServico
     * @exception ObjetoNaoEncontradoException Se o id da ordem de serviço a ser removida não for encontrado
     */
    @Override
    public void remover(OrdemServico objeto) throws ObjetoNaoEncontradoException {
        for (int i = 0; i < this.listaOrdensServico.size(); i++){
            if (listaOrdensServico.get(i).getId() == objeto.getId()){
                this.listaOrdensServico.remove(objeto);

                return;
            }
        }
        throw new ObjetoNaoEncontradoException("OrdemServico");
    }

    /**
     * Esvazia a lista de ordens de serviço
     */
    @Override
    public void deletarTudo() {
        this.listaOrdensServico = new LinkedList<OrdemServico>();
        this.proxId = 0;
    }

    /**
     * Busca pelo id e retorna uma lista de objetos do tipo OrdemServico dos quais um cliente faz parte
     * @param id Id do cliente
     * @return Lista de objetos do tipo OrdemServico
     */
    @Override
    public List<OrdemServico> buscarPorCliente(int id) {
        List<OrdemServico> lista = new LinkedList<OrdemServico>();

        for (OrdemServico ordemServico : DAO.getOrdemServico().buscarTodos()){
            if (ordemServico.getClienteId() == id)
                lista.add(ordemServico);
        }

        return lista;
    }

    /**
     * Busca pelo id e retorna uma lista de objetos do tipo OrdemServico dos quais um tecnico faz parte
     * @param id Id do tecnico
     * @return Lista de objetos do tipo OrdemServico
     */
    @Override
    public List<OrdemServico> buscarPorTecnico(int id) {
        List<OrdemServico> lista = new LinkedList<OrdemServico>();

        for (OrdemServico ordemServico : DAO.getOrdemServico().buscarTodos()){
            if (ordemServico.getTecnicoId() == id)
                lista.add(ordemServico);
        }

        return lista;
    }

    /**
     * Busca pelo id e retorna uma lista de objetos do tipo OrdemServico dos quais um serviço faz parte
     * @param id Id do serviço
     * @return Lista de objetos do tipo OrdemServico
     */
    @Override
    public List<OrdemServico> buscarPorServico(int id, String tipoServico) {
        List<OrdemServico> lista = new LinkedList<OrdemServico>();

        for (OrdemServico ordemServico : this.listaOrdensServico) {
            if (tipoServico.equals("Montagem")){
                for (Montagem montagem : ordemServico.getMontagens()){
                    if (montagem.getId() == id)
                        lista.add(ordemServico);
                }
            } else if (tipoServico.equals("Limpeza")){
                for (Limpeza limpeza : ordemServico.getLimpezas()){
                    if (limpeza.getId() == id)
                        lista.add(ordemServico);
                }
            } else if (tipoServico.equals("Instalacao")){
                for (Instalacao instalacao : ordemServico.getInstalacoes()){
                    if (instalacao.getId() == id)
                        lista.add(ordemServico);
                }
            }
        }

        return lista;
    }
}
