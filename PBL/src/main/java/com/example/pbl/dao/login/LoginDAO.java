package com.example.pbl.dao.login;

import com.example.pbl.dao.CRUD;
import com.example.pbl.model.LoginInfo;

public interface LoginDAO extends CRUD<LoginInfo> {
    public LoginInfo buscarPorLogin(String usuario, String senha);
}
