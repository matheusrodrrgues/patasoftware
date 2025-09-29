package repository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.Animal;
import model.SetorResponsavel;

class AnimalRepositoryTest {
    @Test
    void testAnimalRepository() {
        SetorRepository setorRepo = new SetorRepository();
        AnimalRepository repo = new AnimalRepository(setorRepo);
        SetorResponsavel setor = new SetorResponsavel("Setor Teste", "Endereço Teste");
        setorRepo.salvar(setor);
        Animal animal = new Animal("Rex", "Cachorro", "SRD", 5, "M", "Em observação", setor, null);
        repo.salvar(animal, setor.getId());
        assertTrue(repo.listarTodos().contains(animal));
        assertEquals(animal, repo.buscarPorId(animal.getId(), setor.getId()));
    }
}
