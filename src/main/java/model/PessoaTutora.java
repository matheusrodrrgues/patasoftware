package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class PessoaTutora {
    private String email;
    private String nome;
    private String endereco;
    private String telefone;
    private SetorResponsavel setor;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(gmail|outlook|hotmail)\\.com$");
    private static final Pattern TELEFONE_PATTERN = Pattern.compile("^\\d{10,11}$");

    public PessoaTutora(String email, String nome, String endereco, String telefone, SetorResponsavel setor) {
        setEmail(email);
        setNome(nome);
        setEndereco(endereco);
        setTelefone(telefone);
        setSetor(setor);
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email == null || email.isEmpty()) throw new IllegalArgumentException("E-mail é obrigatório.");
        if (!EMAIL_PATTERN.matcher(email).matches()) throw new IllegalArgumentException("E-mail deve ser @gmail.com, @outlook.com ou @hotmail.com.");
        this.email = email;
    }
    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("Nome é obrigatório.");
        this.nome = nome;
    }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) {
        if (endereco == null || endereco.isEmpty()) throw new IllegalArgumentException("Endereço é obrigatório.");
        this.endereco = endereco;
    }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) {
        if (telefone == null || telefone.isEmpty()) throw new IllegalArgumentException("Telefone é obrigatório.");
        if (!TELEFONE_PATTERN.matcher(telefone).matches()) throw new IllegalArgumentException("Telefone deve conter 10 ou 11 dígitos.");
        this.telefone = telefone;
    }
    public SetorResponsavel getSetor() { return setor; }
    public void setSetor(SetorResponsavel setor) {
        if (setor == null) throw new IllegalArgumentException("Setor responsável é obrigatório.");
        this.setor = setor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PessoaTutora that = (PessoaTutora) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
