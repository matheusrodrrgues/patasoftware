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

    public AnimalRepository(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
        carregarDeArquivo();
    }

    public void salvar(Animal animal, int setorId) {
        SetorResponsavel setor = setorRepository.buscarPorId(setorId);
        if (setor != null) {
            setor.adicionarAnimal(animal);
            setorRepository.atualizar(setor);
        }
    }

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

    public void remover(int animalId, int setorId) {
        SetorResponsavel setor = setorRepository.buscarPorId(setorId);
        if (setor != null) {
            setor.getAnimais().removeIf(a -> a.getId() == animalId);
            setorRepository.atualizar(setor);
        }
    }

    public Animal buscarPorId(int animalId, int setorId) {
        SetorResponsavel setor = setorRepository.buscarPorId(setorId);
        if (setor != null) {
            return setor.getAnimais().stream().filter(a -> a.getId() == animalId).findFirst().orElse(null);
        }
        return null;
    }

    public List<Animal> listarTodos() {
        List<Animal> todosAnimais = new ArrayList<>();
        for (SetorResponsavel setor : setorRepository.listarTodos()) {
            todosAnimais.addAll(setor.getAnimais());
        }
        return todosAnimais;
    }

    public void salvarEmArquivo() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ARQUIVO_JSON), setorRepository.listarTodos());
        } catch (Exception e) {
            System.out.println("Erro ao salvar setores: " + e.getMessage());
        }
    }

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
