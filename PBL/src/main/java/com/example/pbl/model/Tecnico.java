package com.example.pbl.model;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.OrdemServicoAtualException;

import java.util.LinkedList;
import java.util.List;

public class Tecnico {
    private int id;
    private String nome;
    private String email;
    private Integer ordemServicoAtualId;

    /***
     * Dados para gerar um objeto tecnico.
     * @param nome
     * @param email
     */
    public Tecnico(String nome, String email) {
        this.nome = nome;
        this.email = email;

        this.ordemServicoAtualId = -1;
    }

    // Getters e Setters

    /***
     * Get id
     * @return id
     */
    public int getId() {
        return id;
    }

    /***
     * Set id
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

    /***
     * Get ordem de serviço
     * @return ordem de serviço
     */
    public OrdemServico getOrdemServicoAtual() {
        return DAO.getOrdemServico().buscarPorId(this.ordemServicoAtualId);
    }

    /***
     * Busca de ordem de serviço atual.
     * @param novaOrdemServicoAtualId
     * @throws OrdemServicoAtualException
     */
    public void addOrdemServicoAtual(Integer novaOrdemServicoAtualId) throws OrdemServicoAtualException {
        if (this.ordemServicoAtualId == -1) {
            this.ordemServicoAtualId = novaOrdemServicoAtualId;
        }
        else if (novaOrdemServicoAtualId == -1 && this.getOrdemServicoAtual().isFinalizado()) {
            this.ordemServicoAtualId = -1;
        }
        else{
            throw new OrdemServicoAtualException(this.ordemServicoAtualId);
        }
    }

    /***
     * Busca pelo DAO de todas as ordens de serviços do tecnico.
     * @return ordem de serviço
     */
    public List<OrdemServico> getOrdensServico() {
        return DAO.getOrdemServico().buscarPorTecnico(this.id);
    }

    // Fim Getters e Setters

    /***
     * Comparar tecnicos
     * @param obj
     * @return booleano
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Tecnico){
            Tecnico i = (Tecnico) obj;
            if (this.getId() == i.getId()){
                return true;
            }
        }
        return false;
    }
}
