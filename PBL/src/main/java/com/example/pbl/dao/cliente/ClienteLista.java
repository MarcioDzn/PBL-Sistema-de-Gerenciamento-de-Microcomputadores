package com.example.pbl.dao.cliente;

import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Cliente;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementação em lista encadeada do DAO do Cliente
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class ClienteLista implements ClienteDAO {
    private List<Cliente> listaClientes;
    private int proxId;

    /**
     * Dados para gerar um objeto ClienteLista.
     */
    public ClienteLista() {
        this.listaClientes = new LinkedList<Cliente>();
        this.proxId = 0;
    }

    /**
     * Guarda um objeto do tipo Cliente em uma lista
     * Cada objeto tem um id único.
     * @param objeto Objeto do tipo Cliente
     */
    @Override
    public void criar(Cliente objeto) {
        objeto.setId(this.proxId);
        listaClientes.add(objeto);

        this.proxId++;

    }

    /**
     * Busca pelo id e retorna um objeto do tipo Cliente
     * @param id Id referente ao cliente
     * @return Objeto do tipo Cliente ou null
     */
    @Override
    public Cliente buscarPorId(int id){
        for (Cliente cliente : listaClientes) {
            if (cliente.getId() == id)
                return cliente;
        }
        return null;
    }

    /**
     * Retorna uma lista de todos os objetos do tipo Cliente
     * @return Lista de objetos do tipo Cliente
     */
    @Override
    public List<Cliente> buscarTodos() {
        List<Cliente> lista = new LinkedList<Cliente>();

        for (Cliente cliente : listaClientes){
            lista.add(cliente);
        }

        return lista;
    }

    /**
     * Substitui um objeto do tipo Cliente por outro de mesmo id
     * @param objeto Objeto do tipo Cliente
     * @exception ObjetoNaoEncontradoException Se o id do cliente a ser atualizado não for encontrado
     */
    @Override
    public void atualizar(Cliente objeto) throws ObjetoNaoEncontradoException {
        for (int i = 0; i < this.listaClientes.size(); i++){
            if (listaClientes.get(i).getId() == objeto.getId()){
                this.listaClientes.set(i, objeto);

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Cliente");
    }

    /**
     * Remove um objeto do tipo Cliente da lista
     * @param objeto Objeto do tipo Cliente
     * @exception ObjetoNaoEncontradoException Se o id do cliente a ser removido não for encontrado
     */
    @Override
    public void remover(Cliente objeto) throws ObjetoNaoEncontradoException {
        for (int i = 0; i < this.listaClientes.size(); i++){
            if (listaClientes.get(i).getId() == objeto.getId()){
                this.listaClientes.remove(objeto);

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Cliente");
    }

    /**
     * Esvazia a lista de clientes
     */
    @Override
    public void deletarTudo() {
        this.listaClientes = new LinkedList<Cliente>();
        this.proxId = 0;
    }

    /**
     * Busca pelo nome e retorna uma lista de objetos do tipo Cliente
     * @param nome Nome referente ao cliente
     * @return Lista de objetos do tipo Cliente
     */
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
