package service;

/**
 * Serviço responsável pelas regras de negócio dos animais.
 * Permite criar, atualizar, remover e buscar animais.
 */
import model.Animal;
import model.SetorResponsavel;
import model.PessoaTutora;
import repository.AnimalRepository;
import repository.SetorRepository;
import java.util.List;
import java.util.stream.Collectors;

public class AnimalService {
    private AnimalRepository animalRepository;
    private SetorRepository setorRepository;

    /**
     * Construtor do serviço de animais.
     * @param setorRepository repositório de setores
     */
    public AnimalService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
        this.animalRepository = new AnimalRepository(setorRepository);
    }

    /**
     * Cria um novo animal.
     * @param nome nome do animal
     * @param especie espécie do animal
     * @param raca raça do animal
     * @param idade idade do animal
     * @param sexo sexo do animal
     * @param situacao situação do animal
     * @param setorId id do setor
     * @param pessoaTutora pessoa tutora do animal
     * @return animal criado
     */
    public Animal criarAnimal(String nome, String especie, String raca, int idade, String sexo, String situacao, int setorId, PessoaTutora pessoaTutora) {
        SetorResponsavel setor = setorRepository.buscarPorId(setorId);
        Animal animal = new Animal(nome, especie, raca, idade, sexo, situacao, setor, pessoaTutora);
        animalRepository.salvar(animal, setorId);
        return animal;
    }

    /**
     * Atualiza um animal.
     * @param animal animal a ser atualizado
     * @param setorId id do setor
     */
    public void atualizarAnimal(Animal animal, int setorId) {
        animalRepository.atualizar(animal, setorId);
    }

    /**
     * Remove um animal.
     * @param animalId id do animal
     * @param setorId id do setor
     */
    public void removerAnimal(int animalId, int setorId) {
        animalRepository.remover(animalId, setorId);
    }

    /**
     * Busca um animal pelo id e setor.
     * @param animalId id do animal
     * @param setorId id do setor
     * @return animal encontrado ou null
     */
    public Animal buscarPorId(int animalId, int setorId) {
        return animalRepository.buscarPorId(animalId, setorId);
    }

    /**
     * Lista todos os animais do sistema.
     * @return lista de animais
     */
    public List<Animal> listarTodos() {
        return animalRepository.listarTodos();
    }

    public List<Animal> listarPorSetor(int setorId) {
        SetorResponsavel setor = setorRepository.buscarPorId(setorId);
        return setor != null ? setor.getAnimais() : List.of();
    }

    public List<Animal> listarPorPessoaTutora(PessoaTutora pessoa) {
        return animalRepository.listarTodos().stream()
                .filter(a -> a.getPessoaTutora().equals(pessoa))
                .collect(Collectors.toList());
    }
}
