package dao.montagem;

import model.Limpeza;
import model.Montagem;

import java.util.LinkedList;
import java.util.List;

public class MontagemLista implements MontagemDAO{
    private List<Montagem> listaMontagens;
    private int proxId;

    public MontagemLista() {
        this.listaMontagens = new LinkedList<Montagem>();
        this.proxId = 0;
    }

    @Override
    public void adicionarNaLista(Montagem objeto) {
        objeto.setId(this.proxId);
        listaMontagens.add(objeto);

        this.proxId++;
    }

    @Override
    public Montagem buscarPorId(int id) {
        for (Montagem montagem : listaMontagens){
            if (montagem.getId() == id)
                return montagem;
        }
        return null;
    }

    @Override
    public List<Montagem> buscarTodos() {
        List<Montagem> lista = new LinkedList<Montagem>();

        for (Montagem montagem : listaMontagens){
            lista.add(montagem);
        }

        return lista;
    }

    @Override
    public void atualizar(Montagem objeto) {
        for (int i = 0; i < this.listaMontagens.size(); i++){
            if (listaMontagens.get(i).getId() == objeto.getId()){
                this.listaMontagens.add(objeto.getId(), objeto);

                return; // Para o laço assim que entrar no if e atualizar a lista
            }
        }
    }

    @Override
    public void remover(Montagem objeto) {
        for (int i = 0; i < this.listaMontagens.size(); i++){
            if (listaMontagens.get(i).getId() == objeto.getId()){
                this.listaMontagens.remove(objeto);

                return;
            }
        }
    }
}
