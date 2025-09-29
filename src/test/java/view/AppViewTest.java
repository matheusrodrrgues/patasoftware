package view;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppViewTest {
    @Test
    void testAppView() {
        AppView view = new AppView();
        assertDoesNotThrow(() -> view.exibirMensagem("Teste de mensagem"));
    }
}

