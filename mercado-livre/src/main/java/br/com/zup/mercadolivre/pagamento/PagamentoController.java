package br.com.zup.mercadolivre.pagamento;

import br.com.zup.mercadolivre.compra.Compra;
import br.com.zup.mercadolivre.compra.CompraRepository;
import br.com.zup.mercadolivre.compra.StatusCompra;
import br.com.zup.mercadolivre.notafiscal.CadastroNfDTO;
import br.com.zup.mercadolivre.notafiscal.NotaFiscalController;
import br.com.zup.mercadolivre.rankingVendedores.CadastroVendedorDTO;
import br.com.zup.mercadolivre.rankingVendedores.RankingController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    private final CompraRepository compraRepository;
    private final NotaFiscalController nfController;
    private final RankingController rankingController;
    private final PagamentoRepository pagamentoRepository;

    public PagamentoController(CompraRepository compraRepository,
                               NotaFiscalController nfController,
                               RankingController rankingController,
                               PagamentoRepository pagamentoRepository) {
        this.nfController = nfController;
        this.compraRepository = compraRepository;
        this.rankingController = rankingController;
        this.pagamentoRepository = pagamentoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> validaPagamento(@RequestBody @Valid RetornoGatewayDTO retornoDTO) {

        Optional<Compra> compra = compraRepository.findById(retornoDTO.getIdCompra());

        if (!compra.isPresent()) {
            return ResponseEntity.badRequest().body("Inválido, compra não encontrada nos registros");
        } else {
            if (compra.get().getStatus().equals(StatusCompra.FINALIZADA_PAGO)) {
                return ResponseEntity.badRequest().body("Esta compra ja foi paga");
            } else {
                if (retornoDTO.getEstadoCompra().equals("SUCESSO") || retornoDTO.getEstadoCompra().equals("1")) {
                    Pagamento novoPagamento = retornoDTO.toPagamento(compra.get(), StatusPagamento.SUCESSO);
                    compra.get().setStatus(StatusCompra.FINALIZADA_PAGO);
                    pagamentoRepository.save(novoPagamento);
                    //Comunicação nota fiscal: (id Compra + id Cliente)
                    CadastroNfDTO nfDTO = new CadastroNfDTO(compra.get().getId(), compra.get().getUsuario().getId());
                    nfController.criar(nfDTO);

                    //Ranking dos vendedores: (id compra + id vendedor)
                    CadastroVendedorDTO vendedorDTO = new CadastroVendedorDTO(compra.get().getProduto().getUsuario().getId(), compra.get().getId());
                    rankingController.adicionar(vendedorDTO);

                    System.out.println("Email de ok");

                    return ResponseEntity.ok("Venda Finalizada com sucesso");


                } else {
                    Pagamento novoPagamento = retornoDTO.toPagamento(compra.get(), StatusPagamento.ERRO);
                    compra.get().setStatus(StatusCompra.AGUARDANDO_PAGAMENTO);
                    pagamentoRepository.save(novoPagamento);
                    System.out.println("Email de erro");
                    return ResponseEntity.badRequest().body("Erro no gateway de pagamento");
                }

            }
        }

    }
}
