package com.example.pbl.utils.componentesJavafx;

import com.example.pbl.dao.DAO;
import com.example.pbl.model.OrdemServico;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdensCard extends ComponenteJavaFx {
    private static HBox criarComponenteNovo(Map<String, String> dados, int width, int height){
        int FONT_SIZE = 15;

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(5, 5, 5, 5));

        VBox vboxGeral = new VBox();
        HBox hbox = criarComponente(width, height);

        Label objLabel;
        Label objLabelInfo;
        for (String nome : dados.keySet()){
            if (!nome.equals("Id")) {
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
        }


        Button button = new Button("Selecionar");
        button.setMinWidth(width);
        button.setId(dados.get("Nome") + "-" + dados.get("Id"));
        button.setStyle("-fx-background-color: #bfbfbf;");
        button.setDisable(true);

        vbox.getChildren().add(button);
        vboxGeral.getChildren().add(vbox);

        hbox.getChildren().add(vboxGeral);
        hbox.setId(String.valueOf(dados.get("Id")));

        return hbox;
    }

    private static HBox criarComponenteNovoOrdemAtual(Map<String, String> dados, int width, int height){
        int FONT_SIZE = 15;

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(5, 5, 5, 5));

        VBox vboxGeral = new VBox();
        HBox hbox = criarComponente(width, height);

        Label objLabel;
        Label objLabelInfo;
        for (String nome : dados.keySet()){
            if (!nome.equals("Id")) {
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
        }


        Button buttonFinalizar = new Button("Finalizar");
        buttonFinalizar.setMinWidth(width);
        buttonFinalizar.setId(dados.get("Nome") + "-" + dados.get("Id"));
        vbox.getChildren().add(buttonFinalizar);

        Button buttonCancelar = new Button("Cancelar");

        buttonCancelar.setMinWidth(width);
        buttonCancelar.setId(dados.get("Nome") + "-" + dados.get("Id"));
        vbox.getChildren().add(buttonCancelar);

        Button buttonEditar = new Button("Editar");
        buttonEditar.setMinWidth(width);
        buttonEditar.setId(dados.get("Nome") + "-" + dados.get("Id"));
        vbox.getChildren().add(buttonEditar);

        vbox.setSpacing(10);

        vboxGeral.getChildren().add(vbox);

        hbox.getChildren().add(vboxGeral);
        hbox.setId(String.valueOf(dados.get("Id")));

        return hbox;
    }

    public static FlowPane criarTabela(List<OrdemServico> listaItens, int width, int height) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, String> dados;
        FlowPane flowPane = new FlowPane();

        HBox hbox;
        for (OrdemServico item : listaItens){
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

    public static FlowPane criarTabelaOrdemAtual(List<OrdemServico> listaItens, int width, int height) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, String> dados;
        FlowPane flowPane = new FlowPane();

        HBox hbox;
        for (OrdemServico item : listaItens){
            dados = getDados(item);

            hbox = criarComponenteNovoOrdemAtual(dados, width, height);

            flowPane.getChildren().add(hbox);
        }

        // Configurando o flowPane
        flowPane.setHgap(30);
        flowPane.setVgap(30);

        flowPane.setMinWidth(1280);
//        flowPane.setMinWidth(width * listaItens.size() + listaItens.size()*49.8);

        flowPane.setStyle("-fx-background-color: #282828;");

        return flowPane;
    }


    // Guarda apenas os dados selecionados
    private static Map<String, String> getDados(OrdemServico item) throws InvocationTargetException, IllegalAccessException {
        Map<String, String> dados = new HashMap<>();

        dados.put("Cliente", DAO.getCliente().buscarPorId(item.getClienteId()).getNome());

        dados.put("Id", String.valueOf(item.getId()));

        if (item.getDescricao() != null)
            dados.put("Descrição", item.getDescricao());
        else
            dados.put("Descrição", "Indefinido");

        if (item.getTecnico() != null)
            dados.put("Tecnico", item.getTecnico().getNome());
        else
            dados.put("Tecnico", "Indefinido");

        if (item.getMetodoPagamento() != null)
            dados.put("Metodo de Pagamento", item.getMetodoPagamento());
        else
            dados.put("Metodo de Pagamento", "Indefinido");

        if (item.isCancelado())
            dados.put("Status", "Cancelado");

        else if (item.isFinalizado())
            dados.put("Status", "Finalizado");

        else if (item.isEmAberto())
            dados.put("Status", "Em Aberto");

        else if (item.isEmAndamento())
            dados.put("Status", "Em Andamento");
        return dados;
    }
}
