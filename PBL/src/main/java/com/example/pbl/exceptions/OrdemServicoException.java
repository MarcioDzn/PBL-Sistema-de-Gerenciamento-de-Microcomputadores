package com.example.pbl.exceptions;
/***
 * Implementação da classe OrdemServicoException.
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class OrdemServicoException extends Exception{
    String status;

    /***
     * Exceção lançada quando uma ordem de serviço já foi finalizada ou cancelada.
     * @param status Status da ordem de serviço.
     */
    public OrdemServicoException(String status){
        super("Não é possível manipular uma ordem de serviço já "+status+"!");
        this.status = status;
    }

    /***
     * Retorna o status da ordem de serviço.
     * @return Status da ordem de serviço.
     */
    public String getStatus() {
        return status;
    }
}
