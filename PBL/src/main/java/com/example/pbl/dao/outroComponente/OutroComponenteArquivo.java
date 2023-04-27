package com.example.pbl.dao.outroComponente;

import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Cliente;
import com.example.pbl.model.OutroComponente;
import com.example.pbl.utils.ManipuladorArquivo;

import java.util.LinkedList;
import java.util.List;

public class OutroComponenteArquivo implements OutroComponenteDAO{
    private int proxId;

    /**
     * Dados para gerar um objeto ClienteArquivo.
     */
    public OutroComponenteArquivo() {
        List<OutroComponente> lista = ManipuladorArquivo.recuperarBinario("outrosComponentes.dat");
        // Garante que o proxId não zere sempre que o programa for reiniciado
        // Pega o id do último elemento adicionado na lista
        this.proxId = lista.size() > 0 ? lista.get(0).getId() + 1 : 0;
    }

    @Override
    public void criar(OutroComponente objeto) {
        List<OutroComponente> lista =  ManipuladorArquivo.recuperarBinario("outrosComponentes.dat");

        objeto.setId(this.proxId);
        lista.add(objeto);

        ManipuladorArquivo.guardarBinario(lista, "outrosComponentes.dat");

        this.proxId++;
    }

    @Override
    public OutroComponente buscarPorId(int id) {
        List<OutroComponente> lista = ManipuladorArquivo.recuperarBinario("outrosComponentes.dat");

        for (OutroComponente outroComponente : lista) {
            if (outroComponente.getId() == id)
                return outroComponente;
        }
        return null;
    }

    @Override
    public List<OutroComponente> buscarTodos() {
        return ManipuladorArquivo.recuperarBinario("outrosComponentes.dat");
    }

    @Override
    public void atualizar(OutroComponente objeto) throws ObjetoNaoEncontradoException {
        List<OutroComponente> lista = ManipuladorArquivo.recuperarBinario("outrosComponentes.dat");

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == objeto.getId()){
                lista.set(i, objeto);

                ManipuladorArquivo.guardarBinario(lista, "outrosComponentes.dat");

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Outro componente");
    }

    @Override
    public void remover(OutroComponente objeto) throws ObjetoNaoEncontradoException {
        List<OutroComponente> lista = ManipuladorArquivo.recuperarBinario("outrosComponentes.dat");

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == objeto.getId()){
                lista.remove(objeto);

                ManipuladorArquivo.guardarBinario(lista, "outrosComponentes.dat");

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Outro componente");
    }

    @Override
    public void deletarTudo() {
        ManipuladorArquivo.guardarBinario(new LinkedList<OutroComponente>(), "outrosComponentes.dat");
        this.proxId = 0;
    }
}
