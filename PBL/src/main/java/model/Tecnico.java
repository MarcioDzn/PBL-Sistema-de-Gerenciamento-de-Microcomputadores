package model;

import dao.DAO;

import java.util.LinkedList;
import java.util.List;

public class Tecnico {
    private int id;
    private String nome;
    private String email;

    private List<Integer> ordensServicoId;


    public Tecnico(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.id = 0;

        this.ordensServicoId = new LinkedList<Integer>();
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
