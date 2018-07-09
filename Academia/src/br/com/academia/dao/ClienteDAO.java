package br.com.academia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.academia.jdbc.CriaConexao;
import br.com.academia.modelo.Cliente;


public class ClienteDAO {
	Connection connection = new CriaConexao().conexao();
	
	public void insereCliente(Cliente cliente){
		String sql = "insert into cliente (nome, sexo, altura, peso, datanascimento, email, cpf, whatsapp)"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, cliente.getNome());
			statement.setString(2, cliente.getSexo());
			statement.setFloat(3, cliente.getAltura());
			statement.setFloat(4, cliente.getPeso());
			statement.setDate(5, new Date(cliente.getDataNascimento().getTimeInMillis()));
			statement.setString(6, cliente.getEmail());
			statement.setString(7, cliente.getCpf());
			statement.setString(8, cliente.getWhatsapp());
			
			statement.execute();
			statement.close();
			
		} catch (SQLException e) {
			System.out.println("Falha ao inserir");
		}
		
	}//inserirCliente
	
	public Cliente pesquisarClientePorEmail(String email){
		Cliente cliente = null;
		String sql = "select * from cliente where email = ?";
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			
			ResultSet rs = statement.executeQuery();
			rs.next();
			cliente = new Cliente();
			cliente.setNome(rs.getString("nome"));
			cliente.setSexo(rs.getString("sexo"));
			cliente.setAltura(rs.getFloat("altura"));
			cliente.setPeso(rs.getFloat("peso"));
			
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("datanascimento"));
			cliente.setDataNascimento(data);
			cliente.setEmail(rs.getString("email"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setWhatsapp(rs.getString("whatsapp"));
			
			rs.close();
			statement.close();
			
			
		} catch (SQLException e) {
		}
		
		return cliente;
	}//pesquisarCliente
	
	public ArrayList<Cliente> listaClientes(){
		ArrayList<Cliente> clientes = new ArrayList<>();
		Cliente cliente;
		String sql = "select * from cliente";

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				cliente = new Cliente();
				cliente.setNome(rs.getString("nome"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setAltura(rs.getFloat("altura"));
				cliente.setPeso(rs.getFloat("peso"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("datanascimento"));
				cliente.setDataNascimento(data);
				cliente.setEmail(rs.getString("email"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setWhatsapp(rs.getString("whatsapp"));
				
				clientes.add(cliente);

			}

			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clientes;
	}
	
	
	public ArrayList<Cliente> listaClientesPesquisa(String nome){
		ArrayList<Cliente> clientes = new ArrayList<>();
		Cliente cliente;
		String sql = "select * from cliente where nome ilike ?";

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, nome + "%");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				cliente = new Cliente();
				cliente.setNome(rs.getString("nome"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setAltura(rs.getFloat("altura"));
				cliente.setPeso(rs.getFloat("peso"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("datanascimento"));
				cliente.setDataNascimento(data);
				cliente.setEmail(rs.getString("email"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setWhatsapp(rs.getString("whatsapp"));
				
				clientes.add(cliente);

			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clientes;
	}
	public void atualiza(Cliente cliente) {
		String sql = "UPDATE cliente SET nome = ?, sexo = ?, altura = ?, peso = ?, datanascimento = ?, email = ?,"
				+ "cpf = ?, whatsapp = ? WHERE email = ?";

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, cliente.getNome());
			statement.setString(2, cliente.getSexo());
			statement.setFloat(3, cliente.getAltura());
			statement.setFloat(4, cliente.getPeso());
			statement.setDate(5, new Date(cliente.getDataNascimento().getTimeInMillis()));
			statement.setString(6, cliente.getEmail());
			statement.setString(7, cliente.getCpf());
			statement.setString(8, cliente.getWhatsapp());
			statement.setString(9, cliente.getEmail());
			
			statement.execute();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(Cliente cliente) {
		String sql = "DELETE FROM cliente WHERE email = ?";

		PreparedStatement statement;

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, cliente.getEmail());
			statement.execute();

			statement.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	

}
