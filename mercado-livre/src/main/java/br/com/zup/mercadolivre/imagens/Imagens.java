package br.com.zup.mercadolivre.imagens;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import br.com.zup.mercadolivre.produto.Produto;

@Entity
public class Imagens {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@URL
	private String imagem;

	@ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Produto.class)
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	@Deprecated
	public Imagens() {

	}

	public Imagens(@NotBlank @URL String imagem, Produto produto) {
		this.imagem = imagem;
		this.produto = produto;
	}

}
