package com.example.pbl.model;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.exceptions.QuantidadeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PecaTest {
    private Peca peca1;

    @BeforeEach
    void setUp(){
        this.peca1 = new Peca(10, 20, 10, "RTX 4090", "Fabricante");
        DAO.getPeca().criar(peca1);
    }

    @Test
    void testSetQuantidadeNegativa() {
        assertThrows(QuantidadeException.class, () -> this.peca1.setQuantidade(-1));
    }

    @Test
    void testGetMontagens() {
        List<Montagem> lista = new LinkedList<Montagem>();

        Montagem montagem1 = new Montagem();
        DAO.getMontagem().criar(montagem1);
        lista.add(montagem1);

        Montagem montagem2 = new Montagem();
        DAO.getMontagem().criar(montagem2);
        lista.add(montagem2);

        montagem1.setComponente(this.peca1, 1);
        montagem2.setComponente(this.peca1, 1);

        assertEquals(lista, this.peca1.getMontagens());
    }

    @Test
    void testEquals() {
        Peca peca2 = new Peca(10, 10, 10, "nome2", "fabricante2");
        peca2.setId(0);

        assertTrue(this.peca1.equals(peca2));

        peca2.setId(1);

        assertFalse(this.peca1.equals(peca2));
    }

    @AfterEach
    void tearDown(){
        DAO.getPeca().deletarTudo();
        DAO.getMontagem().deletarTudo();
    }
}