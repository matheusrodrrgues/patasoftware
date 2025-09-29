package service;

/**
 * Serviço responsável pelas regras de negócio dos setores.
 * Permite criar, atualizar, remover e buscar setores, além de vincular animais e pessoas tutoras.
 */
import model.SetorResponsavel;
import model.Animal;
import model.PessoaTutora;
import repository.SetorRepository;
import java.util.List;

public class SetorService {
    private SetorRepository setorRepository;

    /**
     * Construtor padrão do serviço de setores.
     */
    public SetorService() {
        setorRepository = new SetorRepository();
    }

    /**
     * Construtor que recebe o repositório de setores.
     * @param setorRepository repositório de setores
     */
    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    /**
     * Cria um novo setor.
     * @param nome nome do setor
     * @param endereco endereço do setor
     * @return setor criado
     */
    public SetorResponsavel criarSetor(String nome, String endereco) {
        SetorResponsavel setor = new SetorResponsavel(nome, endereco);
        setorRepository.salvar(setor);
        return setor;
    }

    /**
     * Atualiza os dados de um setor.
     * @param setor setor a ser atualizado
     */
    public void atualizarSetor(SetorResponsavel setor) {
        setorRepository.atualizar(setor);
    }

    /**
     * Remove um setor pelo id.
     * @param id id do setor
     */
    public void removerSetor(int id) {
        setorRepository.remover(id);
    }

    /**
     * Busca um setor pelo id.
     * @param id id do setor
     * @return setor encontrado ou null
     */
    public SetorResponsavel buscarPorId(int id) {
        return setorRepository.buscarPorId(id);
    }

    /**
     * Lista todos os setores do sistema.
     * @return lista de setores
     */
    public List<SetorResponsavel> listarTodos() {
        return setorRepository.listarTodos();
    }

    /**
     * Vincula um animal a um setor.
     * @param setor setor ao qual o animal será vinculado
     * @param animal animal a ser vinculado
     */
    public void vincularAnimal(SetorResponsavel setor, Animal animal) {
        setor.adicionarAnimal(animal);
        atualizarSetor(setor);
    }

    /**
     * Vincula uma pessoa tutora a um setor.
     * @param setor setor ao qual a pessoa tutora será vinculada
     * @param pessoa pessoa tutora a ser vinculada
     */
    public void vincularPessoaTutora(SetorResponsavel setor, PessoaTutora pessoa) {
        setor.setPessoaTutora(pessoa);
        atualizarSetor(setor);
    }
}
