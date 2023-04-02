package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private List<Integer> ordensServicoId;

    public Cliente(String nome, String endereco, String telefone, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.id = 0;

        this.ordensServicoId = new LinkedList<Integer>();
    }

    // Fim Getters e Setters
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<OrdemServico> getOrdensServico() {
        List<OrdemServico> lista = new LinkedList<OrdemServico>();

        for (Integer id : this.ordensServicoId){
            lista.add(DAO.getOrdemServico().buscarPorId(id));
        }

        return lista;
    }

    public void addOrdensServico(Integer id) {
        this.ordensServicoId.add(id);
    }
    // Fim Getters e Setters
}
