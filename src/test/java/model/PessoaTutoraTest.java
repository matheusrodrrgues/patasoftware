package model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PessoaTutoraTest {
    @Test
    void testPessoaTutoraModel() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new PessoaTutora("teste@gmail.com", "Nome", "Endereço", "123", null);
        });
        assertTrue(e.getMessage().contains("Telefone deve conter 10 ou 11 dígitos"));
        PessoaTutora pessoa = new PessoaTutora("teste@gmail.com", "Nome", "Endereço", "11999999999", null);
        assertEquals("Nome", pessoa.getNome());
    }
}
