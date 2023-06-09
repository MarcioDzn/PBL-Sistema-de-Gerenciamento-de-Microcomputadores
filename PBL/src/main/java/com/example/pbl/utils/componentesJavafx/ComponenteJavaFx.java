package com.example.pbl.utils.componentesJavafx;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public class ComponenteJavaFx {
    protected static HBox criarComponente(int width, int height){

        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: #282828; -fx-border-style: solid; -fx-border-color: white; -fx-border-width: 1.5; -fx-border-radius: 8;");
//        hbox.setId("servico");
        hbox.setPrefWidth(width);
        hbox.setPrefHeight(height);
        hbox.setPadding(new Insets(5, 5, 5, 5));

        return hbox;
    }
}
