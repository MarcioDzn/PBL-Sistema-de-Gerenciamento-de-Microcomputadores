package com.example.pbl.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.pbl.dao.DAO;
import com.example.pbl.exceptions.ObjetoNaoEncontradoException;
import com.example.pbl.model.Cliente;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class GerenciarClientesWindow {

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
    private TableView<Cliente> tblClientes;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtBuscarNome;

    private ObservableList<Cliente> listaClientes;


    @FXML
    void initialize() {
        assert btnCadastrar != null : "fx:id=\"btnCadastrar\" was not injected: check your FXML file 'GerenciarClientesWindow.fxml'.";
        assert btnDeletar != null : "fx:id=\"btnDeletar\" was not injected: check your FXML file 'GerenciarClientesWindow.fxml'.";
        assert btnEditar != null : "fx:id=\"btnEditar\" was not injected: check your FXML file 'GerenciarClientesWindow.fxml'.";
        assert tblClientes != null : "fx:id=\"tblClientes\" was not injected: check your FXML file 'GerenciarClientesWindow.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'GerenciarClientesWindow.fxml'.";
        assert txtEndereco != null : "fx:id=\"txtEndereco\" was not injected: check your FXML file 'GerenciarClientesWindow.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'GerenciarClientesWindow.fxml'.";
        assert txtTelefone != null : "fx:id=\"txtTelefone\" was not injected: check your FXML file 'GerenciarClientesWindow.fxml'.";

        this.listaClientes = FXCollections.observableArrayList();
        this.listaClientes.setAll(DAO.getCliente().buscarTodos());

        this.carregarCSS();
        this.carregarTabela();

        this.txtBuscarNome.setOnKeyReleased(keyEvent -> {
            this.pesquisarCliente();
        });

    }


    @FXML
    void cadastrarAction(ActionEvent event) {
        Cliente novoCliente = this.criarCliente();
        boolean clienteValido = this.validarFormulario(novoCliente);

        if (clienteValido){
            DAO.getCliente().criar(novoCliente);

            // Mostra a tabela toda mesmo se estiver pesquisando algo
            this.listaClientes.clear();
            this.listaClientes.addAll(DAO.getCliente().buscarTodos());
            this.txtBuscarNome.setText("");
            this.limparCampos();
        }

    }

    @FXML
    void deletarAction(ActionEvent event) {
        Cliente clienteRemovido = this.tblClientes.getSelectionModel().getSelectedItem();

        if (clienteRemovido.getOrdensServico().size() == 0) {
            try {
                DAO.getCliente().remover(clienteRemovido);
            } catch (ObjetoNaoEncontradoException e) {
                throw new RuntimeException(e);
            }

            this.listaClientes.clear();
            this.listaClientes.addAll(DAO.getCliente().buscarTodos());

            this.limparCampos();

        }

    }

    @FXML
    void editarAction(ActionEvent event) {
        Cliente cliente = this.tblClientes.getSelectionModel().getSelectedItem();

        if (cliente != null){
            Cliente clienteEditado = this.editarCliente(cliente, this.criarCliente());

            try {
                DAO.getCliente().atualizar(clienteEditado);
            } catch (ObjetoNaoEncontradoException e) {
                throw new RuntimeException(e);
            }

            int index = this.listaClientes.indexOf(cliente);
            this.listaClientes.set(index, clienteEditado);

            this.limparCampos();

            if (this.listaClientes.size() < DAO.getCliente().buscarTodos().size())
                this.txtBuscarNome.setText(clienteEditado.getNome());

            this.pesquisarCliente();
        }

    }


    // Cria o cliente
    private Cliente criarCliente(){
        String nome = this.txtNome.getText();
        String endereco = this.txtEndereco.getText();
        String telefone = this.txtTelefone.getText();
        String email = this.txtEmail.getText();

        Cliente novoCliente = new Cliente(nome, endereco, telefone, email);

        return novoCliente;

    }

    // Verifica as mudanças feitas no cliente e as atribui a ele
    private Cliente editarCliente(Cliente clienteAntigo, Cliente clienteNovo){

        if (!clienteNovo.getNome().equals("")){
            clienteAntigo.setNome(clienteNovo.getNome());
        }
        if (!clienteNovo.getEndereco().equals("")){
            clienteAntigo.setEndereco(clienteNovo.getEndereco());
        }
        if (!clienteNovo.getTelefone().equals("")){
            clienteAntigo.setTelefone(clienteNovo.getTelefone());
        }
        if (!clienteNovo.getEmail().equals("")){
            clienteAntigo.setEmail(clienteNovo.getEmail());
        }

        return clienteAntigo;
    }

    // Verifica se algum textField ficou vazio
    private boolean validarFormulario(Cliente novoCliente){
        this.carregarCSS(); // Carrega o css padrão
        boolean clienteValido = true;

        if (novoCliente.getNome().equals("")){
            this.txtNome.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0;");
            this.txtNome.setPromptText("Nome inválido!");
            clienteValido = false;
        }

        if (novoCliente.getEndereco().equals("")){
            this.txtEndereco.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0; ");
            this.txtEndereco.setPromptText("Endereço inválido!");
            clienteValido = false;
        }

        if (novoCliente.getTelefone().equals("")){
            this.txtTelefone.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0; ");
            this.txtTelefone.setPromptText("Telefone inválido!");
            clienteValido = false;
        }

        if (novoCliente.getEmail().equals("")){
            this.txtEmail.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0; ");
            this.txtEmail.setPromptText("Email inválido!");
            clienteValido = false;
        }

        return clienteValido;
    }

    void pesquisarCliente(){
            listaClientes.clear();
            if ("".equals(txtBuscarNome.getText())){
                listaClientes.addAll(DAO.getCliente().buscarTodos());
            } else{
                for (Cliente cliente : DAO.getCliente().buscarTodos()){
                    if (cliente.getNome().toLowerCase().startsWith(txtBuscarNome.getText().toLowerCase())){
                        listaClientes.add(cliente);
                    }
                }
            }

        if (listaClientes.size() == 0){
            this.txtBuscarNome.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: red; -fx-border-width: 0 0 1.5 0");
        } else{
            this.txtBuscarNome.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        }
    }

    void limparCampos(){
        this.txtNome.clear();
        this.txtEndereco.clear();
        this.txtTelefone.clear();
        this.txtEmail.clear();
        this.txtBuscarNome.clear();

        this.txtNome.setPromptText("");
        this.txtEndereco.setPromptText("");
        this.txtTelefone.setPromptText("");
        this.txtEmail.setPromptText("");
    }

    private void carregarCSS(){
        txtNome.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtEmail.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtEndereco.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtTelefone.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
        txtBuscarNome.setStyle("-fx-text-inner-color: white; -fx-background-color: #282828; -fx-border-color: white; -fx-border-width: 0 0 1.5 0");
    }

    private void carregarTabela(){
        TableColumn nomeCol = new TableColumn("Nome");
        TableColumn enderecoCol = new TableColumn("Endereço");
        TableColumn telefoneCol = new TableColumn("Telefone");
        TableColumn emailCol = new TableColumn("Email");

        nomeCol.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        enderecoCol.setCellValueFactory(new PropertyValueFactory<Cliente, String>("endereco"));
        telefoneCol.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));

        this.tblClientes.getColumns().addAll(nomeCol, enderecoCol, telefoneCol, emailCol);
        this.tblClientes.setItems(this.listaClientes);
    }

}
