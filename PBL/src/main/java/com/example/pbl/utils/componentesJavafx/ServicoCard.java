package com.example.pbl.utils.componentesJavafx;

import com.example.pbl.dao.DAO;
import com.example.pbl.model.Montagem;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicoCard extends ComponenteJavaFx {
//    public static HBox criarComponente(Servico servico, int width, int height){
////        FlowPane flowPane = new FlowPane();
//        Montagem montagem = (Montagem) servico;
//
//        HBox hbox = criarComponente(width, height);
//        Map<String, String> servicoInfos = getServicoInfos(montagem);
//
//        int FONT_SIZE = 15;
//        hbox.setId(String.valueOf(montagem.getId()));
//        // Criando vbox pra receber as informações
//        VBox vbox = new VBox();
//        vbox.setPadding(new Insets(0, 5, 5, 5));
//
//
//        // Criando os labels pra receber os dados
//        Label nomeLabel = new Label(servicoInfos.get("nome"));
//        nomeLabel.setFont(new javafx.scene.text.Font("Arial", FONT_SIZE));
//        nomeLabel.setTextFill(Color.WHITE);
//
//        Label precoLabel = new Label(servicoInfos.get("preco"));
//        precoLabel.setFont(new javafx.scene.text.Font("Arial", FONT_SIZE));
//        precoLabel.setTextFill(Color.WHITE);
//
//        Label custoLabel = new Label(servicoInfos.get("custo"));
//        custoLabel.setFont(new javafx.scene.text.Font("Arial", FONT_SIZE));
//        custoLabel.setTextFill(Color.WHITE);
//
//        Label pecasLabel = new Label(servicoInfos.get("pecas"));
//        pecasLabel.setFont(new javafx.scene.text.Font("Arial", FONT_SIZE));
//        pecasLabel.setTextFill(Color.WHITE);
//
//        Label outrosCompLabel = new Label(servicoInfos.get("outrosComp"));
//        outrosCompLabel.setFont(new javafx.scene.text.Font("Arial", FONT_SIZE));
//        outrosCompLabel.setTextFill(Color.WHITE);
//
//        // Adicionando labels no vbox
//        vbox.getChildren().add(nomeLabel);
//        vbox.getChildren().add(precoLabel);
//        vbox.getChildren().add(custoLabel);
//        vbox.getChildren().add(pecasLabel);
//        vbox.getChildren().add(outrosCompLabel);
//
//        // Adicionando no hbox
//        hbox.getChildren().add(vbox);
////        flowPane.getChildren().add(hbox);
//
//        return hbox;
//    }
//
//    public static FlowPane criarComponente(List<Servico> listaMontagem, int width, int height){
//        FlowPane flowPane = new FlowPane();
////        HBox hboxMaior = new HBox();
//        HBox hbox;
//        for (Servico servico : listaMontagem){
//            hbox = criarComponente(servico, width, height);
////            hboxMaior.getChildren().add(hbox);
//            flowPane.getChildren().add(hbox);
//        }
//
//
//        flowPane.setVgap(20);; // Margin left e right
//        flowPane.setOrientation(Orientation.VERTICAL);
//        flowPane.setMinHeight(height*2*listaMontagem.size() + 20*(listaMontagem.size()-1)); // Tamanho máximo da lista
//
//        return flowPane;
//    }
//
//    public static HBox criarComponenteReduzido(Montagem montagem, int width, int height){
//        HBox hbox = criarComponente(width, height);
//        Map<String, String> servicoInfos = getServicoInfos(montagem);
//
//        int FONT_SIZE = 15;
//        hbox.setId(String.valueOf(montagem.getId()));
//
//        // Criando vbox pra receber as informações
//        VBox vbox = new VBox();
//        vbox.setPadding(new Insets(5, 5, 5, 5));
//
//
//        // Criando os labels pra receber os dados
//        Label nomeLabel = new Label(servicoInfos.get("nome"));
//        nomeLabel.setFont(new javafx.scene.text.Font("Arial", FONT_SIZE));
//        nomeLabel.setTextFill(Color.WHITE);
//
//        // Adicionando labels no vbox
//        vbox.getChildren().add(nomeLabel);
//
//        // Adicionando no hbox
//        hbox.getChildren().add(vbox);
//
//        return hbox;
//    }
//
//    // Percorre uma lista de montagens e as adiciona em um hbox
//    public static FlowPane criarComponenteReduzido(List<Montagem> listaMontagem, int width, int height){
//        FlowPane flowPane = new FlowPane();
////        HBox hboxMaior = new HBox();
//        HBox hbox;
//
//        for (Montagem montagem : listaMontagem){
//            hbox = criarComponenteReduzido(montagem, width, height);
////            hboxMaior.getChildren().add(hbox);
//            flowPane.getChildren().add(hbox);
//        }
//
//
//        flowPane.setHgap(20); // Margin left e right
//        flowPane.setMinWidth(width * listaMontagem.size() + 20*(listaMontagem.size()-1)); // Tamanho máximo da lista
//
//        return flowPane;
//    }
//
//    private static Map<String, String> getServicoInfos(Montagem montagem){
//        Map<String, String> servicoMap = new HashMap<>();
//
//        // Nome do servico montagem
//        String nome = montagem.getDescricao();
//        servicoMap.put("nome", nome);
//
//        // Pegando o preço total
//        String preco = String.valueOf(montagem.getPreco());
//        servicoMap.put("preco", "Preço: " + preco);
//
//        // Pegando o custo total
//        String custo = String.valueOf(montagem.getCusto());
//        servicoMap.put("custo", "Custo: " + custo);
//
//        // Montando a String com a lista de peças e componentes
//        StringBuilder builder = new StringBuilder();
//
//            // Guardando o nome das peças
//        builder.append("Pecas:");
//        for (Peca peca : montagem.getPecas()){
//            builder.append(" ").append(peca.getNome()).append(",");
//            System.out.println(peca.getNome());
//        }
//        String listaPecas = builder.toString();
//        listaPecas = listaPecas.substring(0, listaPecas.length() - 1); // Removendo última vírgula
//
//        servicoMap.put("pecas", listaPecas);
//
//            // Guardando o nome dos "outrosComponentes
//
//        builder.setLength(0); // Limpa o builder
//
//        builder.append("Outros Componentes:");
//        for (OutroComponente outroComp : montagem.getOutrosComponentes()){
//            builder.append(" ").append(outroComp.getDescricao()).append(",");
//        }
//        String listaOutrosComp = builder.toString();
//        listaOutrosComp = listaOutrosComp.substring(0, listaOutrosComp.length() - 1); // Removendo última vírgula
//
//        servicoMap.put("outrosComp", listaOutrosComp);
//
//        return servicoMap;
//    }

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
        for (String nome : dados.keySet()){
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
        for (String nome : dados.keySet()){
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
