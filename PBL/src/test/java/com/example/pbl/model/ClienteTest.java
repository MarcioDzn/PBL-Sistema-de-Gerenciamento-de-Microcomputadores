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
    private List<OrdemServico> osLista;

    @BeforeEach
    void setUp(){
        this.cliente1 = new Cliente("Gordon Freeman", "City17", "telefone1", "email1");
        DAO.getCliente().criar(cliente1);

        this.osLista = new LinkedList<OrdemServico>();

        for (int i = 0; i < 3; i++){
            osLista.add(new OrdemServico(0));
            DAO.getOrdemServico().criar(osLista.get(i));
        }
    }
    @Test
    void testGetOrdensServico() {
        for (int i = 0; i < 3; i++){
            this.cliente1.addOrdensServico(DAO.getOrdemServico().buscarPorId(i).getId());
        }

        assertEquals(this.osLista, this.cliente1.getOrdensServico());
    }

    @Test
    void testAddOrdensServico() {
        this.cliente1.addOrdensServico(0);
        this.cliente1.addOrdensServico(1);

        assertEquals(2, this.cliente1.getOrdensServico().size());
    }

    @AfterEach
    void tearDown(){
        DAO.getCliente().deletarTudo();
        DAO.getOrdemServico().deletarTudo();
    }
}