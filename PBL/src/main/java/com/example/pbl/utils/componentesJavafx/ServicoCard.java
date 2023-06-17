package com.example.pbl.utils.componentesJavafx;

import com.example.pbl.dao.DAO;
import com.example.pbl.model.Montagem;
import com.example.pbl.model.OutroComponente;
import com.example.pbl.model.Peca;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ServicoCard extends ComponenteJavaFx {
    // Pega os dados dos getters e setters e armazena em um hashmap
    private static <T> Map<String, String> getDados(T item) throws InvocationTargetException, IllegalAccessException {
        Map<String, String> dados = new HashMap<>();

        Class<?> classeItem = item.getClass();
        Class<?> classePaiItem = classeItem.getSuperclass();

        Method[] metodos = classeItem.getDeclaredMethods();
        Method[] metodosPai = classePaiItem.getDeclaredMethods();

        Object retorno;
        for (Method metodo : metodos) {
            dados.put("Nome", item.getClass().getSimpleName());
            if (metodo.getName().startsWith("get")) {
                if (!metodo.getName().equals("getOrdensServico")) {
                    retorno = metodo.invoke(item);

                    if (!(retorno instanceof List<?>)) {
                        if (retorno instanceof Integer)
                            dados.put(metodo.getName().substring(3), String.valueOf(retorno));
                        else
                            dados.put(metodo.getName().substring(3), (String) retorno);
                    }
                }
            }
        }

        for (Method metodoId : metodosPai){
            if (metodoId.getName().startsWith("get")) {
                if (!metodoId.getName().equals("getOrdensServico")) {
                    retorno = metodoId.invoke(item);

                    if (!(retorno instanceof List<?>)) {
                        dados.put(metodoId.getName().substring(3), retorno.toString());
                    }
                }
            }
        }

        if (item instanceof Montagem){
            StringBuilder builder = new StringBuilder();

            for (Peca peca : ((Montagem) item).getPecas())
                builder.append(peca.getNome()).append(", ");

            for (OutroComponente pecaExtra : ((Montagem) item).getOutrosComponentes())
                builder.append(pecaExtra.getDescricao()).append(", ");

            String texto = builder.toString();
            dados.put("Peças", texto.substring(0, texto.length() - 2));
        }

        return dados;
    }



    private static HBox criarComponenteNovo(Map<String, String> dados, int width, int height){
        int FONT_SIZE = 15;

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(5, 5, 5, 5));

        VBox vboxGeral = new VBox();
        HBox hbox = criarComponente(width, height);

        Label objLabel;
        Label objLabelInfo;

        List<String> listaNomes = new ArrayList<>(Arrays.asList("Nome", "Descricao", "Preco", "Custo"));
        if (dados.containsKey("Peças"))
            listaNomes.add("Peças");

        for (String nome : listaNomes){
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


        Button button = new Button("Selecionar");
        button.setMinWidth(width);
        button.setId(dados.get("Nome") + "-" + dados.get("Id"));

        vbox.getChildren().add(button);
        vboxGeral.getChildren().add(vbox);

        hbox.getChildren().add(vboxGeral);
        hbox.setId(String.valueOf(dados.get("Id")));

        return hbox;
    }


    // Teste de método pra receber tipo genérico e retornar infos dos getters
    public static <T> FlowPane criarTabela(List<T> listaItens, int width, int height) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, String> dados;
        FlowPane flowPane = new FlowPane();

        HBox hbox;
        for (T item : listaItens){
            dados = getDados(item);

            hbox = criarComponenteNovo(dados, width, height);

            flowPane.getChildren().add(hbox);
        }

        // Configurando o flowPane
        flowPane.setHgap(30);
        flowPane.setVgap(30);

        flowPane.setMinHeight(height * (Math.ceil((double) listaItens.size() / 5)) + Math.ceil((double) listaItens.size() / 5)*60); // Tamanho máximo da lista
        flowPane.setMinWidth(1240);

        flowPane.setStyle("-fx-background-color: #282828;");

        return flowPane;
    }


    // Guarda apenas os dados selecionados
    private static <T> Map<String, String> getDados(T item, List<String> nomes) throws InvocationTargetException, IllegalAccessException {
        Map<String, String> dados = new HashMap<>();

        Class<?> classeItem = item.getClass();
        Class<?> classePaiItem = classeItem.getSuperclass();

        Method[] metodos = classeItem.getDeclaredMethods();
        Method[] metodosPai = classePaiItem.getDeclaredMethods();

        Object retorno;
        dados.put("Nome", item.getClass().getSimpleName());

        for (Method metodo : metodos) {
            if (nomes.contains(metodo.getName().substring(3) )){
                if (metodo.getName().startsWith("get")) {
                    try {
                        retorno = metodo.invoke(item);

                        if (!(retorno instanceof List<?>)){
                            if (retorno instanceof Integer)
                                dados.put(metodo.getName().substring(3), String.valueOf(retorno));
                            else
                                dados.put(metodo.getName().substring(3), (String) retorno);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        for (Method metodoId : metodosPai){
            if (nomes.contains(metodoId.getName().substring(3) )) {
                if (metodoId.getName().startsWith("get")) {
                    retorno = metodoId.invoke(item);

                    if (!(retorno instanceof List<?>)) {
                        dados.put(metodoId.getName().substring(3), retorno.toString());
                    }
                }
            }
        }

        if (item instanceof Montagem){
            StringBuilder builder = new StringBuilder();

            for (Peca peca : ((Montagem) item).getPecas())
                builder.append(peca.getNome()).append(", ");

            for (OutroComponente pecaExtra : ((Montagem) item).getOutrosComponentes())
                builder.append(pecaExtra.getDescricao()).append(", ");

            String texto = builder.toString();
            dados.put("Peças", texto.substring(0, texto.length() - 2));
        }

        return dados;
    }

    private static HBox criarComponenteNovoMenor(Map<String, String> dados, int width, int height){
        int FONT_SIZE = 13;

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(2, 2, 2, 2));

        VBox vboxGeral = new VBox();
        HBox hbox = criarComponente(width, height);

        Label objLabel;
        Label objLabelInfo;

        List<String> listaNomes = new ArrayList<>(Arrays.asList("Nome", "Descricao"));
        if (dados.containsKey("Peças"))
            listaNomes.add("Peças");

        for (String nome : listaNomes){
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
            vbox.setPadding(new Insets(3, 3, 3, 3));
        }


        Button button = new Button("Deletar");
        button.setMinWidth(width * 0.20);

        button.setId(dados.get("Nome") + "-" + dados.get("Id"));

        vbox.getChildren().add(button);
        vbox.setAlignment(Pos.BOTTOM_RIGHT);

        vboxGeral.getChildren().add(vbox);

        hbox.getChildren().add(vboxGeral);
        hbox.setId(String.valueOf(dados.get("Id")));

        return hbox;
    }

    public static <T> FlowPane criarTabelaMenor(List<T> listaItens, List<String> listaNomes, int width, int height) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, String> dados;
        FlowPane flowPane = new FlowPane();


        HBox hbox;
        for (T item : listaItens){
            dados = getDados(item, listaNomes);

            hbox = criarComponenteNovoMenor(dados, width, height);

            flowPane.getChildren().add(hbox);
        }

        // Configurando o flowPane
        flowPane.setHgap(15);
        flowPane.setVgap(15);

        flowPane.setMinHeight(height * (Math.ceil((double) listaItens.size() / 5)) + Math.ceil((double) listaItens.size() / 5)*60); // Tamanho máximo da lista
        flowPane.setMinWidth(433);
        flowPane.setMinHeight(548);

        flowPane.setStyle("-fx-background-color: #282828;");

        return flowPane;
    }
}
