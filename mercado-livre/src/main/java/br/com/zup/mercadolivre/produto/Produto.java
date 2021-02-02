package br.com.zup.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.caracteristicas.CadastroCaracDTO;
import br.com.zup.mercadolivre.caracteristicas.Caracteristicas;
import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.imagens.Imagens;
import br.com.zup.mercadolivre.usuario.Usuario;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@NotNull
	@Positive
	private BigDecimal valor;

	@NotNull
	@Positive
	private Integer quantidade;

	@NotBlank
	@Size(max = 1000)
	private String descricao;

	@NotNull
	@ManyToOne
	private Categoria categoria;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private Set<Caracteristicas> caracteristicas = new HashSet<>();
	
	@NotNull
	@ManyToOne
	private Usuario usuario;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private Set<Imagens> imagens = new HashSet<>();

	private LocalDate dataCriacao;

	@Deprecated
	public Produto() {

	}

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @Positive Integer quantidade,
			@NotBlank @Size(max = 1000) String descricao, @NotNull Categoria categoria,
			Collection<CadastroCaracDTO> caracteristicas, @NotNull Usuario usuario) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.dataCriacao = LocalDate.now();
		Set<Caracteristicas> novasCaracteristicas = caracteristicas
				.stream().map(caracteristica -> caracteristica.converter(this))
				.collect(Collectors.toSet());
		this.caracteristicas.addAll(novasCaracteristicas);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public void associaImagens (Set<String> links) {
		Set<Imagens> imagens = links.stream().map(link -> new Imagens(link, this)).collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

}
