package controller;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.PessoaTutora;
import model.SetorResponsavel;
import repository.SetorRepository;

class PessoaTutoraControllerTest {
    @Test
    void testPessoaTutoraController() {
        SetorRepository setorRepo = new SetorRepository();
        PessoaTutoraController controller = new PessoaTutoraController(setorRepo);
        SetorResponsavel setor = new SetorResponsavel("Setor Teste", "Endereço Teste");
        setorRepo.salvar(setor);
        PessoaTutora pessoa = controller.criarPessoaTutora("teste@gmail.com", "Nome", "Endereço", "11999999999", setor);
        assertTrue(controller.listarTodos().contains(pessoa));
    }
}
