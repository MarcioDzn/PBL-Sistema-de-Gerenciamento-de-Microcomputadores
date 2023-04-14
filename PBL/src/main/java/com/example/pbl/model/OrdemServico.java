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

    /***
     * Dados para gerar um objeto ordem de serviço.
     * @param clienteId
     */
    public OrdemServico(Integer clienteId) {
        this.clienteId = clienteId;
        this.status = "Em andamento";
        this.servicos = new LinkedList<Servico>();

        this.criadoEm = new Date().getTime();
        this.finalizadoEm = 0;

        this.id = 0;
    }

    /***
     * Get preço da ordem de serviço
     * @return preço total
     */
    public double getPreco(){
        double precoTotal = 0;

        for (Servico servico : this.servicos){
            precoTotal += servico.getPreco();
        }

        return precoTotal;
    }

    /***
     * Get custo total da ordem de serviço
     * @return custo total
     */
    public double getCusto() {
        double precoTotal = 0;

        for (Servico servico : this.servicos){
            precoTotal += servico.getCusto();
        }

        return precoTotal;
    }

    /***
     * Colocar ordem de serviço como finalizada
     */
    public void finalizar(){
        this.status = "Finalizado";
        this.finalizadoEm = new Date().getTime();
    }

    /***
     * Colocar ordem de serviço como cancelada
     */
    public void cancelar(){
        this.status = "Cancelado";
        this.criadoEm = 0;
    }

    /***
     * Verificar se a ordem de serviço está finalizada
     * @return booleano
     */
    public boolean isFinalizado(){
        if (this.status.equals("Finalizado"))
            return true;
        return false;
    }

    /***
     * Verificar se a ordem de serviço está cancelada
     * @return booleano
     */
    public boolean isCancelado(){
        if (this.status.equals("Cancelado"))
            return true;
        return false;
    }

    /***
     *Verificar se a ordem de serviço está na fila para atendimento
     * @return booleano
     */
    public boolean isEmAndamento(){
        if (this.status.equals("Em andamento"))
            return true;
        return false;
    }

    /***
     * Get tempo de duração do servço
     * @return tempode de duração do serviço
     */
    public long getTempoTotal(){
        return this.finalizadoEm - this.criadoEm;
    }

    /***
     * Get id
     * @return id
     */
    // Getters e Setters
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
     * Get cliente
     * @return cliente
     */
    public Cliente getCliente() {
        return DAO.getCliente().buscarPorId(this.clienteId);
    }

    /***
     * Get cliente id
     * @return cliente id
     */
    public Integer getClienteId() {
        return clienteId;
    }

    /***
     * Get tecnico
     * @return tecnico
     */
    public Tecnico getTecnico() {
        return DAO.getTecnico().buscarPorId(this.tecnicoId);
    }

    /***
     * Set tecnico id
     * @param id
     */
    public void setTecnicoId(Integer id) {
        this.tecnicoId = id;
    }

    /***
     * Get tecnico id
     * @return tecnico id
     */
    public Integer getTecnicoId() {
        return tecnicoId;
    }

    /***
     * Get status da ordem de serviço
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /***
     * Set status da ordem de serviço
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /***
     * Get lista de serviços
     * @return lista de serviços
     */
    public List<Servico> getServicos() {
        return servicos;
    }

    /***
     * Adcionar um serviço a ordem de serviço
     * @param servico
     */
    public void addServicos(Servico servico) {
        this.servicos.add(servico);
    }

    /***
     * Remover serviços da ordem de serviços.
     * @param id
     * @param tipo
     */
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

    /***
     * Get metodo de pagamento
     * @return metodo de pagamento
     */
    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    /***
     * Set metodo de pagamento
     * @param metodoPagamento
     */
    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    /***
     * Get descrição
     * @return descrição
     */
    public String getDescricao() {
        return descricao;
    }

    /***
     * Set descrição
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    // Fim Getters e Setters

    /***
     * Compara duas ordens de serviço
     * @param obj
     * @return booleano
     */
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
