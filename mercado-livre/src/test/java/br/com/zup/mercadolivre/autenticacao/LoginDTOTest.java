package br.com.zup.mercadolivre.autenticacao;

import br.com.zup.mercadolivre.usuario.CadastroUsuarioDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginDTOTest {

    @Test
    public void LoginDTOTest() {
        LoginDTO loginDTO = new LoginDTO("jr@email.com", "1234");

        Assertions.assertEquals("jr@email.com", loginDTO.getLogin());
        Assertions.assertEquals("1234", loginDTO.getSenha());
    }
}
