package controller;

import model.Animal;
import model.SetorResponsavel;
import model.PessoaTutora;
import service.AnimalService;
import java.util.List;
import repository.SetorRepository;

public class AnimalController {
    private AnimalService animalService;

    public AnimalController(SetorRepository setorRepository) {
        animalService = new AnimalService(setorRepository);
    }

    public Animal criarAnimal(String nome, String especie, String raca, int idade, String sexo, String situacao, int setorId, PessoaTutora pessoaTutora) {
        String situacaoFinal;
        switch (situacao.toLowerCase()) {
            case "em observação":
            case "em observacao":
                situacaoFinal = "Em observação";
                break;
            case "disponível para adoção":
            case "disponivel para adocao":
                situacaoFinal = "Disponível para adoção";
                break;
            case "em tratamento":
                situacaoFinal = "Em tratamento";
                break;
            default:
                System.out.println("Situação inválida! Usando 'Em observação' por padrão.");
                situacaoFinal = "Em observação";
        }
        return animalService.criarAnimal(nome, especie, raca, idade, sexo, situacaoFinal, setorId, pessoaTutora);
    }

    public void atualizarAnimal(Animal animal, int setorId) {
        animalService.atualizarAnimal(animal, setorId);
    }

    public void removerAnimal(int animalId, int setorId) {
        animalService.removerAnimal(animalId, setorId);
    }

    public Animal buscarPorId(int animalId, int setorId) {
        return animalService.buscarPorId(animalId, setorId);
    }

    public List<Animal> listarTodos() {
        return animalService.listarTodos();
    }

    public List<Animal> listarPorSetor(int setorId) {
        return animalService.listarPorSetor(setorId);
    }

    public List<Animal> listarPorPessoaTutora(PessoaTutora pessoa) {
        return animalService.listarPorPessoaTutora(pessoa);
    }
}
