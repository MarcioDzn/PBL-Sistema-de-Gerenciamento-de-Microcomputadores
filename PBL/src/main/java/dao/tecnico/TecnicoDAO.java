package dao.tecnico;

import dao.CRUD;
import model.Cliente;
import model.Tecnico;

public interface TecnicoDAO extends CRUD<Tecnico> {
    public Tecnico buscarPorNome(String nome);
}
