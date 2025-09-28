package repository;

import model.Animal;
import java.util.ArrayList;
import java.util.List;

public class AnimalRepository {
    private List<Animal> animais;

    public AnimalRepository() {
        animais = new ArrayList<>();
    }

    public void salvar(Animal animal) {
        animais.add(animal);
    }

    public void atualizar(Animal animal) {
        for (int i = 0; i < animais.size(); i++) {
            if (animais.get(i).getId() == animal.getId()) {
                animais.set(i, animal);
                return;
            }
        }
    }

    public void remover(int id) {
        animais.removeIf(a -> a.getId() == id);
    }

    public Animal buscarPorId(int id) {
        return animais.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    public List<Animal> listarTodos() {
        return new ArrayList<>(animais);
    }
}
