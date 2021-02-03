package br.com.zup.mercadolivre.pergunta;

import java.time.format.DateTimeFormatter;

public class PerguntaDetalhadaDTO {

	private String titulo;
	private String mensagem;
	private String usuario;
	private String data;

	public PerguntaDetalhadaDTO(Pergunta pergunta) {
		this.titulo = pergunta.getTitulo();
		this.mensagem = pergunta.getMensagem();
		this.usuario = pergunta.getUsuario().getUsername();
		this.data = pergunta.getCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getData() {
		return data;
	}

}
