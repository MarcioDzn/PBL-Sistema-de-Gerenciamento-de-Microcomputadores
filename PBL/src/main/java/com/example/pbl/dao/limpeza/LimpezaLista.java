package com.example.pbl.dao.limpeza;

import com.example.pbl.model.Limpeza;

import java.util.LinkedList;
import java.util.List;

public class LimpezaLista implements LimpezaDAO {
    private List<Limpeza> listaLimpeza;
    private int proxId;

    public LimpezaLista() {
        this.listaLimpeza = new LinkedList<Limpeza>();
        this.proxId = 0;
    }

    @Override
    public void criar(Limpeza objeto) {
        objeto.setId(this.proxId);
        listaLimpeza.add(objeto);

        this.proxId++;
    }

    @Override
    public Limpeza buscarPorId(int id) {
        for (Limpeza limpeza : listaLimpeza){
            if (limpeza.getId() == id)
                return limpeza;
        }
        return null;
    }

    @Override
    public List<Limpeza> buscarTodos() {
        List<Limpeza> lista = new LinkedList<Limpeza>();

        for (Limpeza limpeza : listaLimpeza){
            lista.add(limpeza);
        }

        return lista;
    }

    @Override
    public void atualizar(Limpeza objeto) {
        for (int i = 0; i < this.listaLimpeza.size(); i++){
            if (listaLimpeza.get(i).getId() == objeto.getId()){
                this.listaLimpeza.add(objeto.getId(), objeto);

                return; // Para o laço assim que entrar no if e atualizar a lista
            }
        }
    }

    @Override
    public void remover(Limpeza objeto) {
        for (int i = 0; i < this.listaLimpeza.size(); i++){
            if (listaLimpeza.get(i).getId() == objeto.getId()){
                this.listaLimpeza.remove(objeto);

                return;
            }
        }
    }

    @Override
    public void deletarTudo() {
        this.listaLimpeza = new LinkedList<Limpeza>();
        this.proxId = 0;
    }
}
