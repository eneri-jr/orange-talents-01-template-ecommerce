package br.com.zup.mercadolivre.usuario;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder encoder;
	
	public UsuarioController (UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
		this.encoder = encoder;
		this.usuarioRepository = usuarioRepository;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> criar(@RequestBody @Valid CadastroUsuarioDTO usuarioDTO){
		
		String novaSenha = encoder.encode(usuarioDTO.getSenha());
		Usuario usuario = usuarioDTO.toUsuario(novaSenha);
		usuarioRepository.save(usuario);
		
        return ResponseEntity.ok().build();
		
	}
}
