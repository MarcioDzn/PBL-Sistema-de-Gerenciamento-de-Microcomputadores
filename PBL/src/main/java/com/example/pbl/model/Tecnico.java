package com.example.pbl.model;

import com.example.pbl.dao.DAO;

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
        this.id = 0;

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

    public void addOrdemServicoAtual(Integer ordemServicoAtualId) {
        this.ordemServicoAtualId = ordemServicoAtualId;
    }

    public List<OrdemServico> getOrdensServico() {
        return DAO.getOrdemServico().buscarPorTecnico(this.id);
    }

    // Fim Getters e Setters
}
