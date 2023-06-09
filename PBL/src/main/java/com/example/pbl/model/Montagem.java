package com.example.pbl.model;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;

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

    private String descricao;
    /**
     * Dados para gerar um objeto montagem.
     */
    public Montagem() {
        super(0, 0);
        this.pecas = new LinkedList<Integer>();
        this.outrosComponentes = new LinkedList<Integer>();
    }

    /**
     * Método que retorna a lista de peças desse serviço de montagem
     * @return Lista de peças
     */
    public List<Peca> getPecas() {
        List<Peca> lista = new LinkedList<Peca>();

        for (Integer id : this.pecas){
            lista.add(DAO.getPeca().buscarPorId(id));
        }

        return lista;
    }

    /**
     * Método que retorna a lista de "outros" componentes desse serviço de montagem
     * @return Lista de "outros" componentes
     */
    public List<OutroComponente> getOutrosComponentes() {
        List<OutroComponente> lista = new LinkedList<OutroComponente>();

        for (Integer id : this.outrosComponentes){
            lista.add(DAO.getOutroComponente().buscarPorId(id));
        }

        return lista;
    }

    /**
     * Método que adiciona um componente na lista de componentes. Também atualiza o preço e o custo totais.
     * Se o elemento for Peca, adiciona na lista de peças.
     * Se o elemento for OutroComponente, adiciona na lista de "outros" componentes.
     * @param quantidade Quantidade do componente
     * @param componente Novo componente
     */
    public void setComponente(Componente componente, Integer quantidade) {
        for(int i = 0; i < quantidade; i++){
            if(componente instanceof Peca)
                this.pecas.add(componente.getId());

            else if(componente instanceof OutroComponente)
                this.outrosComponentes.add(componente.getId());

            this.setPreco(super.getPreco() + componente.getPreco());
            this.setCusto(super.getCusto() + componente.getCusto());
        }
    }

    /**
     * Método que remove um componente de uma das listas de componente
     * @param id Id do componente a ser removido
     * @param quantidade Quantidade do componente a ser removido
     * @param tipo Tipo da classe do componente a ser removido
     */
    public void removerComponente(int id, int quantidade, String tipo) throws ObjetoNaoEncontradoException {
        for (int j = 0; j < quantidade; j++){
            int idRemovido = -1;

            // Remove o elemento do tipo Peca da lista de pecas
            if (tipo.equals("Peca")){
                for (int i = 0; i < this.pecas.size(); i++){
                    if (this.pecas.get(i) == id)
                        idRemovido = i;
                }

                if (idRemovido != -1)
                    this.pecas.remove(idRemovido);

                // Remove o elemento do tipo OutroComponente da lista de outrosComponentes
            } else if (tipo.equals("OutroComponente")) {
                for (int i = 0; i < this.outrosComponentes.size(); i++) {
                    if (this.outrosComponentes.get(i) == id)
                        idRemovido = i;
                }

                if (idRemovido != -1)
                    this.outrosComponentes.remove(idRemovido);
            }

            if (idRemovido == -1) {
                throw new ObjetoNaoEncontradoException("Montagem");
            }
        }
    }

    /**
     * Método que retorna uma lista de ordens de serviço associados a este serviço de montagem
     * @return Lista de objetos do tipo OrdemServico
     */
    public List<OrdemServico> getOrdensServico(){
        return DAO.getOrdemServico().buscarPorServico(super.getId(), "Montagem");
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
