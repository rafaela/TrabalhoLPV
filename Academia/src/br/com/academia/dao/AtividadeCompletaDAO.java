package br.com.academia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.academia.jdbc.CriaConexao;
import br.com.academia.modelo.AtividadeCompleta;
import br.com.academia.modelo.AtividadeSimples;

public class AtividadeCompletaDAO {
	Connection connection = new CriaConexao().conexao();
	
	public void insereAtividadeCompleta(AtividadeCompleta atividade, String email){
		String sql = "insert into atividade_completa (data, nome, tempo, duracao, distancia, "
				+ "calorias, passos, velocidade_media, velocidade_maxima, "
				+ "ritmo_medio, ritmo_maximo, menor_elevacao, maior_elevacao, email_cliente)"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setDate(1, new Date(atividade.getData().getTimeInMillis()));
			statement.setString(2, atividade.getNome());
			statement.setString(3, atividade.getTempo());
			statement.setString(4, atividade.getDuracao());
			statement.setFloat(5, atividade.getDistancia());
			statement.setFloat(6, atividade.getCalorias());
			statement.setInt(7, atividade.getPassos());
			statement.setFloat(8, atividade.getVelocidadeMedia());
			statement.setFloat(9, atividade.getVelocidadeMaxima());
			statement.setString(10, atividade.getRitmoMedio());
			statement.setString(11, atividade.getRitmoMaximo());
			statement.setFloat(12, atividade.getMenorElevacao());
			statement.setFloat(13, atividade.getMaiorElevacao());
			statement.setString(14, email);
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir atividade completa");
		}
	}
	

	public ArrayList<AtividadeCompleta> pesquisarAtividadeCliente(String email){
		ArrayList<AtividadeCompleta> exercicios = new ArrayList<>();

		String sql = "select * from atividade_completa where email_cliente = ?";

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				AtividadeCompleta atividade = new AtividadeCompleta();
				
				atividade.setId_atividade(rs.getLong("id"));
				atividade.setNome(rs.getString("nome"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				atividade.setData(data);
				atividade.setTempo(rs.getString("tempo"));
				atividade.setDuracao(rs.getString("duracao"));
				atividade.setDistacia(rs.getFloat("distancia"));
				atividade.setCalorias(rs.getFloat("calorias"));
				atividade.setPassos(rs.getInt("passos"));
				atividade.setVelocidadeMedia(rs.getFloat("velocidade_media"));
				atividade.setVelocidadeMaxima(rs.getFloat("velocidade_maxima"));
				atividade.setRitmoMedio(rs.getString("ritmo_medio"));
				atividade.setRitmoMaximo(rs.getString("ritmo_medio"));
				atividade.setMenorElevacao(rs.getFloat("menor_elevacao"));
				atividade.setMaiorElevacao(rs.getFloat("maior_elevacao"));
				
				exercicios.add(atividade);
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exercicios;
	}
	
	public AtividadeCompleta pesquisarData(Calendar data){
		AtividadeCompleta atividade = null;
		String sql = "select * from atividade_completa where data = ?";
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setDate(1, new Date(data.getTimeInMillis()));
			
			ResultSet rs = statement.executeQuery();
			rs.next();
			
			atividade = new AtividadeCompleta();
			atividade.setId_atividade(rs.getLong("id"));
			atividade.setNome(rs.getString("nome"));
			Calendar date = Calendar.getInstance();
			date.setTime(rs.getDate("data"));
			atividade.setData(date);
			atividade.setTempo(rs.getString("tempo"));
			atividade.setDuracao(rs.getString("duracao"));
			atividade.setDistacia(rs.getFloat("distancia"));
			atividade.setCalorias(rs.getFloat("calorias"));
			atividade.setPassos(rs.getInt("passos"));
			atividade.setVelocidadeMedia(rs.getFloat("velocidade_media"));
			atividade.setVelocidadeMaxima(rs.getFloat("velocidade_maxima"));
			atividade.setRitmoMedio(rs.getString("ritmo_medio"));
			atividade.setRitmoMaximo(rs.getString("ritmo_medio"));
			atividade.setMenorElevacao(rs.getFloat("menor_elevacao"));
			atividade.setMaiorElevacao(rs.getFloat("maior_elevacao"));

			
			rs.close();
			statement.close();
			
			
		} catch (SQLException e) {
			System.out.println("Não cadastrado");
		}
		
		return atividade;
	}//pesquisarData
	
	public AtividadeCompleta pesquisarTempo(String tempo){
		AtividadeCompleta atividade = null;
		String sql = "select * from atividade_completa where tempo = ?";
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, tempo);
			
			ResultSet rs = statement.executeQuery();
			rs.next();
			atividade = new AtividadeCompleta();
			atividade.setId_atividade(rs.getLong("id"));
			atividade.setNome(rs.getString("nome"));
			Calendar date = Calendar.getInstance();
			date.setTime(rs.getDate("data"));
			atividade.setData(date);
			atividade.setTempo(rs.getString("tempo"));
			atividade.setDuracao(rs.getString("duracao"));
			atividade.setDistacia(rs.getFloat("distancia"));
			atividade.setCalorias(rs.getFloat("calorias"));
			atividade.setPassos(rs.getInt("passos"));
			atividade.setVelocidadeMedia(rs.getFloat("velocidade_media"));
			atividade.setVelocidadeMaxima(rs.getFloat("velocidade_maxima"));
			atividade.setRitmoMedio(rs.getString("ritmo_medio"));
			atividade.setRitmoMaximo(rs.getString("ritmo_medio"));
			atividade.setMenorElevacao(rs.getFloat("menor_elevacao"));
			atividade.setMaiorElevacao(rs.getFloat("maior_elevacao"));

			
			rs.close();
			statement.close();
			
			
		} catch (SQLException e) {
		}
		
		return atividade;
	}//pesquisarData
	
	
	public AtividadeCompleta pesquisarID(String email){
		AtividadeCompleta atividade = null;
		String sql = "select * from atividade_completa where email_cliente = ?";
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			
			ResultSet rs = statement.executeQuery();
			rs.next();
			atividade = new AtividadeCompleta();
			atividade.setId_atividade(rs.getLong("id"));
			atividade.setNome(rs.getString("nome"));
			Calendar date = Calendar.getInstance();
			date.setTime(rs.getDate("data"));
			atividade.setData(date);
			atividade.setTempo(rs.getString("tempo"));
			atividade.setDuracao(rs.getString("duracao"));
			atividade.setDistacia(rs.getFloat("distancia"));
			atividade.setCalorias(rs.getFloat("calorias"));
			atividade.setPassos(rs.getInt("passos"));
			atividade.setVelocidadeMedia(rs.getFloat("velocidade_media"));
			atividade.setVelocidadeMaxima(rs.getFloat("velocidade_maxima"));
			atividade.setRitmoMedio(rs.getString("ritmo_medio"));
			atividade.setRitmoMaximo(rs.getString("ritmo_medio"));
			atividade.setMenorElevacao(rs.getFloat("menor_elevacao"));
			atividade.setMaiorElevacao(rs.getFloat("maior_elevacao"));

			
			rs.close();
			statement.close();
			
			
		} catch (SQLException e) {
		}
		
		return atividade;
	}
	
	public void atualiza(AtividadeCompleta atividade) {
		String sql = "UPDATE atividade_completa SET nome = ?, data = ?, tempo = ?, duracao = ?, distancia = ?,"
				+ "velocidade_media = ?, velocidade_maxima = ?, ritmo_medio = ?, ritmo_maximo = ?, "
				+ "menor_elevacao = ?, maior_elevacao = ? passos = ? WHERE id = ?";

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, atividade.getNome());
			statement.setDate(2, new Date(atividade.getData().getTimeInMillis()));
			statement.setString(3, atividade.getTempo());
			statement.setString(4, atividade.getDuracao());
			statement.setFloat(5, atividade.getDistancia());
			statement.setInt(6, atividade.getPassos());
			statement.setFloat(8, atividade.getVelocidadeMedia());
			statement.setFloat(9, atividade.getVelocidadeMaxima());
			statement.setString(10, atividade.getRitmoMedio());
			statement.setString(11, atividade.getRitmoMaximo());
			statement.setFloat(12, atividade.getMenorElevacao());
			statement.setFloat(13, atividade.getMaiorElevacao());
			statement.setLong(14, atividade.getId());
			
			
			statement.execute();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(AtividadeSimples atividade) {
		String sql = "DELETE FROM atividade_completa WHERE id = ?";

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

	public ArrayList<AtividadeCompleta> listaAtividadesPesquisa(String nome){
		ArrayList<AtividadeCompleta> exercicios = new ArrayList<>();

		String sql = "select * from atividade_completa where nome ilike ?";

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, nome + "%");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				AtividadeCompleta atividade = new AtividadeCompleta();
				
				atividade.setId_atividade(rs.getLong("id"));
				atividade.setNome(rs.getString("nome"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				atividade.setData(data);
				atividade.setTempo(rs.getString("tempo"));
				atividade.setDuracao(rs.getString("duracao"));
				atividade.setDistacia(rs.getFloat("distancia"));
				atividade.setCalorias(rs.getFloat("calorias"));
				atividade.setPassos(rs.getInt("passos"));
				atividade.setVelocidadeMedia(rs.getFloat("velocidade_media"));
				atividade.setVelocidadeMaxima(rs.getFloat("velocidade_maxima"));
				atividade.setRitmoMedio(rs.getString("ritmo_medio"));
				atividade.setRitmoMaximo(rs.getString("ritmo_medio"));
				atividade.setMenorElevacao(rs.getFloat("menor_elevacao"));
				atividade.setMaiorElevacao(rs.getFloat("maior_elevacao"));
				
				exercicios.add(atividade);
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exercicios;
	}
	
	public ArrayList<AtividadeCompleta> listaAtividades(){
		ArrayList<AtividadeCompleta> exercicios = new ArrayList<>();

		String sql = "select * from atividade_completa";

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				AtividadeCompleta atividade = new AtividadeCompleta();
				
				atividade.setId_atividade(rs.getLong("id"));
				atividade.setNome(rs.getString("nome"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				atividade.setData(data);
				atividade.setTempo(rs.getString("tempo"));
				atividade.setDuracao(rs.getString("duracao"));
				atividade.setDistacia(rs.getFloat("distancia"));
				atividade.setCalorias(rs.getFloat("calorias"));
				atividade.setPassos(rs.getInt("passos"));
				atividade.setVelocidadeMedia(rs.getFloat("velocidade_media"));
				atividade.setVelocidadeMaxima(rs.getFloat("velocidade_maxima"));
				atividade.setRitmoMedio(rs.getString("ritmo_medio"));
				atividade.setRitmoMaximo(rs.getString("ritmo_medio"));
				atividade.setMenorElevacao(rs.getFloat("menor_elevacao"));
				atividade.setMaiorElevacao(rs.getFloat("maior_elevacao"));
				
				exercicios.add(atividade);
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exercicios;
	}
	
}
