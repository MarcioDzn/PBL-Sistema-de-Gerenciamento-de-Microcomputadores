package com.example.pbl.dao.peca;

import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Cliente;
import com.example.pbl.model.Peca;
import com.example.pbl.utils.ManipuladorArquivo;

import java.util.LinkedList;
import java.util.List;

public class PecaArquivo implements PecaDAO{
    private int proxId;

    /**
     * Dados para gerar um objeto PecaArquivo.
     */
    public PecaArquivo() {
        List<Peca> lista = ManipuladorArquivo.recuperarBinario("pecas.dat");
        // Garante que o proxId não zere sempre que o programa for reiniciado
        // Pega o id do último elemento adicionado na lista
        this.proxId = lista.size() > 0 ? lista.get(lista.size()-1).getId() + 1 : 0;
    }

    @Override
    public void criar(Peca objeto) {
        List<Peca> lista =  ManipuladorArquivo.recuperarBinario("pecas.dat");

        objeto.setId(this.proxId);
        lista.add(objeto);

        ManipuladorArquivo.guardarBinario(lista, "pecas.dat");

        this.proxId++;
    }

    @Override
    public Peca buscarPorId(int id) {
        List<Peca> lista = ManipuladorArquivo.recuperarBinario("pecas.dat");

        for (Peca peca : lista) {
            if (peca.getId() == id)
                return peca;
        }
        return null;
    }

    @Override
    public List<Peca> buscarTodos() {
        return ManipuladorArquivo.recuperarBinario("pecas.dat");
    }

    @Override
    public void atualizar(Peca objeto) throws ObjetoNaoEncontradoException {
        List<Peca> lista = ManipuladorArquivo.recuperarBinario("pecas.dat");

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == objeto.getId()){
                lista.set(i, objeto);

                ManipuladorArquivo.guardarBinario(lista, "pecas.dat");

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Peça");
    }

    @Override
    public void remover(Peca objeto) throws ObjetoNaoEncontradoException {
        List<Peca> lista = ManipuladorArquivo.recuperarBinario("pecas.dat");

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == objeto.getId()){
                lista.remove(objeto);

                ManipuladorArquivo.guardarBinario(lista, "pecas.dat");

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Peça");
    }

    @Override
    public void deletarTudo() {
        ManipuladorArquivo.guardarBinario(new LinkedList<Peca>(), "pecas.dat");
        this.proxId = 0;
    }

    @Override
    public Peca buscarPorNome(String nome) {
        List<Peca> lista = ManipuladorArquivo.recuperarBinario("pecas.dat");

        for (Peca peca : lista){
            if (peca.getNome() == nome)
                return peca;
        }
        return null;
    }
}
