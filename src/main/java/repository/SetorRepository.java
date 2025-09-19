package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import model.SetorResponsavel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SetorRepository {
    private static final String FILE_PATH = "setores.json";
    private List<SetorResponsavel> setores;
    private ObjectMapper mapper;

    public SetorRepository() {
        mapper = new ObjectMapper();
        setores = carregarSetores();
    }

    public void salvar(SetorResponsavel setor) {
        setores.add(setor);
        persistir();
    }

    public void atualizar(SetorResponsavel setor) {
        for (int i = 0; i < setores.size(); i++) {
            if (setores.get(i).getId().equals(setor.getId())) {
                setores.set(i, setor);
                persistir();
                return;
            }
        }
    }

    public void remover(String id) {
        setores.removeIf(s -> s.getId().equals(id));
        persistir();
    }

    public SetorResponsavel buscarPorId(String id) {
        return setores.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    public List<SetorResponsavel> listarTodos() {
        return new ArrayList<>(setores);
    }

    private void persistir() {
        try {
            mapper.writeValue(new File(FILE_PATH), setores);
        } catch (IOException e) {
            System.err.println("Erro ao salvar setores: " + e.getMessage());
        }
    }

    private List<SetorResponsavel> carregarSetores() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                return mapper.readValue(file, new TypeReference<List<SetorResponsavel>>(){});
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar setores: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
