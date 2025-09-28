package model;

import java.util.Objects;

public class Animal {
    private static int contador = 1;
    private int id;
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private String sexo;
    private String situacao;
    private SetorResponsavel setor;
    private PessoaTutora pessoaTutora;

    public Animal(String nome, String especie, String raca, int idade, String sexo, String situacao, SetorResponsavel setor, PessoaTutora pessoaTutora) {
        this.id = contador++;
        setNome(nome);
        setEspecie(especie);
        setRaca(raca);
        setIdade(idade);
        setSexo(sexo);
        setSituacao(situacao);
        setSetor(setor);
        setPessoaTutora(pessoaTutora);
    }

    public Animal() {}

    public int getId() { return id; }
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID do animal deve ser positivo.");
        this.id = id;
    }
    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("Nome do animal é obrigatório.");
        this.nome = nome;
    }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) {
        if (especie == null || especie.isEmpty()) throw new IllegalArgumentException("Espécie é obrigatória.");
        this.especie = especie;
    }
    public String getRaca() { return raca; }
    public void setRaca(String raca) {
        this.raca = raca == null ? "" : raca;
    }
    public int getIdade() { return idade; }
    public void setIdade(int idade) {
        if (idade < 0) throw new IllegalArgumentException("Idade não pode ser negativa.");
        this.idade = idade;
    }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) {
        if (sexo == null || !(sexo.equalsIgnoreCase("M") || sexo.equalsIgnoreCase("F"))) throw new IllegalArgumentException("Sexo deve ser M ou F.");
        this.sexo = sexo.toUpperCase();
    }
    public String getSituacao() { return situacao; }
    public void setSituacao(String situacao) {
        if (situacao == null || situacao.isEmpty()) throw new IllegalArgumentException("Situação é obrigatória.");
        this.situacao = situacao;
    }
    public SetorResponsavel getSetor() { return setor; }
    public void setSetor(SetorResponsavel setor) { this.setor = setor; }
    public PessoaTutora getPessoaTutora() { return pessoaTutora; }
    public void setPessoaTutora(PessoaTutora pessoaTutora) {
        this.pessoaTutora = pessoaTutora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
