# TalentHUB

O sistema tem como objetivo permitir o gerenciamento de candidatos, vagas, candidaturas e entrevistas, com diferentes níveis de acesso de acordo com o perfil do usuário.

---

# Tecnologias e Ferramentas

## Linguagem e plataforma

* **Java** — linguagem de programação principal do sistema.
* **JDK** — kit necessário para desenvolvimento e execução das aplicações Java.
* **JavaFX** — framework utilizado para desenvolvimento da interface gráfica do sistema.

## Banco de dados

* **MySQL Community Server** — sistema de gerenciamento do banco de dados.
* **MySQL Connector/J** — driver JDBC utilizado para permitir a comunicação entre a aplicação Java e o banco de dados MySQL.

## Gerenciamento do projeto

* **Maven** — ferramenta utilizada para gerenciamento do projeto Java e de suas dependências.
* **Git** — sistema de controle de versões utilizado para acompanhar as alterações no código.
* **GitHub** — plataforma utilizada para hospedagem e colaboração no repositório do projeto.

## Ambiente de desenvolvimento

* **Visual Studio Code (VS Code)** — editor utilizado para desenvolvimento do sistema.

---

# Extensões do Visual Studio Code

1. **Extension Pack for Java** — Microsoft
   Conjunto de extensões que fornece suporte ao desenvolvimento Java no VS Code, incluindo recursos de execução, depuração, testes e gerenciamento de projetos Maven.

2. **SQLTools** — Matheus Teixeira: 
   Utilizada para trabalhar com bancos de dados SQL diretamente pelo VS Code.

4. **SQLTools MySQL/MariaDB Driver** — Matheus Teixeira: 
   Driver utilizado pelo SQLTools para estabelecer conexão com bancos de dados MySQL/MariaDB.

5. **GitLens — Git supercharged** — GitKraken: 
   Fornece recursos adicionais para utilização e visualização do histórico do Git.

---

# Resumo das Tecnologias

| Tecnologia/Ferramenta             | O que é                                                                   |
| --------------------------------- | ------------------------------------------------------------------------- |
| **Java**                          | Linguagem de programação principal do sistema.                            |
| **JDK**                           | Kit necessário para desenvolver e executar aplicações Java.               |
| **JavaFX**                        | Framework utilizado para desenvolver a interface gráfica.                 |
| **MySQL Community Server**        | Sistema de gerenciamento do banco de dados.                               |
| **MySQL Connector/J**             | Driver JDBC utilizado para a comunicação entre Java e MySQL.              |
| **Maven**                         | Ferramenta para gerenciamento do projeto e suas dependências.             |
| **Git**                           | Sistema de controle de versões.                                           |
| **GitHub**                        | Plataforma utilizada para hospedagem e colaboração do projeto.            |
| **Visual Studio Code**            | Editor utilizado para desenvolvimento do sistema.                         |
| **SQLTools**                      | Extensão utilizada para acessar e gerenciar bancos de dados pelo VS Code. |
| **SQLTools MySQL/MariaDB Driver** | Driver utilizado pelo SQLTools para conexão com o MySQL.                  |
| **GitLens**                       | Extensão que adiciona recursos ao gerenciamento de versões com Git.       |

---

# Links Oficiais

## Java / JDK

[Oracle — Java Downloads](https://www.oracle.com/br/java/technologies/downloads/)

## MySQL Community Server

[MySQL — Community Server](https://dev.mysql.com/downloads/mysql/)

## Git

[Git — Download](https://git-scm.com/downloads)

## GitHub

[GitHub](https://github.com/)

## Visual Studio Code

[Visual Studio Code](https://code.visualstudio.com/)

---

# Estrutura do Ambiente

A comunicação entre os principais componentes do sistema será realizada da seguinte maneira:

┌─────────────────────────┐
│       JavaFX            │
│   Interface gráfica     │
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│         Java            │
│    Lógica do sistema    │
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│    MySQL Connector/J    │
│       Driver JDBC       │
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│    MySQL Server         │
│      Banco de dados     │
└─────────────────────────┘


O **Maven** será utilizado para gerenciar o projeto e suas dependências, incluindo bibliotecas como JavaFX e MySQL Connector/J.

O **Git e GitHub** serão utilizados para controle de versões e colaboração entre os integrantes da equipe.

---

# Ambiente de Desenvolvimento

Cada integrante da equipe deverá possuir, no mínimo:

* JDK instalado;
* Git instalado;
* MySQL Community Server instalado;
* Visual Studio Code instalado;
* Extensões necessárias para Java e banco de dados configuradas;
* Acesso ao repositório do projeto no GitHub.

---


