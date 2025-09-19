package repository;

import java.util.HashSet;
import java.util.Set;

public class IDManager {
    private static Set<String> ids = new HashSet<>();

    public static boolean adicionarId(String id) {
        return ids.add(id);
    }

    public static void removerId(String id) {
        ids.remove(id);
    }

    public static boolean existeId(String id) {
        return ids.contains(id);
    }

    public static Set<String> listarIds() {
        return new HashSet<>(ids);
    }
}
