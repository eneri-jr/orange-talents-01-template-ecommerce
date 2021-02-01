package br.com.zup.mercadolivre.categoria;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.zup.mercadolivre.validacao.ValorUnico;

public class CadastroCategoriaDTO {

	@NotBlank
	@ValorUnico(Classe = Categoria.class, campo = "nome")
	private String nome;

	private Long idCategoria;

	public CadastroCategoriaDTO(@NotBlank @Email String nome, Long idCategoria) {
		super();
		this.nome = nome;
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}
	
	public Categoria toCategoria(CategoriaRepository categoriaRepository) {
		if(idCategoria == null) {
			return new Categoria(nome);
		}
		
		Categoria categoria = categoriaRepository.getOne(idCategoria);
		return new Categoria(nome, categoria);
		
	}

}
