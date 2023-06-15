package com.example.pbl.utils.componentesJavafx;

import com.example.pbl.dao.DAO;
import com.example.pbl.model.OrdemServico;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class OrdensCard extends ComponenteJavaFx {
    private static HBox criarComponenteNovo(Map<String, String> dados, int width, int height){
        int FONT_SIZE = 15;

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(5, 5, 5, 5));

        VBox vboxGeral = new VBox();
        HBox hbox = criarComponente(width, height);

        Label objLabel;
        Label objLabelInfo;

        List<String> listaNomes = new ArrayList<>(Arrays.asList("Cliente", "Servicos", "Status", "Descrição", "Tecnico", "Metodo de Pagamento"));
        for (String nome : listaNomes){
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


        Button buttonSelecionar = new Button("Selecionar");
        buttonSelecionar.setMinWidth(width);
        buttonSelecionar.setId(dados.get("Nome") + "-" + dados.get("Id"));
        buttonSelecionar.setStyle("-fx-background-color: #bfbfbf;");
        buttonSelecionar.setDisable(true);
        vbox.getChildren().add(buttonSelecionar);

        Button buttonRemover = new Button("Remover");
        buttonRemover.setMinWidth(width);
        buttonRemover.setId(dados.get("Nome") + "-" + dados.get("Id"));
        vbox.getChildren().add(buttonRemover);

        vbox.setSpacing(10);

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

        List<String> listaNomes = new ArrayList<>(Arrays.asList("Cliente", "Servicos", "Status", "Descrição", "Tecnico", "Metodo de Pagamento"));
        for (String nome : listaNomes){
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

        flowPane.setMinWidth(875);

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
        flowPane.setAlignment(Pos.CENTER);
        // Configurando o flowPane
        flowPane.setHgap(30);
        flowPane.setVgap(30);

        flowPane.setMinWidth(875);

        flowPane.setStyle("-fx-background-color: #282828;");

        return flowPane;
    }


    // Guarda apenas os dados selecionados
    private static Map<String, String> getDados(OrdemServico item) throws InvocationTargetException, IllegalAccessException {
        Map<String, String> dados = new HashMap<>();

        dados.put("Cliente", DAO.getCliente().buscarPorId(item.getClienteId()).getNome());

        dados.put("Id", String.valueOf(item.getId()));

        if (!item.getDescricao().equals(""))
            dados.put("Descrição", item.getDescricao());
        else
            dados.put("Descrição", "Indefinido");

        if (item.getTecnico() != null)
            dados.put("Tecnico", item.getTecnico().getNome());
        else
            dados.put("Tecnico", "Indefinido");

        if (!item.getMetodoPagamento().equals(""))
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

        dados.put("Servicos", pegarServicos(item));

        return dados;
    }

    private static String pegarServicos(OrdemServico item){
        StringBuilder builder = new StringBuilder();

        if (item.getInstalacoes().size() > 0)
            builder.append("Instalação\n");

        if (item.getLimpezas().size() > 0)
            builder.append("Limpeza\n");

        if (item.getMontagens().size() > 0)
            builder.append("Montagem\n");


        return builder.toString();
    }
}
