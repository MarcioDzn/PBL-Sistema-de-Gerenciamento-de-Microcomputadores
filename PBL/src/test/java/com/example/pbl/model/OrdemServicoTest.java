package com.example.pbl.model;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.exceptions.OrdemServicoAtualException;
import com.example.pbl.exceptions.OrdemServicoException;
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
        montagem.setComponente(peca1, 1);
        montagem.setComponente(peca2, 1);

        DAO.getMontagem().criar(montagem);

        try{
            this.os1.addServicos(montagem, 1);
            double valorPreco = this.os1.getPreco();
            assertEquals(45, valorPreco);
        } catch (OrdemServicoException e) {
            fail();
        }
    }

    @Test
    void testIsFinalizado() {
        try{
            this.os1.finalizar();
            assertTrue(this.os1.isFinalizado());
        } catch (OrdemServicoException e) {
            fail();
        }
    }

    @Test
    void testIsCancelado() {
        try{
            this.os1.cancelar();
            assertTrue(this.os1.isCancelado());
        } catch (OrdemServicoException e) {
            fail();
        }


    }

    @Test
    void testIsEmAndamento() {
        assertTrue(this.os1.isEmAndamento());
    }

    @Test
    void testGetTempoTotal() throws InterruptedException {
        Thread.sleep(900);
        try{
            this.os1.finalizar();
            long tempoTotal = this.os1.getTempoTotal();
            assertTrue(tempoTotal > 800 && tempoTotal < 1000);
        } catch (OrdemServicoException e) {
            fail();
        }
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
        try{
            this.os1.setTecnicoId(0);
            assertEquals(tecnico1, this.os1.getTecnico());
        } catch (OrdemServicoException e) {
            fail();
        }
    }

    @Test
    void testAddServicosMontagem(){
        Montagem montagem = new Montagem();
        DAO.getMontagem().criar(montagem);

        Montagem montagem2 = new Montagem();
        DAO.getMontagem().criar(montagem2);
        try{
            this.os1.addServicos(montagem, 3);
            this.os1.addServicos(montagem2, 1);
            assertEquals(4, this.os1.getMontagens().size());
        } catch (OrdemServicoException e) {
            fail();
        }
    }

    @Test
    void testAddServicosLimpeza(){
        Limpeza limpeza = new Limpeza(10, 5, "Limpeza de algo");
        DAO.getLimpeza().criar(limpeza);

        Limpeza limpeza2 = new Limpeza(10, 5, "Limpeza de outro algo");
        DAO.getLimpeza().criar(limpeza2);
        try {
            this.os1.addServicos(limpeza, 5);
            this.os1.addServicos(limpeza2, 2);
            assertEquals(7, this.os1.getLimpezas().size());
        } catch (OrdemServicoException e){
            fail();
        }

    }

    @Test
    void testAddServicosInstalacao(){
        Instalacao instalacao = new Instalacao(10, 5, "Inst de algo");
        DAO.getInstalacao().criar(instalacao);

        Instalacao instalacao2 = new Instalacao(10, 5, "Inst de algo");
        DAO.getInstalacao().criar(instalacao2);
        try {
            this.os1.addServicos(instalacao, 2);
            this.os1.addServicos(instalacao2, 2);

            assertEquals(4, this.os1.getInstalacoes().size());
        } catch (OrdemServicoException e){
            fail();
        }

    }

    @Test
    void testGetMontagens(){
        List<Servico> lista = new LinkedList<Servico>();

        Montagem montagem = new Montagem();
        DAO.getMontagem().criar(montagem);

        Montagem montagem2 = new Montagem();
        DAO.getMontagem().criar(montagem2);
        try{
            this.os1.addServicos(montagem, 2);
            this.os1.addServicos(montagem2, 1);

            lista.add(montagem);
            lista.add(montagem);
            lista.add(montagem2);

            assertEquals(lista, this.os1.getMontagens());
        } catch (OrdemServicoException e){
            fail();
        }

    }

    @Test
    void testGetLimpezas(){
        List<Servico> lista = new LinkedList<Servico>();

        Limpeza limpeza = new Limpeza(10, 5, "Limpeza de algo");
        DAO.getLimpeza().criar(limpeza);

        Limpeza limpeza2 = new Limpeza(12, 6, "Limpeza de outro algo");
        DAO.getLimpeza().criar(limpeza2);
        try{
            this.os1.addServicos(limpeza, 2);
            this.os1.addServicos(limpeza2, 1);

            lista.add(limpeza);
            lista.add(limpeza);
            lista.add(limpeza2);

            assertEquals(lista, this.os1.getLimpezas());
        } catch (OrdemServicoException e){
            fail();
        }

    }

    @Test
    void testGetInstalacoes(){
        List<Servico> lista = new LinkedList<Servico>();

        Instalacao instalacao = new Instalacao(10, 5, "Inst de algo");
        DAO.getInstalacao().criar(instalacao);

        Instalacao instalacao2 = new Instalacao(10, 5, "Inst de outro algo");
        DAO.getInstalacao().criar(instalacao2);
        try{
            this.os1.addServicos(instalacao, 2);
            this.os1.addServicos(instalacao2, 1);

            lista.add(instalacao);
            lista.add(instalacao);
            lista.add(instalacao2);

            assertEquals(lista, this.os1.getInstalacoes());
        } catch (OrdemServicoException e){
            fail();
        }
    }

    @Test
    void removerServicoMontagem(){
        Montagem montagem = new Montagem();
        DAO.getMontagem().criar(montagem);

        Montagem montagem2 = new Montagem();
        DAO.getMontagem().criar(montagem2);
        try{
            this.os1.addServicos(montagem, 5);
            this.os1.addServicos(montagem2, 1);

            try {
                this.os1.removerServico(0, 2, "Montagem");

            } catch (ObjetoNaoEncontradoException e) {
                throw new RuntimeException(e);
            }

            assertEquals(4, this.os1.getMontagens().size());
        } catch (OrdemServicoException e){
            fail();
        }

    }
    @Test
    void removerServicoLimpeza(){
        Limpeza limpeza = new Limpeza(10, 5, "Limpeza de algo");
        DAO.getLimpeza().criar(limpeza);

        Limpeza limpeza2 = new Limpeza(12, 6, "Limpeza de outro algo");
        DAO.getLimpeza().criar(limpeza2);
        try{
            this.os1.addServicos(limpeza, 1);
            this.os1.addServicos(limpeza2, 2);

            try {
                this.os1.removerServico(1, 2, "Limpeza");

            } catch (ObjetoNaoEncontradoException e) {
                throw new RuntimeException(e);
            }

            assertEquals(1, this.os1.getLimpezas().size());
        } catch (OrdemServicoException e){
            fail();
        }

    }
    @Test
    void removerServicoInstalacao(){
        Instalacao instalacao = new Instalacao(10, 5, "Inst de algo");
        DAO.getInstalacao().criar(instalacao);

        Instalacao instalacao2 = new Instalacao(10, 5, "Inst de outro algo");
        DAO.getInstalacao().criar(instalacao2);
        try{
            this.os1.addServicos(instalacao, 3);
            this.os1.addServicos(instalacao2, 1);

            try {
                this.os1.removerServico(0, 2, "Instalacao");

            } catch (ObjetoNaoEncontradoException e) {
                throw new RuntimeException(e);
            }

            assertEquals(2, this.os1.getInstalacoes().size());
        } catch (OrdemServicoException e){
            fail();
        }

    }

    @Test
    void testRemoverServicoInexistente(){
        assertThrows(ObjetoNaoEncontradoException.class, () -> this.os1.removerServico(1, 4, "Instalacao"));
    }

    @Test
    void testRemoverServicoExcesso(){
        Instalacao instalacao = new Instalacao(10, 5, "Inst de algo");
        DAO.getInstalacao().criar(instalacao);
        try{
            this.os1.addServicos(instalacao, 3);

            assertThrows(ObjetoNaoEncontradoException.class, () -> this.os1.removerServico(0, 4, "Instalacao"));
        } catch (OrdemServicoException e){
            fail();
        }

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
        DAO.getMontagem().deletarTudo();
        DAO.getLimpeza().deletarTudo();
        DAO.getInstalacao().deletarTudo();
    }
}