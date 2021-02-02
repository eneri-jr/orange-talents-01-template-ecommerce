package br.com.zup.mercadolivre.caracteristicas;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.produto.Produto;

public class CadastroCaracDTO {

	@NotBlank
	private String nome;

	@NotBlank
	private String valor;

	public CadastroCaracDTO(@NotBlank String nome, @NotBlank String valor) {
		this.nome = nome;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public String getValor() {
		return valor;
	}

	public Caracteristicas converter(@NotNull @Valid Produto produto) {
		return new Caracteristicas(nome, valor, produto);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CadastroCaracDTO other = (CadastroCaracDTO) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
