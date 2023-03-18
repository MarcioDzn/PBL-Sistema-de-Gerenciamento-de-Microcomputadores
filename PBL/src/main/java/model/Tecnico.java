package model;

import java.util.LinkedList;
import java.util.List;

public class Tecnico {
    private int id;
    private String nome;
    private String email;

    private List<OrdemServico> ordensServico;


    public Tecnico(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.id = 0;

        this.ordensServico = new LinkedList<OrdemServico>();
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

    public List<OrdemServico> getOrdensServico() {
        return ordensServico;
    }

    public void addOrdensServico(OrdemServico ordemServico) {
        this.ordensServico.add(ordemServico);
    }

    // Fim Getters e Setters
}
