package service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.SetorResponsavel;
import repository.SetorRepository;

class SetorServiceTest {
    @Test
    void testSetorService() {
        SetorRepository repo = new SetorRepository();
        SetorService service = new SetorService(repo);
        SetorResponsavel setor = service.criarSetor("Setor", "Endereço");
        assertTrue(service.listarTodos().contains(setor));
    }
}
