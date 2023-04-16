package com.example.pbl.dao.limpeza;

import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Limpeza;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementação em lista encadeada do DAO da Limpeza
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class LimpezaLista implements LimpezaDAO {
    private List<Limpeza> listaLimpeza;
    private int proxId;

    public LimpezaLista() {
        this.listaLimpeza = new LinkedList<Limpeza>();
        this.proxId = 0;
    }

    /**
     * Guarda um objeto do tipo Limpeza em uma lista
     * Cada objeto tem um id único.
     * @param objeto Objeto do tipo Limpeza
     */
    @Override
    public void criar(Limpeza objeto) {
        objeto.setId(this.proxId);
        listaLimpeza.add(objeto);

        this.proxId++;
    }

    /**
     * Busca pelo id e retorna um objeto do tipo Limpeza
     * @param id Id referente à limpeza
     * @return Objeto do tipo Limpeza ou null
     */
    @Override
    public Limpeza buscarPorId(int id) {
        for (Limpeza limpeza : listaLimpeza){
            if (limpeza.getId() == id)
                return limpeza;
        }
        return null;
    }

    /**
     * Retorna uma lista de todos os objetos do tipo Instalacao
     * @return Lista de objetos do tipo Instalacao
     */
    @Override
    public List<Limpeza> buscarTodos() {
        List<Limpeza> lista = new LinkedList<Limpeza>();

        for (Limpeza limpeza : listaLimpeza){
            lista.add(limpeza);
        }

        return lista;
    }

    /**
         * Substitui um objeto do tipo Limpeza por outro de mesmo id
     * @param objeto Objeto do tipo Limpeza
     * @exception ObjetoNaoEncontradoException Se o id da limpeza a ser atualizada não for encontrado
     */
    @Override
    public void atualizar(Limpeza objeto) throws ObjetoNaoEncontradoException {
        for (int i = 0; i < this.listaLimpeza.size(); i++){
            if (listaLimpeza.get(i).getId() == objeto.getId()){
                this.listaLimpeza.set(i, objeto);

                return;
            }
        }
        throw new ObjetoNaoEncontradoException("Limpeza");
    }

    /**
     * Remove um objeto do tipo Limpeza da lista
     * @param objeto Objeto do tipo Limpeza
     * @exception ObjetoNaoEncontradoException Se o id da limpeza a ser removida não for encontrado
     */
    @Override
    public void remover(Limpeza objeto) throws ObjetoNaoEncontradoException {
        for (int i = 0; i < this.listaLimpeza.size(); i++){
            if (listaLimpeza.get(i).getId() == objeto.getId()){
                this.listaLimpeza.remove(objeto);

                return;
            }
        }
        throw new ObjetoNaoEncontradoException("Limpeza");
    }

    /**
     * Esvazia a lista de limpezas
     */
    @Override
    public void deletarTudo() {
        this.listaLimpeza = new LinkedList<Limpeza>();
        this.proxId = 0;
    }
}
