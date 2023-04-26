package com.example.pbl.dao.montagem;

import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Cliente;
import com.example.pbl.model.Montagem;
import com.example.pbl.model.OutroComponente;
import com.example.pbl.model.Peca;
import com.example.pbl.utils.ManipuladorArquivo;

import java.util.LinkedList;
import java.util.List;

public class MontagemArquivo implements MontagemDAO{

    private int proxId;

    /**
     * Dados para gerar um objeto MontagemArquivo.
     */
    public MontagemArquivo() {
        List<Montagem> lista = ManipuladorArquivo.recuperarBinario("montagens.dat");
        // Garante que o proxId não zere sempre que o programa for reiniciado
        // Pega o id do último elemento adicionado na lista
        this.proxId = lista.size() > 0 ? lista.get(0).getId() : 0;
    }

    @Override
    public void criar(Montagem objeto) {
        List<Montagem> lista =  ManipuladorArquivo.recuperarBinario("montagens.dat");

        objeto.setId(this.proxId);
        lista.add(objeto);

        ManipuladorArquivo.guardarBinario(lista, "montagens.dat");

        this.proxId++;
    }

    @Override
    public Montagem buscarPorId(int id) {
        List<Montagem> lista = ManipuladorArquivo.recuperarBinario("montagens.dat");

        for (Montagem montagem : lista) {
            if (montagem.getId() == id)
                return montagem;
        }
        return null;
    }

    @Override
    public List<Montagem> buscarTodos() {
        return ManipuladorArquivo.recuperarBinario("montagens.dat");
    }

    @Override
    public void atualizar(Montagem objeto) throws ObjetoNaoEncontradoException {
        List<Montagem> lista = ManipuladorArquivo.recuperarBinario("montagens.dat");

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == objeto.getId()){
                lista.set(i, objeto);

                ManipuladorArquivo.guardarBinario(lista, "montagens.dat");

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Montagem");
    }

    @Override
    public void remover(Montagem objeto) throws ObjetoNaoEncontradoException {
        List<Montagem> lista = ManipuladorArquivo.recuperarBinario("montagens.dat");

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == objeto.getId()){
                lista.remove(objeto);

                ManipuladorArquivo.guardarBinario(lista, "montagens.dat");

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Montagem");
    }

    @Override
    public void deletarTudo() {
        ManipuladorArquivo.guardarBinario(new LinkedList<Montagem>(), "montagens.dat");
        this.proxId = 0;
    }

    @Override
    public List<Montagem> buscarPorComponente(int id, String tipoComponente) {
        List<Montagem> lista = ManipuladorArquivo.recuperarBinario("montagens.dat");
        List<Montagem> listaComponentes = new LinkedList<Montagem>();

        for (Montagem montagem : lista) {
            if (tipoComponente.equals("Peca")){
                for (Peca peca : montagem.getPecas()){
                    if (peca.getId() == id && !listaComponentes.contains(montagem))
                        listaComponentes.add(montagem);
                }

            } else if (tipoComponente.equals("OutroComponente")){
                for (OutroComponente outroComponente : montagem.getOutrosComponentes()){
                    if (outroComponente.getId() == id && !listaComponentes.contains(montagem))
                        listaComponentes.add(montagem);
                }
            }
        }

        return listaComponentes;
    }
}
