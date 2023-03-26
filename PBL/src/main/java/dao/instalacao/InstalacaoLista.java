package dao.instalacao;

import model.Cliente;
import model.Instalacao;

import java.util.LinkedList;
import java.util.List;

public class InstalacaoLista implements InstalacaoDAO{
    private List<Instalacao> listaInstalacoes;
    private int proxId;

    public InstalacaoLista() {
        this.listaInstalacoes = new LinkedList<Instalacao>();
        this.proxId = 0;
    }

    @Override
    public void adicionarNaLista(Instalacao objeto) {
        objeto.setId(this.proxId);
        listaInstalacoes.add(objeto);

        this.proxId++;
    }

    @Override
    public Instalacao buscarPorId(int id) {
        for (Instalacao instalacao : listaInstalacoes){
            if (instalacao.getId() == id)
                return instalacao;
        }
        return null;
    }

    @Override
    public List<Instalacao> buscarTodos() {
        List<Instalacao> lista = new LinkedList<Instalacao>();

        for (Instalacao instalacao : listaInstalacoes){
            lista.add(instalacao);
        }

        return lista;
    }

    @Override
    public void atualizar(Instalacao objeto) {
        for (int i = 0; i < this.listaInstalacoes.size(); i++){
            if (listaInstalacoes.get(i).getId() == objeto.getId()){
                this.listaInstalacoes.add(objeto.getId(), objeto);

                return; // Para o laço assim que entrar no if e atualizar a lista
            }
        }
    }

    @Override
    public void remover(Instalacao objeto) {
        for (int i = 0; i < this.listaInstalacoes.size(); i++){
            if (listaInstalacoes.get(i).getId() == objeto.getId()){
                this.listaInstalacoes.remove(objeto);

                return;
            }
        }
    }

    @Override
    public void delete_Many(Instalacao objeto) {
        listaInstalacoes.clear();
    }
}
