package br.com.zup.mercadolivre.usuario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CadastroUsuarioDTOTest {

    @Test
    public void CadastroUsuarioDTOTest() {
        CadastroUsuarioDTO usuarioDTO = new CadastroUsuarioDTO("jr@email.com", "1234");

        Assertions.assertEquals("jr@email.com", usuarioDTO.getLogin());
        Assertions.assertEquals("1234", usuarioDTO.getSenha());

        Assertions.assertTrue(usuarioDTO.toString().contains("CadastroUsuarioDTO{"));
    }

}
