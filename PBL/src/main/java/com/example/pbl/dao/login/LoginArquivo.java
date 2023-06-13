package com.example.pbl.dao.login;

import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Limpeza;
import com.example.pbl.model.LoginInfo;
import com.example.pbl.utils.ManipuladorArquivo;

import java.util.LinkedList;
import java.util.List;

public class LoginArquivo implements LoginDAO{

    private int proxId;

    public LoginArquivo() {
        List<LoginInfo> lista = ManipuladorArquivo.recuperarBinario("logins.dat");
        // Garante que o proxId não zere sempre que o programa for reiniciado
        // Pega o id do último elemento adicionado na lista
        this.proxId = lista.size() > 0 ? lista.get(lista.size()-1).getId() + 1 : 0;
    }
    @Override
    public void criar(LoginInfo objeto) {
        List<LoginInfo> lista =  ManipuladorArquivo.recuperarBinario("logins.dat");

        objeto.setId(this.proxId);
        lista.add(objeto);

        ManipuladorArquivo.guardarBinario(lista, "logins.dat");

        this.proxId++;
    }

    @Override
    public LoginInfo buscarPorId(int id) {
        List<LoginInfo> lista = ManipuladorArquivo.recuperarBinario("logins.dat");

        for (LoginInfo login : lista) {
            if (login.getId() == id)
                return login;
        }
        return null;
    }

    @Override
    public List<LoginInfo> buscarTodos() {
        return ManipuladorArquivo.recuperarBinario("logins.dat");
    }

    @Override
    public void atualizar(LoginInfo objeto) throws ObjetoNaoEncontradoException {
        List<LoginInfo> lista = ManipuladorArquivo.recuperarBinario("logins.dat");

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == objeto.getId()){
                lista.set(i, objeto);

                ManipuladorArquivo.guardarBinario(lista, "logins.dat");

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Login");
    }

    @Override
    public void remover(LoginInfo objeto) throws ObjetoNaoEncontradoException {
        List<LoginInfo> lista = ManipuladorArquivo.recuperarBinario("logins.dat");

        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getId() == objeto.getId()){
                lista.remove(objeto);

                ManipuladorArquivo.guardarBinario(lista, "logins.dat");

                return;
            }
        }

        throw new ObjetoNaoEncontradoException("Login");
    }

    @Override
    public void deletarTudo() {
        ManipuladorArquivo.guardarBinario(new LinkedList<LoginInfo>(), "logins.dat");
        this.proxId = 0;
    }

    @Override
    public LoginInfo buscarPorLogin(String usuario, String senha) {
        List<LoginInfo> lista = ManipuladorArquivo.recuperarBinario("logins.dat");

        for (LoginInfo login : lista) {
            if (login.getUsuario().equals(usuario))
                if (login.getSenha().equals(senha))
                    return login;
        }

        return null;
    }
}
