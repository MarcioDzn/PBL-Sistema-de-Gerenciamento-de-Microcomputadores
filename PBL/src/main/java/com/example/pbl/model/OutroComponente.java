package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.List;

/**
 * <p>
 * Classe referente à OutroComponente.
 * </p>
 *
 * <p>
 * Um OutroComponente possui descrição.
 * </p>
 *
 * <p>
 * Esta classe contém métodos para manipular seus atributos,
 * bem como para buscar todas as ordens de serviço da qual esta Peca
 * faz parte. Também contém um método para comparar
 * a si mesma com outro objeto pelo id.
 * </p>
 *
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class OutroComponente extends Componente{
    private String descricao;

    /**
     * Dados para gerar um objeto outroComponente.
     * @param custo Custo do "outro" componente
     * @param preco Preço do "outro" componente
     * @param descricao Descrição "outro" do componente
     */
    public OutroComponente(double custo, double preco, String descricao) {
        super(custo, preco);
        this.descricao = descricao;
    }

    /**
     * Método para retornar a descrição do "outro" componente
     * @return Descrição do componente
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Método para definir a descrição do "outro" componente
     * @param descricao Nova descrição do "outro" componente
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Método para retornar uma lista de montagens associadas a este "outro" componente
     * @return Lista de objetos do tipo Montagem
     */
    public List<Montagem> getMontagens() {
        return DAO.getMontagem().buscarPorComponente(super.getId(), "OutroComponente");
    }

    /**
     * Método para comparar o objeto de um OutroComponente com este objeto pelo id
     * @param obj Objeto do tipo OutroComponente a ser comparado
     * @return Valor booleano true para id's iguais e false para id's diferentes
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof OutroComponente){
            OutroComponente i = (OutroComponente) obj;
            if (this.getId() == i.getId()){
                return true;
            }
        }
        return false;
    }

}
