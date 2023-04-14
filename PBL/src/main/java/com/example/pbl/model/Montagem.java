package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.LinkedList;
import java.util.List;

public class Montagem extends Servico{
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

    public void removerComponente(int id, String tipo){
        int indiceCompRemov = -1;

        for (int i = 0; i < this.componentesUsados.size(); i++){
            if (tipo == "OutroComponente"){
                if (this.componentesUsados.get(i) instanceof OutroComponente && this.componentesUsados.get(i).getId() == id)
                    indiceCompRemov = i;

            } else if (tipo == "Peca"){
                if (this.componentesUsados.get(i) instanceof Peca && this.componentesUsados.get(i).getId() == id)
                    indiceCompRemov = i;
            }
        }

        this.componentesUsados.remove(indiceCompRemov);
    }

    public List<OrdemServico> getOrdensServico(){
        return DAO.getOrdemServico().buscarPorServico(super.getId(), "Montagem");
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Montagem){
            Montagem i = (Montagem) obj;
            if (this.getId() == i.getId()){
                return true;
            }
        }
        return false;
    }
}
