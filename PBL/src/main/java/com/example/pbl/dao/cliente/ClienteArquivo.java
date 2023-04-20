package com.example.pbl.dao.cliente;

import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Cliente;
import com.example.pbl.utils.ManipuladorArquivo;

import java.util.LinkedList;
import java.util.List;

public class ClienteArquivo implements ClienteDAO{
    private int proxId;

    /**
     * Dados para gerar um objeto ClienteArquivo.
     */
    public ClienteArquivo() {
        ManipuladorArquivo.guardarBinario(new LinkedList<Cliente>(), "clientes.dat");
        this.proxId = 0;
    }

    @Override
    public void criar(Cliente objeto) {
        List<Cliente> lista =  ManipuladorArquivo.recuperarBinario("clientes.dat");

        objeto.setId(this.proxId);
        lista.add(objeto);

        ManipuladorArquivo.guardarBinario(lista, "clientes.dat");

        this.proxId++;
    }

    @Override
    public Cliente buscarPorId(int id) {
        List<Cliente> lista = ManipuladorArquivo.recuperarBinario("clientes.dat");

        for (Cliente cliente : lista) {
            if (cliente.getId() == id)
                return cliente;
        }
        return null;
    }

    @Override
    public List<Cliente> buscarTodos() {
        return ManipuladorArquivo.recuperarBinario("clientes.dat");
    }

    @Override
    public void atualizar(Cliente objeto) throws ObjetoNaoEncontradoException {
        List<Cliente> lista = ManipuladorArquivo.recuperarBinario("clientes.dat");

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == objeto.getId()){
                lista.set(i, objeto);

                ManipuladorArquivo.guardarBinario(lista, "clientes.dat");

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Cliente");
    }

    @Override
    public void remover(Cliente objeto) throws ObjetoNaoEncontradoException {
        List<Cliente> lista = ManipuladorArquivo.recuperarBinario("clientes.dat");

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == objeto.getId()){
                lista.remove(objeto);

                ManipuladorArquivo.guardarBinario(lista, "clientes.dat");

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Cliente");
    }

    @Override
    public void deletarTudo() {
        ManipuladorArquivo.guardarBinario(new LinkedList<Cliente>(), "clientes.dat");
        this.proxId = 0;
    }

    @Override
    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> lista = ManipuladorArquivo.recuperarBinario("clientes.dat");
        List<Cliente> listaClientes = new LinkedList<Cliente>();

        for (Cliente cliente : lista){
            if (cliente.getNome().equals(nome))
                listaClientes.add(cliente);
        }
        return listaClientes;
    }
}
