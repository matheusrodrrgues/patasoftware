package controller;

import model.Animal;
import model.SetorResponsavel;
import model.PessoaTutora;
import service.AnimalService;
import java.util.List;

public class AnimalController {
    private AnimalService animalService;

    public AnimalController() {
        animalService = new AnimalService();
    }

    public Animal criarAnimal(String nome, String especie, String raca, int idade, String sexo, String situacao, SetorResponsavel setor, PessoaTutora pessoaTutora) {
        return animalService.criarAnimal(nome, especie, raca, idade, sexo, situacao, setor, pessoaTutora);
    }

    public void atualizarAnimal(Animal animal) {
        animalService.atualizarAnimal(animal);
    }

    public void removerAnimal(String id) {
        animalService.removerAnimal(id);
    }

    public Animal buscarPorId(String id) {
        return animalService.buscarPorId(id);
    }

    public List<Animal> listarTodos() {
        return animalService.listarTodos();
    }

    public List<Animal> listarPorSetor(SetorResponsavel setor) {
        return animalService.listarPorSetor(setor);
    }

    public List<Animal> listarPorPessoaTutora(PessoaTutora pessoa) {
        return animalService.listarPorPessoaTutora(pessoa);
    }
}
