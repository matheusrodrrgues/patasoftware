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

    public SetorController(SetorRepository setorRepository) {
        setorService = new SetorService(setorRepository);
        pessoaTutoraService = new PessoaTutoraService(setorRepository);
    }

    public SetorResponsavel criarSetor(String nome, String endereco) {
        return setorService.criarSetor(nome, endereco);
    }

    public void atualizarSetor(SetorResponsavel setor) {
        setorService.atualizarSetor(setor);
    }

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

    public SetorResponsavel buscarPorId(int id) {
        return setorService.buscarPorId(id);
    }

    public List<SetorResponsavel> listarTodos() {
        return setorService.listarTodos();
    }

    public void vincularAnimal(SetorResponsavel setor, Animal animal) {
        setorService.vincularAnimal(setor, animal);
    }

    public void vincularPessoaTutora(SetorResponsavel setor, PessoaTutora pessoa) {
        setorService.vincularPessoaTutora(setor, pessoa);
    }
}
