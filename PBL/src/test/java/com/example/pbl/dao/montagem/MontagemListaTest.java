package com.example.pbl.dao.montagem;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Montagem;
import com.example.pbl.model.Peca;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MontagemListaTest {

    private Montagem montagem1;
    private Montagem montagem2;
    @BeforeEach
    void setUp(){
        this.montagem1 = new Montagem();
        DAO.getMontagem().criar(montagem1);
        this.montagem2 = new Montagem();
        DAO.getMontagem().criar(montagem2);
    }
    @Test
    void testCriar() {
        assertEquals(2, DAO.getMontagem().buscarTodos().size());
    }

    @Test
    void testBuscarPorId() {
        assertEquals(this.montagem1, DAO.getMontagem().buscarPorId(0));
        assertEquals(null, DAO.getMontagem().buscarPorId(3));
    }

    @Test
    void testBuscarTodos() {
        List<Montagem> lista = new LinkedList<Montagem>();

        lista.add(montagem1);
        lista.add(montagem2);

        assertEquals(lista, DAO.getMontagem().buscarTodos());
    }

    @Test
    void testAtualizarExistente() {
        Montagem montagem3 = new Montagem();
        montagem3.setId(0);

        try {
            DAO.getMontagem().atualizar(montagem3);
        } catch (ObjetoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }

        assertEquals(montagem3, DAO.getMontagem().buscarPorId(0));

    }

    @Test
    void testAtualizarInexistente() {
        Montagem montagem3 = new Montagem();
        montagem3.setId(3);

        assertThrows(ObjetoNaoEncontradoException.class, () -> DAO.getMontagem().atualizar(montagem3));
    }

    @Test
    void testRemoverExistente() {
        try {
            DAO.getMontagem().remover(this.montagem2);
        } catch (ObjetoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }

        assertEquals(1, DAO.getMontagem().buscarTodos().size());
    }

    @Test
    void testRemoverInexistente() {
        Montagem montagem3 = new Montagem();
        montagem3.setId(3);

        assertThrows(ObjetoNaoEncontradoException.class, () -> DAO.getMontagem().remover(montagem3));
    }

    @Test
    void testDeletarTudo() {
        DAO.getMontagem().deletarTudo();
        assertEquals(0, DAO.getMontagem().buscarTodos().size());
    }

    @Test
    void buscarPorComponente() {
        List<Montagem> lista = new LinkedList<Montagem>();

        Peca peca1 = new Peca(10, 10, 15, "RTX 2080", "Fabricante");
        DAO.getPeca().criar(peca1);

        this.montagem1.setComponente(peca1, 2);
        lista.add(this.montagem1);
        this.montagem2.setComponente(peca1, 2);
        lista.add(this.montagem2);

        try {
            DAO.getMontagem().atualizar(this.montagem1);
            DAO.getMontagem().atualizar(this.montagem2);

        } catch (ObjetoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }


        assertEquals(lista, DAO.getMontagem().buscarPorComponente(0, "Peca"));
    }

    @AfterEach
    void tearDown(){
        DAO.getMontagem().deletarTudo();
        DAO.getPeca().deletarTudo();
    }
}