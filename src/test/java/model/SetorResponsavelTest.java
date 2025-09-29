package model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SetorResponsavelTest {
    @Test
    void testSetorResponsavelModel() {
        SetorResponsavel setor = new SetorResponsavel("Setor", "Endereço");
        assertEquals("Setor", setor.getNome());
        assertEquals("Endereço", setor.getEndereco());
        assertNotNull(setor.getAnimais());
    }
}

