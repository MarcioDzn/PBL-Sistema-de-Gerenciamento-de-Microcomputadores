package com.example.pbl.dao.instalacao;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Instalacao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstalacaoListaTest {

    private Instalacao instalacao1;
    private Instalacao instalacao2;
    @BeforeEach
    void setUp(){
        this.instalacao1 = new Instalacao(10, 10, "instalacao1");
        DAO.getInstalacao().criar(instalacao1);

        this.instalacao2 = new Instalacao(10, 10, "instalacao2");
        DAO.getInstalacao().criar(instalacao2);
    }

    @Test
    void testCriar() {
        assertEquals(2, DAO.getInstalacao().buscarTodos().size());
    }

    @Test
    void testBuscarPorId() {
        assertEquals(this.instalacao1, DAO.getInstalacao().buscarPorId(0));

        assertEquals(null, DAO.getInstalacao().buscarPorId(3));
    }

    @Test
    void testBuscarTodos() {
        List<Instalacao> lista = new LinkedList<Instalacao>();

        lista.add(instalacao1);
        lista.add(instalacao2);

        assertEquals(lista, DAO.getInstalacao().buscarTodos());
    }

    @Test
    void testAtualizarExistente() {
        Instalacao instalacao3 = new Instalacao(10, 10, "instalacao3");
        instalacao3.setId(1);

        try {
            DAO.getInstalacao().atualizar(instalacao3);
        } catch (ObjetoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(instalacao3, DAO.getInstalacao().buscarPorId(1));

    }

    @Test
    void testAtualizarInexistente() {
        Instalacao instalacao3 = new Instalacao(10, 10, "instalacao3");
        instalacao3.setId(3);

        assertThrows(ObjetoNaoEncontradoException.class, () -> DAO.getInstalacao().atualizar(instalacao3));
    }

    @Test
    void testRemoverExistente() {
        try {
            DAO.getInstalacao().remover(this.instalacao1);
        } catch (ObjetoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, DAO.getInstalacao().buscarTodos().size());

    }

    @Test
    void testRemoverInexistente() {
        Instalacao instalacao3 = new Instalacao(10, 10, "instalacao3");
        instalacao3.setId(3);

        assertThrows(ObjetoNaoEncontradoException.class, () -> DAO.getInstalacao().remover(instalacao3));
    }

    @Test
    void testDeletarTudo() {
        DAO.getInstalacao().deletarTudo();

        assertEquals(0, DAO.getInstalacao().buscarTodos().size());
    }

    @AfterEach
    void tearDown(){
        DAO.getInstalacao().deletarTudo();
    }
}