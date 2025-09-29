package repository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IDManagerTest {
    @Test
    void testIDManager() {
        String id = "Animal-1";
        assertTrue(IDManager.adicionarId(id));
        assertTrue(IDManager.existeId(id));
        IDManager.removerId(id);
        assertFalse(IDManager.existeId(id));
    }
}
