package controller;

import model.SetorResponsavel;
import model.Animal;
import model.PessoaTutora;
import service.SetorService;
import java.util.List;

public class SetorController {
    private SetorService setorService;

    public SetorController() {
        setorService = new SetorService();
    }

    public SetorResponsavel criarSetor(String nome, String endereco) {
        return setorService.criarSetor(nome, endereco);
    }

    public void atualizarSetor(SetorResponsavel setor) {
        setorService.atualizarSetor(setor);
    }

    public void removerSetor(String id) {
        setorService.removerSetor(id);
    }

    public SetorResponsavel buscarPorId(String id) {
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
