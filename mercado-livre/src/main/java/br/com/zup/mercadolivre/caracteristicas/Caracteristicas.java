package br.com.zup.mercadolivre.caracteristicas;

public class Caracteristicas {
	
	private String nome;
	private String valor;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public Caracteristicas(String nome, String valor) {
		super();
		this.nome = nome;
		this.valor = valor;
	}

}
