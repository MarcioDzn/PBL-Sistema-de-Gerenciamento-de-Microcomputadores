package dao;

import dao.cliente.ClienteDAO;
import dao.cliente.ClienteLista;
import dao.ordemServico.OrdemServicoDAO;
import dao.ordemServico.OrdemServicoLista;
import dao.tecnico.TecnicoDAO;
import dao.tecnico.TecnicoLista;

public class DAO {
    private static ClienteDAO clienteDAO;

    private static TecnicoDAO tecnicoDAO;

    private static OrdemServicoDAO ordemServicoDAO;

    public static ClienteDAO getCliente(){
        if (clienteDAO == null){
            clienteDAO = new ClienteLista();
        }
        return clienteDAO;
    }

    public static TecnicoDAO getTecnico(){
        if (tecnicoDAO == null){
            tecnicoDAO = new TecnicoLista();
        }
        return tecnicoDAO;
    }

    public static OrdemServicoDAO getOrdemServico(){
        if (ordemServicoDAO == null){
            ordemServicoDAO = new OrdemServicoLista();
        }
        return ordemServicoDAO;
    }
}
