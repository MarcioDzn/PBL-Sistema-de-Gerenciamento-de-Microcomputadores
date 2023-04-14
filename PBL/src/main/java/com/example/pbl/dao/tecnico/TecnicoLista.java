package com.example.pbl.dao.tecnico;

import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Tecnico;

import java.util.LinkedList;
import java.util.List;

public class TecnicoLista implements TecnicoDAO{
    private List<Tecnico> listaTecnicos;
    private int proxId;

    public TecnicoLista() {
        this.listaTecnicos = new LinkedList<Tecnico>();
        this.proxId = 0;
    }

    @Override
    public void criar(Tecnico objeto) {
        objeto.setId(this.proxId);
        listaTecnicos.add(objeto);

        this.proxId++;
    }

    @Override
    public Tecnico buscarPorId(int id) {
        for (Tecnico tecnico : listaTecnicos){
            if (tecnico.getId() == id)
                return tecnico;
        }
        return null;
    }

    @Override
    public List<Tecnico> buscarTodos() {
        List<Tecnico> lista = new LinkedList<Tecnico>();

        for (Tecnico tecnico : listaTecnicos){
            lista.add(tecnico);
        }

        return lista;
    }

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

    @Override
    public void deletarTudo() {
        this.listaTecnicos = new LinkedList<Tecnico>();
        this.proxId = 0;
    }

    @Override
    public Tecnico buscarPorNome(String nome) {
        for (Tecnico tecnico : listaTecnicos){
            if (tecnico.getNome() == nome)
                return tecnico;
        }
        return null;
    }
}
