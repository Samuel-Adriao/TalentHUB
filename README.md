# TalentHUB-

# 📋 Sistema de Gerenciamento de Candidatos (JavaFX + MySQL)

Aplicação desktop desenvolvida em 
**Java** utilizando **JavaFX** para interface gráfica, 
**Apache Maven** para gerenciamento de dependências, padrão arquitetural 
**DAO (Data Access Object)** e persistência de dados com **MySQL**.

---

## 🚀 Tecnologias Utilizadas

* **Java (JDK 17+)**
* **JavaFX 21** (Interface Gráfica e FXML)
* **Apache Maven** (Build e Gerenciamento de Dependências)
* **MySQL & JDBC** (Banco de dados e conexão)
* **VS Code** (Ambiente de desenvolvimento)

---

## 📂 Estrutura do Projeto

O projeto segue a organização padrão Maven e separação de responsabilidades:

```text
demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       ├── config/      # Conexão com o banco de dados
│   │   │       ├── controller/  # Controladores das telas JavaFX
│   │   │       ├── dao/         # Classes de persistência (Data Access Object)
│   │   │       ├── main/        # Classe principal de inicialização (Main.java)
│   │   │       └── model/       # Classes de modelo (Entidades / Beans)
│   │   └── resources/
│   │       └── com/example/view/
│   │           └── painelCandidato.fxml  # Layouts visuais da aplicação
└── pom.xml

--⚙️ Pré-requisitos------
Antes de executar o projeto, certifique-se de ter instalado em sua máquina:

Java JDK (versão 17 ou superior)

Apache Maven configurado no Path

Servidor MySQL rodando perfeitamente

--🏃 Como Executar o Projeto------
Clone o repositório ou abra a pasta do projeto no seu terminal.

Navegue até a pasta do projeto (demo):
Ba
cd demo

Execute o comando Maven para compilar e iniciar a aplicação gráfica:

Bash
mvn javafx:run