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
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.caracteristicas.CadastroCaracDTO;
import br.com.zup.mercadolivre.caracteristicas.Caracteristicas;
import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.imagens.Imagens;
import br.com.zup.mercadolivre.opiniao.CadastroOPiniaoDTO;
import br.com.zup.mercadolivre.opiniao.Opiniao;
import br.com.zup.mercadolivre.pergunta.CadastroPerguntaDTO;
import br.com.zup.mercadolivre.pergunta.Pergunta;
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

	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private Set<Opiniao> opinioes = new HashSet<>();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private Set<Pergunta> perguntas = new HashSet<>();

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
		Set<Caracteristicas> novasCaracteristicas = caracteristicas.stream()
				.map(caracteristica -> caracteristica.converter(this)).collect(Collectors.toSet());
		this.caracteristicas.addAll(novasCaracteristicas);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Set<Caracteristicas> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<Imagens> getImagens() {
		return imagens;
	}

	public Set<Opiniao> getOpinioes() {
		return opinioes;
	}

	public Set<Pergunta> getPerguntas() {
		return perguntas;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void associaImagens(Set<String> links) {
		Set<Imagens> imagens = links.stream().map(link -> new Imagens(link, this)).collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	public void associaOpiniao(@Valid CadastroOPiniaoDTO opiniaoDTO, Produto produto, Usuario usuario) {
		Opiniao opiniao = new Opiniao(opiniaoDTO.getNota(), opiniaoDTO.getTitulo(), opiniaoDTO.getDescricao(), usuario,
				produto);
		this.opinioes.add(opiniao);

	}

	public void associaPergunta(@Valid CadastroPerguntaDTO perguntaDTO, Produto produto, Usuario usu) {
		Pergunta pergunta = new Pergunta(perguntaDTO.getTitulo(), perguntaDTO.getMensagem(), usuario, produto);
		this.perguntas.add(pergunta);

	}

}
