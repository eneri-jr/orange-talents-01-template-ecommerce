package br.com.zup.mercadolivre.notafiscal;

import javax.validation.constraints.NotNull;

public class CadastroNfDTO {

    @NotNull
    private Long idCliente;

    @NotNull
    private Long idCompra;

    public CadastroNfDTO(@NotNull Long idCliente, @NotNull Long idCompra) {
        this.idCliente = idCliente;
        this.idCompra = idCompra;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public Long getIdCompra() {
        return idCompra;
    }
}
