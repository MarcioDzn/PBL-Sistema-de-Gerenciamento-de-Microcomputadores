package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.Date;
import java.util.List;
import java.util.LinkedList;

public class OrdemServico {
    private int id;
    private Integer clienteId;
    private Integer tecnicoId;
    private String status;
    private List<Servico> servicos;
    private String descricao;
    private long criadoEm;
    private long finalizadoEm;
    private String metodoPagamento;
    private double preco;
    private double custo;

    public OrdemServico(Integer clienteId) {
        this.clienteId = clienteId;
        this.status = "Em andamento";
        this.servicos = new LinkedList<Servico>();

        this.criadoEm = new Date().getTime();
        this.finalizadoEm = 0;

        this.id = 0;
    }

    public double getPreco(){
        double precoTotal = 0;

        for (Servico servico : this.servicos){
            precoTotal += servico.getPreco();
        }

        return precoTotal;
    }

    public double getCusto() {
        double precoTotal = 0;

        for (Servico servico : this.servicos){
            precoTotal += servico.getCusto();
        }

        return precoTotal;
    }

    public void finalizar(){
        this.status = "Finalizado";
        this.finalizadoEm = new Date().getTime();
    }

    public void cancelar(){
        this.status = "Cancelado";
        this.criadoEm = 0;
    }

    public boolean isFinalizado(){
        if (this.status.equals("Finalizado"))
            return true;
        return false;
    }

    public boolean isCancelado(){
        if (this.status.equals("Cancelado"))
            return true;
        return false;
    }

    public boolean isEmAndamento(){
        if (this.status.equals("Em andamento"))
            return true;
        return false;
    }

    public long getTempoTotal(){
        return this.finalizadoEm - this.criadoEm;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return DAO.getCliente().buscarPorId(this.clienteId);
    }

    public Integer getClienteId() {
        return clienteId;
    }
    public Tecnico getTecnico() {
        return DAO.getTecnico().buscarPorId(this.tecnicoId);
    }

    public void setTecnicoId(Integer id) {
        this.tecnicoId = id;
    }

    public Integer getTecnicoId() {
        return tecnicoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void addServicos(Servico servico) {
        this.servicos.add(servico);
    }

    public void removerServico(int id, String tipo){
        int indiceServicoRemov = -1;

        for (int i = 0; i < this.servicos.size(); i++){
            if (tipo == "Montagem"){
                if (this.servicos.get(i) instanceof Montagem && this.servicos.get(i).getId() == id)
                    indiceServicoRemov = i;

            } else if (tipo == "Instalacao"){
                if (this.servicos.get(i) instanceof Instalacao && this.servicos.get(i).getId() == id)
                    indiceServicoRemov = i;

            } else if (tipo == "Limpeza"){
                if (this.servicos.get(i) instanceof Limpeza && this.servicos.get(i).getId() == id)
                    indiceServicoRemov = i;
            }
        }

        this.servicos.remove(indiceServicoRemov);
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    // Fim Getters e Setters

    @Override
    public boolean equals(Object obj){
        if (obj instanceof OrdemServico){
            OrdemServico i = (OrdemServico) obj;
            if (this.getId() == i.getId()){
                return true;
            }
        }
        return false;
    }
}
