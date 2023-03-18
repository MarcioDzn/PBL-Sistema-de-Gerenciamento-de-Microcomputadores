package model;

import java.util.LinkedList;
import java.util.List;

public class Servico {
    private double preco;
    private double custo;
    private List<OrdemServico> ordensServico;

    public Servico() {
        this.ordensServico = new LinkedList<OrdemServico>();
    }

    public double getPreco() {
        return preco;
    }

    public double getCusto() {
        return custo;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public List<OrdemServico> getOrdensServico() {
        return ordensServico;
    }

    public void setOrdensServico(OrdemServico ordemServico) {
        this.ordensServico.add(ordemServico);
    }
}
