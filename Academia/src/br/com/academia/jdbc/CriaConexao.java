package br.com.academia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CriaConexao {
	public Connection conexao(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost/academia",
					"postgres", "12345");
		} catch (SQLException e) {
			System.out.println("Não foi possivel conectar ao banco");
		}
		
		return connection;
	}
}
