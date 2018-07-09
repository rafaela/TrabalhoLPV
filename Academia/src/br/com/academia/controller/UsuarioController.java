package br.com.academia.controller;


import br.com.academia.dao.UsuarioDAO;
import br.com.academia.modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class UsuarioController {
	@FXML TableView<Usuario> tabela;
	
	@FXML TableColumn<Usuario, String> usuarioCol;	
	@FXML TableColumn<Usuario, String> papelCol;
	
	@FXML TextField tfUsuario;
	@FXML TextField tfPapel;
	@FXML PasswordField pfSenha;
	
	
	@FXML Button remover;
	@FXML Button btn;
	
	private ObservableList<Usuario> usuarios;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario usuario = new Usuario();
	
	@FXML
	private void initialize() {
		usuarioCol.setCellValueFactory(new PropertyValueFactory<>("usuario"));
		papelCol.setCellValueFactory(new PropertyValueFactory<>("papel"));
		
		
		usuarios = FXCollections.observableList(usuarioDAO.listaUsuarios());
		
		tabela.setItems(usuarios);
		remover.setVisible(false);
	}
	
	@FXML
	public void adiciona() {
		
		usuario.setUsuario(tfUsuario.getText());
		usuario.setSenha(pfSenha.getText());
		usuario.setPapel(tfPapel.getText());
		
		if (btn.getText().equals("Alterar")) 
			usuarioDAO.atualiza(usuario);
		else 
			usuarioDAO.insere(usuario);
			
		limparDados();
		
		btn.setText("Gravar");
		//atualizando a lista de contatos no formulário
		usuarios = FXCollections.observableList(usuarioDAO.listaUsuarios());		
		tabela.setItems(usuarios);
	}
	
	@FXML
	private void onMouseClicked(MouseEvent event) {
		//verificando se o usuário clicou com o botão esquerdo
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if(event.getClickCount() == 2 && 
					tabela.getSelectionModel().getSelectedItem() != null) {
				usuario = tabela.getSelectionModel().getSelectedItem();
				tfUsuario.setText(usuario.getUsuario());
				tfPapel.setText(usuario.getPapel());
				pfSenha.setText(usuario.getSenha());
				
				btn.setText("Alterar");
				remover.setVisible(true);
			}
		}
	}
	
	@FXML
	public void remove(){
		usuarioDAO.remove(usuario);
		
		//atualizando a lista de contatos no formulário
		usuarios = FXCollections.observableList(usuarioDAO.listaUsuarios());		
		tabela.setItems(usuarios);
		limparDados();
	}
	
	@FXML
	public void limparDados() {
		tfUsuario.clear();
		tfPapel.clear();
		pfSenha.clear();
	}
}
