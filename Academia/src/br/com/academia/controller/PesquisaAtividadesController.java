package br.com.academia.controller;

import static br.com.academia.dados.ManipulaDados.obtemAtividadesPeriodo;
import static br.com.academia.dados.ManipulaDados.obtemTodasAtividades;
import static br.com.academia.dados.ValidaDados.isDataValida;

import java.time.format.DateTimeFormatter;

import br.com.academia.modelo.AtividadeSimples;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class PesquisaAtividadesController {
	@FXML TableView<AtividadeSimples> tabela;
	
	@FXML TableColumn<AtividadeSimples, String> emailCol;	
	@FXML TableColumn<AtividadeSimples, String> nomeCol;
	@FXML TableColumn<AtividadeSimples, String> dataCol;
	@FXML TableColumn<AtividadeSimples, String> tempoCol;
	@FXML TableColumn<AtividadeSimples, String> duracaoCol;
	
	@FXML TextField tfNome;
	@FXML DatePicker dpInicial;
	@FXML DatePicker dpFinal;
	
	
	private ObservableList<AtividadeSimples> atividades;
	
	@FXML
	private void initialize() {
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));
		tempoCol.setCellValueFactory(new PropertyValueFactory<>("tempo"));
		duracaoCol.setCellValueFactory(new PropertyValueFactory<>("duracao"));
		
		atividades = FXCollections.observableArrayList(obtemTodasAtividades());
		tabela.setItems(atividades);
		
	}
	
	@FXML
	public void pesquisa(){
		String dateIncial = dpInicial.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String dateFinal = dpFinal.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		atividades = FXCollections.observableList(obtemAtividadesPeriodo(tfNome.getText(),
				isDataValida(dateIncial), isDataValida(dateFinal)));
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