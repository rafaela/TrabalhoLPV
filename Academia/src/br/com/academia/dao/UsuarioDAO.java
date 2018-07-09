package br.com.academia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.academia.jdbc.CriaConexao;
import br.com.academia.modelo.Usuario;

public class UsuarioDAO {
	Connection connection = new CriaConexao().conexao();
	/**
	 * Insere dados na tabela ritmo
	 * @param ritmo ritmo a ser inserido
	 * @param id_atividade código de identificação da atividade
	 */
	public void insere(Usuario usuario){
		String sql = "insert into usuario (usuario, senha, papel)"
				+ "values (?, ?, ?)";
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getUsuario());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, usuario.getPapel());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir ritmo");
		}
	}
	
	/**
	 * Pesquisa se em determinada data possui informações sobre ritmo
	 * @param id_atividade data a ser pesquisada
	 * @return <code>ArrayList</code> contendo os ritmos encontrados
	 */
	public List<Usuario> listaUsuarios(){
		List<Usuario> usuarios = new ArrayList<>();

		String sql = "select * from usuario";

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				
				usuario.setId(rs.getLong("id"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPapel(rs.getString("papel"));
				
				usuarios.add(usuario);
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
	}
	
	public void atualiza(Usuario usuario) {
		String sql = "UPDATE usuario SET usuario = ?, senha = ?, papel = ?, logado = ? WHERE id = ?";

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getUsuario());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, usuario.getPapel());
			statement.setBoolean(4, usuario.isLogado());
			statement.setLong(5, usuario.getId());
			
			statement.execute();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(Usuario usuario) {
		String sql = "DELETE FROM usuario WHERE if = ?";

		PreparedStatement statement;

		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, usuario.getId());
			statement.execute();

			statement.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public Usuario pesquisarUsuario(String user){
		Usuario usuario = null;
		String sql = "select * from usuario where usuario = ?";
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, user);
			
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				usuario = new Usuario();
				usuario.setId(rs.getLong("id"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPapel(rs.getString("papel"));
			}
			
			rs.close();
			statement.close();
			return usuario;
			
		} catch (SQLException e) {
		}
		
		return null;
	}//pesquisarCliente

}
