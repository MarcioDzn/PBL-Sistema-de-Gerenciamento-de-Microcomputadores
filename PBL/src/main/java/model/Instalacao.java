package model;

import java.util.List;

public class Instalacao extends Servico{
    private int id;
    private List<String> programas;
    private String sistemaOperacional;

    public Instalacao(double preco, double custo) {
        super(preco, custo);
    }

    public List<String> getProgramas() {
        return programas;
    }

    public void setProgramas(String programa) {
        this.programas.add(programa);
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProgramas(List<String> programas) {
        this.programas = programas;
    }
}
