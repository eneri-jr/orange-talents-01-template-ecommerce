package br.com.zup.mercadolivre.notafiscal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/notafiscal")
public class NotaFiscalController {

    @PostMapping
    @Transactional
    public ResponseEntity<?> criar(@RequestBody @Valid CadastroNfDTO nfDTO) {
        System.out.println("Emitida Nota Fiscal");
        return ResponseEntity.ok().build();
    }
}
