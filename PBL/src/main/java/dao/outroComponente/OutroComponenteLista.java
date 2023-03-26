package dao.outroComponente;

import model.OutroComponente;

import java.util.LinkedList;
import java.util.List;

public class OutroComponenteLista implements OutroComponenteDAO{
    private List<OutroComponente> listaOutrosComponentes;
    private int proxId;

    public OutroComponenteLista() {
        this.listaOutrosComponentes = new LinkedList<OutroComponente>();
        this.proxId = 0;
    }

    @Override
    public void adicionarNaLista(OutroComponente objeto) {
        objeto.setId(this.proxId);
        listaOutrosComponentes.add(objeto);

        this.proxId++;
    }

    @Override
    public OutroComponente buscarPorId(int id) {
        for (OutroComponente outroComponente : listaOutrosComponentes){
            if (outroComponente.getId() == id)
                return outroComponente;
        }
        return null;
    }

    @Override
    public List<OutroComponente> buscarTodos() {
        List<OutroComponente> lista = new LinkedList<OutroComponente>();

        for (OutroComponente outroComponente : listaOutrosComponentes){
            lista.add(outroComponente);
        }

        return lista;
    }

    @Override
    public void atualizar(OutroComponente objeto) {
        for (int i = 0; i < this.listaOutrosComponentes.size(); i++){
            if (listaOutrosComponentes.get(i).getId() == objeto.getId()){
                this.listaOutrosComponentes.add(objeto.getId(), objeto);

                return; 
            }
        }
    }

    @Override
    public void remover(OutroComponente objeto) {
        for (int i = 0; i < this.listaOutrosComponentes.size(); i++){
            if (listaOutrosComponentes.get(i).getId() == objeto.getId()){
                this.listaOutrosComponentes.remove(objeto);

                return;
            }
        }
    }

    @Override
    public void deletarTudo() {
        this.listaOutrosComponentes = new LinkedList<OutroComponente>();
        this.proxId = 0;
    }
}
