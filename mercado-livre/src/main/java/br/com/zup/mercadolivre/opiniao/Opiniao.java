package br.com.zup.mercadolivre.opiniao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

@Entity
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Min(1)
	@Max(5)
	@NotNull
	private int nota;

	@NotBlank
	private String titulo;
	
	@NotBlank
	@Size(max = 500)
	private String descricao;

	@ManyToOne
	@NotNull
	private Usuario usuario;

	@ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Produto.class)
	@JoinColumn(name = "produto_id")
	private Produto produto;

	@Deprecated
	public Opiniao() {

	}

	public Opiniao(@Min(1) @Max(5) @NotNull int nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull Usuario usuario, Produto produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}



}
