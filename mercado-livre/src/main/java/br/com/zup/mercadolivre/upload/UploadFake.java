package br.com.zup.mercadolivre.upload;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadFake {

	public Set<String> salvar(List<MultipartFile> imagens) {
		return imagens.stream()
				.map(imagem -> "http://cloud.io/imagem." + imagem.getOriginalFilename())
				.collect(Collectors.toSet());
	}

}
