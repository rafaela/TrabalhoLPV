package br.com.academia.modelo;

public class Ritmo {
	private Long id;
	private float numKm;
	private int minuto;
	private int segundo;
	
	public Ritmo() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getNumKm() {
		return numKm;
	}

	public void setNumKm(float numKm) {
		this.numKm = numKm;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public int getSegundo() {
		return segundo;
	}

	public void setSegundo(int segundo) {
		this.segundo = segundo;
	}

	@Override
	public String toString() {
		return String.format("id: %s, numKm: %s, minuto: %s, segundo: %s", id, numKm, minuto, segundo);
	}
	
	

	
	
}
