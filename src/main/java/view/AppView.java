package view;

import controller.AnimalController;
import controller.SetorController;
import controller.PessoaTutoraController;
import model.Animal;
import model.SetorResponsavel;
import model.PessoaTutora;
import java.util.List;
import java.util.Scanner;

public class AppView {
    private AnimalController animalController;
    private SetorController setorController;
    private PessoaTutoraController pessoaTutoraController;
    private Scanner scanner;

    public AppView() {
        animalController = new AnimalController();
        setorController = new SetorController();
        pessoaTutoraController = new PessoaTutoraController();
        scanner = new Scanner(System.in);
    }

    /**
     * Inicia o menu principal da aplicação.
     */
    public void iniciar() {
        while (true) {
            System.out.println("\n=== Pata Amiga UEFS ===");
            System.out.println("1. Gerenciar Animais");
            System.out.println("2. Gerenciar Setores Responsáveis");
            System.out.println("3. Gerenciar Pessoas Tutoras");
            System.out.println("4. Relatórios");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = lerInt();
            switch (opcao) {
                case 1: menuAnimais(); break;
                case 2: menuSetores(); break;
                case 3: menuPessoasTutoras(); break;
                case 4: menuRelatorios(); break;
                case 0: System.out.println("Saindo..."); return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    // Menus e métodos auxiliares para CRUD e relatórios
    private void menuAnimais() {
        System.out.println("\n--- Animais ---");
        System.out.println("1. Cadastrar Animal");
        System.out.println("2. Listar Animais");
        System.out.println("3. Editar Animal");
        System.out.println("4. Remover Animal");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = lerInt();
        switch (opcao) {
            case 1: cadastrarAnimal(); break;
            case 2: listarAnimais(); break;
            case 3: editarAnimal(); break;
            case 4: removerAnimal(); break;
            case 0: return;
            default: System.out.println("Opção inválida!");
        }
    }

    private void cadastrarAnimal() {
        try {
            System.out.print("Nome: "); String nome = scanner.nextLine();
            System.out.print("Espécie: "); String especie = scanner.nextLine();
            System.out.print("Raça (opcional): "); String raca = scanner.nextLine();
            System.out.print("Idade: "); int idade = lerInt();
            System.out.print("Sexo (M/F): "); String sexo = scanner.nextLine();
            System.out.print("Situação: "); String situacao = scanner.nextLine();
            SetorResponsavel setor = escolherSetor();
            PessoaTutora pessoa = escolherPessoaTutora();
            Animal animal = animalController.criarAnimal(nome, especie, raca, idade, sexo, situacao, setor, pessoa);
            setorController.vincularAnimal(setor, animal);
            System.out.println("Animal cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarAnimais() {
        List<Animal> animais = animalController.listarTodos();
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado.");
        } else {
            animais.forEach(a -> System.out.println(a.getId() + " - " + a.getNome() + " - " + a.getEspecie() + " - " + a.getSituacao()));
        }
    }

    private void editarAnimal() {
        listarAnimais();
        System.out.print("Informe o ID do animal para editar: ");
        String id = scanner.nextLine();
        Animal animal = animalController.buscarPorId(id);
        if (animal == null) {
            System.out.println("Animal não encontrado.");
            return;
        }
        try {
            System.out.print("Novo nome (atual: " + animal.getNome() + "): "); String nome = scanner.nextLine();
            System.out.print("Nova espécie (atual: " + animal.getEspecie() + "): "); String especie = scanner.nextLine();
            System.out.print("Nova raça (atual: " + animal.getRaca() + "): "); String raca = scanner.nextLine();
            System.out.print("Nova idade (atual: " + animal.getIdade() + "): "); int idade = lerInt();
            System.out.print("Novo sexo (atual: " + animal.getSexo() + "): "); String sexo = scanner.nextLine();
            System.out.print("Nova situação (atual: " + animal.getSituacao() + "): "); String situacao = scanner.nextLine();
            animal.setNome(nome); animal.setEspecie(especie); animal.setRaca(raca); animal.setIdade(idade); animal.setSexo(sexo); animal.setSituacao(situacao);
            animalController.atualizarAnimal(animal);
            System.out.println("Animal atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void removerAnimal() {
        listarAnimais();
        System.out.print("Informe o ID do animal para remover: ");
        String id = scanner.nextLine();
        Animal animal = animalController.buscarPorId(id);
        if (animal == null) {
            System.out.println("Animal não encontrado.");
            return;
        }
        System.out.print("Tem certeza que deseja remover? (s/n): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("s")) {
            animalController.removerAnimal(id);
            System.out.println("Animal removido com sucesso!");
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    private void menuSetores() {
        System.out.println("\n--- Setores Responsáveis ---");
        System.out.println("1. Cadastrar Setor");
        System.out.println("2. Listar Setores");
        System.out.println("3. Editar Setor");
        System.out.println("4. Remover Setor");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = lerInt();
        switch (opcao) {
            case 1: cadastrarSetor(); break;
            case 2: listarSetores(); break;
            case 3: editarSetor(); break;
            case 4: removerSetor(); break;
            case 0: return;
            default: System.out.println("Opção inválida!");
        }
    }

    private void cadastrarSetor() {
        try {
            System.out.print("Nome do setor: "); String nome = scanner.nextLine();
            System.out.print("Endereço do setor: "); String endereco = scanner.nextLine();
            setorController.criarSetor(nome, endereco);
            System.out.println("Setor cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarSetores() {
        List<SetorResponsavel> setores = setorController.listarTodos();
        if (setores.isEmpty()) {
            System.out.println("Nenhum setor cadastrado.");
        } else {
            setores.forEach(s -> System.out.println(s.getId() + " - " + s.getNome() + " - " + s.getEndereco()));
        }
    }

    private void editarSetor() {
        listarSetores();
        System.out.print("Informe o ID do setor para editar: ");
        String id = scanner.nextLine();
        SetorResponsavel setor = setorController.buscarPorId(id);
        if (setor == null) {
            System.out.println("Setor não encontrado.");
            return;
        }
        try {
            System.out.print("Novo nome (atual: " + setor.getNome() + "): "); String nome = scanner.nextLine();
            System.out.print("Novo endereço (atual: " + setor.getEndereco() + "): "); String endereco = scanner.nextLine();
            setor.setNome(nome); setor.setEndereco(endereco);
            setorController.atualizarSetor(setor);
            System.out.println("Setor atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void removerSetor() {
        listarSetores();
        System.out.print("Informe o ID do setor para remover: ");
        String id = scanner.nextLine();
        SetorResponsavel setor = setorController.buscarPorId(id);
        if (setor == null) {
            System.out.println("Setor não encontrado.");
            return;
        }
        System.out.print("Tem certeza que deseja remover? (s/n): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("s")) {
            setorController.removerSetor(id);
            System.out.println("Setor removido com sucesso!");
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    private void menuPessoasTutoras() {
        System.out.println("\n--- Pessoas Tutoras ---");
        System.out.println("1. Cadastrar Pessoa Tutora");
        System.out.println("2. Listar Pessoas Tutoras");
        System.out.println("3. Editar Pessoa Tutora");
        System.out.println("4. Remover Pessoa Tutora");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = lerInt();
        switch (opcao) {
            case 1: cadastrarPessoaTutora(); break;
            case 2: listarPessoasTutoras(); break;
            case 3: editarPessoaTutora(); break;
            case 4: removerPessoaTutora(); break;
            case 0: return;
            default: System.out.println("Opção inválida!");
        }
    }

    private void cadastrarPessoaTutora() {
        try {
            System.out.print("E-mail: "); String email = scanner.nextLine();
            System.out.print("Nome: "); String nome = scanner.nextLine();
            System.out.print("Endereço: "); String endereco = scanner.nextLine();
            System.out.print("Telefone: "); String telefone = scanner.nextLine();
            SetorResponsavel setor = escolherSetor();
            PessoaTutora pessoa = pessoaTutoraController.criarPessoaTutora(email, nome, endereco, telefone, setor);
            setorController.vincularPessoaTutora(setor, pessoa);
            System.out.println("Pessoa tutora cadastrada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarPessoasTutoras() {
        List<PessoaTutora> pessoas = pessoaTutoraController.listarTodos();
        if (pessoas.isEmpty()) {
            System.out.println("Nenhuma pessoa tutora cadastrada.");
        } else {
            pessoas.forEach(p -> System.out.println(p.getEmail() + " - " + p.getNome() + " - " + p.getSetor().getNome()));
        }
    }

    private void editarPessoaTutora() {
        listarPessoasTutoras();
        System.out.print("Informe o e-mail da pessoa tutora para editar: ");
        String email = scanner.nextLine();
        PessoaTutora pessoa = pessoaTutoraController.buscarPorEmail(email);
        if (pessoa == null) {
            System.out.println("Pessoa tutora não encontrada.");
            return;
        }
        try {
            System.out.print("Novo nome (atual: " + pessoa.getNome() + "): "); String nome = scanner.nextLine();
            System.out.print("Novo endereço (atual: " + pessoa.getEndereco() + "): "); String endereco = scanner.nextLine();
            System.out.print("Novo telefone (atual: " + pessoa.getTelefone() + "): "); String telefone = scanner.nextLine();
            pessoa.setNome(nome); pessoa.setEndereco(endereco); pessoa.setTelefone(telefone);
            pessoaTutoraController.atualizarPessoaTutora(pessoa);
            System.out.println("Pessoa tutora atualizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void removerPessoaTutora() {
        listarPessoasTutoras();
        System.out.print("Informe o e-mail da pessoa tutora para remover: ");
        String email = scanner.nextLine();
        PessoaTutora pessoa = pessoaTutoraController.buscarPorEmail(email);
        if (pessoa == null) {
            System.out.println("Pessoa tutora não encontrada.");
            return;
        }
        System.out.print("Tem certeza que deseja remover? (s/n): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("s")) {
            pessoaTutoraController.removerPessoaTutora(email);
            System.out.println("Pessoa tutora removida com sucesso!");
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    private void menuRelatorios() {
        System.out.println("\n--- Relatórios ---");
        System.out.println("1. Relação completa de Animais");
        System.out.println("2. Animais por Setor Responsável");
        System.out.println("3. Animais por Pessoa Tutora");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = lerInt();
        switch (opcao) {
            case 1: listarAnimais(); break;
            case 2: relatorioAnimaisPorSetor(); break;
            case 3: relatorioAnimaisPorPessoaTutora(); break;
            case 0: return;
            default: System.out.println("Opção inválida!");
        }
    }

    private void relatorioAnimaisPorSetor() {
        List<SetorResponsavel> setores = setorController.listarTodos();
        for (SetorResponsavel setor : setores) {
            System.out.println("Setor: " + setor.getNome());
            List<Animal> animais = animalController.listarPorSetor(setor);
            if (animais.isEmpty()) {
                System.out.println("  Nenhum animal vinculado.");
            } else {
                animais.forEach(a -> System.out.println("  " + a.getId() + " - " + a.getNome() + " - " + a.getEspecie()));
            }
        }
    }

    private void relatorioAnimaisPorPessoaTutora() {
        List<PessoaTutora> pessoas = pessoaTutoraController.listarTodos();
        for (PessoaTutora pessoa : pessoas) {
            System.out.println("Pessoa Tutora: " + pessoa.getNome() + " (" + pessoa.getEmail() + ")");
            List<Animal> animais = animalController.listarPorPessoaTutora(pessoa);
            if (animais.isEmpty()) {
                System.out.println("  Nenhum animal vinculado.");
            } else {
                animais.forEach(a -> System.out.println("  " + a.getId() + " - " + a.getNome() + " - " + a.getEspecie()));
            }
        }
    }

    // Métodos auxiliares para seleção de setor e pessoa tutora
    private SetorResponsavel escolherSetor() {
        List<SetorResponsavel> setores = setorController.listarTodos();
        if (setores.isEmpty()) throw new IllegalArgumentException("Cadastre um setor primeiro.");
        System.out.println("Setores disponíveis:");
        for (int i = 0; i < setores.size(); i++) {
            System.out.println((i+1) + ". " + setores.get(i).getNome());
        }
        System.out.print("Escolha o número do setor: ");
        int idx = lerInt() - 1;
        if (idx < 0 || idx >= setores.size()) throw new IllegalArgumentException("Setor inválido.");
        return setores.get(idx);
    }

    private PessoaTutora escolherPessoaTutora() {
        List<PessoaTutora> pessoas = pessoaTutoraController.listarTodos();
        if (pessoas.isEmpty()) throw new IllegalArgumentException("Cadastre uma pessoa tutora primeiro.");
        System.out.println("Pessoas tutoras disponíveis:");
        for (int i = 0; i < pessoas.size(); i++) {
            System.out.println((i+1) + ". " + pessoas.get(i).getNome());
        }
        System.out.print("Escolha o número da pessoa tutora: ");
        int idx = lerInt() - 1;
        if (idx < 0 || idx >= pessoas.size()) throw new IllegalArgumentException("Pessoa tutora inválida.");
        return pessoas.get(idx);
    }

    private int lerInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }
}
