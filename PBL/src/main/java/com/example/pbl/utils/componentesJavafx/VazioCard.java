package com.example.pbl.utils.componentesJavafx;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class VazioCard {
    public static HBox mensagemVazio(String tipo){
        Label labelMensagem = new Label("Não há nenhum(a) " + tipo + "!");

        labelMensagem.setStyle("-fx-font-weight: bold");
        labelMensagem.setTextFill(Color.WHITE);
        labelMensagem.setFont(new javafx.scene.text.Font("Arial", 30));

        HBox hbox = new HBox();
        hbox.getChildren().add(labelMensagem);
        hbox.setAlignment(Pos.CENTER);

        hbox.setPrefWidth(900);
        hbox.setPrefHeight(310);

        hbox.setStyle("-fx-background-color: #282828;");
        return hbox;


    }
}
