package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.LinkedList;
import java.util.List;

public class Montagem extends Servico{
    private List<Componente> componentesUsados;

    /***
     * Dados para gerar um objeto montagem.
     */
    public Montagem() {
        super(0, 0);
        this.componentesUsados = new LinkedList<Componente>();
    }

    /***
     * Get lista de componentes usados
     * @return lista de componentes usados
     */
    public List<Componente> getComponentes() {
        return componentesUsados;
    }

    /***
     * Set componente
     * @param componente
     */
    public void setComponente(Componente componente) {
        this.componentesUsados.add(componente);

        this.setPreco(super.getPreco() + componente.getPreco());
        this.setCusto(super.getCusto() + componente.getCusto());
    }

    /***
     * Remover componentes da lista de componentes usados.
     * @param id
     * @param tipo
     */
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

    /***
     * Busca pelo DAO de todos os serviços de montagem.
     * @return lista de serviços do tipo montagem
     */
    public List<OrdemServico> getOrdensServico(){
        return DAO.getOrdemServico().buscarPorServico(super.getId(), "Montagem");
    }

    /***
     * Comparar dois objetos do tipo montagem
     * @param obj
     * @return booleano
     */
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
