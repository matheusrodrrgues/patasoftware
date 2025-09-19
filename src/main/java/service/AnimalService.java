package service;

import model.Animal;
import model.SetorResponsavel;
import model.PessoaTutora;
import repository.AnimalRepository;
import repository.IDManager;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AnimalService {
    private AnimalRepository animalRepository;

    public AnimalService() {
        animalRepository = new AnimalRepository();
    }

    public Animal criarAnimal(String nome, String especie, String raca, int idade, String sexo, String situacao, SetorResponsavel setor, PessoaTutora pessoaTutora) {
        String id = gerarIdUnico();
        Animal animal = new Animal(id, nome, especie, raca, idade, sexo, situacao, setor, pessoaTutora);
        animalRepository.salvar(animal);
        IDManager.adicionarId(id);
        return animal;
    }

    public void atualizarAnimal(Animal animal) {
        animalRepository.atualizar(animal);
    }

    public void removerAnimal(String id) {
        animalRepository.remover(id);
        IDManager.removerId(id);
    }

    public Animal buscarPorId(String id) {
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

    private String gerarIdUnico() {
        String id;
        do {
            id = UUID.randomUUID().toString();
        } while (IDManager.existeId(id));
        return id;
    }
}
