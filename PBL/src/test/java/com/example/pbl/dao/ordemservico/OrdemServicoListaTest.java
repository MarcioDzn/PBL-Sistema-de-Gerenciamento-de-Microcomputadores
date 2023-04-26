package com.example.pbl.dao.ordemservico;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.exceptions.OrdemServicoException;
import com.example.pbl.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrdemServicoListaTest {
    private OrdemServico ordem1;
    private OrdemServico ordem2;
    @BeforeEach
    void setUp(){
        this.ordem1 = new OrdemServico(0);
        try {
            this.ordem1.colocarEmAndamento();
        } catch (OrdemServicoException e) {
            throw new RuntimeException(e);
        }
        DAO.getOrdemServico().criar(ordem1);

        this.ordem2 = new OrdemServico(0);
        try {
            this.ordem2.colocarEmAndamento();

        } catch (OrdemServicoException e) {
            throw new RuntimeException(e);
        }
        DAO.getOrdemServico().criar(ordem2);
    }
    @Test
    void testCriar() {
        assertEquals(2, DAO.getOrdemServico().buscarTodos().size());
    }

    @Test
    void testBuscarPorId() {
        assertEquals(this.ordem1, DAO.getOrdemServico().buscarPorId(0));
        assertEquals(null, DAO.getOrdemServico().buscarPorId(3));
    }

    @Test
    void testBuscarTodos() {
        List<OrdemServico> lista = new LinkedList<OrdemServico>();

        lista.add(ordem1);
        lista.add(ordem2);

        assertEquals(lista, DAO.getOrdemServico().buscarTodos());
    }

    @Test
    void testAtualizarExistente() {
        OrdemServico ordem3 = new OrdemServico(0);
        ordem3.setId(0);

        try {
            DAO.getOrdemServico().atualizar(ordem3);
        } catch (ObjetoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }

        assertEquals(ordem3, DAO.getOrdemServico().buscarPorId(0));
    }

    @Test
    void testAtualizarInexistente() {
        OrdemServico ordem3 = new OrdemServico(0);
        ordem3.setId(3);

        assertThrows(ObjetoNaoEncontradoException.class, () -> DAO.getOrdemServico().atualizar(ordem3));
    }

    @Test
    void testRemoverExistente() {

        try {
            DAO.getOrdemServico().remover(this.ordem2);
        } catch (ObjetoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }

        assertEquals(1, DAO.getOrdemServico().buscarTodos().size());
    }

    @Test
    void testRemoverInexistente() {
        OrdemServico ordem3 = new OrdemServico(0);
        ordem3.setId(3);

        assertThrows(ObjetoNaoEncontradoException.class, () -> DAO.getOrdemServico().remover(ordem3));
    }
    @Test
    void testRemoverEAtualizarQuantidade(){
        OrdemServico ordem3 = new OrdemServico(0);
        ordem3.setId(0);
        try{
            DAO.getOrdemServico().remover(this.ordem1);
        } catch (ObjetoNaoEncontradoException e) {
            System.out.println(e);
        }


        try{
            DAO.getOrdemServico().atualizar(ordem3);
        } catch (ObjetoNaoEncontradoException e) {
            System.out.println(e);
        }
        assertEquals(1, DAO.getOrdemServico().buscarTodos().size());
    }

    @Test
    void testDeletarTudo() {
        DAO.getOrdemServico().deletarTudo();
        assertEquals(0, DAO.getOrdemServico().buscarTodos().size());
    }

    @Test
    void buscarPorCliente() {
        List<OrdemServico> lista = new LinkedList<OrdemServico>();

        Cliente cliente1 = new Cliente("Fulano", "endereco1", "telefone1", "email1");
        DAO.getCliente().criar(cliente1);

        lista.add(this.ordem1);
        lista.add(this.ordem2);

        assertEquals(lista, DAO.getOrdemServico().buscarPorCliente(0));
    }

    @Test
    void buscarPorTecnico() {
        List<OrdemServico> lista = new LinkedList<OrdemServico>();

        Tecnico tecnico1 = new Tecnico("Fulano", "email1");
        DAO.getTecnico().criar(tecnico1);

        try {
            this.ordem1.setTecnicoId(0);
            this.ordem2.setTecnicoId(0);

            DAO.getOrdemServico().atualizar(this.ordem1);
            DAO.getOrdemServico().atualizar(this.ordem2);

        } catch (OrdemServicoException e) {
            throw new RuntimeException(e);

        } catch (ObjetoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }

        lista.add(this.ordem1);
        lista.add(this.ordem2);

        assertEquals(lista, DAO.getOrdemServico().buscarPorTecnico(0));
    }

    @Test
    void buscarPorServico() {
        List<OrdemServico> lista = new LinkedList<OrdemServico>();

        Montagem montagem1 = new Montagem();
        DAO.getMontagem().criar(montagem1);

        try {
            this.ordem1.addServicos(montagem1, 2);
            this.ordem2.addServicos(montagem1, 2);

            DAO.getOrdemServico().atualizar(this.ordem1);
            DAO.getOrdemServico().atualizar(this.ordem2);

        } catch (OrdemServicoException e) {
            throw new RuntimeException(e);

        } catch (ObjetoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }

        lista.add(this.ordem1);
        lista.add(this.ordem2);

        assertEquals(lista, DAO.getOrdemServico().buscarPorServico(0, "Montagem"));
    }
    @Test
    void setOrdemFinalizada(){
        try {
            ordem1.finalizar();
        } catch (OrdemServicoException e) {
            throw new RuntimeException(e);
        }

        assertThrows(OrdemServicoException.class, () -> ordem1.setDescricao("teste1"));

    }

    @Test
    void setOrdemNaoFinalizada(){

        try {
            ordem2.setDescricao("Teste2");
        } catch (OrdemServicoException e) {
            throw new RuntimeException(e);
        }

        assertEquals("Teste2", ordem2.getDescricao());
    }

    @AfterEach
    void tearDown(){
        DAO.getOrdemServico().deletarTudo();
        DAO.getCliente().deletarTudo();
        DAO.getTecnico().deletarTudo();
        DAO.getMontagem().deletarTudo();
    }
}