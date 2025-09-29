package service;

import model.PessoaTutora;
import model.SetorResponsavel;
import repository.SetorRepository;
import repository.IDManager;
import java.util.List;
import java.util.ArrayList;

public class PessoaTutoraService {
    private SetorRepository setorRepository;

    public PessoaTutoraService() {
        setorRepository = new SetorRepository();
    }

    public PessoaTutoraService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public PessoaTutora criarPessoaTutora(String email, String nome, String endereco, String telefone, SetorResponsavel setor) {
        if (IDManager.existeId(email)) {
            throw new IllegalArgumentException("Já existe uma pessoa tutora com este e-mail.");
        }
        PessoaTutora pessoa = new PessoaTutora(email, nome, endereco, telefone, setor);
        setor.setPessoaTutora(pessoa);
        setorRepository.atualizar(setor);
        IDManager.adicionarId(email);
        return pessoa;
    }

    public void atualizarPessoaTutora(PessoaTutora pessoa) {
        SetorResponsavel setor = pessoa.getSetor();
        if (setor != null) {
            setor.setPessoaTutora(pessoa);
            setorRepository.atualizar(setor);
        }
    }

    public void removerPessoaTutora(String email) {
        List<SetorResponsavel> setores = setorRepository.listarTodos();
        for (SetorResponsavel setor : setores) {
            PessoaTutora pessoa = setor.getPessoaTutora();
            if (pessoa != null && pessoa.getEmail().equals(email)) {
                setor.setPessoaTutora(null);
                setorRepository.atualizar(setor);
                IDManager.removerId(email);
                break;
            }
        }
    }

    public PessoaTutora buscarPorEmail(String email) {
        List<SetorResponsavel> setores = setorRepository.listarTodos();
        for (SetorResponsavel setor : setores) {
            PessoaTutora pessoa = setor.getPessoaTutora();
            if (pessoa != null && pessoa.getEmail().equals(email)) {
                return pessoa;
            }
        }
        return null;
    }

    public List<PessoaTutora> listarTodos() {
        List<PessoaTutora> pessoas = new ArrayList<>();
        List<SetorResponsavel> setores = setorRepository.listarTodos();
        for (SetorResponsavel setor : setores) {
            PessoaTutora pessoa = setor.getPessoaTutora();
            if (pessoa != null) {
                pessoas.add(pessoa);
            }
        }
        return pessoas;
    }
}
