package br.com.zup.mercadolivre.caracteristicas;

public class CaracteristicasDetalhadaDTO {

	private String nome;
	private String valor;

	public CaracteristicasDetalhadaDTO(Caracteristicas carac) {
		this.nome = carac.getNome();
		this.valor = carac.getValor();
	}

	public String getNome() {
		return nome;
	}

	public String getValor() {
		return valor;
	}

}
