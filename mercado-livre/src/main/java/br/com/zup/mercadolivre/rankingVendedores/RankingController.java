package br.com.zup.mercadolivre.rankingVendedores;

import br.com.zup.mercadolivre.notafiscal.CadastroNfDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @PostMapping
    @Transactional
    public ResponseEntity<?> adicionar(@RequestBody @Valid CadastroVendedorDTO vendedorDTO) {
        System.out.println("Vendedor adicionado ao Ranking");
        return ResponseEntity.ok().build();
    }

}
