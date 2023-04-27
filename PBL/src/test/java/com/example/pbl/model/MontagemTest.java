package com.example.pbl.model;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.exceptions.OrdemServicoException;
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
    void testSetComponentePeca() {
        Peca peca1 = new Peca(10, 15, 20, "nome1", "");
        DAO.getPeca().criar(peca1);

        Peca peca2 = new Peca(10, 15, 20, "nome2", "");
        DAO.getPeca().criar(peca2);

        this.montagem.setComponente(peca1, 2);
        this.montagem.setComponente(peca2, 4);

        assertEquals(6, this.montagem.getPecas().size());
    }

    @Test
    void testSetComponenteOutroComponente() {
        OutroComponente outroComp1 = new OutroComponente(10, 10, "outro");
        DAO.getOutroComponente().criar(outroComp1);

        OutroComponente outroComp2 = new OutroComponente(10, 10, "outro2");
        DAO.getOutroComponente().criar(outroComp2);

        this.montagem.setComponente(outroComp1, 2);
        this.montagem.setComponente(outroComp2, 4);

        assertEquals(6, this.montagem.getOutrosComponentes().size());
    }

    @Test
    void testGetPecas(){
        List<Componente> lista = new LinkedList<Componente>();

        Peca peca1 = new Peca(10, 10, 10, "nome", "fab");
        DAO.getPeca().criar(peca1);

        Peca peca2 = new Peca(10, 10, 10, "nome", "fab");
        DAO.getPeca().criar(peca2);

        this.montagem.setComponente(peca1, 2);
        this.montagem.setComponente(peca2, 1);

        lista.add(peca1);
        lista.add(peca1);
        lista.add(peca2);

        assertEquals(lista, this.montagem.getPecas());
    }

    @Test
    void testGeOutrosComponentes(){
        List<Componente> lista = new LinkedList<Componente>();

        OutroComponente outroComp1 = new OutroComponente(10, 10, "desc");
        DAO.getOutroComponente().criar(outroComp1);

        OutroComponente outroComp2 = new OutroComponente(10, 10, "nome");
        DAO.getOutroComponente().criar(outroComp2);

        this.montagem.setComponente(outroComp1, 2);
        this.montagem.setComponente(outroComp2, 1);

        lista.add(outroComp1);
        lista.add(outroComp1);
        lista.add(outroComp2);

        assertEquals(lista, this.montagem.getOutrosComponentes());
    }

    @Test
    void testRemoverPeca(){
        Peca peca1 = new Peca(10, 10, 10, "nome", "fab");
        DAO.getPeca().criar(peca1);

        Peca peca2 = new Peca(10, 10, 10, "nome", "fab");
        DAO.getPeca().criar(peca2);

        this.montagem.setComponente(peca1, 3);
        this.montagem.setComponente(peca2, 1);

        try {
            this.montagem.removerComponente(peca1.getId(), 2, "Peca");

        } catch (ObjetoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }

        assertEquals(2, this.montagem.getPecas().size());
    }

    @Test
    void testRemoverOutroComponente(){
        OutroComponente outroComp1 = new OutroComponente(10, 10, "nome");
        DAO.getOutroComponente().criar(outroComp1);

        OutroComponente outroComp2 = new OutroComponente(10, 10, "nome");
        DAO.getOutroComponente().criar(outroComp2);

        this.montagem.setComponente(outroComp1, 3);
        this.montagem.setComponente(outroComp2, 1);

        try {
            this.montagem.removerComponente(outroComp1.getId(), 2, "OutroComponente");

        } catch (ObjetoNaoEncontradoException e) {
            throw new RuntimeException(e);
        }

        assertEquals(2, this.montagem.getOutrosComponentes().size());
    }

    @Test
    void testRemoverServicoInexistente(){
        assertThrows(ObjetoNaoEncontradoException.class, () -> this.montagem.removerComponente(1, 4, "Peca"));
    }

    @Test
    void testRemoverServicoExcesso(){
        Peca peca1 = new Peca(10, 10, 10, "nome", "fab");
        DAO.getPeca().criar(peca1);

        this.montagem.setComponente(peca1, 3);

        assertThrows(ObjetoNaoEncontradoException.class, () -> this.montagem.removerComponente(0, 4, "Peca"));
    }

    @Test
    void testEquals() {
        Montagem montagem2 = new Montagem();
        montagem2.setId(0);

        assertTrue(this.montagem.equals(montagem2));

        montagem2.setId(1);

        assertFalse(this.montagem.equals(montagem2));
    }

    @Test
    void testGetOrdensServico() {
        List<OrdemServico> osLista = new LinkedList<OrdemServico>();

        for (int i = 0; i < 3; i++){
            OrdemServico os = new OrdemServico(0);

            try {
                os.colocarEmAndamento();
                osLista.add(os);
                osLista.get(i).addServicos(this.montagem, 1);

            } catch (OrdemServicoException e) {
                throw new RuntimeException(e);
            }
            DAO.getOrdemServico().criar(osLista.get(i));
        }

        assertEquals(osLista, DAO.getOrdemServico().buscarPorServico(0, "Montagem"));
    }

    @AfterEach
    void tearDown(){
        DAO.getMontagem().deletarTudo();
        DAO.getOrdemServico().deletarTudo();
        DAO.getPeca().deletarTudo();
        DAO.getOutroComponente().deletarTudo();
    }
}