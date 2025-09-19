package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SetorResponsavel {
    private String id;
    private String nome;
    private String endereco;
    private List<Animal> animais;
    private List<PessoaTutora> pessoasTutoras;

    public SetorResponsavel(String id, String nome, String endereco) {
        setId(id);
        setNome(nome);
        setEndereco(endereco);
        this.animais = new ArrayList<>();
        this.pessoasTutoras = new ArrayList<>();
    }

    public String getId() { return id; }
    public void setId(String id) {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("ID do setor é obrigatório.");
        this.id = id;
    }
    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("Nome do setor é obrigatório.");
        this.nome = nome;
    }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) {
        if (endereco == null || endereco.isEmpty()) throw new IllegalArgumentException("Endereço do setor é obrigatório.");
        this.endereco = endereco;
    }
    public List<Animal> getAnimais() { return animais; }
    public void setAnimais(List<Animal> animais) {
        this.animais = animais == null ? new ArrayList<>() : animais;
    }
    public List<PessoaTutora> getPessoasTutoras() { return pessoasTutoras; }
    public void setPessoasTutoras(List<PessoaTutora> pessoasTutoras) {
        this.pessoasTutoras = pessoasTutoras == null ? new ArrayList<>() : pessoasTutoras;
    }

    public void adicionarAnimal(Animal animal) {
        if (animal != null && !animais.contains(animal)) {
            animais.add(animal);
        }
    }

    public void adicionarPessoaTutora(PessoaTutora pessoa) {
        if (pessoa != null && !pessoasTutoras.contains(pessoa)) {
            pessoasTutoras.add(pessoa);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetorResponsavel setor = (SetorResponsavel) o;
        return Objects.equals(id, setor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
