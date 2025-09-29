/**
 * Repositório responsável por gerenciar os setores do sistema.
 * Permite salvar, atualizar, remover e buscar setores.
 */
package repository;

import model.SetorResponsavel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SetorRepository {
    private List<SetorResponsavel> setores;
    private static final String ARQUIVO_JSON = "src/main/java/dados/setores.json";

    /**
     * Construtor do repositório de setores.
     */
    public SetorRepository() {
        setores = new ArrayList<>();
        carregarDeArquivo();
    }

    /**
     * Salva um novo setor no sistema.
     * @param setor setor a ser salvo
     */
    public void salvar(SetorResponsavel setor) {
        setores.add(setor);
        salvarEmArquivo();
    }

    /**
     * Atualiza um setor existente.
     * @param setor setor a ser atualizado
     */
    public void atualizar(SetorResponsavel setor) {
        for (int i = 0; i < setores.size(); i++) {
            if (setores.get(i).getId() == setor.getId()) {
                setores.set(i, setor);
                salvarEmArquivo();
                return;
            }
        }
    }

    /**
     * Remove um setor pelo id.
     * @param id id do setor
     */
    public void remover(int id) {
        setores.removeIf(s -> s.getId() == id);
        salvarEmArquivo();
    }

    /**
     * Busca um setor pelo id.
     * @param id id do setor
     * @return setor encontrado ou null
     */
    public SetorResponsavel buscarPorId(int id) {
        return setores.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    /**
     * Lista todos os setores cadastrados.
     * @return lista de setores
     */
    public List<SetorResponsavel> listarTodos() {
        return new ArrayList<>(setores);
    }

    public void salvarEmArquivo() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ARQUIVO_JSON), setores);
        } catch (Exception e) {
            System.out.println("Erro ao salvar setores: " + e.getMessage());
        }
    }

    public void carregarDeArquivo() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(ARQUIVO_JSON);
            if (file.exists()) {
                List<SetorResponsavel> lista = mapper.readValue(file, new TypeReference<List<SetorResponsavel>>(){});
                if (lista != null) setores = lista;
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar setores: " + e.getMessage());
        }
    }

    public void setSetores(List<SetorResponsavel> setores) {
        this.setores = setores;
    }
}
