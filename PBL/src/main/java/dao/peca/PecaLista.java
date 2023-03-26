package dao.peca;

import model.Cliente;
import model.OrdemServico;
import model.Peca;

import java.util.LinkedList;
import java.util.List;

public class PecaLista implements PecaDAO{
    private List<Peca> listaPecas;
    private int proxId;

    public PecaLista() {
        this.listaPecas = new LinkedList<Peca>();
        this.proxId = 0;
    }

    @Override
    public void adicionarNaLista(Peca objeto) {
        objeto.setId(this.proxId);
        listaPecas.add(objeto);

        this.proxId++;
    }

    @Override
    public Peca buscarPorId(int id) {
        for (Peca peca: listaPecas){
            if (peca.getId() == id)
                return peca;
        }
        return null;
    }

    @Override
    public List<Peca> buscarTodos() {
        List<Peca> lista = new LinkedList<Peca>();

        for (Peca peca : listaPecas){
            lista.add(peca);
        }

        return lista;
    }

    @Override
    public void atualizar(Peca objeto) {
        for (int i = 0; i < this.listaPecas.size(); i++){
            if (listaPecas.get(i).getId() == objeto.getId()){
                this.listaPecas.add(objeto.getId(), objeto);

                return; // Para o laço assim que entrar no if e atualizar a lista
            }
        }
    }

    @Override
    public void remover(Peca objeto) {
        for (int i = 0; i < this.listaPecas.size(); i++){
            if (listaPecas.get(i).getId() == objeto.getId()){
                this.listaPecas.remove(objeto);

                return;
            }
        }
    }

    @Override
    public void delete_Many(Peca objeto) {
        listaPecas.clear();
    }

    @Override
    public Peca buscarPorNome(String nome) {
        for (Peca peca : listaPecas){
            if (peca.getNome() == nome)
                return peca;
        }
        return null;
    }
}
