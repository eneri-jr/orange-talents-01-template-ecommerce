package br.com.zup.mercadolivre.caracteristicas;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.zup.mercadolivre.produto.Produto;

@Entity
public class Caracteristicas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String valor;

	@ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Produto.class)
	@JoinColumn(name = "produto_id")
	private Produto produto;

	@Deprecated
	public Caracteristicas() {

	}

	public Caracteristicas(String nome, String valor, Produto produto) {
		this.nome = nome;
		this.valor = valor;
		this.produto = produto;
	}

}
