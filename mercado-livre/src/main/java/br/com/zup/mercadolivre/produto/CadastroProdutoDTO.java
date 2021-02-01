package br.com.zup.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

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
	private Map<String, String> caracteristicas = new HashMap<String, String>();
	

	@Override
	public String toString() {
		return "CadastroProdutoDTO [nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade + ", descricao="
				+ descricao + ", idCategoria=" + idCategoria + ", caracteristicas="
				+ caracteristicas + "]";
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

	public Long getIdCategoria() {
		return idCategoria;
	}

	public CadastroProdutoDTO(@NotBlank String nome, @NotBlank @Positive BigDecimal valor,
			@NotBlank @Positive Integer quantidade, @NotBlank @Size(max = 1000) String descricao,
			@NotNull Long idCategoria, Map<String,String> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas.putAll(caracteristicas);
		
	}

}
