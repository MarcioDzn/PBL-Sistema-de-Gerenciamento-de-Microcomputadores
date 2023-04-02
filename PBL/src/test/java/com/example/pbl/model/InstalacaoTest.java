package com.example.pbl.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstalacaoTest {
    private Instalacao instalacao;

    @BeforeEach
    void setUp(){
        this.instalacao = new Instalacao(20, 40);

        instalacao.setProgramas("Steam");
        instalacao.setProgramas("Adobe");
    }
    
    @Test
    void testGetProgramas() {
        List<String> lista = new LinkedList<String>();

        lista.add("Steam");
        lista.add("Adobe");

        assertEquals(lista, this.instalacao.getProgramas());
    }

    @Test
    void testSetProgramas() {
        assertEquals(2, this.instalacao.getProgramas().size());
    }
}