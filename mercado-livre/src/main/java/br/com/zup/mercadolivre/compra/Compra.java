package br.com.zup.mercadolivre.compra;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

import java.util.Objects;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private StatusCompra status = StatusCompra.INICIADA;

	@Enumerated(EnumType.STRING)
	private Gateway gateway;

	@NotNull
	@ManyToOne
	private Produto produto;

	@NotNull
	private int quantidade;

	@NotNull
	@ManyToOne
	private Usuario usuario;

	@Deprecated
	public Compra() {

	}

	public Compra(Gateway gateway, @NotNull Produto produto, @NotNull int quantidade,
			@NotNull Usuario usuario) {
		this.gateway = gateway;
		this.produto = produto;
		this.quantidade = quantidade;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public StatusCompra getStatus() {
		return status;
	}

	public Gateway getGateway() {
		return gateway;
	}

	public Produto getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setStatus(StatusCompra status) {
		this.status = status;
	}
}
