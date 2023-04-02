package com.example.pbl.dao.cliente;

import com.example.pbl.model.Cliente;

import java.util.LinkedList;
import java.util.List;

public class ClienteLista implements ClienteDAO {
    private List<Cliente> listaClientes;
    private int proxId;

    public ClienteLista() {
        this.listaClientes = new LinkedList<Cliente>();
        this.proxId = 0;
    }

    @Override
    public void criar(Cliente objeto) {
        objeto.setId(this.proxId);
        listaClientes.add(objeto);

        this.proxId++;

    }

    @Override
    public Cliente buscarPorId(int id){
        for (Cliente cliente : listaClientes) {
            if (cliente.getId() == id)
                return cliente;
        }
        return null;
    }

    @Override
    public List<Cliente> buscarTodos() {
        List<Cliente> lista = new LinkedList<Cliente>();

        for (Cliente cliente : listaClientes){
            lista.add(cliente);
        }

        return lista;
    }

    @Override
    public void atualizar(Cliente objeto) {
        for (int i = 0; i < this.listaClientes.size(); i++){
            if (listaClientes.get(i).getId() == objeto.getId()){
                this.listaClientes.add(objeto.getId(), objeto);

                return; // Para o laço assim que entrar no if e atualizar a lista
            }
        }
    }

    @Override
    public void remover(Cliente objeto) {
        for (int i = 0; i < this.listaClientes.size(); i++){
            if (listaClientes.get(i).getId() == objeto.getId()){
                this.listaClientes.remove(objeto);

                return;
            }
        }
    }

    @Override
    public void deletarTudo() {
        this.listaClientes = new LinkedList<Cliente>();
        this.proxId = 0;
    }

    @Override
    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> lista = new LinkedList<Cliente>();

        for (Cliente cliente : listaClientes){
            if (cliente.getNome() == nome)
                lista.add(cliente);
        }
        return lista;
    }
}
