package br.com.zup.mercadolivre.compra;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

public class CadastroCompraDTO {

	@NotNull
	private Long idProduto;

	@NotNull
	@Positive
	private int quant;

	@Enumerated(EnumType.STRING)
	private Gateway gateway;

	public CadastroCompraDTO(@NotNull Long idProduto, @NotNull @Positive int quant, Gateway gateway) {
		super();
		this.idProduto = idProduto;
		this.quant = quant;
		this.gateway = gateway;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public int getQuant() {
		return quant;
	}

	public Gateway getGateway() {
		return gateway;
	}

	public Compra converter(Produto produto, Usuario usuario) {
		return new Compra(gateway, produto, quant, usuario);
	}

}
