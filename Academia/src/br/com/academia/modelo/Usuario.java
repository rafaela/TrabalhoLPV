package br.com.academia.modelo;

public class Usuario {
	private Long id;
	private String usuario;
	private String senha;
	private String papel;
	private boolean logado;
	
	public Usuario() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}
	
	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	@Override
	public String toString() {
		return String.format("Id=%s, usuario=%s, senha=%s, papel=%s, logado=%s", id, usuario, senha, papel,
				logado);
	}

	
	
}
