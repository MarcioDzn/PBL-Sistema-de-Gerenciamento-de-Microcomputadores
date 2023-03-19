package model;

import java.util.LinkedList;
import java.util.List;

public class Montagem extends Servico{
    private List<Componente> componentesUsados;

    public Montagem(double preco, double custo) {
        super(preco, custo);
        this.componentesUsados = new LinkedList<Componente>();
    }

    public List<Componente> getComponentesUsados() {
        return componentesUsados;
    }

    public void setComponentesUsados(Componente componente) {
        this.componentesUsados.add(componente);
    }
}
