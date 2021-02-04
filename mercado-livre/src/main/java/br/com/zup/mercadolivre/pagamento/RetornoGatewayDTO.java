package br.com.zup.mercadolivre.pagamento;

import br.com.zup.mercadolivre.compra.Compra;
import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.validacao.ValorUnico;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoGatewayDTO {

    @NotNull
    private Long idCompra;

    @NotNull
    @ValorUnico(Classe = Pagamento.class, campo = "idPagamento")
    private Long idGateway;

    @NotBlank
    private String estadoCompra;

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdGateway() {
        return idGateway;
    }

    public String getEstadoCompra() {
        return estadoCompra;
    }

    public RetornoGatewayDTO(@NotNull Long idCompra, @NotNull Long idGateway, @NotBlank String estadoCompra) {
        this.idCompra = idCompra;
        this.idGateway = idGateway;
        this.estadoCompra = estadoCompra;
    }

    public Pagamento toPagamento(Compra compra, StatusPagamento status) {
        return new Pagamento(compra, idGateway, status);
    }
}
