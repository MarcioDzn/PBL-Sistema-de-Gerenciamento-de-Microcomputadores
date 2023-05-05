package com.example.pbl.dao.tecnico;

import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Cliente;
import com.example.pbl.model.Tecnico;
import com.example.pbl.utils.ManipuladorArquivo;

import java.util.LinkedList;
import java.util.List;

public class TecnicoArquivo implements TecnicoDAO{
    private int proxId;

    /**
     * Dados para gerar um objeto TecnicoArquivo.
     */
    public TecnicoArquivo() {
        List<Tecnico> lista = ManipuladorArquivo.recuperarBinario("tecnicos.dat");
        // Garante que o proxId não zere sempre que o programa for reiniciado
        // Pega o id do último elemento adicionado na lista
        this.proxId = lista.size() > 0 ? lista.get(lista.size()-1).getId() + 1 : 0;
    }

    @Override
    public void criar(Tecnico objeto) {
        List<Tecnico> lista =  ManipuladorArquivo.recuperarBinario("tecnicos.dat");

        objeto.setId(this.proxId);
        lista.add(objeto);

        ManipuladorArquivo.guardarBinario(lista, "tecnicos.dat");

        this.proxId++;
    }

    @Override
    public Tecnico buscarPorId(int id) {
        List<Tecnico> lista = ManipuladorArquivo.recuperarBinario("tecnicos.dat");

        for (Tecnico tecnico : lista) {
            if (tecnico.getId() == id)
                return tecnico;
        }
        return null;
    }

    @Override
    public List<Tecnico> buscarTodos() {
        return ManipuladorArquivo.recuperarBinario("tecnicos.dat");
    }

    @Override
    public void atualizar(Tecnico objeto) throws ObjetoNaoEncontradoException {
        List<Tecnico> lista = ManipuladorArquivo.recuperarBinario("tecnicos.dat");

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == objeto.getId()){
                lista.set(i, objeto);

                ManipuladorArquivo.guardarBinario(lista, "tecnicos.dat");

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Tecnico");
    }

    @Override
    public void remover(Tecnico objeto) throws ObjetoNaoEncontradoException {
        List<Tecnico> lista = ManipuladorArquivo.recuperarBinario("tecnicos.dat");

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == objeto.getId()){
                lista.remove(objeto);

                ManipuladorArquivo.guardarBinario(lista, "tecnicos.dat");

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Tecnico");
    }

    @Override
    public void deletarTudo() {
        ManipuladorArquivo.guardarBinario(new LinkedList<Tecnico>(), "tecnicos.dat");
        this.proxId = 0;
    }

    @Override
    public Tecnico buscarPorNome(String nome) {
        List<Tecnico> lista = ManipuladorArquivo.recuperarBinario("tecnicos.dat");

        for (Tecnico tecnico : lista){
            if (tecnico.getNome() == nome)
                return tecnico;
        }
        return null;
    }
}
