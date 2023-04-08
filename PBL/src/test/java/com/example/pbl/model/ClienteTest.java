package com.example.pbl.model;

import com.example.pbl.dao.DAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {
    private Cliente cliente1;

    @BeforeEach
    void setUp(){
        this.cliente1 = new Cliente("Gordon Freeman", "City17", "telefone1", "email1");
        DAO.getCliente().criar(cliente1);
    }

    @Test
    void testGetOrdensServico() {
        List<OrdemServico> osLista = new LinkedList<OrdemServico>();

        for (int i = 0; i < 3; i++){
            osLista.add(new OrdemServico(0));
            DAO.getOrdemServico().criar(osLista.get(i));
        }

        assertEquals(osLista, this.cliente1.getOrdensServico());

        osLista.add(new OrdemServico(1));

        assertNotEquals(osLista, this.cliente1.getOrdensServico());
    }

    @Test
    void testEquals(){
        Cliente cliente2 = new Cliente("GLaDOS", "Aperture", "telefone2", "email2");
        cliente2.setId(0);

        assertTrue(this.cliente1.equals(cliente2));

        cliente2.setId(1);

        assertFalse(this.cliente1.equals(cliente2));
    }

    @AfterEach
    void tearDown(){
        DAO.getCliente().deletarTudo();
        DAO.getOrdemServico().deletarTudo();
    }
}