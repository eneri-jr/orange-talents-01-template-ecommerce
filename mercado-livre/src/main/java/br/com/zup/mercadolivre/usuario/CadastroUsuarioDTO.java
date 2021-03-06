package br.com.zup.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.validacao.ValorUnico;

public class CadastroUsuarioDTO {

	@NotBlank
	@Email
	@ValorUnico(Classe = Usuario.class, campo = "login")
	private String login;

	@NotBlank
	@Size(min = 6)
	private String senha;

	public CadastroUsuarioDTO(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}
	
	public Usuario toUsuario(String novaSenha) {
		return new Usuario(login, novaSenha);
	}

	@Override
	public String toString() {
		return "CadastroUsuarioDTO{" +
				"login='" + login + '\'' +
				", senha='" + senha + '\'' +
				'}';
	}
}
