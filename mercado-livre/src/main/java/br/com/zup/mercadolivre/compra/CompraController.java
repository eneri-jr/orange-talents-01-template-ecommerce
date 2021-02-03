package br.com.zup.mercadolivre.compra;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;
import br.com.zup.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/compra")
public class CompraController {

	private final ProdutoRepository produtoRepository;
	private final CompraRepository compraRepository;

	public CompraController(ProdutoRepository produtoRepository, CompraRepository compraRepository) {
		this.produtoRepository = produtoRepository;
		this.compraRepository = compraRepository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> comprar(@RequestBody @Valid CadastroCompraDTO compraDTO,
			@AuthenticationPrincipal Usuario usuarioLogado, UriComponentsBuilder uriBuilder) {

		Optional<Produto> produto = produtoRepository.findById(compraDTO.getIdProduto());

		if (produto.isPresent()) {

			if (produto.get().getQuantidade() >= compraDTO.getQuant()) {
				Compra compra = compraDTO.converter(produto.get(), usuarioLogado);
				produto.get().atualizaEstoque(compraDTO.getQuant());
				compraRepository.save(compra);

				if (compra.getGateway().toString() == "PAYPAL") {
					URI location = uriBuilder.path("paypal.com/{idGeradoDaCompra}?redirectUrl=teste.com").buildAndExpand(compra.getId()).toUri();
					return ResponseEntity.status(HttpStatus.FOUND).body(location);
				}
				URI location = uriBuilder.path("pagseguro.com?returnId={idGeradoDaCompra}&redirectUrl=teste.com").buildAndExpand(compra.getId()).toUri();
				return ResponseEntity.status(HttpStatus.FOUND).body(location);
				
			} else {
				return ResponseEntity.badRequest().body("Produto não possui estoque suficiente!");
			}
		}

		return ResponseEntity.badRequest().body("Produto não encontrado!");

	}
}
