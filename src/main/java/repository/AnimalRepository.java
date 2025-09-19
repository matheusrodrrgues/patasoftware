package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import model.Animal;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnimalRepository {
    private static final String FILE_PATH = "animais.json";
    private List<Animal> animais;
    private ObjectMapper mapper;

    public AnimalRepository() {
        mapper = new ObjectMapper();
        animais = carregarAnimais();
    }

    public void salvar(Animal animal) {
        animais.add(animal);
        persistir();
    }

    public void atualizar(Animal animal) {
        for (int i = 0; i < animais.size(); i++) {
            if (animais.get(i).getId().equals(animal.getId())) {
                animais.set(i, animal);
                persistir();
                return;
            }
        }
    }

    public void remover(String id) {
        animais.removeIf(a -> a.getId().equals(id));
        persistir();
    }

    public Animal buscarPorId(String id) {
        return animais.stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Animal> listarTodos() {
        return new ArrayList<>(animais);
    }

    private void persistir() {
        try {
            mapper.writeValue(new File(FILE_PATH), animais);
        } catch (IOException e) {
            System.err.println("Erro ao salvar animais: " + e.getMessage());
        }
    }

    private List<Animal> carregarAnimais() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                return mapper.readValue(file, new TypeReference<List<Animal>>(){});
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar animais: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
