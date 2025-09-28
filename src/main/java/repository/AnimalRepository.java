package repository;

import model.Animal;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AnimalRepository {
    private List<Animal> animais;
    private static final String ARQUIVO_JSON = "src/main/java/dados/animais.json";

    public AnimalRepository() {
        animais = new ArrayList<>();
        carregarDeArquivo();
    }

    public void salvar(Animal animal) {
        animais.add(animal);
        salvarEmArquivo();
    }

    public void atualizar(Animal animal) {
        for (int i = 0; i < animais.size(); i++) {
            if (animais.get(i).getId() == animal.getId()) {
                animais.set(i, animal);
                salvarEmArquivo();
                return;
            }
        }
    }

    public void remover(int id) {
        animais.removeIf(a -> a.getId() == id);
        salvarEmArquivo();
    }

    public Animal buscarPorId(int id) {
        return animais.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    public List<Animal> listarTodos() {
        return new ArrayList<>(animais);
    }

    public void salvarEmArquivo() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ARQUIVO_JSON), animais);
        } catch (Exception e) {
            System.out.println("Erro ao salvar animais: " + e.getMessage());
        }
    }

    public void carregarDeArquivo() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(ARQUIVO_JSON);
            if (file.exists()) {
                List<Animal> lista = mapper.readValue(file, new TypeReference<List<Animal>>(){});
                if (lista != null) animais = lista;
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar animais: " + e.getMessage());
        }
    }
}
