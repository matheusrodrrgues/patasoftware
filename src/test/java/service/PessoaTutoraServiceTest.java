package service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.PessoaTutora;
import model.SetorResponsavel;
import repository.SetorRepository;

class PessoaTutoraServiceTest {
    @Test
    void testPessoaTutoraService() {
        SetorRepository setorRepo = new SetorRepository();
        PessoaTutoraService service = new PessoaTutoraService(setorRepo);
        SetorResponsavel setor = new SetorResponsavel("Setor Teste", "Endereço Teste");
        setorRepo.salvar(setor);
        // Usa e-mail único para evitar duplicidade
        String emailUnico = "teste" + System.currentTimeMillis() + "@gmail.com";
        PessoaTutora pessoa = service.criarPessoaTutora(emailUnico, "Nome", "Endereço", "11999999999", setor);
        // Listar todas as pessoas tutoras dos setores
        boolean found = setorRepo.listarTodos().stream()
            .anyMatch(s -> s.getPessoaTutora() != null && s.getPessoaTutora().equals(pessoa));
        assertTrue(found);
    }
}
