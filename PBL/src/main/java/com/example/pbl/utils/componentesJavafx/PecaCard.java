package com.example.pbl.utils.componentesJavafx;

import com.example.pbl.model.Peca;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class PecaCard extends ComponenteJavaFx{
    private static HBox criarComponenteNovo(Map<String, String> dados, int width, int height){
        int FONT_SIZE = 15;

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(5, 5, 5, 5));

        VBox vboxGeral = new VBox();
        HBox hbox = criarComponente(width, height);

        Label objLabel;
        Label objLabelInfo;

        List<String> listaNomes = new ArrayList<>(Arrays.asList("Nome", "Fabricante", "Quantidade", "Preco", "Custo"));
        for (String nome : listaNomes){
            objLabel = new Label(nome);
            objLabelInfo = new Label(dados.get(nome));

            objLabel.setFont(new javafx.scene.text.Font("Arial", FONT_SIZE * 1.25));
            objLabel.setTextFill(Color.WHITE);

            objLabelInfo.setFont(new javafx.scene.text.Font("Arial", FONT_SIZE));
            objLabelInfo.setTextFill(Color.WHITE);

            vbox.getChildren().add(objLabel);
            vbox.getChildren().add(objLabelInfo);

            vboxGeral.getChildren().add(vbox);

            vbox = new VBox();
            vbox.setPadding(new Insets(5, 5, 5, 5));

        }


        Button button = new Button("Selecionar");
        button.setMinWidth(width);
        button.setId(dados.get("Nome") + "-" + dados.get("Id"));

        if (dados.get("Quantidade").equals("0")){
            button.setDisable(true);
            button.setText("Quantidade insuficiente!");
        }

        TextField txtQuantidade = new TextField();
        txtQuantidade.setPromptText("Quantidade");
        txtQuantidade.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtQuantidade.setId(dados.get("Nome") + "-" + dados.get("Id"));
        txtQuantidade.setDisable(true);

        vbox.getChildren().add(button);
        vbox.getChildren().add(txtQuantidade);
        vboxGeral.getChildren().add(vbox);

        hbox.getChildren().add(vboxGeral);
        hbox.setId(String.valueOf(dados.get("Id")));

        return hbox;
    }

    public static FlowPane criarTabela(List<Peca> listaItens, int width, int height) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, String> dados;
        FlowPane flowPane = new FlowPane();

        HBox hbox;
        for (Peca item : listaItens){
            dados = getDados(item);

            hbox = criarComponenteNovo(dados, width, height);

            flowPane.getChildren().add(hbox);
        }

        // Configurando o flowPane
        flowPane.setHgap(30);
        flowPane.setVgap(30);

        flowPane.setMinWidth(1280);

        flowPane.setStyle("-fx-background-color: #282828;");

        return flowPane;
    }


    // Guarda apenas os dados selecionados
    private static Map<String, String> getDados(Peca item) throws InvocationTargetException, IllegalAccessException {
        Map<String, String> dados = new HashMap<>();

        dados.put("Nome", item.getNome());
        dados.put("Fabricante", item.getFabricante());
        dados.put("Preco", "R$" + item.getPreco());
        dados.put("Custo", "R$" + item.getCusto());
        dados.put("Quantidade", String.valueOf(item.getQuantidade()));
        dados.put("Id", String.valueOf(item.getId()));

        return dados;
    }
}
