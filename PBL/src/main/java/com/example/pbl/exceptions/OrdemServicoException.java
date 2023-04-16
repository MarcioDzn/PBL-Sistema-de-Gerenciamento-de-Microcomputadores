package com.example.pbl.exceptions;

public class OrdemServicoException extends Exception{
    String status;
    public OrdemServicoException(String status){
        super("Não é possível manipular uma ordem de serviço já "+status+"!");
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
