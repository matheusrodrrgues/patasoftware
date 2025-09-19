package controller;

import model.PessoaTutora;
import model.SetorResponsavel;
import service.PessoaTutoraService;
import java.util.List;

public class PessoaTutoraController {
    private PessoaTutoraService pessoaTutoraService;

    public PessoaTutoraController() {
        pessoaTutoraService = new PessoaTutoraService();
    }

    public PessoaTutora criarPessoaTutora(String email, String nome, String endereco, String telefone, SetorResponsavel setor) {
        return pessoaTutoraService.criarPessoaTutora(email, nome, endereco, telefone, setor);
    }

    public void atualizarPessoaTutora(PessoaTutora pessoa) {
        pessoaTutoraService.atualizarPessoaTutora(pessoa);
    }

    public void removerPessoaTutora(String email) {
        pessoaTutoraService.removerPessoaTutora(email);
    }

    public PessoaTutora buscarPorEmail(String email) {
        return pessoaTutoraService.buscarPorEmail(email);
    }

    public List<PessoaTutora> listarTodos() {
        return pessoaTutoraService.listarTodos();
    }
}
