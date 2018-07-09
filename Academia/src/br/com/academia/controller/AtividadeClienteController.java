package br.com.academia.controller;

import static br.com.academia.dados.ManipulaDados.obtemDadosAtividades;
import static br.com.academia.dados.ValidaDados.isDataValida;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import br.com.academia.dao.ClienteDAO;
import br.com.academia.modelo.AtividadeSimples;
import br.com.academia.modelo.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class AtividadeClienteController {
	@FXML TableView<AtividadeSimples> tabela;
	
	@FXML TableColumn<AtividadeSimples, String> nomeCol;
	@FXML TableColumn<AtividadeSimples, String> dataCol;
	@FXML TableColumn<AtividadeSimples, String> tempoCol;
	@FXML TableColumn<AtividadeSimples, String> duracaoCol;
	
	@FXML DatePicker dpInicial;
	@FXML DatePicker dpfinal;
	
	@FXML ComboBox<String> comboCliente;
	
	//private  atividades;
	
	@FXML
	private void initialize() {
		ClienteDAO dao = new ClienteDAO();
		ArrayList<Cliente> cl = dao.listaClientes();
		ArrayList<String> dados = new ArrayList<>();
		
		for(Cliente c : cl){
			String nome = "";
			nome = c.getNome() + " - " + c.getEmail();
			dados.add(nome);
		}
		ObservableList<String> opcoesCliente = FXCollections.observableArrayList(dados);
		comboCliente.setItems(opcoesCliente);
		
		/*nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));
		tempoCol.setCellValueFactory(new PropertyValueFactory<>("tempo"));
		duracaoCol.setCellValueFactory(new PropertyValueFactory<>("duracao"));
		
		tabela.setItems(atividades);
		*/
	}
	
	@FXML
	public void listaAtividades(){
		String texto = comboCliente.getSelectionModel().getSelectedItem().split(" - ")[1];
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.pesquisarClientePorEmail(texto);
		
		String dateInicial = dpInicial.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String dateFinal = dpfinal.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));
		tempoCol.setCellValueFactory(new PropertyValueFactory<>("tempo"));
		duracaoCol.setCellValueFactory(new PropertyValueFactory<>("duracao"));
		
		ObservableList<AtividadeSimples> atividades = FXCollections.observableList(obtemDadosAtividades(cliente, isDataValida(dateInicial), 
				isDataValida(dateFinal)));
		
		tabela.setItems(atividades);
	}
	
	@FXML
	private void onMouseClicked(MouseEvent event) {
		AtividadeSimples atividade = new AtividadeSimples();
		//verificando se o usuário clicou com o botão esquerdo
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if(event.getClickCount() == 2 && 
					tabela.getSelectionModel().getSelectedItem() != null) {
				atividade = tabela.getSelectionModel().getSelectedItem();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText(atividade.toString());
				alert.setTitle("Detalhes da atividade");
				alert.showAndWait();
				
			}
		}
	}	
}
