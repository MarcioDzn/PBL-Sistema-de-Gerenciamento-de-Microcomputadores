package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.List;

/**
 * <p>
 * Classe referente à Limpeza.
 * </p>
 *
 * <p>
 * Uma Limpeza possui uma descrição do tipo de limpeza.
 * </p>
 *
 * <p>
 * Esta classe contém métodos para manipular seus atributos,
 * bem como para buscar todas as ordens de serviço da qual
 * esta Limpeza faz parte. Também contém um método para comparar
 * a si mesma com outro objeto pelo id.
 * </p>
 *
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class Limpeza extends Servico{
    String limpeza;

    /**
     * Dados para gerar um objeto limpeza.
     * @param preco Preço da limpeza
     * @param custo Custo da limpeza
     * @param limpeza Descrição do serviço de limpeza
     */
    public Limpeza(double preco, double custo, String limpeza) {
        super(preco, custo);
        this.limpeza = limpeza;
    }

    /**
     * Método que retorna uma lista de ordens de serviço associadas a este serviço de limpeza
     * @return lista de objetos do tipo OrdemServico
     */
    public List<OrdemServico> getOrdensServico(){
        return DAO.getOrdemServico().buscarPorServico(super.getId(), "Limpeza");
    }

    /**
     * Método que retorna a descrição deste serviço de limpeza
     * @return Descrição do serviço de limpeza
     */
    public String getLimpeza() {
        return limpeza;
    }

    /**
     * Método que define a descrição deste serviço de limpeza
     * @param limpeza Nova descrição do serviço de limpeza
     */
    public void setLimpeza(String limpeza) {
        this.limpeza = limpeza;
    }


    /**
     * Método para comparar o objeto de uma Limpeza com este objeto pelo id
     * @param obj Objeto do tipo Limpeza a ser comparado
     * @return Valor booleano true para id's iguais e false para id's diferentes
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Limpeza){
            Limpeza i = (Limpeza) obj;
            if (this.getId() == i.getId()){
                return true;
            }
        }
        return false;
    }
}
