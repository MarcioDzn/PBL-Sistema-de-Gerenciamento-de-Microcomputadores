package com.example.pbl.model;

import com.example.pbl.dao.DAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TecnicoTest {
    private Tecnico tecnico;
    private OrdemServico os1;
    private OrdemServico os2;

    @BeforeEach
    void setUp(){
        this.tecnico = new Tecnico("Levi Ackermann", "email1");
        DAO.getTecnico().criar(this.tecnico);

        this.os1 = new OrdemServico(0);
        DAO.getOrdemServico().criar(this.os1);

        this.os2 = new OrdemServico(0);
        DAO.getOrdemServico().criar(this.os2);
    }

    @Test
    void testGetOrdemServicoAtual(){
        assertEquals(null, this.tecnico.getOrdemServicoAtual());

        this.tecnico.addOrdemServicoAtual(0);
        assertEquals(this.os1, this.tecnico.getOrdemServicoAtual());
    }

    @Test
    void testGetOrdensServico() {
        List<OrdemServico> lista = new LinkedList<OrdemServico>();

        this.os1.setTecnicoId(0);
        lista.add(this.os1);

        this.os2.setTecnicoId(0);
        lista.add(this.os2);

        assertEquals(lista, this.tecnico.getOrdensServico());

        OrdemServico os3 = new OrdemServico(0);
        os3.setTecnicoId(1);
        lista.add(os3);

        assertNotEquals(lista, this.tecnico.getOrdensServico());
    }

    @AfterEach
    void tearDown(){
        DAO.getTecnico().deletarTudo();
        DAO.getOrdemServico().deletarTudo();
    }
}