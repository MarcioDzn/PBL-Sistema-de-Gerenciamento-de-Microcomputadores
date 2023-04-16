package com.example.pbl.model;

import com.example.pbl.dao.DAO;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * Classe referente à Instalacao.
 * </p>
 *
 * <p>
 * Uma Instalacao possui uma descrição do tipo de instalação.
 * </p>
 *
 * <p>
 * Esta classe contém métodos para manipular seus atributos,
 * bem como para buscar todas as ordens de serviço da qual
 * esta Instalacao faz parte. Também contém um método para comparar
 * a si mesma com outro objeto pelo id.
 * </p>
 *
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class Instalacao extends Servico{
    private String instalacao; // Instalação de programas ou formatação

    /**
     * Dados para gerar um objeto Instalação.
     * @param preco Preço da instalação
     * @param custo custo da instalação
     * @param instalacao Descrição do serviço de instalação
     */
    public Instalacao(double preco, double custo, String instalacao) {
        super(preco, custo);
        this.instalacao = instalacao;
    }

    /**
     * Método que retorna a descrição da instalação
     * @return Descrição da instalação
     */
    public String getInstalacao() {
        return instalacao;
    }

    /**
     * Método que define a descrição da instalação
     * @param tipoInstalacao Nova descrição da instalação
     */
    public void setInstalacao(String tipoInstalacao) {
        this.instalacao = tipoInstalacao;
    }

    /**
     * Método que retorna uma lista de ordens de serviço associadas a este serviço de instalação
     * @return Lista de objetos do tipo OrdemServico
     */
    public List<OrdemServico> getOrdensServico(){
        return DAO.getOrdemServico().buscarPorServico(super.getId(), "Instalacao");
    }

    /**
     * Método para comparar o objeto de uma Instalacao com este objeto pelo id
     * @param obj Objeto do tipo Instalacao a ser comparado
     * @return Valor booleano true para id's iguais e false para id's diferentes
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Instalacao){
            Instalacao i = (Instalacao) obj;
            if (this.getId() == i.getId()){
                return true;
            }
        }
        return false;
    }
}
