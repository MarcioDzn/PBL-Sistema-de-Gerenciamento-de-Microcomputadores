package com.example.pbl.dao.limpeza;

import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Cliente;
import com.example.pbl.model.Limpeza;
import com.example.pbl.utils.ManipuladorArquivo;

import java.util.LinkedList;
import java.util.List;

public class LimpezaArquivo implements LimpezaDAO{

    private int proxId;

    /**
     * Dados para gerar um objeto LimpezaArquivo.
     */
    public LimpezaArquivo() {
        List<Limpeza> lista = ManipuladorArquivo.recuperarBinario("limpezas.dat");
        // Garante que o proxId não zere sempre que o programa for reiniciado
        // Pega o id do último elemento adicionado na lista
        this.proxId = lista.size() > 0 ? lista.get(0).getId() : 0;
    }

    @Override
    public void criar(Limpeza objeto) {
        List<Limpeza> lista =  ManipuladorArquivo.recuperarBinario("limpezas.dat");

        objeto.setId(this.proxId);
        lista.add(objeto);

        ManipuladorArquivo.guardarBinario(lista, "limpezas.dat");

        this.proxId++;
    }

    @Override
    public Limpeza buscarPorId(int id) {
        List<Limpeza> lista = ManipuladorArquivo.recuperarBinario("limpezas.dat");

        for (Limpeza limpeza : lista) {
            if (limpeza.getId() == id)
                return limpeza;
        }
        return null;
    }

    @Override
    public List<Limpeza> buscarTodos() {
        return ManipuladorArquivo.recuperarBinario("limpezas.dat");
    }

    @Override
    public void atualizar(Limpeza objeto) throws ObjetoNaoEncontradoException {
        List<Limpeza> lista = ManipuladorArquivo.recuperarBinario("limpezas.dat");

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == objeto.getId()){
                lista.set(i, objeto);

                ManipuladorArquivo.guardarBinario(lista, "limpezas.dat");

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Limpeza");
    }

    @Override
    public void remover(Limpeza objeto) throws ObjetoNaoEncontradoException {
        List<Limpeza> lista = ManipuladorArquivo.recuperarBinario("limpezas.dat");

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == objeto.getId()){
                lista.remove(objeto);

                ManipuladorArquivo.guardarBinario(lista, "limpezas.dat");

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Limpeza");
    }

    @Override
    public void deletarTudo() {
        ManipuladorArquivo.guardarBinario(new LinkedList<Limpeza>(), "limpezas.dat");
        this.proxId = 0;
    }
}
