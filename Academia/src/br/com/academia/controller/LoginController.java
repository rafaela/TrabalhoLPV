package br.com.academia.controller;

import java.io.IOException;

import br.com.academia.dao.UsuarioDAO;
import br.com.academia.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {
	public static Usuario user;
	@FXML Label textoAcao;

	@FXML TextField tfLogin;
	@FXML PasswordField pfSenha;

	@FXML
	protected void acaoBotao() {
		user = new Usuario();
		Stage stage = new Stage();
		Parent root = null;
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();
		
		usuario = usuarioDAO.pesquisarUsuario(tfLogin.getText());
		
		if (usuario != null) {
			Stage stageLogin = (Stage) tfLogin.getScene().getWindow();
			usuario.setLogado(true);
			user = usuario;
			try {
				root = FXMLLoader.load(getClass().getResource("../visual/Avaliacao.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Scene scene = new Scene(root, 1000, 500);
			scene.getStylesheets().add(getClass().getResource("../visual/application.css").toExternalForm());
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);	
			stageLogin.hide();
			stage.setTitle("Academia");
			stage.show();
			
		} else {
			textoAcao.setText("Usuário ou senha inválido!");
			tfLogin.clear();
			pfSenha.clear();
		}

	}
	
	
}
