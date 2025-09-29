package service;

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

    public AnimalService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
        this.animalRepository = new AnimalRepository(setorRepository);
    }

    public Animal criarAnimal(String nome, String especie, String raca, int idade, String sexo, String situacao, int setorId, PessoaTutora pessoaTutora) {
        SetorResponsavel setor = setorRepository.buscarPorId(setorId);
        Animal animal = new Animal(nome, especie, raca, idade, sexo, situacao, setor, pessoaTutora);
        animalRepository.salvar(animal, setorId);
        return animal;
    }

    public void atualizarAnimal(Animal animal, int setorId) {
        animalRepository.atualizar(animal, setorId);
    }

    public void removerAnimal(int animalId, int setorId) {
        animalRepository.remover(animalId, setorId);
    }

    public Animal buscarPorId(int animalId, int setorId) {
        return animalRepository.buscarPorId(animalId, setorId);
    }

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
