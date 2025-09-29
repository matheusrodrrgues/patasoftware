/**
 * Controlador responsável pelas operações de setores.
 * Usa o serviço SetorService para manipular dados dos setores.
 */
package controller;

import model.SetorResponsavel;
import model.Animal;
import model.PessoaTutora;
import service.SetorService;
import service.PessoaTutoraService;
import repository.SetorRepository;
import java.util.List;

public class SetorController {
    private SetorService setorService;
    private PessoaTutoraService pessoaTutoraService;

    /**
     * Construtor do controlador de setores.
     * @param setorRepository repositório de setores
     */
    public SetorController(SetorRepository setorRepository) {
        setorService = new SetorService(setorRepository);
        pessoaTutoraService = new PessoaTutoraService(setorRepository);
    }

    /**
     * Cria um novo setor.
     * @param nome nome do setor
     * @param endereco endereço do setor
     * @return setor criado
     */
    public SetorResponsavel criarSetor(String nome, String endereco) {
        return setorService.criarSetor(nome, endereco);
    }

    /**
     * Atualiza os dados de um setor.
     * @param setor setor a ser atualizado
     */
    public void atualizarSetor(SetorResponsavel setor) {
        setorService.atualizarSetor(setor);
    }

    /**
     * Remove um setor do sistema, podendo remanejar animais para outro setor.
     * @param id id do setor a ser removido
     * @param novoSetorRemanejamento setor para onde os animais serão remanejados
     */
    public void removerSetor(int id, SetorResponsavel novoSetorRemanejamento) {
        SetorResponsavel setor = setorService.buscarPorId(id);
        if (setor == null) {
            System.out.println("Setor não encontrado.");
            return;
        }
        // Remanejamento de animais permanece igual
        List<Animal> animaisVinculados = setor.getAnimais();
        if (animaisVinculados != null && !animaisVinculados.isEmpty()) {
            System.out.println("Remanejando animais para o setor: " + novoSetorRemanejamento.getNome());
            for (Animal animal : animaisVinculados) {
                animal.setSetor(novoSetorRemanejamento);
                setorService.vincularAnimal(novoSetorRemanejamento, animal);
            }
        }
        // Remove a pessoa tutora vinculada ao setor
        PessoaTutora pessoa = setor.getPessoaTutora();
        if (pessoa != null) {
            pessoaTutoraService.removerPessoaTutora(pessoa.getEmail());
        }
        setorService.removerSetor(id);
        System.out.println("Setor removido com sucesso.");
    }

    /**
     * Busca um setor pelo seu ID.
     * @param id id do setor a ser buscado
     * @return setor encontrado, ou null se não existir
     */
    public SetorResponsavel buscarPorId(int id) {
        return setorService.buscarPorId(id);
    }

    /**
     * Lista todos os setores existentes.
     * @return lista de setores
     */
    public List<SetorResponsavel> listarTodos() {
        return setorService.listarTodos();
    }

    /**
     * Vincula um animal a um setor.
     * @param setor setor ao qual o animal será vinculado
     * @param animal animal a ser vinculado
     */
    public void vincularAnimal(SetorResponsavel setor, Animal animal) {
        setorService.vincularAnimal(setor, animal);
    }

    /**
     * Vincula uma pessoa tutora a um setor.
     * @param setor setor ao qual a pessoa tutora será vinculada
     * @param pessoa pessoa tutora a ser vinculada
     */
    public void vincularPessoaTutora(SetorResponsavel setor, PessoaTutora pessoa) {
        setorService.vincularPessoaTutora(setor, pessoa);
    }
}
