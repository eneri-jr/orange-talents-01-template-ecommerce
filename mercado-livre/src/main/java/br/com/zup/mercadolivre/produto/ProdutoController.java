package br.com.zup.mercadolivre.produto;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.categoria.CategoriaRepository;
import br.com.zup.mercadolivre.imagens.ImagensDTO;
import br.com.zup.mercadolivre.opiniao.CadastroOPiniaoDTO;
import br.com.zup.mercadolivre.pergunta.CadastroPerguntaDTO;
import br.com.zup.mercadolivre.upload.UploadFake;
import br.com.zup.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	private final ProdutoRepository produtoRepository;
	private final CategoriaRepository categoriaRepository;
	private final UploadFake uploadFake;

	public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository,
			UploadFake uploadFake) {
		this.produtoRepository = produtoRepository;
		this.categoriaRepository = categoriaRepository;
		this.uploadFake = uploadFake;

	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> criar(@RequestBody @Valid CadastroProdutoDTO produtoDTO,
			@AuthenticationPrincipal Usuario usuarioLogado) {

		Produto produto = produtoDTO.converter(categoriaRepository, usuarioLogado);
		produtoRepository.save(produto);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/{id}/imagens")
	@Transactional
	public ResponseEntity<?> adicionaImagens(@PathVariable Long id, @Valid ImagensDTO imagensDTO,
			@AuthenticationPrincipal Usuario usuarioLogado) {

		Optional<Produto> produto = produtoRepository.findById(id);
		Set<String> imagens = uploadFake.salvar(imagensDTO.getImagens());

		if (!produto.isPresent()) {
			return ResponseEntity.badRequest().build();
		} else {
			if (produto.get().getUsuario().equals(usuarioLogado)) {
				produto.get().associaImagens(imagens);

				return ResponseEntity.ok().build();
			}
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

	}

	@PostMapping("/{id}/opiniao")
	@Transactional
	public ResponseEntity<?> adicionaOpiniao(@PathVariable Long id, @RequestBody @Valid CadastroOPiniaoDTO opiniaoDTO,
			@AuthenticationPrincipal Usuario usuarioLogado) {

		Optional<Produto> produto = produtoRepository.findById(id);
		if (!produto.isPresent()) {
			return ResponseEntity.badRequest().build();
		} else {
			produto.get().associaOpiniao(opiniaoDTO, produto.get(), usuarioLogado);
			return ResponseEntity.ok().build();
		}
	}
	
	@PostMapping("/{id}/pergunta")
	@Transactional
	public ResponseEntity<CadastroPerguntaDTO> fazPergunta(@PathVariable Long id, @RequestBody @Valid CadastroPerguntaDTO perguntaDTO,
			@AuthenticationPrincipal Usuario usuarioLogado) {

		Optional<Produto> produto = produtoRepository.findById(id);
		if (!produto.isPresent()) {
			return ResponseEntity.badRequest().build();
		} else {
			produto.get().associaPergunta(perguntaDTO, produto.get(), usuarioLogado);
			return ResponseEntity.ok(perguntaDTO);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDetalhadoDTO> detalharProduto(@PathVariable Long id) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		if (!produto.isPresent()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(new ProdutoDetalhadoDTO(produto.get()));
		}
//		System.out.println("Id procurado é = " + id);
//		try {
//			Produto possivelProduto = produtoRepository.getOne(id);
//			return ResponseEntity.ok(new ProdutoDetalhadoDTO(possivelProduto));
//		} catch (Exception e) {
//			return ResponseEntity.notFound().build();
//		}
	}
}
