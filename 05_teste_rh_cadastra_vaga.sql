-- ============================================================
-- TalentHUB - Módulo Recrutador (RH)
-- 05 - 16 Testes
--
-- Caso de referência da Documentacao.md: CT07
--   "RH cadastra vaga válida -> Vaga é persistida no banco
--      
--  rapaziada eu esqueci de colocar mas a vaga é possível colocar salário negativo, não ta podendo ter isso então roda
-- o comando antes:
--     ALTER TABLE vaga ADD CONSTRAINT chk_vaga_salario
--         CHECK (salario IS NULL OR salario >= 0);
-- ele vai dar um Check constraint violation se tentar colocar salário negativo, então o teste vai passar
-- ============================================================

USE talenthub;

DROP PROCEDURE IF EXISTS sp_teste_rh_cadastra_vaga;

-- ------------------------------------------------------------
-- BLOCO 2 - criar a procedure para ser executado o teste
-- ------------------------------------------------------------
CREATE PROCEDURE sp_teste_rh_cadastra_vaga()
BEGIN
    DECLARE v_erro     TINYINT      DEFAULT 0;
    DECLARE v_errno    INT          DEFAULT NULL;
    DECLARE v_msg      VARCHAR(255) DEFAULT NULL;
    DECLARE v_rh       INT;
    DECLARE v_cand     INT;
    DECLARE v_ti       INT;
    DECLARE v_ceo      INT;
    DECLARE v_id       INT;
    DECLARE v_status   VARCHAR(20);
    DECLARE v_criador  INT;
    DECLARE v_data_ok  TINYINT;
    DECLARE v_qtd      INT;
    DECLARE v_total    INT;
    DECLARE v_ok       INT;

    -- Qualquer erro SQL vai ser capturado e informado  
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1 v_errno = MYSQL_ERRNO, v_msg = MESSAGE_TEXT;
        SET v_erro = 1;
    END;

    -- Tabela para os resultados
    DROP TEMPORARY TABLE IF EXISTS tmp_resultado_teste;
    CREATE TEMPORARY TABLE tmp_resultado_teste (
        ordem     INT AUTO_INCREMENT PRIMARY KEY,
        caso      VARCHAR(10)  NOT NULL,
        cenario   VARCHAR(100) NOT NULL,
        esperado  VARCHAR(80)  NOT NULL,
        obtido    VARCHAR(200) NOT NULL,
        resultado VARCHAR(6)   NOT NULL
    ) ENGINE=MEMORY;

    START TRANSACTION;

    -- Usuários para os testes (RH, CANDIDATO, TI, CEO), eles vão ser excluidos dps com o rollback 
    INSERT INTO usuario (email, senha, tipo_usuario) VALUES ('teste.rh@talenthub.test',   'x', 'RH');
    SET v_rh = LAST_INSERT_ID();
    INSERT INTO usuario (email, senha, tipo_usuario) VALUES ('teste.cand@talenthub.test', 'x', 'CANDIDATO');
    SET v_cand = LAST_INSERT_ID();
    INSERT INTO usuario (email, senha, tipo_usuario) VALUES ('teste.ti@talenthub.test',   'x', 'TI');
    SET v_ti = LAST_INSERT_ID();
    INSERT INTO usuario (email, senha, tipo_usuario) VALUES ('teste.ceo@talenthub.test',  'x', 'CEO');
    SET v_ceo = LAST_INSERT_ID();

    -- ========================================================
    -- PARTE A - o RH CONSEGUE cadastrar (casos que devem aceitar)
    -- ========================================================

    SET v_erro = 0, v_errno = NULL, v_msg = NULL;
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria, salario)
    VALUES (v_rh, 'Teste Dev Java', 'Desenvolvimento de aplicacoes Java.', 'Java, JavaFX, MySQL', 'INTEGRAL', 4500.00);
    IF v_erro = 0 THEN
        SET v_id = LAST_INSERT_ID();
        SELECT status, id_rh_criador, (data_publicacao IS NOT NULL)
          INTO v_status, v_criador, v_data_ok
          FROM vaga WHERE id_vaga = v_id;
        INSERT INTO tmp_resultado_teste (caso, cenario, esperado, obtido, resultado) VALUES (
            'CT07.1', 'RH cadastra vaga completa e valida',
            'gravada: status ABERTA, criador = RH, data preenchida',
            CONCAT('status=', IFNULL(v_status, 'NULL'), ', criador_ok=', IF(v_criador = v_rh, 'sim', 'nao'), ', data_ok=', IFNULL(v_data_ok, 'NULL')),
            IF(v_status = 'ABERTA' AND v_criador = v_rh AND v_data_ok = 1, 'PASSOU', 'FALHOU'));
    ELSE
        INSERT INTO tmp_resultado_teste (caso, cenario, esperado, obtido, resultado) VALUES (
            'CT07.1', 'RH cadastra vaga completa e valida',
            'gravada: status ABERTA, criador = RH, data preenchida',
            CONCAT('erro ', v_errno, ': ', LEFT(v_msg, 120)), 'FALHOU');
    END IF;

    -- CT07.2 - Vaga sem salário (campo opcional)
    SET v_erro = 0, v_errno = NULL, v_msg = NULL;
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria, salario)
    VALUES (v_rh, 'Teste sem salario', 'A combinar.', 'Ensino medio', 'FLEXIVEL', NULL);
    INSERT INTO tmp_resultado_teste (caso, cenario, esperado, obtido, resultado) VALUES (
        'CT07.2', 'Vaga sem salario (NULL)', 'aceita',
        IF(v_erro = 0, 'aceita', CONCAT('erro ', v_errno, ': ', LEFT(v_msg, 120))),
        IF(v_erro = 0, 'PASSOU', 'FALHOU'));

    -- CT07.3 - As 4 cargas horárias permitidas
    SET v_erro = 0, v_errno = NULL, v_msg = NULL;
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria, salario)
    VALUES (v_rh, 'Teste carga INTEGRAL',     'd', 'r', 'INTEGRAL',     1000.00);
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria, salario)
    VALUES (v_rh, 'Teste carga MEIO_PERIODO', 'd', 'r', 'MEIO_PERIODO', 1000.00);
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria, salario)
    VALUES (v_rh, 'Teste carga ESTAGIO',      'd', 'r', 'ESTAGIO',      1000.00);
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria, salario)
    VALUES (v_rh, 'Teste carga FLEXIVEL',     'd', 'r', 'FLEXIVEL',     1000.00);
    SELECT COUNT(*) INTO v_qtd FROM vaga WHERE id_rh_criador = v_rh AND titulo LIKE 'Teste carga %';
    INSERT INTO tmp_resultado_teste (caso, cenario, esperado, obtido, resultado) VALUES (
        'CT07.3', 'As 4 cargas horarias (INTEGRAL, MEIO_PERIODO, ESTAGIO, FLEXIVEL)', '4 vagas gravadas',
        CONCAT(v_qtd, ' vagas gravadas', IF(v_erro = 1, CONCAT(' (erro ', v_errno, ')'), '')),
        IF(v_qtd = 4 AND v_erro = 0, 'PASSOU', 'FALHOU'));

    -- CT07.4 - Valores limite de salário: 0,00 e 99.999.999,99 (DECIMAL(10,2))
    SET v_erro = 0, v_errno = NULL, v_msg = NULL;
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria, salario)
    VALUES (v_rh, 'Teste salario zero',   'd', 'r', 'INTEGRAL', 0.00);
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria, salario)
    VALUES (v_rh, 'Teste salario maximo', 'd', 'r', 'INTEGRAL', 99999999.99);
    SELECT COUNT(*) INTO v_qtd FROM vaga WHERE id_rh_criador = v_rh AND titulo LIKE 'Teste salario %';
    INSERT INTO tmp_resultado_teste (caso, cenario, esperado, obtido, resultado) VALUES (
        'CT07.4', 'Salario nos limites (0,00 e 99999999,99)', '2 vagas gravadas',
        CONCAT(v_qtd, ' vagas gravadas', IF(v_erro = 1, CONCAT(' (erro ', v_errno, ')'), '')),
        IF(v_qtd = 2 AND v_erro = 0, 'PASSOU', 'FALHOU'));

    -- ========================================================
    -- PARTE B - o banco REJEITA cadastros inválidos
    -- ========================================================

    -- CT07.5 - Criador é CANDIDATO (trigger deve barrar: erro 1644)
    SET v_erro = 0, v_errno = NULL, v_msg = NULL;
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria)
    VALUES (v_cand, 'Teste criador candidato', 'd', 'r', 'INTEGRAL');
    INSERT INTO tmp_resultado_teste (caso, cenario, esperado, obtido, resultado) VALUES (
        'CT07.5', 'Criador do tipo CANDIDATO', 'rejeitar (erro 1644 do trigger)',
        IF(v_erro = 1, CONCAT('rejeitado, erro ', v_errno), 'ACEITO'),
        IF(v_erro = 1 AND v_errno = 1644, 'PASSOU', 'FALHOU'));

    -- Criador é TI
    SET v_erro = 0, v_errno = NULL, v_msg = NULL;
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria)
    VALUES (v_ti, 'Teste criador TI', 'd', 'r', 'INTEGRAL');
    INSERT INTO tmp_resultado_teste (caso, cenario, esperado, obtido, resultado) VALUES (
        'CT07.6', 'Criador do tipo TI', 'rejeitar (erro 1644 do trigger)',
        IF(v_erro = 1, CONCAT('rejeitado, erro ', v_errno), 'ACEITO'),
        IF(v_erro = 1 AND v_errno = 1644, 'PASSOU', 'FALHOU'));

    -- Criador é CEO
    SET v_erro = 0, v_errno = NULL, v_msg = NULL;
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria)
    VALUES (v_ceo, 'Teste criador CEO', 'd', 'r', 'INTEGRAL');
    INSERT INTO tmp_resultado_teste (caso, cenario, esperado, obtido, resultado) VALUES (
        'CT07.7', 'Criador do tipo CEO', 'rejeitar (erro 1644 do trigger)',
        IF(v_erro = 1, CONCAT('rejeitado, erro ', v_errno), 'ACEITO'),
        IF(v_erro = 1 AND v_errno = 1644, 'PASSOU', 'FALHOU'));

    -- CT07.8 - Criador inexistente (chave estrangeira: erro 1452)
    SET v_erro = 0, v_errno = NULL, v_msg = NULL;
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria)
    VALUES (999999999, 'Teste criador inexistente', 'd', 'r', 'INTEGRAL');
    INSERT INTO tmp_resultado_teste (caso, cenario, esperado, obtido, resultado) VALUES (
        'CT07.8', 'Criador inexistente (id 999999999)', 'rejeitar (erro 1452 de chave estrangeira)',
        IF(v_erro = 1, CONCAT('rejeitado, erro ', v_errno), 'ACEITO'),
        IF(v_erro = 1 AND v_errno = 1452, 'PASSOU', 'FALHOU'));

    -- CT07.9 - Título nulo (erro 1048)
    SET v_erro = 0, v_errno = NULL, v_msg = NULL;
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria)
    VALUES (v_rh, NULL, 'd', 'r', 'INTEGRAL');
    INSERT INTO tmp_resultado_teste (caso, cenario, esperado, obtido, resultado) VALUES (
        'CT07.9', 'Titulo nulo', 'rejeitar (erro 1048)',
        IF(v_erro = 1, CONCAT('rejeitado, erro ', v_errno), 'ACEITO'),
        IF(v_erro = 1 AND v_errno = 1048, 'PASSOU', 'FALHOU'));

    -- CT07.10 - Descrição nula (erro 1048)
    SET v_erro = 0, v_errno = NULL, v_msg = NULL;
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria)
    VALUES (v_rh, 'Teste descricao nula', NULL, 'r', 'INTEGRAL');
    INSERT INTO tmp_resultado_teste (caso, cenario, esperado, obtido, resultado) VALUES (
        'CT07.10', 'Descricao nula', 'rejeitar (erro 1048)',
        IF(v_erro = 1, CONCAT('rejeitado, erro ', v_errno), 'ACEITO'),
        IF(v_erro = 1 AND v_errno = 1048, 'PASSOU', 'FALHOU'));

    -- CT07.11 - Requisitos nulos (erro 1048)
    SET v_erro = 0, v_errno = NULL, v_msg = NULL;
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria)
    VALUES (v_rh, 'Teste requisitos nulos', 'd', NULL, 'INTEGRAL');
    INSERT INTO tmp_resultado_teste (caso, cenario, esperado, obtido, resultado) VALUES (
        'CT07.11', 'Requisitos nulos', 'rejeitar (erro 1048)',
        IF(v_erro = 1, CONCAT('rejeitado, erro ', v_errno), 'ACEITO'),
        IF(v_erro = 1 AND v_errno = 1048, 'PASSOU', 'FALHOU'));

    -- CT07.12 - Carga horária nula (erro 1048)
    SET v_erro = 0, v_errno = NULL, v_msg = NULL;
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria)
    VALUES (v_rh, 'Teste carga nula', 'd', 'r', NULL);
    INSERT INTO tmp_resultado_teste (caso, cenario, esperado, obtido, resultado) VALUES (
        'CT07.12', 'Carga horaria nula', 'rejeitar (erro 1048)',
        IF(v_erro = 1, CONCAT('rejeitado, erro ', v_errno), 'ACEITO'),
        IF(v_erro = 1 AND v_errno = 1048, 'PASSOU', 'FALHOU'));

    -- CT07.13 - Carga horária fora da lista (modo estrito)
    SET v_erro = 0, v_errno = NULL, v_msg = NULL;
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria)
    VALUES (v_rh, 'Teste carga invalida', 'd', 'r', 'PRESENCIAL');
    INSERT INTO tmp_resultado_teste (caso, cenario, esperado, obtido, resultado) VALUES (
        'CT07.13', 'Carga horaria fora da lista (PRESENCIAL)', 'rejeitar',
        IF(v_erro = 1, CONCAT('rejeitado, erro ', v_errno), 'ACEITO'),
        IF(v_erro = 1, 'PASSOU', 'FALHOU'));

    -- CT07.14 - Status fora da lista (modo estrito)
    SET v_erro = 0, v_errno = NULL, v_msg = NULL;
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria, status)
    VALUES (v_rh, 'Teste status invalido', 'd', 'r', 'INTEGRAL', 'PENDENTE');
    INSERT INTO tmp_resultado_teste (caso, cenario, esperado, obtido, resultado) VALUES (
        'CT07.14', 'Status fora da lista (PENDENTE)', 'rejeitar',
        IF(v_erro = 1, CONCAT('rejeitado, erro ', v_errno), 'ACEITO'),
        IF(v_erro = 1, 'PASSOU', 'FALHOU'));

    -- titulo - Tentar cadastrar titulo com 101 caracteres (limite é 100)
    SET v_erro = 0, v_errno = NULL, v_msg = NULL;
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria)
    VALUES (v_rh, REPEAT('A', 101), 'd', 'r', 'INTEGRAL');
    INSERT INTO tmp_resultado_teste (caso, cenario, esperado, obtido, resultado) VALUES (
        'CT07.15', 'Titulo com 101 caracteres', 'rejeitar',
        IF(v_erro = 1, CONCAT('rejeitado, erro ', v_errno), 'ACEITO'),
        IF(v_erro = 1, 'PASSOU', 'FALHOU'));

    -- CT07.16 - Salário negativo (Tentar cadastrar salário negativo, deve ser rejeitado pelo CHECK constraint)
    SET v_erro = 0, v_errno = NULL, v_msg = NULL;
    INSERT INTO vaga (id_rh_criador, titulo, descricao, requisitos, carga_horaria, salario)
    VALUES (v_rh, 'Teste salario positivo', 'd', 'r', 'INTEGRAL', 100.00);
    INSERT INTO tmp_resultado_teste (caso, cenario, esperado, obtido, resultado) VALUES (
        'Teste salário', 'Salario positivo (100,00)', 'aceitar',
        IF(v_erro = 1, CONCAT('rejeitado, erro ', v_errno), 'ACEITO'),
        IF(v_erro = 1, 'PASSOU', 'FALHOU'));

    -- Desfazer o teste
    ROLLBACK;

    -- Resumo + resultado final (uma única consulta no fim)
    SELECT COUNT(*), SUM(resultado = 'PASSOU') INTO v_total, v_ok FROM tmp_resultado_teste;
    INSERT INTO tmp_resultado_teste (caso, cenario, esperado, obtido, resultado) VALUES (
        'RESUMO', CONCAT(v_ok, ' de ', v_total, ' casos passaram'), 'todos passarem', '',
        IF(v_ok = v_total, 'PASSOU', 'FALHOU'));

    SELECT caso AS CASO, cenario AS CENARIO, esperado AS ESPERADO, obtido AS OBTIDO, resultado AS RESULTADO
      FROM tmp_resultado_teste
     ORDER BY ordem;
END;


-- ------------------------------------------------------------
-- BLOCO 3 - rodar o teste e pah
-- ------------------------------------------------------------
CALL sp_teste_rh_cadastra_vaga();


-- ------------------------------------------------------------
-- BLOCO 4 (opcional) - remove a procedure depois de usar
-- ------------------------------------------------------------
-- DROP PROCEDURE IF EXISTS sp_teste_rh_cadastra_vaga;
