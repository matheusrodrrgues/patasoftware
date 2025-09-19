package service;

import model.SetorResponsavel;
import model.Animal;
import model.PessoaTutora;
import repository.SetorRepository;
import repository.IDManager;
import java.util.List;
import java.util.UUID;

public class SetorService {
    private SetorRepository setorRepository;

    public SetorService() {
        setorRepository = new SetorRepository();
    }

    public SetorResponsavel criarSetor(String nome, String endereco) {
        String id = gerarIdUnico();
        SetorResponsavel setor = new SetorResponsavel(id, nome, endereco);
        setorRepository.salvar(setor);
        IDManager.adicionarId(id);
        return setor;
    }

    public void atualizarSetor(SetorResponsavel setor) {
        setorRepository.atualizar(setor);
    }

    public void removerSetor(String id) {
        setorRepository.remover(id);
        IDManager.removerId(id);
    }

    public SetorResponsavel buscarPorId(String id) {
        return setorRepository.buscarPorId(id);
    }

    public List<SetorResponsavel> listarTodos() {
        return setorRepository.listarTodos();
    }

    public void vincularAnimal(SetorResponsavel setor, Animal animal) {
        setor.adicionarAnimal(animal);
        atualizarSetor(setor);
    }

    public void vincularPessoaTutora(SetorResponsavel setor, PessoaTutora pessoa) {
        setor.adicionarPessoaTutora(pessoa);
        atualizarSetor(setor);
    }

    private String gerarIdUnico() {
        String id;
        do {
            id = UUID.randomUUID().toString();
        } while (IDManager.existeId(id));
        return id;
    }
}
