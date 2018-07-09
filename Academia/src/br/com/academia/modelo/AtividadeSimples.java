package br.com.academia.modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AtividadeSimples {
	private Long id;
	private String nome;
	private Calendar data;
	private String tempo;
	private String duracao;
	private float distancia;
	private float calorias;
	private int passos;
	private String email;
	
	public AtividadeSimples() {
		data = Calendar.getInstance();
	}

	public AtividadeSimples(Long id, String nome, Calendar data, String tempo, String duracao, float distancia,
			float calorias, int passos) {
		super();
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.tempo = tempo;
		this.duracao = duracao;
		this.distancia = distancia;
		this.calorias = calorias;
		this.passos = passos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistacia(float distacia) {
		this.distancia = distacia;
	}

	public float getCalorias() {
		return calorias;
	}

	public void setCalorias(float calorias) {
		this.calorias = calorias;
	}

	public int getPassos() {
		return passos;
	}

	public void setPassos(int passos) {
		this.passos = passos;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		return String.format(
				"Id:%s\nNome: %s\nData: %s\nTempo: %s\nDuração: %s\n Distancia: %s\nCalorias: %s\n"
				+ "Passos: %s",
				id, nome, formatoData .format(data.getTime()), tempo, duracao, distancia, calorias, passos);
	}

	
	
}
