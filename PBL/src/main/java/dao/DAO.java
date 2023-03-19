package dao;

import dao.cliente.ClienteDAO;
import dao.cliente.ClienteLista;

public class DAO {
    private static ClienteDAO clienteDAO;

    public static ClienteDAO getCliente(){
        if (clienteDAO == null){
            clienteDAO = new ClienteLista();
        }
        return clienteDAO;
    }
}
