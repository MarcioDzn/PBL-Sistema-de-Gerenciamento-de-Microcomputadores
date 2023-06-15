package com.example.pbl;

import com.example.pbl.dao.DAO;
import com.example.pbl.model.LoginInfo;
import com.example.pbl.utils.LoginAtual;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlURL = getClass().getResource("LoginWindow.fxml");
        loader.setLocation(xmlURL);

        Parent parent = loader.load();

        Scene scene = new Scene(parent);

        primaryStage.setScene(scene);
        primaryStage.show();


        if (DAO.getLogin().buscarPorLogin("admin", "admin") == null){
            LoginInfo login = new LoginInfo("admin", "admin", "admin");
            login.setIdUsuario(-1);

            DAO.getLogin().criar(login);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}