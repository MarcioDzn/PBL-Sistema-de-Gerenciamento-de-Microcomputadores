package com.example.pbl.utils.componentesJavafx;

import com.example.pbl.dao.DAO;
import com.example.pbl.model.Cliente;
import com.example.pbl.model.OrdemServico;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteCard extends ComponenteJavaFx {
    private static HBox criarComponenteNovo(Map<String, String> dados, int width, int height){
        int FONT_SIZE = 15;

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(5, 5, 5, 5));

        VBox vboxGeral = new VBox();
        HBox hbox = criarComponente(width, height);

        Label objLabel;
        Label objLabelInfo;
        for (String nome : dados.keySet()){
            if (!nome.equals("Id")){
                objLabel = new Label(nome);
                objLabelInfo = new Label(dados.get(nome));

                objLabel.setFont(new javafx.scene.text.Font("Arial", FONT_SIZE*1.25));
                objLabel.setTextFill(Color.WHITE);

                objLabelInfo.setFont(new javafx.scene.text.Font("Arial", FONT_SIZE));
                objLabelInfo.setTextFill(Color.WHITE);

                vbox.getChildren().add(objLabel);
                vbox.getChildren().add(objLabelInfo);

                vboxGeral.getChildren().add(vbox);

                vbox = new VBox();
                vbox.setPadding(new Insets(5, 5, 5, 5));
            }
        }


        Button button = new Button("Selecionar");
        button.setMinWidth(width);
        button.setId(dados.get("Nome") + "-" + dados.get("Id"));

        vbox.getChildren().add(button);
        vboxGeral.getChildren().add(vbox);

        hbox.getChildren().add(vboxGeral);
        hbox.setId(String.valueOf(dados.get("Id")));

        return hbox;
    }

    public static FlowPane criarTabela(List<Cliente> listaItens, int width, int height) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, String> dados;
        FlowPane flowPane = new FlowPane();

        HBox hbox;
        for (Cliente item : listaItens){
            dados = getDados(item);

            hbox = criarComponenteNovo(dados, width, height);

            flowPane.getChildren().add(hbox);
        }

        // Configurando o flowPane
        flowPane.setHgap(30);
        flowPane.setVgap(30);

        flowPane.setMinHeight(height * (Math.ceil((double) listaItens.size() / 5)) + Math.ceil((double) listaItens.size() / 5)*30); // Tamanho máximo da lista
        flowPane.setMinWidth(1240);

        flowPane.setStyle("-fx-background-color: #282828;");

        return flowPane;
    }


    // Guarda apenas os dados selecionados
    private static Map<String, String> getDados(Cliente item) throws InvocationTargetException, IllegalAccessException {
        Map<String, String> dados = new HashMap<>();

        dados.put("Cliente", item.getNome());
        dados.put("Email", item.getEmail());
        dados.put("Telefone", item.getTelefone());
        dados.put("Endereço", item.getEndereco());
        dados.put("Id", String.valueOf(item.getId()));

        return dados;
    }
}
