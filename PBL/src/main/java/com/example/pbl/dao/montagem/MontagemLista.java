package com.example.pbl.dao.montagem;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementação em lista encadeada do DAO da Montagem
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class MontagemLista implements MontagemDAO{
    private List<Montagem> listaMontagens;
    private int proxId;

    public MontagemLista() {
        this.listaMontagens = new LinkedList<Montagem>();
        this.proxId = 0;
    }

    /**
     * Guarda um objeto do tipo Montagem em uma lista
     * Cada objeto tem um id único.
     * @param objeto Objeto do tipo Montagem
     */
    @Override
    public void criar(Montagem objeto) {
        objeto.setId(this.proxId);
        listaMontagens.add(objeto);

        this.proxId++;
    }

    /**
     * Busca pelo id e retorna um objeto do tipo Montagem
     * @param id Id referente à montagem
     * @return Objeto do tipo Montagem ou null
     */
    @Override
    public Montagem buscarPorId(int id) {
        for (Montagem montagem : listaMontagens){
            if (montagem.getId() == id)
                return montagem;
        }
        return null;
    }

    /**
     * Retorna uma lista de todos os objetos do tipo Montagem
     * @return Lista de objetos do tipo Montagem
     */
    @Override
    public List<Montagem> buscarTodos() {
        List<Montagem> lista = new LinkedList<Montagem>();

        for (Montagem montagem : listaMontagens){
            lista.add(montagem);
        }

        return lista;
    }

    /**
     * Substitui um objeto do tipo Montagem por outro de mesmo id
     * @param objeto Objeto do tipo Montagem
     * @exception ObjetoNaoEncontradoException Se o id da montagem a ser atualizada não for encontrado
     */
    @Override
    public void atualizar(Montagem objeto) throws ObjetoNaoEncontradoException {
        for (int i = 0; i < this.listaMontagens.size(); i++){
            if (listaMontagens.get(i).getId() == objeto.getId()){
                this.listaMontagens.set(i, objeto);

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Montagem");
    }

    /**
     * Remove um objeto do tipo Montagem da lista
     * @param objeto Objeto do tipo Montagem
     * @exception ObjetoNaoEncontradoException Se o id da montagem a ser removida não for encontrado
     */
    @Override
    public void remover(Montagem objeto) throws ObjetoNaoEncontradoException {
        for (int i = 0; i < this.listaMontagens.size(); i++){
            if (listaMontagens.get(i).getId() == objeto.getId()){
                this.listaMontagens.remove(objeto);

                return;
            }
        }
        throw new ObjetoNaoEncontradoException("Montagem");
    }

    /**
     * Esvazia a lista de montagens
     */
    @Override
    public void deletarTudo() {
        this.listaMontagens = new LinkedList<Montagem>();
        this.proxId = 0;
    }

    /**
     * Busca pelo id e retorna uma lista de objetos do tipo Montagem dos quais um componente faz parte
     * @param id Id do componente
     * @return Lista de objetos do tipo Montagem
     */
    @Override
    public List<Montagem> buscarPorComponente(int id, String tipoComponente) {
        List<Montagem> lista = new LinkedList<Montagem>();

        for (Montagem montagem : DAO.getMontagem().buscarTodos()) {
            if (tipoComponente.equals("Peca")){
                for (Peca peca : montagem.getPecas()){
                    if (peca.getId() == id)
                        lista.add(montagem);
                }

            } else if (tipoComponente.equals("OutroComponente")){
                for (OutroComponente outroComponente : montagem.getOutrosComponentes()){
                    if (outroComponente.getId() == id)
                        lista.add(montagem);
                }
            }
        }

        return lista;
    }
}
