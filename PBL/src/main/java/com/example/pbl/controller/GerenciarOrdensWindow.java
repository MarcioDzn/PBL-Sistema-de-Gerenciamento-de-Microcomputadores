package com.example.pbl.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

import com.example.pbl.HelloApplication;
import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.OrdemServicoException;
import com.example.pbl.model.Cliente;
import com.example.pbl.model.OrdemServico;
import com.example.pbl.model.Servico;
import com.example.pbl.utils.componentesJavafx.ServicoCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GerenciarOrdensWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnCriarOrdem;

    @FXML
    private Button btnInstalacao;

    @FXML
    private Button btnLimpeza;

    @FXML
    private Button btnMontagem;

    @FXML
    private Button btnCliente;

    @FXML
    private ScrollPane scpaneOrdens;
    @FXML
    private ScrollPane scpaneCliente;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtPagamento;

    private FlowPane flowPaneServicos;
    private FlowPane flowPaneClientes;
    private List<Servico> listaServicos;

    private ServicosWindow servicosMontagemWindow;
    private ServicosLimpezaWindow servicosLimpezaWindow;
    private ServicosInstalacaoWindow servicosInstalacaoWindow;
    private EscolherClienteWindow clienteWindow;
    private AlertWindow alertWindow;

    protected List<Servico> listaMontagensSelecionadas;
    protected List<Servico> listaLimpezasSelecionadas;
    protected List<Servico> listaInstalacoesSelecionadas;

    protected Cliente clienteSelecionado;
    private List<Button> listaBotoesServicos;
    private List<Button> listaBotoesClientes;


    @FXML
    void clienteAction(ActionEvent event) {
        this.abrirPagina("EscolherClienteWindow.fxml", "Cliente");
        this.carregarScrollPaneCliente();
    }

    @FXML
    void cancelarAction(ActionEvent event) {
        this.clienteSelecionado = null;

        this.listaInstalacoesSelecionadas.clear();
        this.listaMontagensSelecionadas.clear();
        this.listaLimpezasSelecionadas.clear();
        this.listaServicos.clear();

        this.carregarScrollPaneCliente();
        this.carregarScrollPaneServico();
    }

    @FXML
    void criarOrdemAction(ActionEvent event) {
        try {
            this.acionarAlert("AlertWindow.fxml");
            if (this.alertWindow.getConfirmacao()) {
                if (this.clienteSelecionado != null) {
                    OrdemServico ordem = new OrdemServico(this.clienteSelecionado.getId());

                    for (Servico servicoMontagem : listaMontagensSelecionadas)
                        ordem.addServicos(servicoMontagem, 1);

                    for (Servico servicoLimpeza : listaLimpezasSelecionadas)
                        ordem.addServicos(servicoLimpeza, 1);

                    for (Servico servicoInstalacao : listaInstalacoesSelecionadas)
                        ordem.addServicos(servicoInstalacao, 1);

                    ordem.setDescricao(this.txtDescricao.getText());
                    ordem.setMetodoPagamento(this.txtPagamento.getText());

                    if (!(this.listaMontagensSelecionadas.isEmpty() && this.listaLimpezasSelecionadas.isEmpty() && this.listaInstalacoesSelecionadas.isEmpty())) {
                        DAO.getOrdemServico().criar(ordem);

                        this.clienteSelecionado = null;

                        this.listaInstalacoesSelecionadas.clear();
                        this.listaMontagensSelecionadas.clear();
                        this.listaLimpezasSelecionadas.clear();
                        this.listaServicos.clear();

                        this.carregarScrollPaneCliente();
                        this.carregarScrollPaneServico();
                    }
                }
            }

        } catch (OrdemServicoException e){
                throw new RuntimeException(e);
        }

    }

    @FXML
    void instalacaoAction(ActionEvent event) {
        this.abrirPagina("ServicosInstalacaoWindow.fxml", "Instalacao");

        this.listaServicos.addAll(this.listaInstalacoesSelecionadas);
        this.carregarScrollPaneServico();


    }

    @FXML
    void limpezaAction(ActionEvent event) {
        this.abrirPagina("ServicosLimpezaWindow.fxml", "Limpeza");

        this.listaServicos.addAll(this.listaLimpezasSelecionadas);
        this.carregarScrollPaneServico();

    }

    @FXML
    void montagemAction(ActionEvent event) {
        this.abrirPagina("ServicosWindow.fxml", "Montagem");
        this.listaServicos.addAll(this.listaMontagensSelecionadas);
        this.carregarScrollPaneServico();

    }

    private void abrirPagina(String url, String tipo){
        try {
            FXMLLoader loader = new FXMLLoader(); // Carrega o arquivo do scene builder
            URL xmlURL = HelloApplication.class.getResource(url); // Pega o XML e carrega pra ser utilizado
            loader.setLocation(xmlURL);

            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();

            if (tipo.equals("Montagem")){
                this.servicosMontagemWindow = loader.getController();
                this.setMontagemController();

            } else if (tipo.equals("Limpeza")){
                this.servicosLimpezaWindow = loader.getController();
                this.setLimpezaController();

            } else if (tipo.equals("Instalacao")){
                this.servicosInstalacaoWindow = loader.getController();
                this.setInstalacaoController();

            } else if(tipo.equals("Cliente")){
                this.clienteWindow = loader.getController();
                this.setClienteController();
            }

            stage.setTitle("Nome do Dialog");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void acionarAlert(String url){
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


            stage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'GerenciarOrdensWindow.fxml'.";
        assert btnCriarOrdem != null : "fx:id=\"btnCriarOrdem\" was not injected: check your FXML file 'GerenciarOrdensWindow.fxml'.";
        assert btnInstalacao != null : "fx:id=\"btnInstalacao\" was not injected: check your FXML file 'GerenciarOrdensWindow.fxml'.";
        assert btnLimpeza != null : "fx:id=\"btnLimpeza\" was not injected: check your FXML file 'GerenciarOrdensWindow.fxml'.";
        assert btnMontagem != null : "fx:id=\"btnMontagem\" was not injected: check your FXML file 'GerenciarOrdensWindow.fxml'.";
        assert scpaneOrdens != null : "fx:id=\"scpaneOrdens\" was not injected: check your FXML file 'GerenciarOrdensWindow.fxml'.";

        this.listaLimpezasSelecionadas = new LinkedList<>();
        this.listaMontagensSelecionadas = new LinkedList<>();
        this.listaInstalacoesSelecionadas = new LinkedList<>();

        this.listaServicos = new LinkedList<>();
        this.listaBotoesServicos = new LinkedList<>();
        this.listaBotoesClientes = new LinkedList<>();

        this.carregarScrollPaneServico();
        this.carregarCSS();

    }

    void selecionarBotoesServicos(){
        HBox hboxMontagem;

        this.listaBotoesServicos.clear();

        for (Node montagem : this.flowPaneServicos.getChildren()){
            Button botao;
            hboxMontagem = (HBox) montagem;

            for (Node node : hboxMontagem.getChildren()){
                if (node instanceof VBox){
                    for (Node mostInnerNode : ((VBox) node).getChildren()){
                        if (mostInnerNode instanceof VBox){
                            for (Node innerNode: ((VBox) mostInnerNode).getChildren()){
                                if (innerNode instanceof Button){
                                    botao = (Button) innerNode;
                                    this.listaBotoesServicos.add(botao);
                                }
                            }
                        }
                    }
                }
            }
        }
        this.removerServico();
    }

    void removerServico(){
        for (Button botao : this.listaBotoesServicos){
            botao.setOnAction(e -> {
                int index = this.listaBotoesServicos.indexOf(botao);
                this.listaBotoesServicos.remove(index);
                this.listaServicos.remove(index);
                this.carregarScrollPaneServico();
                this.selecionarBotoesServicos();
            });
        }
    }

    private void carregarScrollPaneServico(){
        try {
            try {
                List<String> listaNomes = new LinkedList<>();
                listaNomes.add("Descricao");
                this.flowPaneServicos = ServicoCard.criarTabelaMenor(this.listaServicos, listaNomes, 432, 0);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            this.scpaneOrdens.setContent(this.flowPaneServicos);

            this.scpaneOrdens.setPannable(true);

            this.scpaneOrdens.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            this.scpaneOrdens.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            this.selecionarBotoesServicos();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    void selecionarBotaoCliente(){
        HBox hboxMontagem;

        this.listaBotoesClientes.clear();

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
                                    this.listaBotoesClientes.add(botao);
                                }
                            }
                        }
                    }
                }
            }
        }
        this.removerCliente();
    }

    void removerCliente(){
        for (Button botao : this.listaBotoesClientes){
            botao.setOnAction(e -> {
                int index = this.listaBotoesClientes.indexOf(botao);
                this.listaBotoesClientes.remove(index);
                this.clienteSelecionado = null;
                this.carregarScrollPaneCliente();
                this.selecionarBotaoCliente();
            });
        }
    }

    private void carregarScrollPaneCliente(){
        if (this.clienteSelecionado != null){
            try {
                try {
                    List<String> listaNomes = new LinkedList<>();
                    listaNomes.add("Nome");

                    List<Cliente> listaClientes = new LinkedList<>();
                    listaClientes.add(this.clienteSelecionado);

                    this.flowPaneClientes = ServicoCard.criarTabelaMenor(listaClientes, listaNomes, 380, 93);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                this.scpaneCliente.setContent(this.flowPaneClientes);

                this.scpaneCliente.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                this.scpaneCliente.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

                this.selecionarBotaoCliente();
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        } else{
            this.scpaneCliente.setContent(new FlowPane());
        }

    }

    void carregarCSS(){
        this.scpaneCliente.setStyle("-fx-background-color: white; -fx-background:  #2C2C2C;");
        this.scpaneOrdens.setStyle("-fx-background-color: white; -fx-background: white;");
    }

    // Passa esse controler pra a página escolhida pra ela mandar a lista de serviços selecionados pra cá
    public void setMontagemController(){
        this.servicosMontagemWindow.gerenciarOrdensController = this;
    }
    public void setLimpezaController(){
        this.servicosLimpezaWindow.gerenciarOrdensController = this;
    }
    public void setInstalacaoController(){
        this.servicosInstalacaoWindow.gerenciarOrdensController = this;
    }
    public void setClienteController(){
        this.clienteWindow.gerenciarOrdensController = this;
    }
}
