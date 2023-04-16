package com.example.pbl.model;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;

import java.util.Date;
import java.util.List;
import java.util.LinkedList;

/**
 * <p>
 * Classe referente à OrdemServico.
 * </p>
 *
 * <p>
 * Uma OrdemServico possui id, clienteId, tecnicoId, status, lista de serviços, descrição,
 * data de criação, data de finalização, método de pagamento e preço.
 * </p>
 *
 * <p>
 * Esta classe contém métodos para manipular seus atributos, modificar seu status,
 * verificar seu status e verificar seu tempo de existência. Também contém um método para comparar
 * a si mesma com outro objeto pelo id.
 * </p>
 *
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class OrdemServico {
    private int id;
    private Integer clienteId;
    private Integer tecnicoId;
    private String status;
    private List<Integer> montagens;
    private List<Integer> limpezas;
    private List<Integer> instalacoes;
    private String descricao;
    private long criadoEm;
    private long finalizadoEm;
    private String metodoPagamento;
    private double preco;

    /**
     * Dados para gerar um objeto ordem de serviço.
     * @param clienteId Id do cliente associado a esta ordem de serviço
     */
    public OrdemServico(Integer clienteId) {
        this.clienteId = clienteId;
        this.status = "Em andamento";

        this.montagens = new LinkedList<Integer>();
        this.limpezas = new LinkedList<Integer>();
        this.instalacoes = new LinkedList<Integer>();

        this.criadoEm = new Date().getTime();
        this.finalizadoEm = 0;

        this.id = 0;
    }

    /**
     * Método que retorna o preço total dos serviços dessa ordem de serviço
     * @return Preço total dos serviços
     */
    public double getPreco(){
        double precoTotal = 0;

        for (Integer id : this.montagens){
            precoTotal += DAO.getMontagem().buscarPorId(id).getPreco();
        }

        for (Integer id : this.limpezas){
            precoTotal += DAO.getLimpeza().buscarPorId(id).getPreco();
        }

        for (Integer id : this.instalacoes){
            precoTotal += DAO.getInstalacao().buscarPorId(id).getPreco();
        }

        return precoTotal;
    }

    /**
     * Método que define o status como "Finalizado"
     */
    public void finalizar(){
        this.status = "Finalizado";
        this.finalizadoEm = new Date().getTime();
    }

    /**
     * Método que define o status como "Cancelado"F
     */
    public void cancelar(){
        this.status = "Cancelado";
        this.criadoEm = 0;
    }

    /**
     * Método que verifica se o status for "Finalizado"
     * @return true se o status for "Finalizado", false caso contrário
     */
    public boolean isFinalizado(){
        if (this.status.equals("Finalizado"))
            return true;
        return false;
    }

    /**
     * Método que verifica se o status for "Cancelado"
     * @return true se o status for "Cancelado", false caso contrário
     */
    public boolean isCancelado(){
        if (this.status.equals("Cancelado"))
            return true;
        return false;
    }

    /**
     * Método que verifica se o status for "Em andamento"
     * @return true se o status for "Em andamento", false caso contrário
     */
    public boolean isEmAndamento(){
        if (this.status.equals("Em andamento"))
            return true;
        return false;
    }

    /**
     * Método que retorna o tempo desde a criação do objeto até o status ser "finalizado" ou "cancelado"
     * @return Tempo até a ordem de serviço ser finalizada ou cancelada
     */
    public long getTempoTotal(){
        return this.finalizadoEm - this.criadoEm;
    }

    /**
     * Método que retorna o id da ordem de serviço
     * @return Id da ordem de serviço
     */
    // Getters e Setters
    public int getId() {
        return id;
    }

    /**
     * Método que define o id da ordem de serviço
     * @param id Novo id da ordem de serviço
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método que retorna o cliente referente a esta ordem de serviço
     * @return Objeto de cliente referente à esta ordem de serviço
     */
    public Cliente getCliente() {
        return DAO.getCliente().buscarPorId(this.clienteId);
    }

     /**
     * Método que retorna o id do cliente referente a esta ordem de serviço
     * @return Id do cliente referente à esta ordem de serviço
     */
    public Integer getClienteId() {
        return clienteId;
    }

    /**
     * Método que retorna o tecnico referente a esta ordem de serviço
     * @return Objeto do tecnico referente a esta ordem de serviço
     */
    public Tecnico getTecnico() {
        return DAO.getTecnico().buscarPorId(this.tecnicoId);
    }

    /**
     * Método que define o id do tecnico referente a esta ordem de serviço
     * @param id Novo id do tecnico
     */
    public void setTecnicoId(Integer id) {
        this.tecnicoId = id;
    }

    /**
     * Metodo que retorna o id do tecnico referente a esta ordem de sevriço
     * @return Id do tecnico referente a esta ordem de serviço
     */
    public Integer getTecnicoId() {
        return tecnicoId;
    }

    /**
     * Método que retorna o status da ordem de serviço
     * @return status da ordem de serviço
     */
    public String getStatus() {
        return status;
    }

    /**
     * Método que define o status da ordem de serviço
     * @param status Status da ordem de serviço
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Método que retorna a lista de serviços de montagem dessa ordem de serviço
     * @return Lista de serviços de montagens
     */
    public List<Montagem> getMontagens() {
        List<Montagem> lista = new LinkedList<Montagem>();

        for (Integer id : montagens){
            lista.add(DAO.getMontagem().buscarPorId(id));
        }

        return lista;
    }

    /**
     * Método que retorna a lista de serviços de limpeza dessa ordem de serviço
     * @return Lista de serviços de limpeza
     */
    public List<Limpeza> getLimpezas() {
        List<Limpeza> lista = new LinkedList<Limpeza>();

        for (Integer id : limpezas){
            lista.add(DAO.getLimpeza().buscarPorId(id));
        }

        return lista;
    }

    /**
     * Método que retorna a lista de serviços de instalação dessa ordem de serviço
     * @return Lista de serviços de instalação
     */
    public List<Instalacao> getInstalacoes() {
        List<Instalacao> lista = new LinkedList<Instalacao>();

        for (Integer id : instalacoes){
            lista.add(DAO.getInstalacao().buscarPorId(id));
        }

        return lista;
    }

    /**
     * Método que adiciona um id de um serviço em uma das listas de serviços.
     * Se o elemento for Montagem, adiciona na lista de montagens.
     * Se o elemento for Limpeza, adiciona na lista de montagens.
     * Se o elemento for Instalação, adiciona na lista de instalações.
     * @param servico Serviço a ser armazenado
     * @param quantidade Quantidade do serviço
     */
    public void addServicos(Servico servico, int quantidade) {
        for(int i = 0; i < quantidade; i++) {
            if (servico instanceof Montagem) {
                this.montagens.add(servico.getId());

            } else if (servico instanceof Limpeza) {
                this.limpezas.add(servico.getId());

            } else if (servico instanceof Instalacao) {
                this.instalacoes.add(servico.getId());
            }
        }
    }

    /**
     * Método que remove um serviço de uma das listas de serviço
     * @param id Id do serviço a ser removido
     * @param quantidade Quantidade do serviço a ser removida
     * @param tipo Tipo da classe do serviço a ser removido
     */
    public void removerServico(int id, int quantidade, String tipo) throws ObjetoNaoEncontradoException {
        for (int j = 0; j < quantidade; j++){
            int idRemovido = -1;

            // Remove o elemento do tipo Montagem da lista de montagens
            if (tipo.equals("Montagem")){
                for (int i = 0; i < this.montagens.size(); i++){
                    if (this.montagens.get(i) == id)
                        idRemovido = i;
                }

                if (idRemovido != -1)
                    this.montagens.remove(idRemovido);

                // Remove o elemento do tipo Limpeza da lista de limpezas
            } else if (tipo.equals("Limpeza")){
                for (int i = 0; i < this.limpezas.size(); i++){
                    if (this.limpezas.get(i) == id)
                        idRemovido = i;
                }

                if (idRemovido != -1)
                    this.limpezas.remove(idRemovido);

                // Remove o elemento do tipo Instalacao da lista de instalações
            } else if (tipo.equals("Instalacao")){
                for (int i = 0; i < this.instalacoes.size(); i++){
                    if (this.instalacoes.get(i) == id)
                        idRemovido = i;
                }

                if (idRemovido != -1)
                    this.instalacoes.remove(idRemovido);
            }

            if (idRemovido == -1){
                throw new ObjetoNaoEncontradoException("Serviço");
            }
        }
    }

    /**
     * Método que retorna o método de pagamento
     * @return Metodo de pagamento
     */
    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    /**
     * Método que define o método de pagamento
     * @param metodoPagamento Método de pagamento
     */
    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    /**
     * Método que retorna a descrição desta ordem de serviço
     * @return Descrição da ordem de serviço
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Método que define a descrição desta ordem de serviço
     * @param descricao Descrição da ordem de serviço
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    // Fim Getters e Setters

    /**
     * Método para comparar o objeto de uma OrdemServiço com este objeto pelo id
     * @param obj Objeto do tipo OrdemServiço a ser comparado
     * @return Valor booleano true para id's iguais e false para id's diferentes
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof OrdemServico){
            OrdemServico i = (OrdemServico) obj;
            if (this.getId() == i.getId()){
                return true;
            }
        }
        return false;
    }
}
