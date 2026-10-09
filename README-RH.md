# TalentHUB — Módulo Recrutador (RH)
## Sobre o Módulo

O módulo Recrutador (RH) do TalentHUB é responsável pelo gerenciamento das vagas de emprego e pelo acompanhamento dos processos seletivos. Por meio de uma interface gráfica desenvolvida em JavaFX, o recrutador poderá administrar vagas, visualizar candidatos e candidaturas, tomar decisões sobre os processos seletivos e gerenciar entrevistas.

O acesso ao módulo será disponibilizado após a autenticação do usuário, desde que seu perfil esteja identificado como RH no banco de dados.

# Funcionalidades
## 1. Dashboard RH

O dashboard será a tela principal do recrutador, apresentando uma visão geral das atividades relacionadas ao recrutamento e à seleção.

Funcionalidades previstas:

- Visualizar a quantidade total de candidatos.
- Visualizar a quantidade de vagas cadastradas.
- Visualizar a quantidade de candidaturas recebidas.
- Visualizar a quantidade de entrevistas agendadas.
- Acompanhar os status das candidaturas.

Os indicadores serão obtidos a partir das informações armazenadas no banco de dados.

## 2. Cadastro e Gerenciamento de Vagas

O recrutador será responsável pela administração das vagas disponibilizadas no sistema.

Funcionalidades:

- Visualizar vagas: consultar as vagas cadastradas e suas informações.
- Adicionar vagas: cadastrar novas oportunidades de emprego.
- Excluir vagas: remover vagas cadastradas, respeitando as regras de integridade do banco de dados.

As informações de uma vaga poderão incluir:

- Título.
- Descrição.
- Requisitos.
- Carga horária.
- Salário.
- Status da vaga.
- Data de publicação.

As vagas serão armazenadas na tabela vaga do banco de dados.

## 3. Visualização de Candidatos

O recrutador poderá consultar os dados dos candidatos que participam dos processos seletivos.

Funcionalidades:

- Visualizar a lista de candidatos.
- Consultar informações de perfil e contato.
- Visualizar perfil de plataformas anexadas (caso informado).
- Acessar o currículo enviado pelo candidato.

Os dados serão obtidos por meio das tabelas usuario e perfil, relacionadas pelo identificador do usuário.

O acesso aos dados pessoais e aos currículos deverá respeitar as permissões do sistema e a finalidade do processo seletivo.

## 4. Gerenciamento de Candidaturas

O recrutador poderá visualizar e acompanhar as candidaturas recebidas para as vagas cadastradas.

Funcionalidades:

- Listar candidaturas recebidas.
- Consultar a vaga relacionada a cada candidatura.
- Consultar a pretensão salarial e a carga horária desejada.
- Verificar o status atual da candidatura.

As candidaturas serão armazenadas na tabela candidatura, que relaciona o candidato à vaga correspondente.

| STATUS | PREVISTO |
| -----------------------------| -----------------------------|
| PENDENTE | candidatura aguardando análise |
| APROVADA | candidatura aprovada pelo recrutador|
| REJEITADA | candidatura recusada pelo recrutador |

## 5. Aprovação e Recusa de Candidaturas

Após analisar uma candidatura, o recrutador poderá aprová-la ou recusá-la.

Aprovar candidatura:

Atualizar o status para APROVADA.
Registrar a data e hora da decisão.
Registrar o identificador do recrutador responsável.

Recusar candidatura:

Atualizar o status para REJEITADA.
Registrar a data e hora da decisão.
Registrar o identificador do recrutador responsável.

Essas operações serão realizadas na tabela candidatura, utilizando os campos status, data_ultima_acao e id_rh_responsavel.

6. Agendamento de Entrevistas

O recrutador poderá agendar entrevistas para candidaturas em processo seletivo.

Funcionalidades:

Selecionar a candidatura relacionada.
Definir a data da entrevista.
Definir o horário da entrevista.
Registrar o agendamento no banco de dados.
Consultar as entrevistas cadastradas.

Os registros serão armazenados na tabela entrevista, relacionados à candidatura correspondente por meio do campo id_candidatura.

Cada entrevista possuirá um status:

AGENDADA — entrevista programada.
REALIZADA — entrevista concluída.
CANCELADA — entrevista cancelada.
7. Gerenciamento de Entrevistas

O recrutador poderá acompanhar os agendamentos existentes.

Funcionalidades:

Visualizar a lista de entrevistas.
Consultar o candidato e a vaga relacionados.
Consultar a data e o horário agendados.
Verificar o status da entrevista.
Cancelar entrevistas previamente agendadas.

Ao cancelar uma entrevista, seu status será atualizado para CANCELADA, preservando o registro para consulta posterior.

Organização das Telas

A interface gráfica do módulo será desenvolvida com JavaFX e poderá ser organizada da seguinte maneira:

Módulo Recrutador (RH)
│
├── Dashboard RH
│   ├── Total de candidatos
│   ├── Total de vagas
│   ├── Total de candidaturas
│   └── Total de entrevistas
│
├── Vagas
│   ├── Listar vagas
│   ├── Cadastrar vaga
│   └── Excluir vaga
│
├── Candidaturas
│   ├── Listar candidaturas
│   ├── Visualizar detalhes
│   ├── Aprovar candidatura
│   └── Recusar candidatura
│
└── Entrevistas
    ├── Listar entrevistas
    ├── Visualizar detalhes
    └── Cancelar entrevista
Tecnologias Utilizadas
Tecnologia	Aplicação no módulo
Java	Implementação da lógica de negócio.
JavaFX	Desenvolvimento das telas e componentes gráficos.
FXML	Organização declarativa das interfaces gráficas, caso adotado no projeto.
MySQL	Armazenamento das vagas, candidaturas e entrevistas.
MySQL Connector/J	Comunicação entre a aplicação Java e o banco de dados.
JDBC	Execução das consultas e operações SQL.
Maven	Gerenciamento das dependências do projeto.
Git e GitHub	Versionamento e colaboração no desenvolvimento.
Tabelas do Banco de Dados Utilizadas

O módulo Recrutador utilizará as seguintes tabelas do banco de dados talenthub:

Tabela	Utilização
usuario	Identificação dos usuários, consulta de e-mails e identificação do perfil de acesso.
perfil	Consulta dos dados pessoais, formação, experiência e currículo dos candidatos.
vaga	Cadastro, consulta e gerenciamento das vagas.
candidatura	Consulta das candidaturas, atualização de status e registro do responsável pela decisão.
entrevista	Cadastro, consulta e cancelamento de entrevistas.

Não é necessário criar uma tabela exclusiva para o recrutador, pois seu perfil será identificado pelo campo tipo_usuario da tabela usuario, cujo valor correspondente é RH.

Regras de Acesso e Funcionamento
Somente usuários autenticados com perfil RH poderão executar as operações exclusivas do recrutador.
As permissões serão verificadas pela lógica da aplicação, e não apenas pela interface gráfica.
As decisões sobre candidaturas deverão registrar a data e hora da última ação e o recrutador responsável.
As entrevistas deverão estar vinculadas a candidaturas existentes.
O cancelamento de uma entrevista deverá atualizar seu status, preservando o registro.
A exclusão de vagas deverá considerar os relacionamentos existentes com candidaturas e entrevistas.
Os dados apresentados no dashboard deverão ser consultados no banco de dados para refletir as informações disponíveis no sistema.
Objetivo do Módulo

O módulo Recrutador tem como objetivo centralizar as atividades de RH no TalentHUB, permitindo administrar oportunidades de emprego, analisar candidaturas, tomar decisões sobre candidatos e organizar entrevistas em uma única interface.

Dessa forma, o módulo contribui para tornar o processo de recrutamento e seleção mais organizado, rastreável e eficiente.
