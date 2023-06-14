package com.example.pbl.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.exceptions.QuantidadeException;
import com.example.pbl.model.Cliente;
import com.example.pbl.model.Montagem;
import com.example.pbl.model.Peca;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GerenciarPecasWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnEditar;

    @FXML
    private TableView<Peca> tblPecas;

    @FXML
    private TextField txtFabricante;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtQuantidade;
    private ObservableList<Peca> listaPecas;


    @FXML
    private TextField txtPreco;

    @FXML
    private TextField txtCusto;

    @FXML
    private TextField txtBuscarNome;

    private Peca criarPeca(){
        String nome = this.txtNome.getText();
        String fabricante = this.txtFabricante.getText();

        int quantidade;
        if (this.txtQuantidade.getText().equals("") || !this.verificarTipoTexto(this.txtQuantidade.getText()))
            quantidade = -1;
        else
            quantidade = Integer.parseInt(this.txtQuantidade.getText());

        double preco;
        if (this.txtPreco.getText().equals("") || !this.verificarTipoTexto(this.txtPreco.getText()))
            preco = -1.0;
        else
            preco = Double.parseDouble(this.txtPreco.getText());

        double custo;
        if (this.txtCusto.getText().equals("") || !this.verificarTipoTexto(this.txtCusto.getText()))
            custo = -1.0;
        else
            custo = Double.parseDouble(this.txtCusto.getText());

        return new Peca(quantidade, custo, preco, nome, fabricante);
    }

    // Verifica se o valor inserido é string ou não
    private boolean verificarTipoTexto(String valor){
        try{
            Double.parseDouble(valor);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    // Verifica se algum textField ficou vazio
    private boolean validarFormulario(Peca novaPeca){
        this.carregarCSS(); // Carrega o css padrão
        boolean pecaValida = true;

        if (novaPeca.getNome().equals("")){
            this.txtNome.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0;");
            this.txtNome.setPromptText("Nome inválido!");
            pecaValida = false;
        }

        if (novaPeca.getFabricante().equals("")){
            this.txtFabricante.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0; ");
            this.txtFabricante.setPromptText("Fabricante inválido!");
            pecaValida = false;
        }

        if (novaPeca.getQuantidade() < 0){
            this.txtQuantidade.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0; ");
            this.txtQuantidade.setPromptText("Quantidade inválida!");
            pecaValida = false;
        }

        if (novaPeca.getPreco() < 0){
            this.txtPreco.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0; ");
            this.txtPreco.setPromptText("Preço inválido!");
            pecaValida = false;
        }

        if (novaPeca.getCusto() < 0){
            this.txtCusto.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0; ");
            this.txtCusto.setPromptText("Custo inválido!");
            pecaValida = false;
        }

        return pecaValida;
    }

    private void carregarCSS(){
        txtNome.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtFabricante.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtQuantidade.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtPreco.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtCusto.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtBuscarNome.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
    }

    void limparCampos(){
        this.txtNome.clear();
        this.txtFabricante.clear();
        this.txtQuantidade.clear();
        this.txtPreco.clear();
        this.txtCusto.clear();

        this.limparPrompText();
    }


    void limparPrompText(){
        this.txtNome.setPromptText("");
        this.txtFabricante.setPromptText("");
        this.txtQuantidade.setPromptText("");
        this.txtPreco.setPromptText("");
        this.txtCusto.setPromptText("");
    }
    private Peca editarPeca(Peca pecaAntiga, Peca pecaNova){
        // VERIFICAR PRA QUANDO INSERIR UM VALOR INVALIDO EM QUANTIDADE PRECO E CUSTO
        // VERIFICAR PRA QUANDO INSERIR UM VALOR INVALIDO EM QUANTIDADE PRECO E CUSTO
        // VERIFICAR PRA QUANDO INSERIR UM VALOR INVALIDO EM QUANTIDADE PRECO E CUSTO
        // VERIFICAR PRA QUANDO INSERIR UM VALOR INVALIDO EM QUANTIDADE PRECO E CUSTO
        // VERIFICAR PRA QUANDO INSERIR UM VALOR INVALIDO EM QUANTIDADE PRECO E CUSTO
        // VERIFICAR PRA QUANDO INSERIR UM VALOR INVALIDO EM QUANTIDADE PRECO E CUSTO
        // VERIFICAR PRA QUANDO INSERIR UM VALOR INVALIDO EM QUANTIDADE PRECO E CUSTO
        // VERIFICAR PRA QUANDO INSERIR UM VALOR INVALIDO EM QUANTIDADE PRECO E CUSTO
        // VERIFICAR PRA QUANDO INSERIR UM VALOR INVALIDO EM QUANTIDADE PRECO E CUSTO
        // VERIFICAR PRA QUANDO INSERIR UM VALOR INVALIDO EM QUANTIDADE PRECO E CUSTO
        // VERIFICAR PRA QUANDO INSERIR UM VALOR INVALIDO EM QUANTIDADE PRECO E CUSTO
        // VERIFICAR PRA QUANDO INSERIR UM VALOR INVALIDO EM QUANTIDADE PRECO E CUSTO
        // VERIFICAR PRA QUANDO INSERIR UM VALOR INVALIDO EM QUANTIDADE PRECO E CUSTO

        if (!pecaNova.getNome().equals("")){
            pecaAntiga.setNome(pecaNova.getNome());
        }
        if (!pecaNova.getFabricante().equals("")){
            pecaAntiga.setFabricante(pecaNova.getFabricante());
        }
        if (!(pecaNova.getQuantidade() == -1)){
            try {
                pecaAntiga.setQuantidade(pecaNova.getQuantidade());
            } catch (QuantidadeException e) {
                throw new RuntimeException(e);
            }
        }
        if (!(pecaNova.getCusto() == -1)){
            pecaAntiga.setCusto(pecaNova.getCusto());
        }

        if (!(pecaNova.getPreco() == -1)){
            pecaAntiga.setPreco(pecaNova.getPreco());
        }

        return pecaAntiga;
    }

    @FXML
    void cadastrarAction(ActionEvent event) {
        Peca novaPeca = this.criarPeca();
        boolean pecaValida = this.validarFormulario(novaPeca);

        if (pecaValida){
            DAO.getPeca().criar(novaPeca);

            this.listaPecas.clear();
            this.listaPecas.addAll(DAO.getPeca().buscarTodos());
            this.txtBuscarNome.setText("");
            this.limparCampos();
        }
    }

    boolean podeRemover(Peca peca){
        for (Montagem montagem : peca.getMontagens()){
            if (montagem.getOrdensServico().size() > 0)
                return false;
        }

        return true;
    }

    @FXML
    void deletarAction(ActionEvent event) {
        Peca pecaRemovida = this.tblPecas.getSelectionModel().getSelectedItem();

        if (podeRemover(pecaRemovida)) {
            try {
                DAO.getPeca().remover(pecaRemovida);
            } catch (ObjetoNaoEncontradoException e) {
                throw new RuntimeException(e);
            }

            this.listaPecas.clear();
            this.listaPecas.addAll(DAO.getPeca().buscarTodos());

            this.limparCampos();
        }
    }

    @FXML
    void editarAction(ActionEvent event) {
        this.carregarCSS();
        this.limparPrompText();


        Peca peca = this.tblPecas.getSelectionModel().getSelectedItem();

        if (peca != null) {
            Peca pecaEditada = this.editarPeca(peca, this.criarPeca());

            try {
                DAO.getPeca().atualizar(pecaEditada);
            } catch (ObjetoNaoEncontradoException e) {
                throw new RuntimeException(e);
            }

            int index = this.listaPecas.indexOf(peca);
            this.listaPecas.set(index, pecaEditada);

            this.limparCampos();

            if (this.listaPecas.size() < DAO.getPeca().buscarTodos().size())
                this.txtBuscarNome.setText(pecaEditada.getNome());

            this.pesquisarPeca();
        }
//        int index = this.tblPecas.getSelectionModel().getSelectedIndex();
//
//        if (index >= 0){
//            Peca peca = this.listaPecas.get(index);
//            Peca pecaEditada = this.editarPeca(peca, this.criarPeca());
//
//            this.listaPecas.set(index, pecaEditada);
//
//            try {
//                DAO.getPeca().atualizar(pecaEditada);
//            } catch (ObjetoNaoEncontradoException e) {
//                throw new RuntimeException(e);
//            }
//
//            this.limparCampos();
//        }
    }

    @FXML
    void initialize() {
        assert btnCadastrar != null : "fx:id=\"btnCadastrar\" was not injected: check your FXML file 'GerenciarPecasWindow.fxml'.";
        assert btnDeletar != null : "fx:id=\"btnDeletar\" was not injected: check your FXML file 'GerenciarPecasWindow.fxml'.";
        assert btnEditar != null : "fx:id=\"btnEditar\" was not injected: check your FXML file 'GerenciarPecasWindow.fxml'.";
        assert tblPecas != null : "fx:id=\"tblPecas\" was not injected: check your FXML file 'GerenciarPecasWindow.fxml'.";
        assert txtFabricante != null : "fx:id=\"txtFabricante\" was not injected: check your FXML file 'GerenciarPecasWindow.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'GerenciarPecasWindow.fxml'.";
        assert txtQuantidade != null : "fx:id=\"txtQuantidade\" was not injected: check your FXML file 'GerenciarPecasWindow.fxml'.";

        this.listaPecas = FXCollections.observableArrayList();
        this.listaPecas.addAll(DAO.getPeca().buscarTodos());

        this.carregarTabela();
        this.carregarCSS();

        this.txtBuscarNome.setOnKeyReleased(keyEvent -> {
            this.pesquisarPeca();
        });
    }

    void pesquisarPeca(){
        listaPecas.clear();
        if ("".equals(txtBuscarNome.getText())){
            listaPecas.addAll(DAO.getPeca().buscarTodos());
        } else{
            for (Peca peca : DAO.getPeca().buscarTodos()){
                if (peca.getNome().toLowerCase().startsWith(txtBuscarNome.getText().toLowerCase())){
                    listaPecas.add(peca);
                }
            }
        }

        if (listaPecas.size() == 0){
            this.txtBuscarNome.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0");
        } else{
            this.txtBuscarNome.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        }
    }

    private void carregarTabela(){
        TableColumn nomeCol = new TableColumn("Nome");
        TableColumn quantidadeCol = new TableColumn("Quantidade");
        TableColumn precoCol = new TableColumn("Preço");
        TableColumn custoCol = new TableColumn("Custo");

        nomeCol.setCellValueFactory(new PropertyValueFactory<Peca, String>("nome"));
        quantidadeCol.setCellValueFactory(new PropertyValueFactory<Peca, Integer>("quantidade"));
        precoCol.setCellValueFactory(new PropertyValueFactory<Peca, Double>("preco"));
        custoCol.setCellValueFactory(new PropertyValueFactory<Peca, Double>("custo"));

        this.tblPecas.getColumns().addAll(nomeCol, quantidadeCol, precoCol, custoCol);
        this.tblPecas.setItems(this.listaPecas);
    }

}
