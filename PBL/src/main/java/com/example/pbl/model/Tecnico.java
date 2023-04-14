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


    public Tecnico(String nome, String email) {
        this.nome = nome;
        this.email = email;

        this.ordemServicoAtualId = -1;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OrdemServico getOrdemServicoAtual() {
        return DAO.getOrdemServico().buscarPorId(this.ordemServicoAtualId);
    }

    public void addOrdemServicoAtual(Integer novaOrdemServicoAtualId) throws OrdemServicoAtualException {
        if (this.ordemServicoAtualId == -1){
            this.ordemServicoAtualId = novaOrdemServicoAtualId;
        }
        
        else if (novaOrdemServicoAtualId == -1 && this.getOrdemServicoAtual().isFinalizado()){
            this.ordemServicoAtualId = -1;
        }

        else{
            throw new OrdemServicoAtualException(this.ordemServicoAtualId);
        }
    }

    public List<OrdemServico> getOrdensServico() {
        return DAO.getOrdemServico().buscarPorTecnico(this.id);
    }

    // Fim Getters e Setters

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
