package repository;

import model.PessoaTutora;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PessoaTutoraRepository {
    private List<PessoaTutora> pessoasTutoras;
    private static final String ARQUIVO_JSON = "pessoas_tutoras.json";

    public PessoaTutoraRepository() {
        pessoasTutoras = new ArrayList<>();
        carregarDeArquivo();
    }

    public void salvar(PessoaTutora pessoa) {
        if (buscarPorEmail(pessoa.getEmail()) == null) {
            pessoasTutoras.add(pessoa);
            salvarEmArquivo();
        }
    }

    public void atualizar(PessoaTutora pessoa) {
        for (int i = 0; i < pessoasTutoras.size(); i++) {
            if (pessoasTutoras.get(i).getEmail().equals(pessoa.getEmail())) {
                pessoasTutoras.set(i, pessoa);
                salvarEmArquivo();
                return;
            }
        }
    }

    public void remover(String email) {
        pessoasTutoras.removeIf(p -> p.getEmail().equals(email));
        salvarEmArquivo();
    }

    public PessoaTutora buscarPorEmail(String email) {
        return pessoasTutoras.stream().filter(p -> p.getEmail().equals(email)).findFirst().orElse(null);
    }

    public List<PessoaTutora> listarTodos() {
        return new ArrayList<>(pessoasTutoras);
    }

    public void salvarEmArquivo() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ARQUIVO_JSON), pessoasTutoras);
        } catch (Exception e) {
            System.out.println("Erro ao salvar pessoas tutoras: " + e.getMessage());
        }
    }

    public void carregarDeArquivo() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(ARQUIVO_JSON);
            if (file.exists()) {
                List<PessoaTutora> lista = mapper.readValue(file, new TypeReference<List<PessoaTutora>>(){});
                if (lista != null) pessoasTutoras = lista;
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar pessoas tutoras: " + e.getMessage());
        }
    }
}
