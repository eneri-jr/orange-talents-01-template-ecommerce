package br.com.zup.mercadolivre.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    Optional<Pagamento> findByIdPagamento(Long idPagamento);
}
