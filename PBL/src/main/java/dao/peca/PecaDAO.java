package dao.peca;

import dao.CRUD;
import model.Peca;

import java.util.List;

public interface PecaDAO extends CRUD<Peca> {
    public Peca buscarPorNome(String nome);
}
