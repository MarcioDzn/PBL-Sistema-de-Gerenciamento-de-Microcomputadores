package com.example.pbl.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

import com.example.pbl.HelloApplication;
import com.example.pbl.dao.DAO;
import com.example.pbl.model.Cliente;
import com.example.pbl.utils.componentesJavafx.ClienteCard;
import com.example.pbl.utils.componentesJavafx.ServicoCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EscolherClienteWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnFinalizar;

    @FXML
    private Button btnNovoCliente;

    @FXML
    private ScrollPane scClientes;

    private FlowPane flowPaneClientes;
    private List<Cliente> listaClientes;
    private Map<Button, String> mapBotoes;

    private Cliente clienteSelecionado;

    GerenciarOrdensWindow gerenciarOrdensController;
    private AlertWindow alertWindow;

    @FXML
    void cancelarAction(ActionEvent event) {

        this.acionarAlert("AlertWindow.fxml", "Cancelar?");
        if (this.alertWindow.getConfirmacao()) {
            this.fecharJanela(event);
        }
    }

    @FXML
    void finalizarAction(ActionEvent event) {
        this.acionarAlert("AlertWindow.fxml", "Finalizar?");
        if (this.alertWindow.getConfirmacao()) {
            for (Button botao : this.mapBotoes.keySet()) {
                if (botao.getText().equals("Selecionado")) {
                    String idBotao = this.mapBotoes.get(botao);
                    int indexHifen = idBotao.indexOf("-");
                    int idDAO = Integer.parseInt(idBotao.substring(indexHifen + 1));

                    this.clienteSelecionado = DAO.getCliente().buscarPorId(idDAO);
                }
            }

            this.gerenciarOrdensController.clienteSelecionado = this.clienteSelecionado;

            this.fecharJanela(event);
        }
    }

    @FXML
    void novoClienteAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'EscolherClienteWindow.fxml'.";
        assert btnFinalizar != null : "fx:id=\"btnFinalizar\" was not injected: check your FXML file 'EscolherClienteWindow.fxml'.";
        assert btnNovoCliente != null : "fx:id=\"btnNovoCliente\" was not injected: check your FXML file 'EscolherClienteWindow.fxml'.";
        assert scClientes != null : "fx:id=\"scClientes\" was not injected: check your FXML file 'EscolherClienteWindow.fxml'.";

        this.listaClientes = new LinkedList<>();
        this.listaClientes.addAll(DAO.getCliente().buscarTodos());

        this.mapBotoes = new HashMap<>();

        this.carregarScrollPaneCliente();
        this.selecionarBotoesClientes();
        this.mudarStatusBotao();
    }

    private void acionarAlert(String url, String texto){
        try {
            FXMLLoader loader = new FXMLLoader(); // Carrega o arquivo do scene builder
            URL xmlURL = HelloApplication.class.getResource(url); // Pega o XML e carrega pra ser utilizado
            loader.setLocation(xmlURL);

            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();

            this.alertWindow = loader.getController();

            stage.setTitle("Nome do Dialog");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);

            this.alertWindow.setTexto(texto);
            stage.showAndWait();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void fecharJanela(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        stage.close();
    }

    private void carregarScrollPaneCliente(){
        try {
            try {
                this.flowPaneClientes = ClienteCard.criarTabela(this.listaClientes, 196, 255);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            this.scClientes.setContent(this.flowPaneClientes);

            this.scClientes.setPannable(true);
            this.scClientes.setFitToHeight(true);

            this.scClientes.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            this.scClientes.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    void selecionarBotoesClientes(){
        HBox hboxMontagem;

        for (Node montagem : this.flowPaneClientes.getChildren()){
            Button botao;
            hboxMontagem = (HBox) montagem;

            for (Node node : hboxMontagem.getChildren()){
                if (node instanceof VBox){
                    for (Node mostInnerNode : ((VBox) node).getChildren()){
                        if (mostInnerNode instanceof VBox){
                            for (Node innerNode: ((VBox) mostInnerNode).getChildren()){
                                if (innerNode instanceof Button){
                                    botao = (Button) innerNode;
                                    this.mapBotoes.put(botao, botao.getId());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void mudarStatusBotao(){
        for (Button botao : this.mapBotoes.keySet()){
            botao.setOnAction(e -> {
                for (Button botaoGeral : this.mapBotoes.keySet()){
                    botaoGeral.setText("Selecionar");
                    botaoGeral.setStyle("-fx-background-color: white;");
                }

                if (botao.getText().equals("Selecionar")){
                    botao.setText("Selecionado");
                    botao.setStyle("-fx-background-color: #bfbfbf;");
                }
            });
        }
    }

}
