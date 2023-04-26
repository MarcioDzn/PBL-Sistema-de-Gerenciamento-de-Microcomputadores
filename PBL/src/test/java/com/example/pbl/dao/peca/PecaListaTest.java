package com.example.pbl.dao.peca;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Peca;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PecaListaTest {
    private Peca peca1;
    private Peca peca2;

    @BeforeEach
    void setUp(){
        this.peca1 = new Peca(1, 60, 70, "peça1", "fab1");
        DAO.getPeca().criar(peca1);
        this.peca2 = new Peca(3, 55, 90, "peça2", "fab2");
        DAO.getPeca().criar(peca2);
    }
    @Test
    void criar() {
        assertEquals(2, DAO.getPeca().buscarTodos().size());
    }

    @Test
    void buscarPorId() {
        assertEquals(this.peca1, DAO.getPeca().buscarPorId(0));
        assertEquals(null, DAO.getPeca().buscarPorId(4));
    }

    @Test
    void buscarTodos() {
        List<Peca> lista = new LinkedList<Peca>();
        lista.add(peca1);
        lista.add(peca2);
        assertEquals(lista, DAO.getPeca().buscarTodos());
    }

    @Test
    void atualizarExistente() {
        Peca peca3 = new Peca(1, 43, 56, "peça3", "fab3");
        peca3.setId(1);

        try{
            DAO.getPeca().atualizar(peca3);
            assertEquals(peca3, DAO.getPeca().buscarPorId(1));
        } catch (ObjetoNaoEncontradoException e) {
            fail();
        }
    }

    @Test
    void atualizarInexistente() {
        Peca peca3 = new Peca(2,34,56,"peça3", "fab3");
        peca3.setId(7);

        assertThrows(ObjetoNaoEncontradoException.class, () -> DAO.getPeca().atualizar(peca3));
    }
    @Test
    void removerExistente() {
        try{
            DAO.getPeca().remover(this.peca1);
        } catch (ObjetoNaoEncontradoException e) {
            fail();
        }
    }

    @Test
    void removerInexistente() {
        Peca peca3 = new Peca(2, 34, 56, "peça3", "fab3");
        peca3.setId(4);

        assertThrows(ObjetoNaoEncontradoException.class, () -> DAO.getPeca().remover(peca3));
    }

    @Test
    void deletarTudo() {
        DAO.getPeca().deletarTudo();
        assertEquals(0, DAO.getPeca().buscarTodos().size());
    }

    @Test
    void buscarPorNomeExistente() {
        String nomePeca = peca1.getNome();
        assertEquals("peça1", nomePeca);
    }

    @Test
    void buscarPorNomeInexistente() {
        Peca peca3 = new Peca(2, 34, 45, "peça3", "fab3");
        assertEquals(null, DAO.getPeca().buscarPorNome(peca3.getNome()));
    }
    @AfterEach
    void tearDown(){
        DAO.getPeca().deletarTudo();
    }
}