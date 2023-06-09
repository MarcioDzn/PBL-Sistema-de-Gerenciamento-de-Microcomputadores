package com.example.pbl.model;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.OrdemServicoException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstalacaoTest {
    private Instalacao instalacao;

    @BeforeEach
    void setUp(){
        instalacao = new Instalacao(20, 40, "Half-Life 2");

        instalacao.setDescricao("Steam");
        instalacao.setDescricao("Adobe");

        DAO.getInstalacao().criar(instalacao);
    }

    @Test
    void testGetOrdensServico(){
        List<OrdemServico> lista = new LinkedList<OrdemServico>();

        for (int i = 0; i < 2; i++){
            OrdemServico os = new OrdemServico(0);

            try {
                os.colocarEmAndamento();
                lista.add(os);
                lista.get(i).addServicos(instalacao, 1);
                
            } catch (OrdemServicoException e) {
                throw new RuntimeException(e);
            }
            DAO.getOrdemServico().criar(lista.get(i));
        }

        assertEquals(lista, DAO.getOrdemServico().buscarPorServico(0, "Instalacao"));

        lista.add(new OrdemServico(4));

        assertNotEquals(lista, DAO.getOrdemServico().buscarPorServico(0, "Instalacao"));
    }

    @Test
    void testEquals(){
        Instalacao instalacao2 = new Instalacao(10, 15, "Elden Ring");
        instalacao2.setId(0);

        assertTrue(this.instalacao.equals(instalacao2));

        instalacao2.setId(1);

        assertFalse(this.instalacao.equals(instalacao2));
    }

    @AfterEach
    void tearDown(){
        DAO.getInstalacao().deletarTudo();
        DAO.getOrdemServico().deletarTudo();
    }
}