package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * Classe referente à Montagem.
 * </p>
 *
 * <p>
 * Uma Montagem possui uma lista de componentes usados.
 * </p>
 *
 * <p>
 * Esta classe contém métodos para manipular seus atributos, para adicionar e
 * remover componentes em uma lista, bem como para buscar todas as ordens de
 * serviço da qual esta Montagem faz parte. Também contém um método para comparar
 * a si mesma com outro objeto pelo id.
 * </p>
 *
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class Montagem extends Servico{
    private List<Integer> pecas;
    private List<Integer> outrosComponentes;

    /**
     * Dados para gerar um objeto montagem.
     */
    public Montagem() {
        super(0, 0);
        this.pecas = new LinkedList<Integer>();
        this.outrosComponentes = new LinkedList<Integer>();
    }

    /**
     * Método que retorna uma lista de componentes referentes a esta montagem
     * @return Lista de componentes
     */
    public List<Peca> getPecas() {
        List<Peca> lista = new LinkedList<Peca>();

        for (Integer id : this.pecas){
            lista.add(DAO.getPeca().buscarPorId(id));
        }

        return lista;
    }
    public List<OutroComponente> getOutrosComponentes() {
        List<OutroComponente> lista = new LinkedList<OutroComponente>();

        for (Integer id : this.pecas){
            lista.add(DAO.getOutroComponente().buscarPorId(id));
        }

        return lista;
    }

    /**
     * Método que adiciona um componente na lista de componentes. Também atualiza o preço e o custo.
     * @param componente Novo componente
     */
    public void setComponente(Componente componente) {
        if(componente instanceof Peca)
            this.pecas.add(componente.getId());

        else if(componente instanceof OutroComponente)
            this.outrosComponentes.add(componente.getId());

        this.setPreco(super.getPreco() + componente.getPreco());
        this.setCusto(super.getCusto() + componente.getCusto());
    }

    /**
     * Método que remove um componente da lista de componentes
     * @param id Id do componente a ser removido
     * @param tipo Tipo da classe do componente a ser removido
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

    /**
     * Método que retorna uma lista de ordens de serviço associados a este serviço de montagem
     * @return Lista de objetos do tipo OrdemServico
     */
    public List<OrdemServico> getOrdensServico(){
        return DAO.getOrdemServico().buscarPorServico(super.getId(), "Montagem");
    }

    /**
     * Método para comparar o objeto de uma Montagem com este objeto pelo id
     * @param obj Objeto do tipo Montagem a ser comparado
     * @return Valor booleano true para id's iguais e false para id's diferentes
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
