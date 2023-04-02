package com.example.pbl.model;

import java.util.LinkedList;
import java.util.List;

public class Montagem extends Servico{
    private int id;
    private List<Componente> componentesUsados;

    public Montagem() {
        super(0, 0);
        this.componentesUsados = new LinkedList<Componente>();
    }

    public List<Componente> getComponentes() {
        return componentesUsados;
    }

    public void setComponente(Componente componente) {
        this.componentesUsados.add(componente);

        this.setPreco(super.getPreco() + componente.getPreco());
        this.setCusto(super.getCusto() + componente.getCusto());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
