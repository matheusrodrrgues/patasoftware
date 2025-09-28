package service;

import model.Animal;
import model.SetorResponsavel;
import model.PessoaTutora;
import repository.AnimalRepository;
import java.util.List;
import java.util.stream.Collectors;

public class AnimalService {
    private AnimalRepository animalRepository;

    public AnimalService() {
        animalRepository = new AnimalRepository();
    }

    public Animal criarAnimal(String nome, String especie, String raca, int idade, String sexo, String situacao, SetorResponsavel setor, PessoaTutora pessoaTutora) {
        Animal animal = new Animal(nome, especie, raca, idade, sexo, situacao, setor, pessoaTutora);
        animalRepository.salvar(animal);
        return animal;
    }

    public void atualizarAnimal(Animal animal) {
        animalRepository.atualizar(animal);
    }

    public void removerAnimal(int id) {
        animalRepository.remover(id);
    }

    public Animal buscarPorId(int id) {
        return animalRepository.buscarPorId(id);
    }

    public List<Animal> listarTodos() {
        return animalRepository.listarTodos();
    }

    public List<Animal> listarPorSetor(SetorResponsavel setor) {
        return animalRepository.listarTodos().stream()
                .filter(a -> a.getSetor().equals(setor))
                .collect(Collectors.toList());
    }

    public List<Animal> listarPorPessoaTutora(PessoaTutora pessoa) {
        return animalRepository.listarTodos().stream()
                .filter(a -> a.getPessoaTutora().equals(pessoa))
                .collect(Collectors.toList());
    }
}
