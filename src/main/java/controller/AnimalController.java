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
        return animalService.criarAnimal(nome, especie, raca, idade, sexo, situacaoFinal, setor, pessoaTutora);
    }

    public void atualizarAnimal(Animal animal) {
        animalService.atualizarAnimal(animal);
    }

    public void removerAnimal(int id) {
        animalService.removerAnimal(id);
    }

    public Animal buscarPorId(int id) {
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
