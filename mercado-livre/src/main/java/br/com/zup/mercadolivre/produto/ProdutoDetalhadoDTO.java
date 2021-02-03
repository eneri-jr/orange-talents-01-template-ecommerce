package br.com.zup.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.zup.mercadolivre.caracteristicas.CaracteristicasDetalhadaDTO;
import br.com.zup.mercadolivre.imagens.ImagemDetalhadaDTO;
import br.com.zup.mercadolivre.opiniao.OpiniaoDetalhadaDTO;
import br.com.zup.mercadolivre.pergunta.PerguntaDetalhadaDTO;

public class ProdutoDetalhadoDTO {

	private Set<ImagemDetalhadaDTO> imagens = new HashSet<>();
	private String nome;
	private BigDecimal preco;
	private Set<CaracteristicasDetalhadaDTO> caracteristicas = new HashSet<>();
	private String descricao;
	private Set<OpiniaoDetalhadaDTO> opinioes = new HashSet<>();
	private Set<PerguntaDetalhadaDTO> perguntas = new HashSet<>();
	private int totalNotas;
	private double mediaNotas;

	public ProdutoDetalhadoDTO(Produto produto) {
		this.imagens.addAll(produto.getImagens().stream().map(imagem -> new ImagemDetalhadaDTO(imagem))
				.collect(Collectors.toSet()));
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		this.caracteristicas.addAll(produto.getCaracteristicas().stream()
				.map(carac -> new CaracteristicasDetalhadaDTO(carac)).collect(Collectors.toSet()));
		this.descricao = produto.getDescricao();
		this.opinioes.addAll(produto.getOpinioes().stream().map(opiniao -> new OpiniaoDetalhadaDTO(opiniao))
				.collect(Collectors.toSet()));
		this.perguntas.addAll(produto.getPerguntas().stream().map(pergunta -> new PerguntaDetalhadaDTO(pergunta))
				.collect(Collectors.toSet()));
		this.totalNotas = opinioes.size();
		this.mediaNotas = calculaMedia(totalNotas, opinioes);
	}

	public Set<ImagemDetalhadaDTO> getImagens() {
		return imagens;
	}

	public Set<CaracteristicasDetalhadaDTO> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<OpiniaoDetalhadaDTO> getOpinioes() {
		return opinioes;
	}

	public Set<PerguntaDetalhadaDTO> getPerguntas() {
		return perguntas;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getTotalNotas() {
		return totalNotas;
	}

	public double getMediaNotas() {
		return mediaNotas;
	}

	private Double calculaMedia(int total, Set<OpiniaoDetalhadaDTO> opiniao) {
		
		if(total == 0) {
			return 0.0;
		}
		
		int soma = 0;
		
		for (OpiniaoDetalhadaDTO opiniaoDetalhada : opiniao) {
			soma += opiniaoDetalhada.getNota();
		}
		
		return (double) (soma/total);
		
	}

}
