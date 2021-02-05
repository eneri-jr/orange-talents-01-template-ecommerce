package br.com.zup.mercadolivre.usuario;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @MockBean
    private UsuarioRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void CriaUmNovoUsuario() throws Exception {
        CadastroUsuarioDTO usuarioDTO = new CadastroUsuarioDTO("jr@teste.com", "123456");

        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        Usuario usuario = usuarioDTO.toUsuario(encoder.encode(usuarioDTO.getSenha()));

        Mockito.when(repository.save(usuario)).thenReturn(usuario);

        mockmvc.perform(MockMvcRequestBuilders.post("/usuario")
                .content(objectMapper.writeValueAsString(usuarioDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
