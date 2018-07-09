package br.com.academia.controller;
import static br.com.academia.dados.ManipulaDados.leEInsereDados;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class AvaliacaoController {
	@FXML BorderPane root;
	
    @FXML private MenuItem clienteMenuItem;
    @FXML private MenuItem atividadeMenu;
	
    
    @FXML
    public void initialize(){
    	if(LoginController.user.getPapel().equals("admin")){
    		clienteMenuItem.setVisible(true);
    		atividadeMenu.setVisible(true);
    	}
    	else{
    		clienteMenuItem.setVisible(false);
    		atividadeMenu.setVisible(false);
    		
    	}
    }
	
	@FXML
	void chamaRelatorio() {
		try {
			AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("../visual/Relatorio.fxml"));
			root.setCenter(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
	void chamaCliente() {
		try {
			AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("../visual/Cliente.fxml"));
			root.setCenter(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
	void chamaPesquisarCliente() {
		try {
			AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("../visual/PesquisaCliente.fxml"));
			root.setCenter(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
	void chamaPesquisarAtividade() {
		try {
			AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("../visual/PesquisaAtividade.fxml"));
			root.setCenter(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
	void chamaAtividadeSimples() {
		try {
			AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("../visual/Atividade.fxml"));
			root.setCenter(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@FXML
	void chamaAtividadeCliente() {
		try {
			AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("../visual/AtividadeCliente.fxml"));
			root.setCenter(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
	void chamaGraficoBarra() {
		try {
			AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("../visual/GraficoBarra.fxml"));
			root.setCenter(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@FXML
	void chamaImportaArquivo() {
		leEInsereDados();
    }
	
	
	@FXML
	void chamaGraficoGeral() {
		try {
			AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("../visual/GraficoGeral.fxml"));
			root.setCenter(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
	void chamaUsuario() {
		try {
			AnchorPane panel = (AnchorPane)FXMLLoader.load(getClass().getResource("../visual/Usuario.fxml"));
			root.setCenter(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public void chamaLogin(){
		Stage primaryStage = new Stage();
		AnchorPane root = null;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("../visual/Login.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("../visual/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void sair(){
		LoginController.user = null;
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
		chamaLogin();
	}
}
