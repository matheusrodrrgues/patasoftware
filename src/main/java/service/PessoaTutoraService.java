package service;

import model.PessoaTutora;
import model.SetorResponsavel;
import repository.PessoaTutoraRepository;
import repository.IDManager;
import java.util.List;

public class PessoaTutoraService {
    private PessoaTutoraRepository pessoaTutoraRepository;

    public PessoaTutoraService() {
        pessoaTutoraRepository = new PessoaTutoraRepository();
    }

    public PessoaTutora criarPessoaTutora(String email, String nome, String endereco, String telefone, SetorResponsavel setor) {
        if (IDManager.existeId(email)) {
            throw new IllegalArgumentException("Já existe uma pessoa tutora com este e-mail.");
        }
        PessoaTutora pessoa = new PessoaTutora(email, nome, endereco, telefone, setor);
        pessoaTutoraRepository.salvar(pessoa);
        IDManager.adicionarId(email);
        return pessoa;
    }

    public void atualizarPessoaTutora(PessoaTutora pessoa) {
        pessoaTutoraRepository.atualizar(pessoa);
    }

    public void removerPessoaTutora(String email) {
        pessoaTutoraRepository.remover(email);
        IDManager.removerId(email);
    }

    public PessoaTutora buscarPorEmail(String email) {
        return pessoaTutoraRepository.buscarPorEmail(email);
    }

    public List<PessoaTutora> listarTodos() {
        return pessoaTutoraRepository.listarTodos();
    }
}
