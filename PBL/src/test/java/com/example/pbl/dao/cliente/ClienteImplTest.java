package com.example.pbl.dao.cliente;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClienteImplTest {
    private Cliente cliente1;
    private Cliente cliente2;
    @BeforeEach
    void setUp(){
        this.cliente1 = new Cliente("Nome1", "Endereço", "Telefone", "Email");
        DAO.getCliente().criar(cliente1);

        this.cliente2 = new Cliente("Nome2", "Endereço", "Telefone", "Email");
        DAO.getCliente().criar(cliente2);
    }

    @Test
    void testCriar() {
        assertEquals(2, DAO.getCliente().buscarTodos().size());
    }

    @Test
    void testBuscarPorId() {
        assertEquals(this.cliente1, DAO.getCliente().buscarPorId(0));

        assertEquals(null, DAO.getCliente().buscarPorId(3));
    }

    @Test
    void testBuscarTodos() {
        List<Cliente> lista = new LinkedList<Cliente>();

        lista.add(cliente1);
        lista.add(cliente2);

        assertEquals(lista, DAO.getCliente().buscarTodos());
    }

    @Test
    void testAtualizarExistente() {
        Cliente cliente3 = new Cliente("Nome3", "Endereço3", "Telefone3", "Email3");
        cliente3.setId(1);

        try {
            DAO.getCliente().atualizar(cliente3);
        } catch (ObjetoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(cliente3, DAO.getCliente().buscarPorId(1));

    }

    @Test
    void testAtualizarInexistente() {
        Cliente cliente3 = new Cliente("Nome3", "Endereço3", "Telefone3", "Email3");
        cliente3.setId(3);

        assertThrows(ObjetoNaoEncontradoException.class, () -> DAO.getCliente().atualizar(cliente3));
    }
    @Test
    void testRemoverExistente() {
        try {
            DAO.getCliente().remover(this.cliente1);
        } catch (ObjetoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, DAO.getCliente().buscarTodos().size());

    }

    @Test
    void testRemoverInexistente() {
        Cliente cliente3 = new Cliente("Nome1", "Endereço", "Telefone", "Email");
        cliente3.setId(3);

        assertThrows(ObjetoNaoEncontradoException.class, () -> DAO.getCliente().remover(cliente3));
    }

    @Test
    void testDeletarTudo() {
        DAO.getCliente().deletarTudo();

        assertEquals(0, DAO.getCliente().buscarTodos().size());
    }

    @Test
    void testBuscarPorNome() {
        List<Cliente> lista = new LinkedList<Cliente>();

        lista.add(this.cliente2);

        Cliente cliente3 = new Cliente("Nome2", "Endereço", "Telefone", "Email");
        DAO.getCliente().criar(cliente3);

        lista.add(cliente3);

        assertEquals(lista, DAO.getCliente().buscarPorNome("Nome2"));

        assertEquals(0, DAO.getCliente().buscarPorNome("manel gome").size());
    }

    @AfterEach
    void tearDown(){
        DAO.getCliente().deletarTudo();
    }
}