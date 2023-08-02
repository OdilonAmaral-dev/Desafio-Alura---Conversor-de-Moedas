package br.com.conversor.moeda.model;

public class Moeda {
	
	private String idMoeda;
	private String Descricao;	
	
	public Moeda(String idMoeda, String descricao) {
		this.idMoeda = idMoeda;
		this.Descricao = descricao;
	}

	public Moeda(String Moeda) {}
	
	public String getIdMoeda() {
		return idMoeda;
	}
	public void setIdMoeda(String idMoeda) {
		this.idMoeda = idMoeda;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
}
