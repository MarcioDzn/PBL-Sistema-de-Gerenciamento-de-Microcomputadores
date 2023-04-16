package com.example.pbl.model;

import com.example.pbl.dao.DAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OutroComponenteTest {
    private OutroComponente outroComp;

    @BeforeEach
    void setUp(){
        this.outroComp = new OutroComponente(10, 10, "descrição1");
        DAO.getOutroComponente().criar(outroComp);
    }

    @Test
    void testGetMontagens() {
        List<Montagem> listaMontagens = new LinkedList<Montagem>();

        for (int i = 0; i < 2; i++){
            listaMontagens.add(new Montagem());
            listaMontagens.get(i).setComponente(this.outroComp, 1);
            DAO.getMontagem().criar(listaMontagens.get(i));
        }

        assertEquals(listaMontagens, DAO.getMontagem().buscarPorComponente(0, "OutroComponente"));

        listaMontagens.add(new Montagem());
        DAO.getMontagem().criar(listaMontagens.get(2));

        assertNotEquals(listaMontagens, DAO.getMontagem().buscarPorComponente(0, "OutroComponente"));
    }

    @Test
    void testEquals() {
        OutroComponente outroComp2 = new OutroComponente(10, 15, "descrição2");
        outroComp2.setId(0);

        assertTrue(this.outroComp.equals(outroComp2));

        outroComp2.setId(1);

        assertFalse(this.outroComp.equals(outroComp2));
    }

    @AfterEach
    void tearDown(){
        DAO.getOutroComponente().deletarTudo();
    }}