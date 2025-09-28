package repository;

import model.PessoaTutora;
import java.util.ArrayList;
import java.util.List;

public class PessoaTutoraRepository {
    private List<PessoaTutora> pessoasTutoras;

    public PessoaTutoraRepository() {
        pessoasTutoras = new ArrayList<>();
    }

    public void salvar(PessoaTutora pessoa) {
        pessoasTutoras.add(pessoa);
    }

    public void atualizar(PessoaTutora pessoa) {
        for (int i = 0; i < pessoasTutoras.size(); i++) {
            if (pessoasTutoras.get(i).getEmail().equals(pessoa.getEmail())) {
                pessoasTutoras.set(i, pessoa);
                return;
            }
        }
    }

    public void remover(String email) {
        pessoasTutoras.removeIf(p -> p.getEmail().equals(email));
    }

    public PessoaTutora buscarPorEmail(String email) {
        return pessoasTutoras.stream().filter(p -> p.getEmail().equals(email)).findFirst().orElse(null);
    }

    public List<PessoaTutora> listarTodos() {
        return new ArrayList<>(pessoasTutoras);
    }
}
