package service;

import model.SetorResponsavel;
import model.Animal;
import model.PessoaTutora;
import repository.SetorRepository;
import java.util.List;

public class SetorService {
    private SetorRepository setorRepository;

    public SetorService() {
        setorRepository = new SetorRepository();
    }

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public SetorResponsavel criarSetor(String nome, String endereco) {
        SetorResponsavel setor = new SetorResponsavel(nome, endereco);
        setorRepository.salvar(setor);
        return setor;
    }

    public void atualizarSetor(SetorResponsavel setor) {
        setorRepository.atualizar(setor);
    }

    public void removerSetor(int id) {
        setorRepository.remover(id);
    }

    public SetorResponsavel buscarPorId(int id) {
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
        setor.setPessoaTutora(pessoa);
        atualizarSetor(setor);
    }
}
