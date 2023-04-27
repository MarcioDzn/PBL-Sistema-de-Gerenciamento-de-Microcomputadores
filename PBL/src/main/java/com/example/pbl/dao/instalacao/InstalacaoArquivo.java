package com.example.pbl.dao.instalacao;

import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Cliente;
import com.example.pbl.model.Instalacao;
import com.example.pbl.utils.ManipuladorArquivo;

import java.util.LinkedList;
import java.util.List;

public class InstalacaoArquivo implements InstalacaoDAO {
    private int proxId;

    /**
     * Dados para gerar um objeto InstalcaoArquivo.
     */
    public InstalacaoArquivo() {
        List<Instalacao> lista = ManipuladorArquivo.recuperarBinario("instalacoes.dat");
        // Garante que o proxId não zere sempre que o programa for reiniciado
        // Pega o id do último elemento adicionado na lista
        this.proxId = lista.size() > 0 ? lista.get(0).getId() + 1 : 0;
    }

    @Override
    public void criar(Instalacao objeto) {
        List<Instalacao> lista =  ManipuladorArquivo.recuperarBinario("instalacoes.dat");

        objeto.setId(this.proxId);
        lista.add(objeto);

        ManipuladorArquivo.guardarBinario(lista, "instalacoes.dat");

        this.proxId++;
    }

    @Override
    public Instalacao buscarPorId(int id) {
        List<Instalacao> lista = ManipuladorArquivo.recuperarBinario("instalacoes.dat");

        for (Instalacao instalacao : lista) {
            if (instalacao.getId() == id)
                return instalacao;
        }
        return null;
    }

    @Override
    public List<Instalacao> buscarTodos() {
        return ManipuladorArquivo.recuperarBinario("instalacoes.dat");
    }

    @Override
    public void atualizar(Instalacao objeto) throws ObjetoNaoEncontradoException {
        List<Instalacao> lista = ManipuladorArquivo.recuperarBinario("instalacoes.dat");

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == objeto.getId()){
                lista.set(i, objeto);

                ManipuladorArquivo.guardarBinario(lista, "instalacoes.dat");

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Instalação");
    }

    @Override
    public void remover(Instalacao objeto) throws ObjetoNaoEncontradoException {
        List<Instalacao> lista = ManipuladorArquivo.recuperarBinario("instalacoes.dat");

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == objeto.getId()){
                lista.remove(objeto);

                ManipuladorArquivo.guardarBinario(lista, "instalacoes.dat");

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Instalação");
    }

    @Override
    public void deletarTudo() {
        ManipuladorArquivo.guardarBinario(new LinkedList<Instalacao>(), "instalacoes.dat");
        this.proxId = 0;
    }
}
