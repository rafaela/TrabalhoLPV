package br.com.academia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.academia.jdbc.CriaConexao;
import br.com.academia.modelo.AtividadeSimples;

public class AtividadeSimplesDAO {
	Connection connection = new CriaConexao().conexao();
	
	public void insereAtividadeSimples(AtividadeSimples atividade, String email){
		String sql = "insert into atividade_simples (nome, data, tempo, duracao, distancia, calorias, "
				+ "passos, email_cliente) values (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement;
		
		try {
			
			statement = connection.prepareStatement(sql);
			statement.setString(1, atividade.getNome());
			statement.setDate(2, new Date(atividade.getData().getTimeInMillis()));
			statement.setString(3, atividade.getTempo());
			statement.setString(4, atividade.getDuracao());
			statement.setFloat(5, atividade.getDistancia());
			statement.setFloat(6, atividade.getCalorias());
			statement.setInt(7, atividade.getPassos());
			statement.setString(8, email);
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir atividade simples");
			e.printStackTrace();
		}
	}
	
	public ArrayList<AtividadeSimples> pesquisaAtividadeCliente(String email){
		ArrayList<AtividadeSimples> exercicios = new ArrayList<>();

		String sql = "select * from atividade_simples where email_cliente = ?";

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				AtividadeSimples atividade = new AtividadeSimples();
				atividade.setId(rs.getLong("id"));
				atividade.setNome(rs.getString("nome"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				atividade.setData(data);
				atividade.setTempo(rs.getString("tempo"));
				atividade.setDuracao(rs.getString("duracao"));
				atividade.setDistacia(rs.getFloat("distancia"));
				atividade.setCalorias(rs.getFloat("calorias"));
				atividade.setPassos(rs.getInt("passos"));
				
				exercicios.add(atividade);
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exercicios;
	}
	
	public ArrayList<AtividadeSimples> listaAtividades(){
		ArrayList<AtividadeSimples> atividades = new ArrayList<>();

		String sql = "select * from atividade_simples";

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				AtividadeSimples atividade = new AtividadeSimples();
				atividade.setId(rs.getLong("id"));
				atividade.setNome(rs.getString("nome"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				atividade.setData(data);
				atividade.setTempo(rs.getString("tempo"));
				atividade.setDuracao(rs.getString("duracao"));
				atividade.setDistacia(rs.getFloat("distancia"));
				atividade.setCalorias(rs.getFloat("calorias"));
				atividade.setPassos(rs.getInt("passos"));
				atividade.setEmail(rs.getString("email_cliente"));
				
				atividades.add(atividade);
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return atividades;
	}
	
	public void atualiza(AtividadeSimples atividade) {
		String sql = "UPDATE atividade_simples SET nome = ?, data = ?, tempo = ?, duracao = ?, distancia = ?,"
				+ "passos = ? WHERE id = ?";

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, atividade.getNome());
			statement.setDate(2, new Date(atividade.getData().getTimeInMillis()));
			statement.setString(3, atividade.getTempo());
			statement.setString(4, atividade.getDuracao());
			statement.setFloat(5, atividade.getDistancia());
			statement.setInt(6, atividade.getPassos());
			statement.setLong(7, atividade.getId());
			
			statement.execute();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(AtividadeSimples atividade) {
		String sql = "DELETE FROM atividade_simples WHERE id = ?";

		PreparedStatement statement;

		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, atividade.getId());
			statement.execute();

			statement.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public ArrayList<AtividadeSimples> listaAtividadesPesquisa(String nome){
		ArrayList<AtividadeSimples> atividades = new ArrayList<>();

		String sql = "select * from atividade_simples where nome ilike ?";

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, nome + "%");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				AtividadeSimples atividade = new AtividadeSimples();
				atividade.setId(rs.getLong("id"));
				atividade.setNome(rs.getString("nome"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				atividade.setData(data);
				atividade.setTempo(rs.getString("tempo"));
				atividade.setDuracao(rs.getString("duracao"));
				atividade.setDistacia(rs.getFloat("distancia"));
				atividade.setCalorias(rs.getFloat("calorias"));
				atividade.setPassos(rs.getInt("passos"));
				atividade.setEmail(rs.getString("email_cliente"));
				
				atividades.add(atividade);
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return atividades;
	}
}
