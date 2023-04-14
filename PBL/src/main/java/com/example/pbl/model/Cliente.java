package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.List;

public class Cliente {
    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;

    /***
     * Dados para gerar um objeto cliente.
     * @param nome
     * @param endereco
     * @param telefone
     * @param email
     */
    public Cliente(String nome, String endereco, String telefone, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.id = 0;
    }

    /***
     * Get id
     * @return id
     */
    public int getId() {
        return id;
    }

    /***
     * Set Id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /***
     * Get nome
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /***
     * Set nome
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /***
     * Get endereço
     * @return endereço
     */
    public String getEndereco() {
        return endereco;
    }

    /***
     * Set endereço
     * @param endereco
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /***
     * Get telefone
     * @return telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /***
     * Set telefone
     * @param telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /***
     * Get email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /***
     * Set email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    // Fim Getters e Setters

    /***
     * Busca pelo DAO de todas as ordens de serviço relacionadas a um determinado cliente.
     * @return ordens de servço
     */
    public List<OrdemServico> getOrdensServico() {
        return DAO.getOrdemServico().buscarPorCliente(this.id);
    }

    /***
     * Compara dois clientes pelo id.
     * @param obj
     * @return booleano
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
