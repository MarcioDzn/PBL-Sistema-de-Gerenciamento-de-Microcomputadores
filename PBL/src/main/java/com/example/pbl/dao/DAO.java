package com.example.pbl.dao;

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
