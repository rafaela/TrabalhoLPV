package br.com.academia.controller;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

import br.com.academia.dao.ClienteDAO;
import br.com.academia.modelo.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ClienteController {
	@FXML TableView<Cliente> tabelaContatos;
	
	@FXML TableColumn<Cliente, String> nomeCol;	
	@FXML TableColumn<Cliente, String> sexoCol;
	@FXML TableColumn<Cliente, String> alturaCol;
	@FXML TableColumn<Cliente, String> pesoCol;
	@FXML TableColumn<Cliente, String> whatsappCol;
	@FXML TableColumn<Cliente, String> emailCol;
	@FXML TableColumn<Cliente, String> cpfCol;
	@FXML TableColumn<Cliente, String> dataCol;
	
	@FXML TextField tfNome;
	@FXML TextField tfSexo;
	@FXML TextField tfAltura;
	@FXML TextField tfPeso;
	@FXML TextField tfWhatsapp;
	@FXML TextField tfEmail;
	@FXML TextField tfCpf;
	@FXML TextField tfBusca;
	@FXML DatePicker dpData;
	@FXML Text textCliente;
	
	@FXML Button remover;
	
	private ObservableList<Cliente> clientes;
	private ClienteDAO clienteDAO = new ClienteDAO();
	private Cliente cliente = new Cliente();
	
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
		remover.setVisible(false);
	}
	
	@FXML
	public void adicionaCliente() {
		
		cliente.setNome(tfNome.getText());
		cliente.setSexo(tfSexo.getText());
		cliente.setAltura(Float.parseFloat(tfAltura.getText()));
		cliente.setPeso(Float.parseFloat(tfPeso.getText()));
		cliente.setEmail(tfEmail.getText());
		cliente.setCpf(tfCpf.getText());
		cliente.setWhatsapp(tfWhatsapp.getText());

		//Convertendo a data de DatePicker para Calendar
		Calendar calendar = Calendar.getInstance();
		calendar.set(dpData.getValue().getYear(), 
					dpData.getValue().getMonthValue(), 
					dpData.getValue().getDayOfMonth());
		cliente.setDataNascimento(calendar);
		
		if (cliente.getEmail() !=null) {
			clienteDAO.atualiza(cliente);
			textCliente.setText("Novo Contato");
		}else {
			//adicionando o contato no banco		
			clienteDAO.insereCliente(cliente);
		}				
		
		//limpando os dados do formulário
		limparDados();
		
		//atualizando a lista de contatos no formulário
		clientes = FXCollections.observableList(clienteDAO.listaClientes());		
		tabelaContatos.setItems(clientes);
	}
	
	@FXML
	private void onMouseClicked(MouseEvent event) {
		//verificando se o usuário clicou com o botão esquerdo
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if(event.getClickCount() == 2 && 
					tabelaContatos.getSelectionModel().getSelectedItem() != null) {
				cliente = tabelaContatos.getSelectionModel().getSelectedItem();
				tfNome.setText(cliente.getNome());
				tfSexo.setText(cliente.getSexo());
				tfAltura.setText("" + cliente.getAltura());
				tfPeso.setText("" + cliente.getAltura());
				tfWhatsapp.setText(cliente.getWhatsapp());
				tfEmail.setText(cliente.getEmail());;
				tfCpf.setText(cliente.getCpf());
				
				//convertendo a data para DatePicker
				Instant instant = Instant.ofEpochMilli(
						cliente.getDataNascimento().getTime().getTime());
				LocalDate ld = 
						LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
				dpData.setValue(ld);
				
				textCliente.setText("Editar Contato");
				remover.setVisible(true);
			}
		}
	}
	
	@FXML
	public void removeCliente(){
		clienteDAO.remove(cliente);
		
		//atualizando a lista de contatos no formulário
		clientes = FXCollections.observableList(clienteDAO.listaClientes());		
		tabelaContatos.setItems(clientes);
		limparDados();
	}
	
	@FXML
	public void limparDados() {
		tfNome.clear();
		tfSexo.clear();
		tfAltura.clear();
		tfPeso.clear();
		tfWhatsapp.clear();
		tfEmail.clear();
		tfCpf.clear();
		dpData.setValue(null);

		textCliente.setText("");
	}
}
