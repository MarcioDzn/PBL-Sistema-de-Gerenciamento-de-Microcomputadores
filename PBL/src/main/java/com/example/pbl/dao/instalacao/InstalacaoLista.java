package com.example.pbl.dao.instalacao;

import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Instalacao;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementação em lista encadeada do DAO da Instalacao
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class InstalacaoLista implements InstalacaoDAO{
    private List<Instalacao> listaInstalacoes;
    private int proxId;

    public InstalacaoLista() {
        this.listaInstalacoes = new LinkedList<Instalacao>();
        this.proxId = 0;
    }

    /**
     * Guarda um objeto do tipo Instalacao em uma lista.
     * Cada objeto tem um id único.
     * @param objeto Objeto do tipo Instalacao
     */
    @Override
    public void criar(Instalacao objeto) {
        objeto.setId(this.proxId);
        listaInstalacoes.add(objeto);

        this.proxId++;
    }

    /**
     * Busca pelo id e retorna um objeto do tipo Instalacao
     * @param id Id referente à instalação
     * @return Objeto do tipo Instalacao ou null
     */
    @Override
    public Instalacao buscarPorId(int id) {
        for (Instalacao instalacao : listaInstalacoes){
            if (instalacao.getId() == id)
                return instalacao;
        }
        return null;
    }

    /**
     * Retorna uma lista de todos os objetos do tipo Instalacao
     * @return Lista de objetos do tipo Instalacao
     */
    @Override
    public List<Instalacao> buscarTodos() {
        List<Instalacao> lista = new LinkedList<Instalacao>();

        for (Instalacao instalacao : listaInstalacoes){
            lista.add(instalacao);
        }

        return lista;
    }

    /**
     * Substitui um objeto do tipo Instalacao por outro de mesmo id
     * @param objeto Objeto do tipo Instalacao
     * @exception ObjetoNaoEncontradoException Se o id da instalação a ser atualizada não for encontrado
     */
    @Override
    public void atualizar(Instalacao objeto) throws ObjetoNaoEncontradoException {
        for (int i = 0; i < this.listaInstalacoes.size(); i++){
            if (listaInstalacoes.get(i).getId() == objeto.getId()){
                this.listaInstalacoes.set(i, objeto);

                return;
            }
        }
        throw new ObjetoNaoEncontradoException("Instalacao");
    }

    /**
     * Remove um objeto do tipo Instalacao da lista
     * @param objeto Objeto do tipo Instalacao
     * @exception ObjetoNaoEncontradoException Se o id da instalação a ser removida não for encontrado
     */
    @Override
    public void remover(Instalacao objeto) throws ObjetoNaoEncontradoException {
        for (int i = 0; i < this.listaInstalacoes.size(); i++){
            if (listaInstalacoes.get(i).getId() == objeto.getId()){
                this.listaInstalacoes.remove(objeto);

                return;
            }
        }
        throw new ObjetoNaoEncontradoException("Instalacao");
    }

    /**
     * Esvazia a lista de instalações
     */
    @Override
    public void deletarTudo() {
        this.listaInstalacoes = new LinkedList<Instalacao>();
        this.proxId = 0;
    }
}
