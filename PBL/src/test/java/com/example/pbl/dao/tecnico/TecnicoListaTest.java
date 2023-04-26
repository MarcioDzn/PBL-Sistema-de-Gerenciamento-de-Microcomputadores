package com.example.pbl.dao.tecnico;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Tecnico;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TecnicoListaTest {

    private Tecnico tecnico1;
    private Tecnico tecnico2;
    @BeforeEach
    void setUp(){
        this.tecnico1 = new Tecnico("Nome1", "Endereço");
        DAO.getTecnico().criar(tecnico1);

        this.tecnico2 = new Tecnico("Nome2", "Endereço");
        DAO.getTecnico().criar(tecnico2);
    }

    @Test
    void criar() {
        assertEquals(2, DAO.getTecnico().buscarTodos().size());
    }

    @Test
    void buscarPorId() {
        assertEquals(this.tecnico1, DAO.getTecnico().buscarPorId(0));

        assertEquals(null, DAO.getTecnico().buscarPorId(3));
    }

    @Test
    void buscarTodos() {
        List<Tecnico> lista = new LinkedList<Tecnico>();

        lista.add(tecnico1);
        lista.add(tecnico2);

        assertEquals(lista, DAO.getTecnico().buscarTodos());
    }

    @Test
    void atualizarExistente() {
        Tecnico tecnico3 = new Tecnico("Nome3", "Endereço3");
        tecnico3.setId(1);

        try {
            DAO.getTecnico().atualizar(tecnico3);
            assertEquals(tecnico3, DAO.getTecnico().buscarPorId(1));

        } catch (ObjetoNaoEncontradoException e) {
            fail();
        }
    }

    @Test
    void atualizarInexistente() {
        Tecnico tecnico3 = new Tecnico("Nome3", "Endereço3");
        tecnico3.setId(3);

        assertThrows(ObjetoNaoEncontradoException.class, () -> DAO.getTecnico().atualizar(tecnico3));
    }

    @Test
    void removerExistente() {
        try {
            DAO.getTecnico().remover(this.tecnico1);
            assertEquals(1, DAO.getTecnico().buscarTodos().size());

        } catch (ObjetoNaoEncontradoException e) {
            fail();
        }
    }

    @Test
    void removerInexistente() {
        Tecnico tecnico3 = new Tecnico("Nome1", "Endereço");
        tecnico3.setId(3);

        assertThrows(ObjetoNaoEncontradoException.class, () -> DAO.getTecnico().remover(tecnico3));
    }

    @Test
    void deletarTudo() {
        DAO.getTecnico().deletarTudo();

        assertEquals(0, DAO.getTecnico().buscarTodos().size());
    }

    @AfterEach
    void tearDown(){
        DAO.getTecnico().deletarTudo();
    }
}