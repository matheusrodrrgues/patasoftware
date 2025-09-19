package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import model.PessoaTutora;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PessoaTutoraRepository {
    private static final String FILE_PATH = "pessoas_tutoras.json";
    private List<PessoaTutora> pessoasTutoras;
    private ObjectMapper mapper;

    public PessoaTutoraRepository() {
        mapper = new ObjectMapper();
        pessoasTutoras = carregarPessoasTutoras();
    }

    public void salvar(PessoaTutora pessoa) {
        pessoasTutoras.add(pessoa);
        persistir();
    }

    public void atualizar(PessoaTutora pessoa) {
        for (int i = 0; i < pessoasTutoras.size(); i++) {
            if (pessoasTutoras.get(i).getEmail().equals(pessoa.getEmail())) {
                pessoasTutoras.set(i, pessoa);
                persistir();
                return;
            }
        }
    }

    public void remover(String email) {
        pessoasTutoras.removeIf(p -> p.getEmail().equals(email));
        persistir();
    }

    public PessoaTutora buscarPorEmail(String email) {
        return pessoasTutoras.stream().filter(p -> p.getEmail().equals(email)).findFirst().orElse(null);
    }

    public List<PessoaTutora> listarTodos() {
        return new ArrayList<>(pessoasTutoras);
    }

    private void persistir() {
        try {
            mapper.writeValue(new File(FILE_PATH), pessoasTutoras);
        } catch (IOException e) {
            System.err.println("Erro ao salvar pessoas tutoras: " + e.getMessage());
        }
    }

    private List<PessoaTutora> carregarPessoasTutoras() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                return mapper.readValue(file, new TypeReference<List<PessoaTutora>>(){});
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar pessoas tutoras: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
