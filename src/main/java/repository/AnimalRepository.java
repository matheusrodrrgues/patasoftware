/**
 * Repositório responsável por gerenciar os animais do sistema.
 * Permite salvar, atualizar, remover e buscar animais vinculados a setores.
 */
package repository;

import model.Animal;
import model.SetorResponsavel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AnimalRepository {
    private SetorRepository setorRepository;
    private static final String ARQUIVO_JSON = "src/main/java/dados/setores.json";

    /**
     * Construtor do repositório de animais.
     * @param setorRepository repositório de setores
     */
    public AnimalRepository(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
        carregarDeArquivo();
    }

    /**
     * Salva um animal em um setor.
     * @param animal animal a ser salvo
     * @param setorId id do setor
     */
    public void salvar(Animal animal, int setorId) {
        SetorResponsavel setor = setorRepository.buscarPorId(setorId);
        if (setor != null) {
            setor.adicionarAnimal(animal);
            setorRepository.atualizar(setor);
        }
    }

    /**
     * Atualiza um animal em um setor.
     * @param animal animal a ser atualizado
     * @param setorId id do setor
     */
    public void atualizar(Animal animal, int setorId) {
        SetorResponsavel setor = setorRepository.buscarPorId(setorId);
        if (setor != null) {
            List<Animal> animais = setor.getAnimais();
            for (int i = 0; i < animais.size(); i++) {
                if (animais.get(i).getId() == animal.getId()) {
                    animais.set(i, animal);
                    setorRepository.atualizar(setor);
                    return;
                }
            }
        }
    }

    /**
     * Remove um animal de um setor.
     * @param animalId id do animal
     * @param setorId id do setor
     */
    public void remover(int animalId, int setorId) {
        SetorResponsavel setor = setorRepository.buscarPorId(setorId);
        if (setor != null) {
            setor.getAnimais().removeIf(a -> a.getId() == animalId);
            setorRepository.atualizar(setor);
        }
    }

    /**
     * Busca um animal pelo id e setor.
     * @param animalId id do animal
     * @param setorId id do setor
     * @return animal encontrado ou null
     */
    public Animal buscarPorId(int animalId, int setorId) {
        SetorResponsavel setor = setorRepository.buscarPorId(setorId);
        if (setor != null) {
            return setor.getAnimais().stream().filter(a -> a.getId() == animalId).findFirst().orElse(null);
        }
        return null;
    }

    /**
     * Lista todos os animais de todos os setores.
     * @return lista de todos os animais
     */
    public List<Animal> listarTodos() {
        List<Animal> todosAnimais = new ArrayList<>();
        for (SetorResponsavel setor : setorRepository.listarTodos()) {
            todosAnimais.addAll(setor.getAnimais());
        }
        return todosAnimais;
    }

    /**
     * Salva os dados dos setores em um arquivo JSON.
     */
    public void salvarEmArquivo() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ARQUIVO_JSON), setorRepository.listarTodos());
        } catch (Exception e) {
            System.out.println("Erro ao salvar setores: " + e.getMessage());
        }
    }

    /**
     * Carrega os dados dos setores a partir de um arquivo JSON.
     */
    public void carregarDeArquivo() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(ARQUIVO_JSON);
            if (file.exists()) {
                List<SetorResponsavel> lista = mapper.readValue(file, new TypeReference<List<SetorResponsavel>>(){});
                if (lista != null) setorRepository.setSetores(lista);
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar setores: " + e.getMessage());
        }
    }
}
