package br.com.academia.modelo;

import java.util.ArrayList;
import java.util.Calendar;

public class AtividadeCompleta extends AtividadeSimples {
	private Long id_atividade;
	private float velocidadeMedia;
	private float velocidadeMaxima;
	private String ritmoMedio;
	private String ritmoMaximo;
	private float MenorElevacao;
	private float MaiorElevacao;
	private ArrayList<Ritmo> ritmos;

	public AtividadeCompleta() {
		super();
	}

	public AtividadeCompleta(Long id, String nome, Calendar data, String tempo, String duracao, float distancia,
			float calorias, int passos) {
		super(id, nome, data, tempo, duracao, distancia, calorias, passos);
	}
	
	public Long getId_atividade() {
		return id_atividade;
	}

	public void setId_atividade(Long id_atividade) {
		this.id_atividade = id_atividade;
	}

	public ArrayList<Ritmo> getRitmos() {
		return ritmos;
	}

	public void setRitmos(ArrayList<Ritmo> ritmos) {
		this.ritmos = ritmos;
	}

	public float getVelocidadeMedia() {
		return velocidadeMedia;
	}

	public void setVelocidadeMedia(float velocidadeMedia) {
		this.velocidadeMedia = velocidadeMedia;
	}

	public float getVelocidadeMaxima() {
		return velocidadeMaxima;
	}

	public void setVelocidadeMaxima(float velocidadeMaxima) {
		this.velocidadeMaxima = velocidadeMaxima;
	}

	public String getRitmoMedio() {
		return ritmoMedio;
	}

	public void setRitmoMedio(String ritmoMedio) {
		this.ritmoMedio = ritmoMedio;
	}

	public String getRitmoMaximo() {
		return ritmoMaximo;
	}

	public void setRitmoMaximo(String ritmoMaximo) {
		this.ritmoMaximo = ritmoMaximo;
	}

	public float getMenorElevacao() {
		return MenorElevacao;
	}

	public void setMenorElevacao(float menorElevação) {
		MenorElevacao = menorElevação;
	}

	public float getMaiorElevacao() {
		return MaiorElevacao;
	}

	public void setMaiorElevacao(float maiorElevacao) {
		MaiorElevacao = maiorElevacao;
	}

	public ArrayList<Ritmo> getRitmo() {
		return ritmos;
	}

	public void setRitmo(ArrayList<Ritmo> ritmo) {
		this.ritmos = ritmo;
	}

	@Override
	public String toString() {
		return super.toString() + " Id: " + id_atividade + "\nVelocidadeMedia: " + velocidadeMedia + "\nVelocidadeMaxima:"
				+ velocidadeMaxima + "\nRitmoMedio: " + ritmoMedio + "\nRitmoMaximo: " +
				ritmoMaximo + "\nMenorElevação: " + MenorElevacao+ "\nMaiorElevação: " + 
				MaiorElevacao;
	}
	
}
