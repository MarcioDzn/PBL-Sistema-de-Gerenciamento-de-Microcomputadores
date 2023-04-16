package com.example.pbl.dao.peca;

import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Peca;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementação em lista encadeada do DAO da Peca
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class PecaLista implements PecaDAO{
    private List<Peca> listaPecas;
    private int proxId;

    public PecaLista() {
        this.listaPecas = new LinkedList<Peca>();
        this.proxId = 0;
    }

    /**
     * Guarda um objeto do tipo Peca em uma lista
     * Cada objeto tem um id único.
     * @param objeto Objeto do tipo Peca
     */
    @Override
    public void criar(Peca objeto) {
        objeto.setId(this.proxId);
        listaPecas.add(objeto);

        this.proxId++;
    }

    /**
     * Busca pelo id e retorna um objeto do tipo Peca
     * @param id Id referente à peça
     * @return Objeto do tipo Peca ou null
     */
    @Override
    public Peca buscarPorId(int id) {
        for (Peca peca: listaPecas){
            if (peca.getId() == id)
                return peca;
        }
        return null;
    }

    /**
     * Retorna uma lista de todos os objetos do tipo Peca
     * @return Lista de objetos do tipo Peca
     */
    @Override
    public List<Peca> buscarTodos() {
        List<Peca> lista = new LinkedList<Peca>();

        for (Peca peca : listaPecas){
            lista.add(peca);
        }

        return lista;
    }

    /**
     * Substitui um objeto do tipo Peca por outro de mesmo id
     * @param objeto Objeto do tipo Peca
     * @exception ObjetoNaoEncontradoException Se o id da peça a ser atualizada não for encontrado
     */
    @Override
    public void atualizar(Peca objeto) throws ObjetoNaoEncontradoException {
        for (int i = 0; i < this.listaPecas.size(); i++){
            if (listaPecas.get(i).getId() == objeto.getId()){
                this.listaPecas.set(i, objeto);

                return;
            }
        }
        throw new ObjetoNaoEncontradoException("Peça");
    }

    /**
     * Remove um objeto do tipo Peca da lista
     * @param objeto Objeto do tipo Peca
     * @exception ObjetoNaoEncontradoException Se o id da peça a ser removida não for encontrado
     */
    @Override
    public void remover(Peca objeto) throws ObjetoNaoEncontradoException {
        for (int i = 0; i < this.listaPecas.size(); i++){
            if (listaPecas.get(i).getId() == objeto.getId()){
                this.listaPecas.remove(objeto);

                return;
            }
        }
        throw new ObjetoNaoEncontradoException("Peça");
    }

    /**
     * Esvazia a lista de peças
     */
    @Override
    public void deletarTudo() {
        this.listaPecas = new LinkedList<Peca>();
        this.proxId = 0;
    }

    /**
     * Busca pelo nome e retorna uma lista de objetos do tipo Peca
     * @param nome Nome referente à paça
     * @return Lista de objetos do tipo Peca
     */
    @Override
    public Peca buscarPorNome(String nome) {
        for (Peca peca : listaPecas){
            if (peca.getNome() == nome)
                return peca;
        }
        return null;
    }
}
