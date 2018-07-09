package br.com.academia.controller;


import java.util.ArrayList;

import br.com.academia.dao.ClienteDAO;
import br.com.academia.modelo.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PesquisaClienteController {
	@FXML TableView<Cliente> tabelaContatos;
	
	@FXML TableColumn<Cliente, String> nomeCol;	
	@FXML TableColumn<Cliente, String> sexoCol;
	@FXML TableColumn<Cliente, String> alturaCol;
	@FXML TableColumn<Cliente, String> pesoCol;
	@FXML TableColumn<Cliente, String> whatsappCol;
	@FXML TableColumn<Cliente, String> emailCol;
	@FXML TableColumn<Cliente, String> cpfCol;
	@FXML TableColumn<Cliente, String> dataCol;
	
	@FXML TextField tfPesquisa;
	
	private ObservableList<Cliente> clientes;
	private ClienteDAO clienteDAO = new ClienteDAO();
	
	@FXML
	private void initialize() {
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		sexoCol.setCellValueFactory(new PropertyValueFactory<>("sexo"));
		alturaCol.setCellValueFactory(new PropertyValueFactory<>("altura"));
		pesoCol.setCellValueFactory(new PropertyValueFactory<>("peso"));
		dataCol.setCellValueFactory(new PropertyValueFactory<>("datanascimento"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		cpfCol.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		whatsappCol.setCellValueFactory(new PropertyValueFactory<>("whatsapp"));
		
		clientes = FXCollections.observableList(clienteDAO.listaClientes());
		
		tabelaContatos.setItems(clientes);
	}
	
	@FXML
	public void pesquisa(){
		ArrayList<Cliente> lista = clienteDAO.listaClientesPesquisa(tfPesquisa.getText());
		clientes = FXCollections.observableList(lista);
		tabelaContatos.setItems(clientes);
	}
	
}
	