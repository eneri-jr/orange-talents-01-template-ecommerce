package br.com.zup.mercadolivre.autenticacao;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.security.token.TokenDTO;
import br.com.zup.mercadolivre.security.token.TokenService;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

	private final AuthenticationManager authManager;
	private final TokenService tokenService;

	public AutenticacaoController(AuthenticationManager authManager, TokenService tokenService) {
		this.authManager = authManager;
		this.tokenService = tokenService;
	}

	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginDTO loginDTO) {

		UsernamePasswordAuthenticationToken dadosLogin = loginDTO.converter();

		Authentication authentication = authManager.authenticate(dadosLogin);
		String token = tokenService.gerarToken(authentication);
		return ResponseEntity.ok(new TokenDTO(token, "Bearer"));

	}
}
