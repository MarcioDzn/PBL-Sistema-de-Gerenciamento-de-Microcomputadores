package com.example.pbl.dao.tecnico;

import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Tecnico;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementação em lista encadeada do DAO do Tecnico
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class TecnicoLista implements TecnicoDAO{
    private List<Tecnico> listaTecnicos;
    private int proxId;

    public TecnicoLista() {
        this.listaTecnicos = new LinkedList<Tecnico>();
        this.proxId = 0;
    }

    /**
     * Guarda um objeto do tipo Tecnico em uma lista
     * Cada objeto tem um id único.
     * @param objeto Objeto do tipo Tecnico
     */
    @Override
    public void criar(Tecnico objeto) {
        objeto.setId(this.proxId);
        listaTecnicos.add(objeto);

        this.proxId++;
    }

    /**
     * Busca pelo id e retorna um objeto do tipo Tecnico
     * @param id Id referente ao tecnico
     * @return Objeto do tipo Tecnico ou null
     */
    @Override
    public Tecnico buscarPorId(int id) {
        for (Tecnico tecnico : listaTecnicos){
            if (tecnico.getId() == id)
                return tecnico;
        }
        return null;
    }

    /**
     * Retorna uma lista de todos os objetos do tipo Tecnico
     * @return Lista de objetos do tipo Tecnico
     */
    @Override
    public List<Tecnico> buscarTodos() {
        List<Tecnico> lista = new LinkedList<Tecnico>();

        for (Tecnico tecnico : listaTecnicos){
            lista.add(tecnico);
        }

        return lista;
    }

    /**
     * Substitui um objeto do tipo Tecnico por outro de mesmo id
     * @param objeto Objeto do tipo Tecnico
     * @exception ObjetoNaoEncontradoException Se o id do tecnico a ser atualizado não for encontrado
     */
    @Override
    public void atualizar(Tecnico objeto) throws ObjetoNaoEncontradoException {
        for (int i = 0; i < this.listaTecnicos.size(); i++){
            if (listaTecnicos.get(i).getId() == objeto.getId()){
                this.listaTecnicos.set(i, objeto);

                return;
            }
        }
        throw new ObjetoNaoEncontradoException("Tecnico");
    }

    /**
     * Remove um objeto do tipo Tecnico da lista
     * @param objeto Objeto do tipo Tecnico
     * @exception ObjetoNaoEncontradoException Se o id do tecnico a ser removido não for encontrado
     */
    @Override
    public void remover(Tecnico objeto) throws ObjetoNaoEncontradoException {
        for (int i = 0; i < this.listaTecnicos.size(); i++){
            if (listaTecnicos.get(i).getId() == objeto.getId()){
                this.listaTecnicos.remove(objeto);

                return;
            }
        }
        throw new ObjetoNaoEncontradoException("Tecnico");
    }

    /**
     * Esvazia a lista de tecnicos
     */
    @Override
    public void deletarTudo() {
        this.listaTecnicos = new LinkedList<Tecnico>();
        this.proxId = 0;
    }

    /**
     * Busca pelo nome e retorna uma lista de objetos do tipo Tecnico
     * @param nome Nome referente ao tecnico
     * @return Lista de objetos do tipo Tecnico
     */
    @Override
    public Tecnico buscarPorNome(String nome) {
        for (Tecnico tecnico : listaTecnicos){
            if (tecnico.getNome() == nome)
                return tecnico;
        }
        return null;
    }
}
