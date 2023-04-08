package com.example.pbl.model;

import com.example.pbl.dao.DAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrdemServicoTest {
    private OrdemServico os1;

    @BeforeEach
    void setUp(){
        this.os1 = new OrdemServico(0);
    }

    @Test
    void testGetPreco() {
        Peca peca1 = new Peca(10, 10, 20, "RAM1", "fabricante1");
        Peca peca2 = new Peca(10, 15, 25, "RAM2", "fabricante2");

        Montagem montagem = new Montagem();
        montagem.setComponente(peca1);
        montagem.setComponente(peca2);

        this.os1.addServicos(montagem);

        double valorPreco = this.os1.getPreco();

        assertEquals(45, valorPreco);

    }

    @Test
    void testGetCusto() {
        Peca peca1 = new Peca(10, 10, 20, "RAM1", "fabricante1");
        Peca peca2 = new Peca(10, 15, 25, "RAM2", "fabricante2");

        Montagem montagem = new Montagem();
        montagem.setComponente(peca1);
        montagem.setComponente(peca2);

        this.os1.addServicos(montagem);

        double valorCusto = this.os1.getCusto();

        assertEquals(25, valorCusto);
    }

    @Test
    void testIsFinalizado() {
        this.os1.finalizar();

        assertTrue(this.os1.isFinalizado());
    }

    @Test
    void testIsCancelado() {
        this.os1.cancelar();

        assertTrue(this.os1.isCancelado());
    }

    @Test
    void testIsEmAndamento() {
        assertTrue(this.os1.isEmAndamento());
    }

    @Test
    void testGetTempoTotal() throws InterruptedException {
        Thread.sleep(900);

        this.os1.finalizar();
        long tempoTotal = this.os1.getTempoTotal();


        assertTrue(tempoTotal > 800 && tempoTotal < 1000);
    }

    @Test
    void testGetCliente() {
        Cliente cliente1 = new Cliente("Walter White", "Endereco1", "Telefone1", "Email1");
        DAO.getCliente().criar(cliente1);

        assertEquals(cliente1, this.os1.getCliente());
    }

    @Test
    void testGetTecnico() {
        Tecnico tecnico1 = new Tecnico("Nome1","email1");

        DAO.getTecnico().criar(tecnico1);

        this.os1.setTecnicoId(0);

        assertEquals(tecnico1, this.os1.getTecnico());
    }

    @Test
    void testGetServicos() {
        List<Servico> lista = new LinkedList<Servico>();

        Peca peca1 = new Peca(10, 10, 20, "RAM1", "fabricante1");

        Montagem montagem = new Montagem();
        montagem.setComponente(peca1);

        Limpeza limpeza = new Limpeza(20, 30);

        Instalacao instalacao = new Instalacao(90, 10);

        this.os1.addServicos(montagem);
        this.os1.addServicos(limpeza);
        this.os1.addServicos(instalacao);

        lista.add(montagem);
        lista.add(limpeza);
        lista.add(instalacao);

        assertEquals(lista, os1.getServicos());
    }

    @Test
    void testAddServicos() {
        Peca peca1 = new Peca(10, 10, 20, "RAM1", "fabricante1");

        Montagem montagem = new Montagem();
        montagem.setComponente(peca1);

        Limpeza limpeza = new Limpeza(20, 30);

        Instalacao instalacao = new Instalacao(90, 10);

        this.os1.addServicos(montagem);
        this.os1.addServicos(limpeza);
        this.os1.addServicos(instalacao);

        assertEquals(3, os1.getServicos().size());
    }

    @Test
    void testEquals(){
        OrdemServico os2 = new OrdemServico(0);
        os2.setId(0);

        assertTrue(this.os1.equals(os2));

        os2.setId(1);

        assertFalse(this.os1.equals(os2));
    }

    @AfterEach
    void tearDown(){
        DAO.getCliente().deletarTudo();
        DAO.getTecnico().deletarTudo();
    }
}