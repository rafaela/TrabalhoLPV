package br.com.academia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.academia.jdbc.CriaConexao;
import br.com.academia.modelo.Ritmo;


public class RitmoDAO {
	Connection connection = new CriaConexao().conexao();
	public void insereRitmo(Ritmo ritmo, Long id_atividade){
		String sql = "insert into ritmo (id_atividade, numkm, minuto, segundo)"
				+ "values (?, ?, ?, ?)";
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id_atividade);
			statement.setFloat(2, ritmo.getNumKm());
			statement.setFloat(3, ritmo.getMinuto());
			statement.setFloat(4, ritmo.getSegundo());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir ritmo");
		}
	}
	
	public ArrayList<Ritmo> pesquisaRitmo(Long id_atividade){
		ArrayList<Ritmo> ritmos = new ArrayList<>();

		String sql = "select * from ritmo where id = ?";

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id_atividade);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Ritmo ritmo = new Ritmo();
				
				ritmo.setNumKm(rs.getFloat("numkm"));
				ritmo.setMinuto(rs.getInt("minuto"));
				ritmo.setSegundo(rs.getInt("segundo"));
				
				ritmos.add(ritmo);
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ritmos;
	}
}
