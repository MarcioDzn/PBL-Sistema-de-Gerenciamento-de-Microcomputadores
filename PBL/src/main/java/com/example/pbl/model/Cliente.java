package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Classe referente à Cliente.
 * </p>
 *
 * <p>
 * Um Cliente possui id, nome, endereço, telefone e email,
 * </p>
 *
 * <p>
 * Esta classe contém métodos para manipular seus atributos,
 * bem como para buscar todas as ordens de serviço da qual
 * este Cliente faz parte. Também contém um método para comparar
 * a si mesma com outro objeto pelo id.
 * </p>
 *
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class Cliente implements Serializable {
    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;

    /**
     * Dados para gerar um objeto cliente.
     * @param nome Nome do cliente
     * @param endereco Endereço do cliente
     * @param telefone Telefone do cliente
     * @param email Email do cliente
     */
    public Cliente(String nome, String endereco, String telefone, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.id = 0;
    }

    /**
     * Método que retorna o id do cliente
     * @return Id do cliente
     */
    public int getId() {
        return id;
    }

    /**
     * Método que define o id do cliente
     * @param id Novo id do cliente
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método que retorna o nome do cliente
     * @return Nome do cliente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que define o nome do cliente
     * @param nome Novo nome do cliente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método que retorna o endereço do cliente
     * @return Endereço do cliente
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Método que define o endereço do cliente
     * @param endereco Novo endereço do cliente
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Método que retorna o telefone do cliente
     * @return Telefone do cliente
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Método que define o telefone do cliente
     * @param telefone Novo telefone do cliente
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Método que retorna o email do cliente
     * @return Email do cliente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método que define o email do cliente
     * @param email Novo email do cliente
     */
    public void setEmail(String email) {
        this.email = email;
    }

    // Fim Getters e Setters

    /**
     * Método que retorna uma lista de ordens de serviço associadas a este cliente
     * @return Lista de objetos do tipo OrdemServico
     */
    public List<OrdemServico> getOrdensServico() {
        return DAO.getOrdemServico().buscarPorCliente(this.id);
    }

    /**
     * Método para comparar o objeto de um Cliente com este objeto pelo id
     * @param obj Objeto do tipo Cliente a ser comparado
     * @return Valor booleano true para id's iguais e false para id's diferentes
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Cliente){
            Cliente i = (Cliente) obj;
            if (this.getId() == i.getId()){
                return true;
            }
        }
        return false;
    }

}
