package com.example.pbl.dao.montagem;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.*;

import java.util.LinkedList;
import java.util.List;

public class MontagemLista implements MontagemDAO{
    private List<Montagem> listaMontagens;
    private int proxId;

    public MontagemLista() {
        this.listaMontagens = new LinkedList<Montagem>();
        this.proxId = 0;
    }

    @Override
    public void criar(Montagem objeto) {
        objeto.setId(this.proxId);
        listaMontagens.add(objeto);

        this.proxId++;
    }

    @Override
    public Montagem buscarPorId(int id) {
        for (Montagem montagem : listaMontagens){
            if (montagem.getId() == id)
                return montagem;
        }
        return null;
    }

    @Override
    public List<Montagem> buscarTodos() {
        List<Montagem> lista = new LinkedList<Montagem>();

        for (Montagem montagem : listaMontagens){
            lista.add(montagem);
        }

        return lista;
    }

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

    @Override
    public void deletarTudo() {
        this.listaMontagens = new LinkedList<Montagem>();
        this.proxId = 0;
    }

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
