package model;

import java.util.Date;
import java.util.List;
import java.util.LinkedList;

public class OrdemServico {
    private int id;
    private Cliente cliente;
    private Tecnico tecnico;
    private String status;
    private List<Servico> servicos;
    private String descricao;
    private long criadoEm;
    private long finalizadoEm;
    private Date tempo = new Date();

    public OrdemServico(Cliente cliente) {
        this.cliente = cliente;
        this.status = "Em andamento";
        this.servicos = new LinkedList<Servico>();

        this.criadoEm = tempo.getTime();
        this.finalizadoEm = 0;

        this.id = 0;

    }

    public double getPreco(){
        return 0;
    }

    public void finalizar(){
        this.finalizadoEm = tempo.getTime();
    }

    public void cancelar(){

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
        return cliente;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    // Fim Getters e Setters
}
