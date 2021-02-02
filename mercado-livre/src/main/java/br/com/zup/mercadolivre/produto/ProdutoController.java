package br.com.zup.mercadolivre.produto;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.categoria.CategoriaRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	private final ProdutoRepository produtoRepository;
	private final CategoriaRepository categoriaRepository;

	public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
		this.produtoRepository = produtoRepository;
		this.categoriaRepository = categoriaRepository;

	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> criar(@RequestBody @Valid CadastroProdutoDTO produtoDTO) {
		System.out.println(produtoDTO);

		Produto produto = produtoDTO.converter(categoriaRepository);
		produtoRepository.save(produto);
		
		return ResponseEntity.ok().build();
	}

}
