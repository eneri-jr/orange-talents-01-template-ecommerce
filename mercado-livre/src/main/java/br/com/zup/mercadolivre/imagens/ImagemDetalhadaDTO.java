package br.com.zup.mercadolivre.imagens;

public class ImagemDetalhadaDTO {
	
	private String link;
	
	public ImagemDetalhadaDTO(Imagens imagem) {
		this.link = imagem.getImagem();
	}

	public String getLink() {
		return link;
	}
	
	

}
