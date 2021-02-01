package br.com.zup.mercadolivre.produto;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> criar(@RequestBody @Valid CadastroProdutoDTO produtoDTO) {
		System.out.println(produtoDTO);
		return ResponseEntity.ok(produtoDTO);
	}

}
