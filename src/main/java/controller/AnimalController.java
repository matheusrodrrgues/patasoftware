/**
 * Controlador responsável pelas operações relacionadas aos animais.
 * Usa o serviço AnimalService para manipular dados dos animais.
 */
package controller;

import model.Animal;
import model.SetorResponsavel;
import model.PessoaTutora;
import service.AnimalService;
import java.util.List;
import repository.SetorRepository;

public class AnimalController {
    private AnimalService animalService;

    /**
     * Construtor do controlador de animais.
     * @param setorRepository repositório de setores usado pelo serviço
     */
    public AnimalController(SetorRepository setorRepository) {
        animalService = new AnimalService(setorRepository);
    }

    /**
     * Cria um novo animal no sistema.
     * @param nome nome do animal
     * @param especie espécie do animal
     * @param raca raça do animal
     * @param idade idade do animal
     * @param sexo sexo do animal
     * @param situacao situação do animal
     * @param setorId id do setor responsável
     * @param pessoaTutora pessoa tutora do animal
     * @return animal criado
     */
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

    /**
     * Atualiza os dados de um animal.
     * @param animal animal a ser atualizado
     * @param setorId id do setor responsável
     */
    public void atualizarAnimal(Animal animal, int setorId) {
        animalService.atualizarAnimal(animal, setorId);
    }

    /**
     * Remove um animal do sistema.
     * @param animalId id do animal
     * @param setorId id do setor responsável
     */
    public void removerAnimal(int animalId, int setorId) {
        animalService.removerAnimal(animalId, setorId);
    }

    /**
     * Busca um animal pelo id e setor.
     * @param animalId id do animal
     * @param setorId id do setor
     * @return animal encontrado ou null
     */
    public Animal buscarPorId(int animalId, int setorId) {
        return animalService.buscarPorId(animalId, setorId);
    }

    /**
     * Lista todos os animais do sistema.
     * @return lista de animais
     */
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
