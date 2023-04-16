package com.example.pbl.model;

import com.example.pbl.dao.DAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LimpezaTest {
    private Limpeza limpeza;
    @BeforeEach
    void setUp(){
        this.limpeza = new Limpeza(10, 10, "limpeza");
        DAO.getLimpeza().criar(this.limpeza);
    }

    @Test
    void testGetOrdensServico() {
        List<OrdemServico> lista = new LinkedList<OrdemServico>();

        OrdemServico os1 = new OrdemServico(0);
        os1.addServicos(this.limpeza, 1);
        DAO.getOrdemServico().criar(os1);

        lista.add(os1);

        assertEquals(lista, DAO.getOrdemServico().buscarPorServico(0, "Limpeza"));

        lista.add(new OrdemServico(4));

        assertNotEquals(lista, DAO.getOrdemServico().buscarPorServico(0, "Limpeza"));
    }

    @Test
    void testEquals() {
        Limpeza limpeza2 = new Limpeza(10, 15, "limpeza");
        limpeza2.setId(0);

        assertTrue(this.limpeza.equals(limpeza2));

        limpeza2.setId(1);

        assertFalse(this.limpeza.equals(limpeza2));
    }

    @AfterEach
    void tearDown(){
        DAO.getLimpeza().deletarTudo();
    }
}