package com.example.pbl.dao.ordemServico;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.*;

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
    public void atualizar(OrdemServico objeto) throws ObjetoNaoEncontradoException {
        for (int i = 0; i < this.listaOrdensServico.size(); i++){
            if (listaOrdensServico.get(i).getId() == objeto.getId()){
                this.listaOrdensServico.set(i, objeto);

                return;
            }
        }
        throw new ObjetoNaoEncontradoException("OrdemServico");
    }

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

    @Override
    public void deletarTudo() {
        this.listaOrdensServico = new LinkedList<OrdemServico>();
        this.proxId = 0;
    }

    @Override
    public List<OrdemServico> buscarPorCliente(int id) {
        List<OrdemServico> lista = new LinkedList<OrdemServico>();

        for (OrdemServico ordemServico : DAO.getOrdemServico().buscarTodos()){
            if (ordemServico.getClienteId() == id)
                lista.add(ordemServico);
        }

        return lista;
    }

    @Override
    public List<OrdemServico> buscarPorTecnico(int id) {
        List<OrdemServico> lista = new LinkedList<OrdemServico>();

        for (OrdemServico ordemServico : DAO.getOrdemServico().buscarTodos()){
            if (ordemServico.getTecnicoId() == id)
                lista.add(ordemServico);
        }

        return lista;
    }

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
