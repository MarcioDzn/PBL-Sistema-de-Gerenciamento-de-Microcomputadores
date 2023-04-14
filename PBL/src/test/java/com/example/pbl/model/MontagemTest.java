package com.example.pbl.model;

import com.example.pbl.dao.DAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MontagemTest {

    private Montagem montagem;

    @BeforeEach
    void setUp(){
        this.montagem = new Montagem();
        DAO.getMontagem().criar(this.montagem);
    }

    @Test
    void testSetComponente() {
        Peca peca1 = new Peca(10, 15, 20, "nome1", "");
        Peca peca2 = new Peca(10, 15, 20, "nome2", "");

        this.montagem.setComponente(peca1);
        this.montagem.setComponente(peca1);

        assertEquals(2, this.montagem.getComponentes().size());
    }

    @Test
    void testRemoverComponente(){
        List<Componente> lista = new LinkedList<Componente>();

        Peca peca1 = new Peca(10, 20, 10, "nome1", "fabricante1");
        peca1.setId(0);
        this.montagem.setComponente(peca1);

        Peca peca2 = new Peca(10, 20, 10, "nome2", "fabricante2");
        peca2.setId(1);
        this.montagem.setComponente(peca2);
        lista.add(peca2);

        OutroComponente outro1 = new OutroComponente(10, 20, "descricao");
        outro1.setId(0);
        this.montagem.setComponente(outro1);
        lista.add(outro1);

        this.montagem.removerComponente(0, "Peca");

        assertEquals(lista, this.montagem.getComponentes());
    }

    @Test
    void testGetOrdensServico() {
        List<OrdemServico> osLista = new LinkedList<OrdemServico>();

        for (int i = 0; i < 3; i++){
            osLista.add(new OrdemServico(0));
            osLista.get(i).addServicos(this.montagem);
            DAO.getOrdemServico().criar(osLista.get(i));
        }

        assertEquals(osLista, DAO.getOrdemServico().buscarPorServico(0, "Montagem"));
    }

    @Test
    void testEquals() {
        Montagem montagem2 = new Montagem();
        montagem2.setId(0);

        assertTrue(this.montagem.equals(montagem2));

        montagem2.setId(1);

        assertFalse(this.montagem.equals(montagem2));
    }

    @AfterEach
    void tearDown(){
        DAO.getMontagem().deletarTudo();
        DAO.getOrdemServico().deletarTudo();
    }
}