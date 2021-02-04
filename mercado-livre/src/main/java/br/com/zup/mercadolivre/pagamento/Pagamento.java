package br.com.zup.mercadolivre.pagamento;

import br.com.zup.mercadolivre.compra.Compra;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Compra compra;

    @NotNull
    private Long idPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    private LocalDate dataRetorno;

    @Deprecated
    public Pagamento() {

    }

    public Pagamento(@NotNull Compra compra, @NotNull Long idPagamento, StatusPagamento status) {
        this.compra = compra;
        this.idPagamento = idPagamento;
        this.status = status;
        this.dataRetorno = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public Compra getCompra() {
        return compra;
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }
}
