package br.com.academia.controller;

import static br.com.academia.dados.ManipulaDados.obtemAtividades;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.academia.dao.AtividadeCompletaDAO;
import br.com.academia.dao.AtividadeSimplesDAO;
import br.com.academia.dao.ClienteDAO;
import br.com.academia.modelo.AtividadeCompleta;
import br.com.academia.modelo.AtividadeSimples;
import br.com.academia.modelo.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class AtividadeController {
	@FXML TableView<AtividadeSimples> tabela;
	
	@FXML TableColumn<AtividadeSimples, String> emailCol;	
	@FXML TableColumn<AtividadeSimples, String> nomeCol;
	@FXML TableColumn<AtividadeSimples, String> dataCol;
	@FXML TableColumn<AtividadeSimples, String> tempoCol;
	@FXML TableColumn<AtividadeSimples, String> duracaoCol;
	
	@FXML TextField tfNome;
	@FXML TextField tftempo;
	@FXML TextField tfDuracao;
	@FXML TextField tfDistancia;
	@FXML TextField tfPassos;
	@FXML TextField tfVelMedia;
	@FXML TextField tfVelMax;
	@FXML TextField tfRitMedio;
	@FXML TextField tfRitMax;
	@FXML TextField tfMaiorElev;
	@FXML TextField tfMenorElev;
	
	@FXML Text textAtividade;
	
	@FXML DatePicker dpData;
	
	@FXML Button remover;
	
	@FXML ComboBox<String> comboCliente;
	
	private ObservableList<AtividadeSimples> atividades;
	private AtividadeSimplesDAO atividadeDAO = new AtividadeSimplesDAO();
	private AtividadeCompletaDAO completaDAO = new AtividadeCompletaDAO();
	private AtividadeSimples atividade = new AtividadeSimples();
	
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
		
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));
		tempoCol.setCellValueFactory(new PropertyValueFactory<>("tempo"));
		duracaoCol.setCellValueFactory(new PropertyValueFactory<>("duracao"));
		
		tabela.setItems(atividades);
		remover.setVisible(false);
		
	}
	
	@FXML
	public void listaAtividades(){
		String texto = comboCliente.getSelectionModel().getSelectedItem().split(" - ")[1];
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.pesquisarClientePorEmail(texto);
		
		atividades = FXCollections.observableList(obtemAtividades(cliente));
		
		tabela.setItems(atividades);
	}
	
	@FXML
	public void adiciona() {
		
		atividade.setNome(tfNome.getText());
		//Convertendo a data de DatePicker para Calendar
		Calendar calendar = Calendar.getInstance();
		calendar.set(dpData.getValue().getYear(), 
					dpData.getValue().getMonthValue(), 
					dpData.getValue().getDayOfMonth());
		atividade.setData(calendar);
		
		atividade.setTempo(tftempo.getText());
		atividade.setDuracao(tfDuracao.getText());
		atividade.setDistacia(Float.parseFloat(tfDistancia.getText()));
		atividade.setPassos(Integer.parseInt(tfPassos.getText()));
		if(atividade instanceof AtividadeCompleta){
			((AtividadeCompleta) atividade).setVelocidadeMaxima(Float.parseFloat(tfVelMax.getText()));
			((AtividadeCompleta) atividade).setVelocidadeMedia(Float.parseFloat(tfVelMax.getText()));
			((AtividadeCompleta) atividade).setRitmoMaximo(tfRitMax.getText());
			((AtividadeCompleta) atividade).setRitmoMaximo(tfRitMedio.getText());
			((AtividadeCompleta) atividade).setMaiorElevacao(Float.parseFloat(tfMaiorElev.getText()));
			((AtividadeCompleta) atividade).setMenorElevacao(Float.parseFloat(tfMenorElev.getText()));
			
		}
		
		if (atividade.getId() != null) {
			if(atividade instanceof AtividadeCompleta)
				completaDAO.atualiza((AtividadeCompleta) atividade);
			else
				atividadeDAO.atualiza(atividade);
			
			textAtividade.setText("Editar Atividade");
		}				
		
		limparDados();
		
		tabela.setItems(atividades);
	}
	
	@FXML
	private void onMouseClicked(MouseEvent event) {
		//verificando se o usuário clicou com o botão esquerdo
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if(event.getClickCount() == 2 && 
					tabela.getSelectionModel().getSelectedItem() != null) {
				atividade = tabela.getSelectionModel().getSelectedItem();
				tfNome.setText(atividade.getNome());				
				//convertendo a data para DatePicker
				Instant instant = Instant.ofEpochMilli(
						atividade.getData().getTime().getTime());
				LocalDate ld = 
						LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
				dpData.setValue(ld);
				
				tftempo.setText(atividade.getTempo());
				tfDuracao.setText(atividade.getDuracao());
				tfDistancia.setText("" + atividade.getDistancia());
				tfPassos.setText("" + atividade.getPassos());
				if(atividade instanceof AtividadeCompleta){
					tfVelMedia.setText("" + ((AtividadeCompleta) atividade).getVelocidadeMedia());
					tfVelMax.setText("" + ((AtividadeCompleta) atividade).getVelocidadeMaxima());
					tfRitMedio.setText(((AtividadeCompleta) atividade).getRitmoMedio());
					tfRitMax.setText(((AtividadeCompleta) atividade).getRitmoMedio());
					tfMaiorElev.setText("" + ((AtividadeCompleta) atividade).getMaiorElevacao());
					tfMenorElev.setText("" + ((AtividadeCompleta) atividade).getMenorElevacao());
				}
				
				
				textAtividade.setText("Editar Contato");
				remover.setVisible(true);
			}
		}
	}
	
	@FXML
	public void remove(){
		atividadeDAO.remove(atividade);
		
		//atualizando a lista de contatos no formulário
		atividades = FXCollections.observableList(atividadeDAO.listaAtividades());		
		tabela.setItems(atividades);
		limparDados();
	}
	
	@FXML
	public void limparDados() {
		tfNome.clear();
		dpData.setValue(null);
		tftempo.clear();
		tfDuracao.clear();
		tfDistancia.clear();
		tfPassos.clear();
		tfVelMedia.clear();
		tfVelMax.clear();
		tfRitMedio.clear();
		tfRitMax.clear();
		tfMaiorElev.clear();
		tfMenorElev.clear();

		textAtividade.setText("");
	}
}
