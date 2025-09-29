package controller;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.Animal;
import model.SetorResponsavel;
import repository.SetorRepository;

class AnimalControllerTest {
    @Test
    void testAnimalController() {
        SetorRepository setorRepo = new SetorRepository();
        SetorResponsavel setor = new SetorResponsavel("Setor Teste", "Endereço Teste");
        setorRepo.salvar(setor);
        AnimalController controller = new AnimalController(setorRepo);
        Animal animal = controller.criarAnimal("Rex", "Cachorro", "SRD", 5, "M", "Em observação", setor.getId(), null);
        assertTrue(controller.listarTodos().contains(animal));
    }
}
