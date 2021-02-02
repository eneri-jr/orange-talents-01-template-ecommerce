package br.com.zup.mercadolivre.imagens;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import br.com.zup.mercadolivre.caracteristicas.Caracteristicas;
import br.com.zup.mercadolivre.produto.Produto;

public class ImagensDTO {

	@Size(min = 1)
	@NotNull
	private List<MultipartFile> imagens = new ArrayList<>();

	public ImagensDTO(@Size(min = 2) @NotNull List<MultipartFile> imagens) {
		super();
		this.imagens = imagens;
	}

	public void setImagens(List<MultipartFile> imagens) {
		this.imagens = imagens;
	}

	@Override
	public String toString() {
		return "ImagensDTO [imagens=" + imagens + "]";
	}

	public List<MultipartFile> getImagens() {
		return this.imagens;
	}
	
	public Imagens converter(@NotNull @Valid Produto produto, String link) {
		return new Imagens(link, produto);
	}

}