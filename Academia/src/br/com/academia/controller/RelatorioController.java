package br.com.academia.controller;
import java.util.ArrayList;

import br.com.academia.dados.Relatorio;
import br.com.academia.dao.ClienteDAO;
import br.com.academia.modelo.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class RelatorioController {
	
	@FXML private ComboBox<String> comboCliente;
	@FXML private TextArea areaTexto;

	@FXML
	public void initialize(){
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
	}
	
	@FXML
	public void relatorio(){
		String texto = comboCliente.getSelectionModel().getSelectedItem().split(" - ")[1];
		ClienteDAO clienteDAO = new ClienteDAO();
		areaTexto.setText("");
	
		Cliente cliente = clienteDAO.pesquisarClientePorEmail(texto);
		Relatorio rel = new Relatorio();
		String duracao = rel.obtemMaiorDuracao(cliente) + "\n\n";
		String distancia = rel.obtemMaiorDistancia(cliente) + "\n\n";
		String calorias = rel.obtemMaiorCalorias(cliente) + "\n\n";
		String passos = rel.obtemMaiorPassos(cliente) + "\n\n";
		String velocidade = rel.obtemMaiorVelocidade(cliente) + "\n";
		
		areaTexto.appendText(duracao + distancia + calorias + passos + velocidade);
		areaTexto.setEditable(true);
	}
}
