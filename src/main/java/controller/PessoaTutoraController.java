package controller;

/**
 * Controlador responsável pelas operações de pessoas tutoras.
 * Usa o serviço PessoaTutoraService para manipular dados das pessoas tutoras.
 */
import model.PessoaTutora;
import model.SetorResponsavel;
import service.PessoaTutoraService;
import repository.SetorRepository;
import java.util.List;

public class PessoaTutoraController {
    private PessoaTutoraService pessoaTutoraService;

    /**
     * Construtor padrão do controlador de pessoas tutoras.
     */
    public PessoaTutoraController() {
        pessoaTutoraService = new PessoaTutoraService();
    }

    /**
     * Construtor que recebe o repositório de setores.
     * @param setorRepository repositório de setores
     */
    public PessoaTutoraController(SetorRepository setorRepository) {
        pessoaTutoraService = new PessoaTutoraService(setorRepository);
    }

    /**
     * Cria uma nova pessoa tutora.
     * @param email email da pessoa tutora
     * @param nome nome da pessoa tutora
     * @param endereco endereço da pessoa tutora
     * @param telefone telefone da pessoa tutora
     * @param setor setor vinculado
     * @return pessoa tutora criada
     */
    public PessoaTutora criarPessoaTutora(String email, String nome, String endereco, String telefone, SetorResponsavel setor) {
        return pessoaTutoraService.criarPessoaTutora(email, nome, endereco, telefone, setor);
    }

    /**
     * Atualiza os dados de uma pessoa tutora.
     * @param pessoa pessoa tutora a ser atualizada
     */
    public void atualizarPessoaTutora(PessoaTutora pessoa) {
        pessoaTutoraService.atualizarPessoaTutora(pessoa);
    }

    /**
     * Remove uma pessoa tutora pelo email.
     * @param email email da pessoa tutora
     */
    public void removerPessoaTutora(String email) {
        pessoaTutoraService.removerPessoaTutora(email);
    }

    /**
     * Busca uma pessoa tutora pelo email.
     * @param email email da pessoa tutora
     * @return pessoa tutora encontrada ou null
     */
    public PessoaTutora buscarPorEmail(String email) {
        return pessoaTutoraService.buscarPorEmail(email);
    }

    /**
     * Lista todas as pessoas tutoras do sistema.
     * @return lista de pessoas tutoras
     */
    public List<PessoaTutora> listarTodos() {
        return pessoaTutoraService.listarTodos();
    }
}
