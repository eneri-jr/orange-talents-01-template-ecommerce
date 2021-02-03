package br.com.zup.mercadolivre.pergunta;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@NotBlank
	private String mensagem;

	@ManyToOne
	@NotNull
	private Usuario usuario;

	@ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Produto.class)
	@JoinColumn(name = "produto_id")
	private Produto produto;

	private LocalDate criacao;

	@Deprecated
	public Pergunta() {

	}

	public Pergunta(@NotBlank String titulo, @NotBlank String mensagem, @NotNull Usuario usuario, Produto produto) {
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.usuario = usuario;
		this.produto = produto;
		this.criacao = LocalDate.now();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}

	public LocalDate getCriacao() {
		return criacao;
	}

}
