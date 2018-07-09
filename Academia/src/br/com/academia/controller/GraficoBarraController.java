package br.com.academia.controller;
import static br.com.academia.dados.ManipulaDados.obtemAtividades;
import static br.com.academia.dados.ValidaDados.calculaDuracao;
import static br.com.academia.dados.ValidaDados.isDataValida;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.academia.dados.Graficos;
import br.com.academia.dao.ClienteDAO;
import br.com.academia.modelo.AtividadeCompleta;
import br.com.academia.modelo.AtividadeSimples;
import br.com.academia.modelo.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class GraficoBarraController {
	
	@FXML private ComboBox<String> comboCliente;
	@FXML private ComboBox<String> comboItem;
	@FXML private ComboBox<String> comboExercicio;
	@FXML private DatePicker pickerInicial;
	@FXML private DatePicker pickerFinal;
	@FXML private Pane pane;
	@FXML private Label erro;

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
		
		comboExercicio.setVisible(false);
		
	}
	
	@FXML
	public void carregarDados(){
		comboExercicio.setVisible(true);
		String email = comboCliente.getSelectionModel().getSelectedItem().split(" - ")[1];
		
		ObservableList<String> opcoesBarras = FXCollections.observableArrayList("Duração", "Distância", 
				"Calorias Perdidas", "Passos Dados", "Velocidade Média", "Ritmo Médio");
		comboItem.setItems(opcoesBarras);
		
		
		
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = dao.pesquisarClientePorEmail(email);
		ArrayList<String> dadosExe = new ArrayList<>();
		List<AtividadeSimples> exercicios = obtemAtividades(cliente);
		for(AtividadeSimples atividade : exercicios){
			String exe = "";
			exe = atividade.getNome();
			dadosExe.add(exe);
		}
		ObservableList<String> opcoesExercicios = FXCollections.observableList(dadosExe);
		comboExercicio.setItems(opcoesExercicios);
		
		comboExercicio.getSelectionModel().selectFirst();
		
		pane.getChildren().clear();
	}
	
	@FXML
	public void graficoBarra(){	
		String email = comboCliente.getSelectionModel().getSelectedItem().split(" - ")[1];
		ClienteDAO clienteDAO = new ClienteDAO();
	
		Cliente cliente = clienteDAO.pesquisarClientePorEmail(email);
		
		Graficos grafico = new Graficos();
		
		String dateIncial = pickerInicial.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String dateFinal = pickerFinal.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		//Definindo os eixos
		final CategoryAxis eixoX = new CategoryAxis();
		final NumberAxis eixoY = new NumberAxis();
		
		if(dateIncial.equals("") || dateFinal.equals("")){
			comboItem.setVisible(false);
		}
	
		comboItem.getSelectionModel().selectFirst();
		List<AtividadeSimples> listaAtividades = new ArrayList<>();
		listaAtividades = grafico.obtemDadosGraficos(cliente, isDataValida(dateIncial), isDataValida(dateFinal));
		
		BarChart<String, Number> graficoD = new BarChart<>(eixoX, eixoY);
		graficoD.getData().clear();
		graficoD.maxWidth(973);
		graficoD.maxHeight(328);
		
		for(AtividadeSimples atividade : listaAtividades){
			if(comboExercicio.getSelectionModel().getSelectedItem().equalsIgnoreCase(atividade.getNome())){
		
				if(!email.equals("") && !dateIncial.equals("") && !dateIncial.equals("")){
					
					if(comboItem.getValue().equals("Duração")){
						float duracao = calculaDuracao(atividade.getDuracao());
						
						XYChart.Series<String, Number> serie = new XYChart.Series<>();
						serie.getData().add(new Data<>("Duração", duracao));
						
						graficoD.getData().add(serie);
						
						pane.getChildren().add(graficoD);
						pane.setVisible(true);
					}
					else if(comboItem.getSelectionModel().getSelectedItem().equals("Distância")){
						
						XYChart.Series<String, Number> serie = new XYChart.Series<>();
						serie.getData().add(new Data<>("Distância", atividade.getDistancia()));
						
						graficoD.getData().add(serie);
						
						pane.getChildren().add(graficoD);
						pane.setVisible(true);
						
					}
					
					else if(comboItem.getSelectionModel().getSelectedItem().equals("Calorias Perdidas")){
						XYChart.Series<String, Number> serie = new XYChart.Series<>();
						serie.getData().add(new Data<>("Calorias", atividade.getCalorias()));
						
						graficoD.getData().add(serie);
						
						pane.getChildren().add(graficoD);
						pane.setVisible(true);
	
					}
					else if(comboItem.getSelectionModel().getSelectedItem().equals("Passos Dados")){
						XYChart.Series<String, Number> serie = new XYChart.Series<>();
						serie.getData().add(new Data<>("Passos", atividade.getPassos()));
						
						graficoD.getData().add(serie);
						
						pane.getChildren().add(graficoD);
						pane.setVisible(true);
					}
					else if(atividade instanceof AtividadeCompleta){
						if(comboItem.getSelectionModel().getSelectedItem().equals("Velocidade Média")){
							XYChart.Series<String, Number> serie = new XYChart.Series<>();
							serie.getData().add(new Data<>("Velocidade Média", ((AtividadeCompleta) atividade).getVelocidadeMedia()));
							
							graficoD.getData().add(serie);
							
							pane.getChildren().add(graficoD);
							pane.setVisible(true);
						}
						else if(comboItem.getSelectionModel().getSelectedItem().equals("Ritmo Médio")){
							XYChart.Series<String, Number> serie = new XYChart.Series<>();
							String texto = ((AtividadeCompleta) atividade).getRitmoMedio();
							String n1, n2;
							
							n1 = texto.split("'")[0].replace("'", ",");
							n2 = texto.split("'")[1].replace("\"", "");
							String resultado = n1 + n2;
							
							serie.getData().add(new Data<>("Ritmo Médio", Float.parseFloat(resultado.split(" /")[0])));
							
							graficoD.getData().add(serie);
							
							pane.getChildren().add(graficoD);
							pane.setVisible(true);
						}
					}//if(atividade instanceof AtividadeCompleta)
				}//if(!email.equals("") && !dateIncial.equals("") && !dateIncial.equals(""))
			}
				
				
		}//for
	}	
		
}
