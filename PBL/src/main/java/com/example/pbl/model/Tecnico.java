package com.example.pbl.model;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.OrdemServicoAtualException;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Classe referente ao Tecnico.
 * </p>
 *
 * <p>
 * Um Tecnico possui id, nome, email e id da ordem de serviço atual na qual está trabalhando.
 * </p>
 *
 * <p>
 * Esta classe contém métodos para manipular seus atributos, para adicionar ou remover ordens de
 * serviço associadas, bem como para buscar todas as ordens de serviço da qual este Tecnico
 * já trabalhou. Também contém um método para comparar
 *  * a si mesma com outro objeto pelo id.
 * </p>
 *
 * @author Márcio Roberto, Amanda Lima Bezerra
 */

public class Tecnico implements Serializable {
    private int id;
    private String nome;
    private String email;
    private Integer ordemServicoAtualId;

    /**
     * Dados para gerar um objeto do tipo Tecnico.
     * @param nome Nome do tecnico
     * @param email Email do tecnico
     */
    public Tecnico(String nome, String email) {
        this.nome = nome;
        this.email = email;

        this.ordemServicoAtualId = -1;
    }

    // Getters e Setters

    /**
     * Método para retornar o id do tecnico
     * @return Id do tecnico
     */
    public int getId() {
        return id;
    }

    /**
     * Método para definir o id do tecnico
     * @param id Novo id do tecnico
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método para retornar o nome do tecnico
     * @return Nome do tecnico
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método para definir o nome do tecnico
     * @param nome Novo nome do tecnico
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método para retornar o email do tecnico
     * @return Email do tecnico
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método para definir o email do tecnico
     * @param email Novo email do tecnico
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método para retornar o objeto da ordem de serviço atual do tecnico
     * @return Ordem de serviço atual do tecnico
     */
    public OrdemServico getOrdemServicoAtual() {
        return DAO.getOrdemServico().buscarPorId(this.ordemServicoAtualId);
    }

    /**
     * Método para adicionar o id de uma ordem de serviço atual do tecnico
     * @param novaOrdemServicoAtualId Id da ordem de servico atual. Valor -1 corresponde a nenhuma ordem de serviço.
     * @throws OrdemServicoAtualException Se ainda houver uma ordem de serviço atual não finalizada associada ao tecnico
     */
    public void addOrdemServicoAtual(Integer novaOrdemServicoAtualId) throws OrdemServicoAtualException {

        if (this.ordemServicoAtualId == -1) {
            this.ordemServicoAtualId = novaOrdemServicoAtualId;
        } else if (novaOrdemServicoAtualId == -1 && (this.getOrdemServicoAtual().isFinalizado() || this.getOrdemServicoAtual().isCancelado())) {
            this.ordemServicoAtualId = -1;
        }
        else{
            throw new OrdemServicoAtualException(this.ordemServicoAtualId);
        }
    }

    /**
     * Método que retorna uma lista de ordens de servico associadas a este tecnico
     * @return Lista de objetos do tipo OrdemServico
     */
    public List<OrdemServico> getOrdensServico() {
        return DAO.getOrdemServico().buscarPorTecnico(this.id);
    }

    // Fim Getters e Setters

    /**
     * Método para comparar o objeto de um Tecnico com este objeto pelo id
     * @param obj Objeto do tipo Tecnico a ser comparado
     * @return Valor booleano true para id's iguais e false para id's diferentes
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Tecnico){
            Tecnico i = (Tecnico) obj;
            if (this.getId() == i.getId()){
                return true;
            }
        }
        return false;
    }
}
