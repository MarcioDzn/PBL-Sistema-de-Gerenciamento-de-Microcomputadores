package com.example.pbl.dao.outroComponente;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.OutroComponente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OutroComponenteListaTest {
    private OutroComponente outroComponente1;
    private OutroComponente outroComponente2;
    @BeforeEach
    void setUp(){
        this.outroComponente1 = new OutroComponente(38, 10, "qualquerPeça");
        DAO.getOutroComponente().criar(outroComponente1);

        this.outroComponente2 = new OutroComponente(8, 56, "qualquerPeça2");
        DAO.getOutroComponente().criar(outroComponente2);
    }

    @Test
    void criar() {
        assertEquals(2, DAO.getOutroComponente().buscarTodos().size());
    }

    @Test
    void buscarPorId() {
        assertEquals(this.outroComponente2, DAO.getOutroComponente().buscarPorId(1));
        assertEquals(null, DAO.getOutroComponente().buscarPorId(5));
    }

    @Test
    void buscarTodos() {
        List<OutroComponente> lista = new LinkedList<OutroComponente>();
        lista.add(outroComponente1);
        lista.add(outroComponente2);
        assertEquals(lista, DAO.getOutroComponente().buscarTodos());
    }

    @Test
    void atualizarExistente() {
        OutroComponente outroComponente3 = new OutroComponente(23, 56, "outroPeça3");
        outroComponente3.setId(1);

        try{
            DAO.getOutroComponente().atualizar(outroComponente3);
            assertEquals(outroComponente3, DAO.getOutroComponente().buscarPorId(1));
        } catch (ObjetoNaoEncontradoException e) {
            fail();
        }
    }

    @Test
    void atualizarInexistente() {
        OutroComponente outroComponente3 = new OutroComponente(34,56, "outraPeça3");
        outroComponente3.setId(5);

        assertThrows(ObjetoNaoEncontradoException.class, () -> DAO.getOutroComponente().atualizar(outroComponente3));
    }

    @Test
    void removerExistente() {
        try{
            DAO.getOutroComponente().remover(this.outroComponente2);
            assertEquals(1, DAO.getOutroComponente().buscarTodos().size());
        } catch (ObjetoNaoEncontradoException e) {
            fail();
        }
    }

    @Test
    void removerInexistente() {
        OutroComponente outroComponente3 = new OutroComponente(34,90, "outraPeça3");
        outroComponente3.setId(4);

        assertThrows(ObjetoNaoEncontradoException.class, () -> DAO.getOutroComponente().remover(outroComponente3));
    }

    @Test
    void deletarTudo() {
        DAO.getOutroComponente().deletarTudo();
        assertEquals(0, DAO.getOutroComponente().buscarTodos().size());
    }

    @AfterEach
    void tearDown(){
        DAO.getOutroComponente().deletarTudo();
    }
}