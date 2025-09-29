/**
 * Classe que representa um setor responsável no sistema.
 * Possui informações como id, nome, endereço, lista de animais e pessoa tutora vinculada.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SetorResponsavel {
    /** Identificador único do setor. */
    private static int contador = 1;
    private int id;
    /** Nome do setor. */
    private String nome;
    /** Endereço do setor. */
    private String endereco;
    /** Lista de animais do setor. */
    private List<Animal> animais;
    /** Pessoa tutora vinculada ao setor. */
    private PessoaTutora pessoaTutora;

    /**
     * Construtor completo do setor.
     * @param nome nome do setor
     * @param endereco endereço do setor
     */
    public SetorResponsavel(String nome, String endereco) {
        this.id = contador++;
        setNome(nome);
        setEndereco(endereco);
        this.animais = new ArrayList<>();
    }

    /**
     * Construtor padrão.
     */
    public SetorResponsavel() {}

    public int getId() { return id; }
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID do setor deve ser positivo.");
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
    public PessoaTutora getPessoaTutora() { return pessoaTutora; }
    public void setPessoaTutora(PessoaTutora pessoaTutora) { this.pessoaTutora = pessoaTutora; }

    public void adicionarAnimal(Animal animal) {
        if (animal != null && !animais.contains(animal)) {
            animais.add(animal);
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
