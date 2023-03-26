package dao;

import dao.cliente.ClienteDAO;
import dao.cliente.ClienteLista;
import dao.instalacao.InstalacaoDAO;
import dao.instalacao.InstalacaoLista;
import dao.limpeza.LimpezaDAO;
import dao.limpeza.LimpezaLista;
import dao.montagem.MontagemDAO;
import dao.montagem.MontagemLista;
import dao.ordemServico.OrdemServicoDAO;
import dao.ordemServico.OrdemServicoLista;
import dao.outroComponente.OutroComponenteDAO;
import dao.outroComponente.OutroComponenteLista;
import dao.peca.PecaDAO;
import dao.peca.PecaLista;
import dao.tecnico.TecnicoDAO;
import dao.tecnico.TecnicoLista;


public class DAO {
    private static ClienteDAO clienteDAO;

    private static TecnicoDAO tecnicoDAO;

    private static OrdemServicoDAO ordemServicoDAO;

    private static InstalacaoDAO instalacaoDAO;

    private static LimpezaDAO limpezaDAO;

    private static MontagemDAO montagemDAO;

    private static OutroComponenteDAO outroComponenteDAO;

    private static PecaDAO pecaDAO;

    public static ClienteDAO getCliente(){
        if (clienteDAO == null){
            clienteDAO = new ClienteLista();
        }
        return clienteDAO;
    }

    public static InstalacaoDAO getInstalacao(){
        if (instalacaoDAO == null){
            instalacaoDAO = new InstalacaoLista();

        }
        return instalacaoDAO;
    }

    public static LimpezaDAO getLimpeza(){
        if (limpezaDAO == null){
            limpezaDAO = new LimpezaLista();

        }
        return limpezaDAO;
    }

    public static MontagemDAO getMontagem(){
        if (montagemDAO == null){
            montagemDAO = new MontagemLista();

        }
        return montagemDAO;
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

    public static OutroComponenteDAO getOutroComponente(){
        if (outroComponenteDAO == null){
            outroComponenteDAO = new OutroComponenteLista();
        }
        return outroComponenteDAO;
    }

    public static PecaDAO getPeca(){
        if (pecaDAO == null){
            pecaDAO = new PecaLista();
        }
        return pecaDAO;
    }


}
