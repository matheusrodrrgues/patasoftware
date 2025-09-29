package repository;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe utilitária para gerenciar IDs únicos no sistema.
 * Usada para evitar duplicidade de identificadores.
 */
public class IDManager {
    private static Set<String> ids = new HashSet<>();

    /**
     * Adiciona um novo ID ao sistema.
     * @param id identificador a ser adicionado
     * @return true se o ID foi adicionado, false se já existia
     */
    public static boolean adicionarId(String id) {
        return ids.add(id);
    }

    /**
     * Remove um ID do sistema.
     * @param id identificador a ser removido
     */
    public static void removerId(String id) {
        ids.remove(id);
    }

    /**
     * Verifica se um ID já existe no sistema.
     * @param id identificador a ser verificado
     * @return true se existe, false se não existe
     */
    public static boolean existeId(String id) {
        return ids.contains(id);
    }

    /**
     * Lista todos os IDs cadastrados.
     * @return conjunto de IDs
     */
    public static Set<String> listarIds() {
        return new HashSet<>(ids);
    }
}
