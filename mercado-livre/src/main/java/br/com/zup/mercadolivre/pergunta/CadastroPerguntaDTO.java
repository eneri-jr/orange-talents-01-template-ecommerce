package br.com.zup.mercadolivre.pergunta;

import javax.validation.constraints.NotBlank;

public class CadastroPerguntaDTO {

	@NotBlank
	private String titulo;

	@NotBlank
	private String mensagem;

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public CadastroPerguntaDTO(@NotBlank String titulo, @NotBlank String mensagem) {
		super();
		this.titulo = titulo;
		this.mensagem = mensagem;
	}

}
