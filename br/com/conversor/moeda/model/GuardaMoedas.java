package br.com.conversor.moeda.model;

public class GuardaMoedas {
	private Moeda[] moedas;
	private int posicaoLivre;
	
	public GuardaMoedas() {
		this.moedas = new Moeda[157];
		this.posicaoLivre = 0;
	}
	
	public void adiciona(Moeda moeda) {
		this.moedas[this.posicaoLivre] = moeda;
		this.posicaoLivre++;
	}

	public int getQuantidadeDeElementos() {
		return this.posicaoLivre;
	}

	public Moeda getMoeda(int pos) {
		return this.moedas[pos];
	}
}
