package repository;

import model.SetorResponsavel;
import java.util.ArrayList;
import java.util.List;

public class SetorRepository {
    private List<SetorResponsavel> setores;

    public SetorRepository() {
        setores = new ArrayList<>();
    }

    public void salvar(SetorResponsavel setor) {
        setores.add(setor);
    }

    public void atualizar(SetorResponsavel setor) {
        for (int i = 0; i < setores.size(); i++) {
            if (setores.get(i).getId() == setor.getId()) {
                setores.set(i, setor);
                return;
            }
        }
    }

    public void remover(int id) {
        setores.removeIf(s -> s.getId() == id);
    }

    public SetorResponsavel buscarPorId(int id) {
        return setores.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    public List<SetorResponsavel> listarTodos() {
        return new ArrayList<>(setores);
    }
}
