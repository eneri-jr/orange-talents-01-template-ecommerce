package br.com.zup.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.caracteristicas.CadastroCaracDTO;
import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.categoria.CategoriaRepository;
import br.com.zup.mercadolivre.usuario.Usuario;

public class CadastroProdutoDTO {

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
	private Long idCategoria;

	@Size(min = 3)
	@Valid
	private Set<CadastroCaracDTO> caracteristicas = new HashSet<>();

	public CadastroProdutoDTO(@NotBlank String nome, @NotBlank @Positive BigDecimal valor,
			@NotBlank @Positive Integer quantidade, @NotBlank @Size(max = 1000) String descricao,
			@NotNull Long idCategoria, Set<CadastroCaracDTO> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
		
	}

	public Set<CadastroCaracDTO> getCaracteristicas() {
		return caracteristicas;
	}

	public Produto converter(CategoriaRepository categoriaRepository, Usuario usuario) {
		Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
		return new Produto(nome, valor, quantidade, descricao, categoria.get(), caracteristicas, usuario);
	}

}
