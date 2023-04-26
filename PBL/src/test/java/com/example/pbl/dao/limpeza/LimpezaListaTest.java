package com.example.pbl.dao.limpeza;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Limpeza;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LimpezaListaTest {
    private Limpeza limpeza1;
    private Limpeza limpeza2;
    @BeforeEach
    void setUp(){
        this.limpeza1 = new Limpeza(10, 5, "peça1");
        DAO.getLimpeza().criar(limpeza1);
        this.limpeza2 = new Limpeza(12, 7, "peça2");
        DAO.getLimpeza().criar(limpeza2);
    }
    @Test
    void testCriar() {
        assertEquals(2, DAO.getLimpeza().buscarTodos().size());
    }

    @Test
    void testBuscarPorId() {
        assertEquals(this.limpeza1, DAO.getLimpeza().buscarPorId(0));
        assertEquals(null, DAO.getLimpeza().buscarPorId(3));
    }

    @Test
    void testBuscarTodos() {
        List<Limpeza> lista = new LinkedList<Limpeza>();

        lista.add(limpeza1);
        lista.add(limpeza2);

        assertEquals(lista, DAO.getLimpeza().buscarTodos());
    }

    @Test
    void testAtualizarExistente() {
        Limpeza limpeza3 = new Limpeza(60, 40, "limpeza3");
        limpeza3.setId(0);
        try{
            DAO.getLimpeza().atualizar(limpeza3);
            assertEquals(limpeza3, DAO.getLimpeza().buscarPorId(0));
        } catch (ObjetoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testAtualizarInexistente() {
        Limpeza limpeza3 = new Limpeza(30, 78, "limpeza3");
        limpeza3.setId(3);

        try {
            DAO.getLimpeza().atualizar(limpeza3);

        } catch (ObjetoNaoEncontradoException e) {
            assertThrows(ObjetoNaoEncontradoException.class, () -> DAO.getLimpeza().atualizar(limpeza3));
        }


    }

    @Test
    void testRemoverExistente() {
        try {
            DAO.getLimpeza().remover(this.limpeza2);
        } catch (ObjetoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }

        assertEquals(1, DAO.getLimpeza().buscarTodos().size());
    }

    @Test
    void testRemoverInexistente() {
        Limpeza limpeza3 = new Limpeza(18, 67, "limpeza3");
        limpeza3.setId(3);

        try {
            DAO.getLimpeza().atualizar(limpeza3);
        } catch (ObjetoNaoEncontradoException e) {
            assertThrows(ObjetoNaoEncontradoException.class, () -> DAO.getLimpeza().remover(limpeza3));
        }


    }
    @Test
    void testRemoverEAtualizarQuantidade(){
        Limpeza limpeza3 = new Limpeza(57,12, "limpeza3");
        limpeza3.setId(0);

        try{
            DAO.getLimpeza().remover(this.limpeza1);
            DAO.getLimpeza().atualizar(limpeza3);

        } catch (ObjetoNaoEncontradoException e) {
            System.out.println(e);
        }

        assertEquals(1, DAO.getLimpeza().buscarTodos().size());
    }
    @Test
    void testDeletarTudo() {
        DAO.getLimpeza().deletarTudo();
        assertEquals(0, DAO.getLimpeza().buscarTodos().size());
    }

    @AfterEach
    void tearDown(){
        DAO.getLimpeza().deletarTudo();
    }
}