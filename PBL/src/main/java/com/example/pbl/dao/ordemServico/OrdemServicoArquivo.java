package com.example.pbl.dao.ordemServico;

import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.*;
import com.example.pbl.utils.ManipuladorArquivo;

import java.util.LinkedList;
import java.util.List;

public class OrdemServicoArquivo implements OrdemServicoDAO{
    private int proxId;

    /**
     * Dados para gerar um objeto OrdemServicoArquivo.
     */
    public OrdemServicoArquivo() {
        List<OrdemServico> lista = ManipuladorArquivo.recuperarBinario("ordensServico.dat");
        // Garante que o proxId não zere sempre que o programa for reiniciado
        // Pega o id do último elemento adicionado na lista
        this.proxId = lista.size() > 0 ? lista.get(lista.size()-1).getId() + 1 : 0;
    }

    @Override
    public void criar(OrdemServico objeto) {
        List<OrdemServico> lista =  ManipuladorArquivo.recuperarBinario("ordensServico.dat");

        objeto.setId(this.proxId);
        lista.add(objeto);

        ManipuladorArquivo.guardarBinario(lista, "ordensServico.dat");

        this.proxId++;
    }

    @Override
    public OrdemServico buscarPorId(int id) {
        List<OrdemServico> lista = ManipuladorArquivo.recuperarBinario("ordensServico.dat");

        for (OrdemServico ordemServico : lista) {
            if (ordemServico.getId() == id)
                return ordemServico;
        }
        return null;
    }

    @Override
    public List<OrdemServico> buscarTodos() {
        return ManipuladorArquivo.recuperarBinario("ordensServico.dat");
    }

    @Override
    public void atualizar(OrdemServico objeto) throws ObjetoNaoEncontradoException {
        List<OrdemServico> lista = ManipuladorArquivo.recuperarBinario("ordensServico.dat");

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == objeto.getId()){
                lista.set(i, objeto);

                ManipuladorArquivo.guardarBinario(lista, "ordensServico.dat");

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Ordem de Serviço");
    }

    @Override
    public void remover(OrdemServico objeto) throws ObjetoNaoEncontradoException {
        List<OrdemServico> lista = ManipuladorArquivo.recuperarBinario("ordensServico.dat");

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == objeto.getId()){
                lista.remove(objeto);

                ManipuladorArquivo.guardarBinario(lista, "ordensServico.dat");

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Ordem de Serviço");
    }

    @Override
    public void deletarTudo() {
        ManipuladorArquivo.guardarBinario(new LinkedList<OrdemServico>(), "ordensServico.dat");
        this.proxId = 0;
    }

    @Override
    public List<OrdemServico> buscarPorCliente(int id) {
        List<OrdemServico> lista = ManipuladorArquivo.recuperarBinario("ordensServico.dat");
        List<OrdemServico> listaOrdens = new LinkedList<OrdemServico>();

        for (OrdemServico ordemServico : lista){
            if (ordemServico.getClienteId() == id)
                listaOrdens.add(ordemServico);
        }

        return listaOrdens;
    }

    @Override
    public List<OrdemServico> buscarPorTecnico(int id) {
        List<OrdemServico> lista = ManipuladorArquivo.recuperarBinario("ordensServico.dat");
        List<OrdemServico> listaOrdens = new LinkedList<OrdemServico>();

        for (OrdemServico ordemServico : lista){
            if (ordemServico.getTecnicoId() == id)
                listaOrdens.add(ordemServico);
        }

        return listaOrdens;
    }

    @Override
    public List<OrdemServico> buscarPorServico(int id, String tipoServico) {
        List<OrdemServico> lista = ManipuladorArquivo.recuperarBinario("ordensServico.dat");
        List<OrdemServico> listaOrdens = new LinkedList<OrdemServico>();

        for (OrdemServico ordemServico : lista) {
            if (tipoServico.equals("Montagem")){
                for (Montagem montagem : ordemServico.getMontagens()){
                    if (montagem.getId() == id && !listaOrdens.contains(ordemServico))
                        listaOrdens.add(ordemServico);
                }
            } else if (tipoServico.equals("Limpeza")){
                for (Limpeza limpeza : ordemServico.getLimpezas()){
                    if (limpeza.getId() == id && !listaOrdens.contains(ordemServico))
                        listaOrdens.add(ordemServico);
                }
            } else if (tipoServico.equals("Instalacao")){
                for (Instalacao instalacao : ordemServico.getInstalacoes()){
                    if (instalacao.getId() == id && !listaOrdens.contains(ordemServico))
                        listaOrdens.add(ordemServico);
                }
            }
        }

        return listaOrdens;
    }

    /**
     * Busca uma lista de ordens em aberto armazenadas no DAO
     * @return Lista de objetos do tipo OrdemServico
     */
    public List<OrdemServico> buscarOrdensEmAberto(){
        List<OrdemServico> lista = ManipuladorArquivo.recuperarBinario("ordensServico.dat");
        List<OrdemServico> listaOrdens = new LinkedList<OrdemServico>();

        for (OrdemServico ordemServico : lista){
            if (ordemServico.isEmAberto())
                listaOrdens.add(ordemServico);
        }

        return listaOrdens;
    }

    /**
     * Busca a primeira ordem de serviço em aberto armazenada no DAO
     * @return Objeto do tipo OrdemServico
     */
    public OrdemServico buscarPrimeiraOrdem(){
        List<OrdemServico> lista = ManipuladorArquivo.recuperarBinario("ordensServico.dat");
        List<OrdemServico> listaOrdens = new LinkedList<OrdemServico>();

        for (OrdemServico ordemServico : lista){
            if (ordemServico.isEmAberto())
                listaOrdens.add(ordemServico);
        }

        return listaOrdens.get(0);
    }
}
