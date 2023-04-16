package com.example.pbl.dao.outroComponente;

import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.OutroComponente;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementação em lista encadeada do DAO do OutroComponente
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class OutroComponenteLista implements OutroComponenteDAO{
    private List<OutroComponente> listaOutrosComponentes;
    private int proxId;

    public OutroComponenteLista() {
        this.listaOutrosComponentes = new LinkedList<OutroComponente>();
        this.proxId = 0;
    }

    /**
     * Guarda um objeto do tipo OutroComponente em uma lista
     * Cada objeto tem um id único.
     * @param objeto Objeto do tipo OutroComponente
     */
    @Override
    public void criar(OutroComponente objeto) {
        objeto.setId(this.proxId);
        listaOutrosComponentes.add(objeto);

        this.proxId++;
    }

    /**
     * Busca pelo id e retorna um objeto do tipo OutroComponente
     * @param id Id referente ao "outro" componente
     * @return Objeto do tipo OutroComponente ou null
     */
    @Override
    public OutroComponente buscarPorId(int id) {
        for (OutroComponente outroComponente : listaOutrosComponentes){
            if (outroComponente.getId() == id)
                return outroComponente;
        }
        return null;
    }

    /**
     * Retorna uma lista de todos os objetos do tipo OutroComponente
     * @return Lista de objetos do tipo OutroComponente
     */
    @Override
    public List<OutroComponente> buscarTodos() {
        List<OutroComponente> lista = new LinkedList<OutroComponente>();

        for (OutroComponente outroComponente : listaOutrosComponentes){
            lista.add(outroComponente);
        }

        return lista;
    }

    /**
     * Substitui um objeto do tipo OutroComponente por outro de mesmo id
     * @param objeto Objeto do tipo OutroComponente
     * @exception ObjetoNaoEncontradoException Se o id do "outro" componente a ser atualizado não for encontrado
     */
    @Override
    public void atualizar(OutroComponente objeto) throws ObjetoNaoEncontradoException {
        for (int i = 0; i < this.listaOutrosComponentes.size(); i++){
            if (listaOutrosComponentes.get(i).getId() == objeto.getId()){
                this.listaOutrosComponentes.set(i, objeto);

                return; 
            }
        }

        throw new ObjetoNaoEncontradoException("OutroComponente");
    }

    /**
     * Remove um objeto do tipo OutroComponente da lista
     * @param objeto Objeto do tipo OutroComponente
     * @exception ObjetoNaoEncontradoException Se o id do "outro" componente a ser removido não for encontrado
     */
    @Override
    public void remover(OutroComponente objeto) throws ObjetoNaoEncontradoException {
        for (int i = 0; i < this.listaOutrosComponentes.size(); i++){
            if (listaOutrosComponentes.get(i).getId() == objeto.getId()){
                this.listaOutrosComponentes.remove(i);
                this.listaOutrosComponentes.remove(objeto);

                return;
            }
        }
        throw new ObjetoNaoEncontradoException("OutroComponente");
    }

    /**
     * Esvazia a lista de "outros" componentes
     */
    @Override
    public void deletarTudo() {
        this.listaOutrosComponentes = new LinkedList<OutroComponente>();
        this.proxId = 0;
    }
}
