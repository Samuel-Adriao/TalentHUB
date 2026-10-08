# TalentHUB — Requisitos e Critérios de Teste

## 1. Requisitos Funcionais

### RF01 — Cadastro de candidato

O sistema deve permitir que novos candidatos realizem seu cadastro informando, no mínimo:

* E-mail;
* Senha;
* Confirmação da senha.

Após o cadastro, o usuário deve ser criado automaticamente com o tipo de usuário `CANDIDATO`.

O sistema deve impedir o cadastro de e-mails já existentes.

### RF02 — Login

O sistema deve possuir uma tela de login única para todos os tipos de usuário.

O usuário deve informar:

* E-mail;
* Senha.

O sistema deve identificar automaticamente o tipo de usuário armazenado no banco de dados e direcioná-lo para o respectivo ambiente:

* CANDIDATO;
* RH;
* CEO;
* TI.

Não deve existir seleção manual do tipo de usuário na tela de login.

### RF03 — Controle de acesso por perfil

O sistema deve restringir o acesso às funcionalidades de acordo com o tipo de usuário autenticado.

O candidato não deve acessar funcionalidades administrativas.

O RH deve possuir acesso às funcionalidades relacionadas às vagas, candidaturas e entrevistas.

O CEO deve possuir acesso de consulta às informações do sistema, sem permissão para alterar os dados.

O TI deve possuir acesso às funcionalidades administrativas relacionadas aos usuários.

### RF04 — Cadastro e manutenção do perfil

O candidato deve poder preencher e consultar seu perfil profissional.

O perfil deve conter:

* Nome;
* E-mail;
* Tipo de pessoa;
* CPF/CNPJ;
* Data de nascimento;
* Telefone;
* CEP;
* Logradouro;
* Número;
* Complemento;
* Bairro;
* Cidade;
* Estado;
* Formação;
* Experiência;
* Currículo em PDF.

O tipo de pessoa deve permitir:

* Pessoa Física;
* Pessoa Jurídica.

A data de nascimento deve ser obrigatória, inclusive para Pessoa Jurídica, para permitir a aplicação das regras de idade definidas pelo sistema.

### RF05 — Armazenamento do currículo

O sistema deve permitir que o candidato selecione um arquivo PDF para seu currículo.

O arquivo deve ser armazenado no banco de dados juntamente com:

* Nome do arquivo;
* Tipo MIME;
* Conteúdo do arquivo.

O sistema não deve depender de serviços externos como Google Drive para armazenar o currículo.

### RF06 — Cadastro e gerenciamento de vagas

O RH deve poder visualizar, cadastrar e excluir vagas.

Uma vaga deve possuir:

* Título;
* Descrição;
* Requisitos;
* Carga horária;
* Salário;
* Status;
* Data de publicação.

As cargas horárias permitidas devem ser:

* Integral;
* Meio período;
* Estágio;
* Flexível.

Os status da vaga devem ser:

* Aberta;
* Fechada.

### RF07 — Candidaturas

O candidato deve poder visualizar vagas disponíveis e se candidatar a elas.

O sistema não deve permitir que o mesmo candidato se candidate mais de uma vez à mesma vaga.

Uma vaga pode receber várias candidaturas de diferentes candidatos.

Um candidato pode realizar várias candidaturas para vagas diferentes.

### RF08 — Dados utilizados na candidatura

Ao realizar uma candidatura, os dados já cadastrados no perfil do candidato devem ser utilizados automaticamente.

O candidato não deve precisar preencher novamente:

* Currículo;
* Formação;
* Experiência.

A candidatura deve registrar uma cópia desses dados para preservar as informações utilizadas no momento da candidatura.

O candidato deve informar na candidatura:

* Pretensão salarial;
* Carga horária desejada.

### RF09 — Análise das candidaturas pelo RH

O RH deve poder visualizar as candidaturas recebidas para as vagas.

A candidatura deve possuir os seguintes estados:

* PENDENTE;
* APROVADA;
* REJEITADA.

O RH deve poder:

* Aprovar uma candidatura;
* Rejeitar uma candidatura;
* Consultar os dados do candidato.

O sistema deve registrar a data e hora da última ação realizada pelo RH e o usuário responsável pela ação.

### RF10 — Entrevistas

Uma candidatura aprovada poderá receber uma entrevista.

Uma candidatura poderá possuir várias entrevistas, permitindo que uma entrevista seja cancelada e posteriormente outra seja agendada.

Cada entrevista deve possuir:

* Data;
* Horário;
* Status;
* Data de criação.

Os status da entrevista devem ser:

* AGENDADA;
* REALIZADA;
* CANCELADA.

### RF11 — Consulta pelo CEO

O CEO deve possuir acesso de consulta às informações relevantes do sistema.

O CEO poderá visualizar informações relacionadas a:

* Usuários;
* Candidatos;
* Perfis;
* Vagas;
* Candidaturas;
* Entrevistas;
* Decisões realizadas pelo RH.

O CEO não deve possuir funcionalidades para alterar ou excluir essas informações.

### RF12 — Administração de usuários pelo TI

O usuário do tipo TI deve poder realizar a manutenção dos usuários administrativos do sistema.

Entre as funcionalidades previstas estão:

* Consultar usuários;
* Criar usuários administrativos;
* Editar usuários;
* Desativar usuários.

O TI não deve utilizar essas funcionalidades para alterar indevidamente os dados profissionais de candidatos.

### RF13 — Validação dos dados

O sistema deve validar os dados informados pelo usuário antes de realizar operações no banco de dados.

Entre as validações estão:

* Campos obrigatórios;
* Formato de e-mail;
* Confirmação de senha;
* CPF/CNPJ;
* Data de nascimento;
* CEP;
* Telefone;
* Arquivo de currículo;
* Valores numéricos;
* Campos de seleção.

### RF14 — Mensagens de erro

O sistema deve apresentar mensagens claras e padronizadas quando uma operação não puder ser realizada.

Exemplos:

* "E-mail ou senha inválidos."
* "Este e-mail já está cadastrado."
* "Preencha todos os campos obrigatórios."
* "As senhas não coincidem."
* "CPF/CNPJ inválido."
* "Selecione um arquivo PDF válido."
* "Você já possui uma candidatura para esta vaga."
* "Você não possui permissão para acessar esta funcionalidade."
* "Não foi possível realizar a operação. Tente novamente."

As mensagens não devem expor informações técnicas do banco de dados, como SQL, nomes de tabelas, nomes de colunas ou stack traces.

---

# 2. Requisitos Não Funcionais

### RNF01 — Desempenho

Operações comuns da interface, como login, consultas e abertura de telas, devem apresentar resposta em tempo adequado.

Como critério de teste, operações simples devem buscar resposta em até aproximadamente **2 segundos** em ambiente de testes local, desconsiderando operações envolvendo arquivos grandes ou indisponibilidade do banco.

### RNF02 — Tempo de resposta

Consultas ao banco utilizadas pelas telas do sistema devem ser executadas de forma eficiente.

Operações que dependam de consultas mais complexas devem ser avaliadas individualmente durante os testes de desempenho.

### RNF03 — Segurança de autenticação

As senhas dos usuários não devem ser armazenadas em texto puro no banco de dados.

O sistema deve utilizar mecanismo de hash de senha, preferencialmente BCrypt ou solução equivalente.

O sistema deve comparar a senha informada com o hash armazenado durante o login.

### RNF04 — Controle de tentativas de login

O sistema deve controlar tentativas consecutivas de autenticação inválida.

Como critério inicial de segurança:

* Após 5 tentativas consecutivas de senha incorreta, o acesso deve ser temporariamente bloqueado;
* O sistema deve informar que o acesso foi temporariamente bloqueado;
* A mensagem não deve revelar qual parte da credencial estava incorreta.

Esse comportamento deverá ser implementado e validado nos testes de segurança.

### RNF05 — Integridade dos dados

O banco de dados deve impedir situações que violem as regras do sistema.

Exemplos:

* E-mail duplicado;
* CPF/CNPJ duplicado;
* Mais de um perfil para o mesmo usuário;
* Mais de uma candidatura do mesmo candidato para a mesma vaga;
* Candidatura relacionada a uma vaga inexistente;
* Entrevista relacionada a uma candidatura inexistente.

### RNF06 — Integridade referencial

As relações entre as tabelas devem utilizar chaves estrangeiras.

O sistema deve impedir registros órfãos e preservar a consistência dos relacionamentos entre:

* Usuários e perfis;
* Usuários e candidaturas;
* Vagas e candidaturas;
* Candidaturas e entrevistas.

### RNF07 — Usabilidade

A interface deve apresentar informações de maneira clara e organizada.

Os campos devem possuir identificação adequada e as mensagens de erro devem orientar o usuário sobre como corrigir o problema.

### RNF08 — Padronização

Campos com opções previamente definidas devem utilizar valores padronizados.

Exemplos:

* Tipo de usuário;
* Tipo de pessoa;
* Status da vaga;
* Status da candidatura;
* Status da entrevista;
* Carga horária.

### RNF09 — Compatibilidade

O sistema deve executar no ambiente definido para o projeto acadêmico, utilizando:

* Java;
* JDK;
* JavaFX;
* MySQL;
* MySQL Connector/J.

### RNF10 — Persistência

Os dados cadastrados devem permanecer disponíveis após o encerramento e reabertura da aplicação, desde que tenham sido persistidos corretamente no banco de dados.

### RNF11 — Tratamento de exceções

Falhas inesperadas durante operações do sistema devem ser tratadas adequadamente.

O usuário não deve visualizar mensagens técnicas como:

* Stack trace;
* SQLException;
* Nome de classe Java;
* Consulta SQL;
* Caminho interno de arquivo.

O sistema deve apresentar uma mensagem amigável e registrar a informação técnica de maneira apropriada para depuração.

### RNF12 — Arquivos

O currículo deve ser armazenado no banco de dados em formato adequado para arquivos binários.

O sistema deve aceitar somente arquivos PDF para o currículo.

Arquivos inválidos ou incompatíveis devem ser recusados antes da persistência.

---

# 3. Testes de Caixa Preta

Os testes de caixa preta devem verificar o comportamento do sistema sem considerar sua implementação interna.

## 3.1 Particionamento de equivalência

Os dados de entrada devem ser divididos em grupos válidos e inválidos.

### Exemplo — Login

**Entrada válida:**

* E-mail cadastrado;
* Senha correta.

**Entrada inválida:**

* E-mail inexistente;
* Senha incorreta;
* E-mail vazio;
* Senha vazia;
* E-mail em formato inválido.

### Exemplo — Carga horária

Valores válidos:

* INTEGRAL;
* MEIO_PERIODO;
* ESTAGIO;
* FLEXIVEL.

Valores inválidos:

* Campo vazio;
* Valor não pertencente às opções disponíveis.

### Exemplo — Currículo

Entrada válida:

* Arquivo PDF válido.

Entradas inválidas:

* Arquivo PNG;
* Arquivo DOCX;
* Arquivo vazio;
* Arquivo corrompido.

---

# 4. Análise de Valor Limite

Os testes devem verificar valores próximos aos limites definidos pelo sistema.

Exemplos:

### Tentativas de login

* 1 tentativa inválida;
* 4 tentativas inválidas;
* 5 tentativas inválidas;
* 6ª tentativa após o bloqueio.

### Campos de texto

Testar:

* Campo vazio;
* Quantidade mínima de caracteres;
* Quantidade máxima permitida;
* Uma quantidade acima do limite.

### Valores financeiros

Testar:

* R$ 0,00;
* Valor positivo válido;
* Valor com duas casas decimais;
* Valor negativo;
* Valor acima do limite definido.

### Arquivo

Testar:

* PDF pequeno;
* PDF próximo ao tamanho máximo permitido;
* Arquivo acima do limite permitido;
* Arquivo com extensão `.pdf` mas conteúdo inválido.

---

# 5. Testes de Caixa Branca

Os testes de caixa branca devem verificar a lógica interna do programa, considerando código, condições, caminhos e tratamento de exceções.

## 5.1 Cobertura de condições

Devem ser testadas condições como:

* Login com credenciais corretas;
* Login com credenciais incorretas;
* Usuário existente e inexistente;
* Usuário ativo e desativado;
* Perfil CANDIDATO;
* Perfil RH;
* Perfil CEO;
* Perfil TI;
* Candidatura PENDENTE;
* Candidatura APROVADA;
* Candidatura REJEITADA;
* Entrevista AGENDADA;
* Entrevista REALIZADA;
* Entrevista CANCELADA.

## 5.2 Cobertura de caminhos

Devem ser avaliados os principais caminhos da aplicação.

### Exemplo — Login

1. Usuário informa credenciais;
2. Sistema valida os campos;
3. Sistema consulta o banco;
4. Sistema verifica a senha;
5. Sistema identifica o tipo de usuário;
6. Sistema abre o dashboard correspondente.

Também devem ser testados os caminhos alternativos:

1. Campo vazio;
2. Usuário inexistente;
3. Senha incorreta;
4. Usuário bloqueado;
5. Banco indisponível.

## 5.3 Testes de exceção

Devem ser testadas situações como:

* Banco de dados indisponível;
* Falha na conexão;
* Falha durante INSERT;
* Falha durante UPDATE;
* Falha durante DELETE;
* Arquivo inválido;
* Arquivo não encontrado;
* Erro durante leitura do PDF;
* Erro durante gravação do PDF;
* Violação de chave estrangeira;
* Violação de valor UNIQUE.

O sistema deve tratar essas situações sem encerrar inesperadamente a aplicação.

---

# 6. Testes de Segurança e Controle de Acesso

Devem ser realizados testes para verificar se um usuário consegue acessar somente as funcionalidades permitidas para seu perfil.

### Exemplos:

**Candidato:**

* Pode acessar seu próprio perfil;
* Pode visualizar vagas;
* Pode realizar candidaturas;
* Não pode acessar funções do RH;
* Não pode acessar funções do CEO;
* Não pode administrar usuários.

**RH:**

* Pode visualizar candidaturas;
* Pode aprovar/rejeitar;
* Pode gerenciar entrevistas;
* Pode gerenciar vagas;
* Não deve alterar funções exclusivas do TI.

**CEO:**

* Pode consultar informações;
* Não pode alterar informações.

**TI:**

* Pode administrar usuários;
* Não deve possuir acesso indevido às funções de decisão do RH.

Também deve ser testada a tentativa de acessar uma funcionalidade diretamente, sem passar pela tela prevista para aquele perfil.

---

# 7. Testes de Banco de Dados

Devem ser verificadas as regras de integridade definidas no banco.

### Testes mínimos:

1. Inserir usuário com e-mail válido;
2. Tentar inserir usuário com e-mail duplicado;
3. Criar perfil para usuário existente;
4. Tentar criar segundo perfil para o mesmo usuário;
5. Inserir CPF/CNPJ duplicado;
6. Criar candidatura válida;
7. Tentar criar candidatura duplicada para a mesma vaga;
8. Criar várias candidaturas para uma mesma vaga;
9. Criar várias candidaturas para um mesmo candidato em vagas diferentes;
10. Criar várias entrevistas para uma candidatura;
11. Tentar criar entrevista para candidatura inexistente;
12. Verificar comportamento das chaves estrangeiras.

---

# 8. Testes de Interface

Devem ser verificados:

* Existência dos campos esperados;
* Campos obrigatórios;
* Máscaras de entrada;
* Botões funcionando;
* Navegação entre telas;
* Mensagens de erro;
* Mensagens de sucesso;
* Estado dos componentes;
* Campos desabilitados quando necessário;
* Exibição correta das informações;
* Redirecionamento correto após login.

---

# 9. Testes de Regressão

Após alterações no sistema, funcionalidades anteriormente aprovadas devem ser executadas novamente para verificar se uma mudança não causou problemas em outras partes da aplicação.

Exemplo:

Após alterar o login, devem ser novamente testados:

* Login do candidato;
* Login do RH;
* Login do CEO;
* Login do TI;
* Bloqueio por tentativas;
* Controle de acesso;
* Logout.

Após alterar o cadastro de vagas, devem ser novamente testados:

* Visualização das vagas;
* Candidaturas;
* Consulta das vagas pelo RH;
* Consulta das vagas pelo CEO.

---

# 10. Critérios de Aceitação Gerais

Uma funcionalidade será considerada aprovada quando:

* Executar o fluxo previsto;
* Produzir o resultado esperado;
* Não permitir entradas inválidas quando houver uma regra de validação;
* Não permitir acesso não autorizado;
* Persistir corretamente os dados;
* Respeitar as regras de integridade do banco;
* Apresentar mensagens adequadas ao usuário;
* Não apresentar erros técnicos diretamente na interface;
* Atender aos requisitos funcionais relacionados;
* Não causar regressão em funcionalidades anteriormente aprovadas.

---

# 11. Critérios de Entrada dos Testes

Os testes devem ser iniciados quando:

* O ambiente Java estiver configurado;
* O JavaFX estiver configurado;
* O MySQL estiver funcionando;
* O banco `talenthub` estiver criado;
* As tabelas necessárias estiverem criadas;
* Houver dados de teste suficientes;
* A funcionalidade a ser testada estiver implementada.

# 12. Critérios de Saída dos Testes

Os testes poderão ser encerrados quando:

* Todos os casos de teste planejados tiverem sido executados;
* Os requisitos críticos tiverem sido validados;
* Os defeitos encontrados tiverem sido registrados;
* Os defeitos críticos tiverem sido corrigidos ou formalmente aceitos;
* Os testes de regressão necessários tiverem sido realizados;
* Os resultados estiverem documentados.

---

# 13. Exemplos de Casos de Teste

| ID   | Funcionalidade | Cenário                                                    | Resultado esperado                                            |
| ---- | -------------- | ---------------------------------------------------------- | ------------------------------------------------------------- |
| CT01 | Cadastro       | Cadastro com dados válidos                                 | Usuário cadastrado com tipo CANDIDATO                         |
| CT02 | Cadastro       | E-mail já existente                                        | Sistema rejeita cadastro e informa o problema                 |
| CT03 | Login          | E-mail e senha corretos                                    | Usuário acessa seu dashboard                                  |
| CT04 | Login          | Senha incorreta                                            | Sistema rejeita acesso                                        |
| CT05 | Login          | 5 tentativas inválidas                                     | Conta é temporariamente bloqueada                             |
| CT06 | Acesso         | Candidato tenta acessar função do RH                       | Acesso negado                                                 |
| CT07 | Vagas          | RH cadastra vaga válida                                    | Vaga é persistida no banco                                    |
| CT08 | Candidatura    | Candidato se candidata a vaga                              | Candidatura criada como PENDENTE                              |
| CT09 | Candidatura    | Mesmo candidato tenta candidatar-se novamente à mesma vaga | Segunda candidatura é impedida                                |
| CT10 | RH             | RH aprova candidatura                                      | Status muda para APROVADA e ação é registrada                 |
| CT11 | RH             | RH rejeita candidatura                                     | Status muda para REJEITADA e ação é registrada                |
| CT12 | Entrevista     | RH agenda entrevista                                       | Entrevista é criada como AGENDADA                             |
| CT13 | Entrevista     | Entrevista é cancelada                                     | Status muda para CANCELADA                                    |
| CT14 | Entrevista     | Nova entrevista é criada após cancelamento                 | Nova entrevista é criada para a mesma candidatura             |
| CT15 | CEO            | CEO consulta informações                                   | Dados são exibidos sem permitir alteração                     |
| CT16 | TI             | TI cria usuário administrativo                             | Usuário é criado com o tipo correto                           |
| CT17 | Currículo      | Candidato envia PDF válido                                 | Arquivo é armazenado corretamente                             |
| CT18 | Currículo      | Candidato envia arquivo não PDF                            | Sistema rejeita o arquivo                                     |
| CT19 | Banco          | Tentativa de e-mail duplicado                              | Banco impede duplicidade                                      |
| CT20 | Banco          | Banco indisponível durante operação                        | Sistema apresenta mensagem amigável sem encerrar abruptamente |

---

# 14. Observação sobre os Tipos de Teste

Os testes de **caixa preta** verificam o comportamento externo do sistema, considerando entradas e resultados esperados, sem depender de como o código foi implementado.

Os testes de **caixa branca** verificam a lógica interna do sistema, considerando condições, caminhos de execução, exceções, consultas, validações e regras implementadas no código.

Os dois tipos são complementares e devem ser utilizados no projeto.

Além disso, **tempo de resposta, número de tentativas de login, mensagens de erro, segurança, usabilidade e integridade do banco não pertencem exclusivamente à caixa preta ou à caixa branca**. Eles são critérios de qualidade que podem ser verificados por diferentes técnicas de teste.

Por exemplo:

* Verificar se o login responde em até 2 segundos → teste de desempenho;
* Verificar o bloqueio após 5 tentativas → teste funcional/de segurança;
* Verificar o `if` responsável pelo bloqueio → caixa branca;
* Verificar se uma mensagem amigável aparece → caixa preta;
* Verificar se a exceção do banco é tratada corretamente → caixa branca;
* Verificar se o usuário realmente vê a mensagem correta → caixa preta.
