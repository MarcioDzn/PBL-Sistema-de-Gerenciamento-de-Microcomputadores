package com.example.pbl.model;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.QuantidadeException;

import java.util.List;

/**
 * <p>
 * Classe referente à Peca.
 * </p>
 *
 * <p>
 * Uma Peca possui nome, fabricante e quantidade.
 * </p>
 *
 * <p>
 * Esta classe contém métodos para manipular seus atributos,
 * bem como para buscar todas as ordens de serviço da qual esta Peca
 * faz parte. Também contém um método para comparar
 *  * a si mesma com outro objeto pelo id.
 * </p>
 *
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class Peca extends Componente {
    private String nome;
    private String fabricante;
    private int quantidade;

    /**
     * Dados para gerar um objeto do tipo Peça.
     * @param quantidade Quantidade de peças
     * @param custo Custo da peça
     * @param preco Preço da peça
     * @param nome Nome da peça
     * @param fabricante Fabricante da peça
     */
    public Peca(int quantidade, double custo, double preco, String nome, String fabricante) {
        super(custo, preco);
        this.nome = nome;
        this.fabricante = fabricante;
        this.quantidade = quantidade;
    }

    /**
     * Método para retornar o nome da peça
     * @return Nome da peça
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método para definir o nome da peça
     * @param nome Novo nome da peça
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método para definir o fabricante da peça
     * @param fabricante Novo fabricante da peça
     */
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    /**
     * Método para retornar o fabricante da peça
     * @return Fabricante da peça
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * Método para retornar a quantidade da peça
     * @return Quantidade da peça
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Método para definir a quantidade da peça
     * @param quantidade Quantidade da peça
     * @throws QuantidadeException Se a quantidade fornecida for negativa
     */
    public void setQuantidade(int quantidade) throws QuantidadeException {
        if (quantidade >= 0)
            this.quantidade = quantidade;

        else{
            throw new QuantidadeException(quantidade);
        }
    }

    /**
     * Método que retorna uma lista de Montagens referentes a esta peça
     * @return Lista de objetos do tipo Montagem
     */
    public List<Montagem> getMontagens() {
        return DAO.getMontagem().buscarPorComponente(super.getId(), "Peca");
    }

    /**
     * Método para comparar o objeto de uma Peca com este objeto pelo id
     * @param obj Objeto do tipo Peca a ser comparado
     * @return Valor booleano true para id's iguais e false para id's diferentes
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Peca){
            Peca i = (Peca) obj;
            if (this.getId() == i.getId()){
                return true;
            }
        }
        return false;
    }
}
