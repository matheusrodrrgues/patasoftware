package view;

import controller.AnimalController;
import controller.SetorController;
import controller.PessoaTutoraController;
import model.Animal;
import model.SetorResponsavel;
import model.PessoaTutora;
import java.util.List;
import java.util.Scanner;
import repository.SetorRepository;

public class AppView {
    private SetorRepository setorRepository;
    private AnimalController animalController;
    private SetorController setorController;
    private PessoaTutoraController pessoaTutoraController;
    private Scanner scanner;

    public AppView() {
        setorRepository = new SetorRepository();
        animalController = new AnimalController(setorRepository);
        setorController = new SetorController(setorRepository);
        pessoaTutoraController = new PessoaTutoraController(setorRepository);
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            System.out.println("\n=== PATA Software ===");
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
            System.out.println("Situação:");
            System.out.println("1 - Em observação");
            System.out.println("2 - Disponível para adoção");
            System.out.println("3 - Em tratamento");
            System.out.print("Escolha a situação (1/2/3): ");
            int opcaoSituacao = lerInt();
            String situacao;
            switch (opcaoSituacao) {
                case 1: situacao = "Em observação"; break;
                case 2: situacao = "Disponível para adoção"; break;
                case 3: situacao = "Em tratamento"; break;
                default: System.out.println("Opção inválida! Usando 'Em observação' por padrão."); situacao = "Em observação";
            }
            SetorResponsavel setor = escolherSetor();
            int setorId = setor.getId();
            PessoaTutora pessoa = setor.getPessoaTutora();
            Animal animal = animalController.criarAnimal(nome, especie, raca, idade, sexo, situacao, setorId, pessoa);
            animal.setSetor(setor); // reforça vínculo
            // setorController.vincularAnimal(setor, animal); // Remover ou adaptar se necessário
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
        int id = lerInt();
        Animal animal = null;
        SetorResponsavel setor = null;
        List<SetorResponsavel> setores = setorController.listarTodos();
        for (SetorResponsavel s : setores) {
            animal = animalController.buscarPorId(id, s.getId());
            if (animal != null) {
                setor = s;
                break;
            }
        }
        if (animal == null || setor == null) {
            System.out.println("Animal não encontrado.");
            return;
        }
        animal.setSetor(setor);
        try {
            System.out.print("Novo nome (atual: " + animal.getNome() + "): "); String nome = scanner.nextLine();
            System.out.print("Nova espécie (atual: " + animal.getEspecie() + "): "); String especie = scanner.nextLine();
            System.out.print("Nova raça (atual: " + animal.getRaca() + "): "); String raca = scanner.nextLine();
            System.out.print("Nova idade (atual: " + animal.getIdade() + "): "); int idade = lerInt();
            System.out.print("Novo sexo (atual: " + animal.getSexo() + "): "); String sexo = scanner.nextLine();
            System.out.print("Nova situação (atual: " + animal.getSituacao() + "): "); String situacao = scanner.nextLine();
            animal.setNome(nome); animal.setEspecie(especie); animal.setRaca(raca); animal.setIdade(idade); animal.setSexo(sexo); animal.setSituacao(situacao);
            animalController.atualizarAnimal(animal, setor.getId());
            setorController.atualizarSetor(setor);
            System.out.println("Animal atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void removerAnimal() {
        listarAnimais();
        System.out.print("Informe o ID do animal para remover: ");
        int id = lerInt();
        Animal animal = null;
        SetorResponsavel setor = null;
        List<SetorResponsavel> setores = setorController.listarTodos();
        for (SetorResponsavel s : setores) {
            animal = animalController.buscarPorId(id, s.getId());
            if (animal != null) {
                setor = s;
                break;
            }
        }
        if (animal == null || setor == null) {
            System.out.println("Animal não encontrado.");
            return;
        }
        animal.setSetor(setor);
        System.out.print("Tem certeza que deseja remover? (s/n): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("s")) {
            animalController.removerAnimal(id, setor.getId());
            setor.getAnimais().removeIf(a -> a.getId() == id);
            setorController.atualizarSetor(setor);
            System.out.println("Animal removido com sucesso!");
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    private SetorResponsavel buscarSetorDoAnimal(Animal animal) {
        List<SetorResponsavel> setores = setorController.listarTodos();
        for (SetorResponsavel setor : setores) {
            for (Animal a : setor.getAnimais()) {
                if (a.getId() == animal.getId()) {
                    return setor;
                }
            }
        }
        return null;
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
        int id = lerInt();
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
        int id = lerInt();
        SetorResponsavel setor = setorController.buscarPorId(id);
        if (setor == null) {
            System.out.println("Setor não encontrado.");
            return;
        }
        if (setor.getAnimais() != null && !setor.getAnimais().isEmpty()) {
            System.out.println("Existem animais vinculados a este setor. Você deve transferi-los para outro setor antes de apagar.");
            SetorResponsavel novoSetor = null;
            while (true) {
                novoSetor = escolherSetor();
                if (novoSetor.getId() == setor.getId()) {
                    System.out.println("O novo setor não pode ser o mesmo que está sendo removido. Escolha outro.");
                } else {
                    break;
                }
            }
            for (Animal animal : setor.getAnimais()) {
                animal.setSetor(novoSetor);
                novoSetor.adicionarAnimal(animal);
            }
            setor.getAnimais().clear();
            setorController.atualizarSetor(novoSetor);
        }
        PessoaTutora pessoa = setor.getPessoaTutora();
        System.out.print("Tem certeza que deseja remover o setor e a pessoa tutora vinculada? (s/n): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("s")) {
            if (pessoa != null) {
                pessoaTutoraController.removerPessoaTutora(pessoa.getEmail());
            }
            setorController.removerSetor(id, null);
            System.out.println("Setor e pessoa tutora removidos com sucesso!");
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
        List<SetorResponsavel> setores = setorController.listarTodos();
        boolean encontrou = false;
        for (SetorResponsavel setor : setores) {
            PessoaTutora pessoa = setor.getPessoaTutora();
            if (pessoa != null) {
                System.out.println(pessoa.getEmail() + " - " + pessoa.getNome() + " - " + setor.getNome());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma pessoa tutora cadastrada.");
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
            System.out.println("Deixe o campo em branco para manter o valor atual.");
            System.out.print("Novo nome (atual: " + pessoa.getNome() + "): "); String nome = scanner.nextLine();
            if (nome.isBlank()) nome = pessoa.getNome();
            System.out.print("Novo endereço (atual: " + pessoa.getEndereco() + "): "); String endereco = scanner.nextLine();
            if (endereco.isBlank()) endereco = pessoa.getEndereco();
            System.out.print("Novo telefone (atual: " + pessoa.getTelefone() + "): "); String telefone = scanner.nextLine();
            if (telefone.isBlank()) telefone = pessoa.getTelefone();
            PessoaTutora pessoaTemp = new PessoaTutora(pessoa.getEmail(), nome, endereco, telefone, pessoa.getSetor());
            pessoa.setNome(nome);
            pessoa.setEndereco(endereco);
            pessoa.setTelefone(telefone);
            SetorResponsavel setor = pessoa.getSetor();
            if (setor == null) {
                List<SetorResponsavel> setores = setorController.listarTodos();
                for (SetorResponsavel s : setores) {
                    if (s.getPessoaTutora() != null && s.getPessoaTutora().getEmail().equals(pessoa.getEmail())) {
                        setor = s;
                        break;
                    }
                }
            }
            if (setor != null) {
                pessoa.setSetor(setor);
                setor.setPessoaTutora(pessoa);
                setorController.atualizarSetor(setor);
            }
            pessoaTutoraController.atualizarPessoaTutora(pessoa);
            pessoa = pessoaTutoraController.buscarPorEmail(email);
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
        System.out.println("2. Setor e Pessoas Tutoras juntos");
        System.out.println("3. Animais por Espécie (Cachorro, Gato, Outros)");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = lerInt();
        switch (opcao) {
            case 1: relatorioAnimais(); break;
            case 2: relatorioSetorPessoaTutora(); break;
            case 3: relatorioAnimaisPorEspecie(); break;
            case 0: return;
            default: System.out.println("Opção inválida!");
        }
    }

    private void relatorioAnimais() {
        List<Animal> animais = animalController.listarTodos();
        System.out.println("Relatório de Animais:");
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado.");
        } else {
            animais.forEach(a -> System.out.println(a.getId() + " - " + a.getNome() + " - " + a.getEspecie() + " - " + a.getSituacao()));
        }
    }

    private void relatorioSetorPessoaTutora() {
        List<SetorResponsavel> setores = setorController.listarTodos();
        System.out.println("Relatório de Setores e Pessoas Tutoras:");
        for (SetorResponsavel setor : setores) {
            System.out.println("Setor: " + setor.getNome());
            PessoaTutora pessoa = setor.getPessoaTutora();
            if (pessoa != null) {
                System.out.println("  Tutora: " + pessoa.getNome() + " (" + pessoa.getEmail() + ")");
            } else {
                System.out.println("  Nenhuma pessoa tutora vinculada.");
            }
        }
    }

    private void relatorioAnimaisPorEspecie() {
        List<Animal> animais = animalController.listarTodos();
        List<Animal> cachorros = new java.util.ArrayList<>();
        List<Animal> gatos = new java.util.ArrayList<>();
        List<Animal> outros = new java.util.ArrayList<>();
        for (Animal animal : animais) {
            String especie = animal.getEspecie().trim().toLowerCase();
            if (especie.equals("cachorro") || especie.equals("cão") || especie.equals("cao")) {
                cachorros.add(animal);
            } else if (especie.equals("gato")) {
                gatos.add(animal);
            } else {
                outros.add(animal);
            }
        }
        System.out.println("Animais por Espécie:");
        System.out.println("Cachorro:");
        if (cachorros.isEmpty()) System.out.println("  Nenhum cachorro cadastrado.");
        else cachorros.forEach(a -> System.out.println("  " + a.getId() + " - " + a.getNome() + " - " + a.getSituacao()));
        System.out.println("Gato:");
        if (gatos.isEmpty()) System.out.println("  Nenhum gato cadastrado.");
        else gatos.forEach(a -> System.out.println("  " + a.getId() + " - " + a.getNome() + " - " + a.getSituacao()));
        System.out.println("Outros:");
        if (outros.isEmpty()) System.out.println("  Nenhum outro animal cadastrado.");
        else outros.forEach(a -> System.out.println("  " + a.getId() + " - " + a.getNome() + " - " + a.getEspecie() + " - " + a.getSituacao()));
    }

    // Métodos auxiliares para seleção de setor e pessoa tutora
    private SetorResponsavel escolherSetor() {
        List<SetorResponsavel> setores = setorController.listarTodos();
        if (setores.isEmpty()) throw new IllegalArgumentException("Cadastre um setor primeiro.");
        System.out.println("Setores disponíveis:");
        for (int i = 0; i < setores.size(); i++) {
            System.out.println(setores.get(i).getId() + " - " + setores.get(i).getNome());
        }
        System.out.print("Escolha o ID do setor: ");
        int id = lerInt();
        SetorResponsavel setor = setorController.buscarPorId(id);
        if (setor == null) throw new IllegalArgumentException("Setor inválido.");
        return setor;
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
