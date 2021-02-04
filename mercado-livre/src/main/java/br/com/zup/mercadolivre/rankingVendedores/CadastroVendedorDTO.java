package br.com.zup.mercadolivre.rankingVendedores;

import javax.validation.constraints.NotNull;

public class CadastroVendedorDTO {

    @NotNull
    private Long idVendedor;

    @NotNull
    private Long idCompra;

    public CadastroVendedorDTO(@NotNull Long idVendedor, @NotNull Long idCompra) {
        this.idVendedor = idVendedor;
        this.idCompra = idCompra;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }

    public Long getIdCompra() {
        return idCompra;
    }
}
