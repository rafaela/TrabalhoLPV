package br.com.academia.controller;
import static br.com.academia.dados.ValidaDados.isDataValida;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.academia.dados.Graficos;
import br.com.academia.dao.ClienteDAO;
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


public class GraficoGeralController {
	
	@FXML private ComboBox<String> comboCliente;
	@FXML private ComboBox<String> comboItem;
	@FXML private ComboBox<String> comboExercicio;
	@FXML private DatePicker pickerInicial;
	@FXML private DatePicker pickerFinal;
	@FXML private Pane pane;
	
	@FXML private Label textoLegenda;

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
		
		ObservableList<String> opcoesBarras = FXCollections.observableArrayList("Passos", "Distância", "Calorias");
		comboItem.setItems(opcoesBarras);
		
	}
	
	@FXML
	public void graficoBarra(){	
		String email = comboCliente.getSelectionModel().getSelectedItem().split(" - ")[1];
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.pesquisarClientePorEmail(email);
		
		final CategoryAxis eixoX = new CategoryAxis();
		final NumberAxis eixoY = new NumberAxis();
		
		Graficos grafico = new Graficos();
		
		String dateIncial = pickerInicial.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String dateFinal = pickerFinal.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		if(dateIncial.equals("") || dateFinal.equals("")){
			comboItem.setVisible(false);
		}
		
		List<AtividadeSimples> listaAtividades = new ArrayList<>();
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		listaAtividades = grafico.obtemDadosGraficos(cliente, isDataValida(dateIncial), isDataValida(dateFinal));
		
		if(!email.equals("") && !dateIncial.equals("") && !dateIncial.equals("")){
			if(!pane.getChildren().isEmpty())
				pane.getChildren().clear();
			if(comboItem.getSelectionModel().getSelectedItem().equals("Distância")){
				BarChart<String, Number> graficoDist = new BarChart<>(eixoX, eixoY);
				List<XYChart.Series<String, Number>> listSerie = new ArrayList<>();
				
				for(AtividadeSimples at : listaAtividades){
					XYChart.Series<String, Number> serie = new XYChart.Series<>();
					serie.getData().add(new Data<>(formatoData.format(at.getData().getTime()), at.getDistancia()));
					listSerie.add(serie);
				}
				graficoDist.getData().addAll(listSerie);
				
				graficoDist.maxWidth(600);
				graficoDist.maxHeight(350);
				
				pane.getChildren().add(graficoDist);
				pane.setVisible(true);
			}
			//}
			if(comboItem.getSelectionModel().getSelectedItem().equals("Calorias")){
				BarChart<String, Number> graficoDist = new BarChart<>(eixoX, eixoY);
				List<XYChart.Series<String, Number>> listSerie = new ArrayList<>();
				
				for(AtividadeSimples at : listaAtividades){
					XYChart.Series<String, Number> serie = new XYChart.Series<>();
					serie.getData().add(new Data<>(formatoData.format(at.getData().getTime()), at.getCalorias()));
					listSerie.add(serie);
				}
				graficoDist.getData().addAll(listSerie);
				
				graficoDist.maxWidth(600);
				graficoDist.maxHeight(350);
				
				pane.getChildren().add(graficoDist);
				pane.setVisible(true);
			}
			if(comboItem.getSelectionModel().getSelectedItem().equals("Passos")){
				BarChart<String, Number> graficoDist = new BarChart<>(eixoX, eixoY);
				List<XYChart.Series<String, Number>> listSerie = new ArrayList<>();
				
				for(AtividadeSimples at : listaAtividades){
					XYChart.Series<String, Number> serie = new XYChart.Series<>();
					serie.getData().add(new Data<>(formatoData.format(at.getData().getTime()), at.getPassos()));
					listSerie.add(serie);
				}
				graficoDist.getData().addAll(listSerie);
				
				graficoDist.maxWidth(600);
				graficoDist.maxHeight(350);
				
				pane.getChildren().add(graficoDist);
				pane.setVisible(true);
			}
		}//if(!email.equals("") && !dateIncial.equals("") && !dateIncial.equals(""))
		
		int totalPassos = 0;
		float distanciaMedia = 0, distanciaTotal = 0, mediaCalorias = 0, totalCalorias = 0;
		for(AtividadeSimples atividade : listaAtividades){
			totalPassos += atividade.getPassos();
			distanciaTotal += atividade.getDistancia();
			totalCalorias += atividade.getCalorias();
		}
		distanciaMedia = distanciaTotal / listaAtividades.size();
		mediaCalorias = totalCalorias / listaAtividades.size();
		String texto = "Total de Passos: " + totalPassos + "\nDistância Média: " + distanciaMedia +
				 "\nDistância Total: " + distanciaTotal + "\nMédia de Calorias perdidas: " + mediaCalorias +
				 "\nTotal de Calorias perdidas: " + totalCalorias;
		textoLegenda.setText(texto);
	}	
		
}
