# PATASoftware

Sistema de gestão de animais desenvolvido com foco no controle, organização e acompanhamento de dados relacionados a animais, tutores e setores responsáveis.

---

## Sobre o projeto

O PATASoftware é uma aplicação desenvolvida com o objetivo de facilitar a administração e o controle de informações sobre animais em um determinado ambiente, como instituições, organizações ou projetos acadêmicos.

O sistema permite o gerenciamento completo de entidades relacionadas, promovendo organização, consistência de dados e facilidade de acesso às informações.

---

## Objetivos

- Gerenciar informações de animais de forma estruturada  
- Controlar dados de tutores e responsáveis  
- Organizar setores responsáveis pelos animais  
- Garantir integridade e consistência dos dados  
- Facilitar consultas e relatórios  

---

## Funcionalidades

- Cadastro de animais  
- Cadastro de tutores  
- Cadastro de setores responsáveis  
- Atualização de informações  
- Remoção de registros  
- Listagem de dados  
- Geração de relatórios  

---

## Estrutura do sistema

O projeto foi desenvolvido seguindo o padrão arquitetural MVC (Model-View-Controller), proporcionando melhor organização e separação de responsabilidades.

- **Model**: responsável pela estrutura e manipulação dos dados  
- **View**: responsável pela interface com o usuário  
- **Controller**: responsável pela lógica de controle e fluxo da aplicação  

---

## Tecnologias utilizadas

- Java  
- Programação orientada a objetos (POO)  
- Estrutura baseada em MVC  
- Serialização de dados (JSON)  

---

## Persistência de dados

O sistema utiliza arquivos JSON para armazenamento de dados, permitindo:

- Persistência entre execuções  
- Facilidade de leitura e escrita  
- Simplicidade na implementação  

---

## Execução

### Pré-requisitos

- Java JDK instalado (versão 8 ou superior)

### Passos

```bash
# Clonar o repositório
git clone https://github.com/matheusrodrrgues/patasoftware.git

# Acessar o diretório
cd patasofware

# Compilar o projeto
javac Main.java

# Executar
java Main
