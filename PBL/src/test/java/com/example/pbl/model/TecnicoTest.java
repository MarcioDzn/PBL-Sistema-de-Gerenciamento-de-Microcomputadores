package com.example.pbl.model;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.OrdemServicoAtualException;
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

        try {
            this.tecnico.addOrdemServicoAtual(0);

        } catch (OrdemServicoAtualException e) {
            System.out.println(e);
        }

        assertEquals(this.os1, this.tecnico.getOrdemServicoAtual());
    }

    @Test
    void testAddOrdemServicoAtualNaoOcupado(){
        try {
            this.tecnico.addOrdemServicoAtual(0);

        } catch (OrdemServicoAtualException e) {
            System.out.println(e);
        }

        assertEquals(this.os1, this.tecnico.getOrdemServicoAtual());
    }

    @Test
    void testAddOrdemServicoAtualJaOcupado(){
        try {
            this.tecnico.addOrdemServicoAtual(0);

        } catch (OrdemServicoAtualException e) {
            System.out.println(e);
        }

        assertThrows(OrdemServicoAtualException.class, () -> tecnico.addOrdemServicoAtual(1));
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

    @Test
    void testEquals() {
        Tecnico tecnico2 = new Tecnico("nome2", "email2");
        tecnico2.setId(0);

        assertTrue(this.tecnico.equals(tecnico2));

        tecnico2.setId(1);

        assertFalse(this.tecnico.equals(tecnico2));
    }

    @AfterEach
    void tearDown(){
        DAO.getTecnico().deletarTudo();
        DAO.getOrdemServico().deletarTudo();
    }
}