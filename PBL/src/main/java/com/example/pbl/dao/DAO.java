package com.example.pbl.dao;

import com.example.pbl.dao.cliente.ClienteArquivo;
import com.example.pbl.dao.cliente.ClienteDAO;
import com.example.pbl.dao.cliente.ClienteLista;
import com.example.pbl.dao.instalacao.InstalacaoDAO;
import com.example.pbl.dao.instalacao.InstalacaoLista;
import com.example.pbl.dao.limpeza.LimpezaDAO;
import com.example.pbl.dao.limpeza.LimpezaLista;
import com.example.pbl.dao.montagem.MontagemDAO;
import com.example.pbl.dao.montagem.MontagemLista;
import com.example.pbl.dao.ordemServico.OrdemServicoDAO;
import com.example.pbl.dao.ordemServico.OrdemServicoLista;
import com.example.pbl.dao.outroComponente.OutroComponenteDAO;
import com.example.pbl.dao.outroComponente.OutroComponenteLista;
import com.example.pbl.dao.peca.PecaDAO;
import com.example.pbl.dao.peca.PecaLista;
import com.example.pbl.dao.tecnico.TecnicoDAO;
import com.example.pbl.dao.tecnico.TecnicoLista;

/**
 * Permite buscar cada DAO do model
 * @author Márcio Roberto, Amanda Lima Bezerra
 */
public class DAO {
    private static ClienteDAO clienteDAO;

    private static TecnicoDAO tecnicoDAO;

    private static OrdemServicoDAO ordemServicoDAO;

    private static InstalacaoDAO instalacaoDAO;

    private static LimpezaDAO limpezaDAO;

    private static MontagemDAO montagemDAO;

    private static OutroComponenteDAO outroComponenteDAO;

    private static PecaDAO pecaDAO;

    /**
     * Retorna uma instância da implementação do DAO de Cliente
     * @return Implementação do DAO de Cliente
     */
    public static ClienteDAO getCliente(){
        if (clienteDAO == null){
            clienteDAO = new ClienteArquivo();
        }
        return clienteDAO;
    }

    /**
     * Retorna uma instância da implementação do DAO de Instalacao
     * @return Implementação do DAO de Instalacao
     */
    public static InstalacaoDAO getInstalacao(){
        if (instalacaoDAO == null){
            instalacaoDAO = new InstalacaoLista();

        }
        return instalacaoDAO;
    }

    /**
     * Retorna uma instância da implementação do DAO de Limpeza
     * @return Implementação do DAO de Limpeza
     */
    public static LimpezaDAO getLimpeza(){
        if (limpezaDAO == null){
            limpezaDAO = new LimpezaLista();

        }
        return limpezaDAO;
    }

    /**
     * Retorna uma instância da implementação do DAO de Montagem
     * @return Implementação do DAO de Montagem
     */
    public static MontagemDAO getMontagem(){
        if (montagemDAO == null){
            montagemDAO = new MontagemLista();

        }
        return montagemDAO;
    }

    /**
     * Retorna uma instância da implementação do DAO de Tecnico
     * @return Implementação do DAO  de Tecnico
     */
    public static TecnicoDAO getTecnico(){
        if (tecnicoDAO == null){
            tecnicoDAO = new TecnicoLista();
        }
        return tecnicoDAO;
    }

    /**
     * Retorna uma instância da implementação do DAO de OrdemServico
     * @return Implementação do DAO de OrdemServico
     */
    public static OrdemServicoDAO getOrdemServico(){
        if (ordemServicoDAO == null){
            ordemServicoDAO = new OrdemServicoLista();
        }
        return ordemServicoDAO;
    }

    /**
     * Retorna uma instância da implementação do DAO de OutroComponente
     * @return Implementação do DAO de OutroComponente
     */
    public static OutroComponenteDAO getOutroComponente(){
        if (outroComponenteDAO == null){
            outroComponenteDAO = new OutroComponenteLista();
        }
        return outroComponenteDAO;
    }

    /**
     * Retorna uma instância da implementação do DAO de Peca
     * @return Implementação do DAO de Peca
     */
    public static PecaDAO getPeca(){
        if (pecaDAO == null){
            pecaDAO = new PecaLista();
        }
        return pecaDAO;
    }


}
