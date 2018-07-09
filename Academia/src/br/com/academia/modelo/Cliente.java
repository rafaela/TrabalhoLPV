package br.com.academia.modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Cliente {
	private String nome;
	private String sexo;
	private float altura;
	private float peso;
	private Calendar dataNascimento;
	private String email;
	private String cpf;
	private String whatsapp;

	public Cliente() {
		dataNascimento = Calendar.getInstance();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	@Override
	public String toString() {
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		return String.format("Nome=%s, sexo=%s, altura=%s, peso=%s, dataNascimento=%s, email=%s, cpf=%s,"
				+ "whatsapp=%s", nome, sexo, altura, peso, formatoData .format(dataNascimento.getTime()),
				email, cpf, whatsapp);
	}
}
