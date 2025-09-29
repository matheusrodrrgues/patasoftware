package controller;

import model.SetorResponsavel;
import repository.SetorRepository;

class SetorControllerTest {
    @org.junit.jupiter.api.Test
    void testSetorController() {
        SetorRepository repo = new SetorRepository();
        SetorController controller = new SetorController(repo);
        SetorResponsavel setor = controller.criarSetor("Setor", "Endereço");
        org.junit.jupiter.api.Assertions.assertNotNull(setor);
        org.junit.jupiter.api.Assertions.assertTrue(controller.listarTodos().contains(setor));
    }
}
