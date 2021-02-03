package br.com.zup.mercadolivre.opiniao;

public class OpiniaoDetalhadaDTO {

	private int nota;
	private String titulo;
	private String descricao;
	private String usuario;

	public OpiniaoDetalhadaDTO(Opiniao opiniao) {
		this.nota = opiniao.getNota();
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
		this.usuario = opiniao.getUsuario().getUsername();
	}

	public int getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getUsuario() {
		return usuario;
	}

}
