package service;

/**
 * Serviço responsável pelas regras de negócio das pessoas tutoras.
 * Permite criar, atualizar, remover e buscar pessoas tutoras.
 */
import model.PessoaTutora;
import model.SetorResponsavel;
import repository.SetorRepository;
import repository.IDManager;
import java.util.List;
import java.util.ArrayList;

public class PessoaTutoraService {
    private SetorRepository setorRepository;

    /**
     * Construtor padrão do serviço de pessoas tutoras.
     */
    public PessoaTutoraService() {
        setorRepository = new SetorRepository();
    }

    /**
     * Construtor que recebe o repositório de setores.
     * @param setorRepository repositório de setores
     */
    public PessoaTutoraService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
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
        if (IDManager.existeId(email)) {
            throw new IllegalArgumentException("Já existe uma pessoa tutora com este e-mail.");
        }
        PessoaTutora pessoa = new PessoaTutora(email, nome, endereco, telefone, setor);
        setor.setPessoaTutora(pessoa);
        setorRepository.atualizar(setor);
        IDManager.adicionarId(email);
        return pessoa;
    }

    /**
     * Atualiza os dados de uma pessoa tutora.
     * @param pessoa pessoa tutora a ser atualizada
     */
    public void atualizarPessoaTutora(PessoaTutora pessoa) {
        SetorResponsavel setor = pessoa.getSetor();
        if (setor != null) {
            setor.setPessoaTutora(pessoa);
            setorRepository.atualizar(setor);
        }
    }

    /**
     * Remove uma pessoa tutora pelo email.
     * @param email email da pessoa tutora
     */
    public void removerPessoaTutora(String email) {
        List<SetorResponsavel> setores = setorRepository.listarTodos();
        for (SetorResponsavel setor : setores) {
            PessoaTutora pessoa = setor.getPessoaTutora();
            if (pessoa != null && pessoa.getEmail().equals(email)) {
                setor.setPessoaTutora(null);
                setorRepository.atualizar(setor);
                IDManager.removerId(email);
                break;
            }
        }
    }

    /**
     * Busca uma pessoa tutora pelo email.
     * @param email email da pessoa tutora
     * @return pessoa tutora encontrada ou null
     */
    public PessoaTutora buscarPorEmail(String email) {
        List<SetorResponsavel> setores = setorRepository.listarTodos();
        for (SetorResponsavel setor : setores) {
            PessoaTutora pessoa = setor.getPessoaTutora();
            if (pessoa != null && pessoa.getEmail().equals(email)) {
                return pessoa;
            }
        }
        return null;
    }

    /**
     * Lista todas as pessoas tutoras do sistema.
     * @return lista de pessoas tutoras
     */
    public List<PessoaTutora> listarTodos() {
        List<PessoaTutora> pessoas = new ArrayList<>();
        List<SetorResponsavel> setores = setorRepository.listarTodos();
        for (SetorResponsavel setor : setores) {
            PessoaTutora pessoa = setor.getPessoaTutora();
            if (pessoa != null) {
                pessoas.add(pessoa);
            }
        }
        return pessoas;
    }
}
