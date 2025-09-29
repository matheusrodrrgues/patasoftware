package repository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.SetorResponsavel;

class SetorRepositoryTest {
    @Test
    void testSetorRepository() {
        SetorRepository repo = new SetorRepository();
        SetorResponsavel setor = new SetorResponsavel("Setor", "Endereço");
        repo.salvar(setor);
        assertTrue(repo.listarTodos().contains(setor));
        assertEquals(setor, repo.buscarPorId(setor.getId()));
    }
}
