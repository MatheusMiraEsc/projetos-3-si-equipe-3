-- Criação do Banco de Dados
CREATE DATABASE ingressart;

-- Conectar ao banco ingressart e criar as tabelas:

-- Tabela pessoa
CREATE TABLE pessoa (
    id_pessoa SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(15),
    data_nascimento DATE,
    endereco VARCHAR(200),
    senha VARCHAR(100) NOT NULL,
    tipo_usuario VARCHAR(20) NOT NULL -- ADMINISTRADOR ou CLIENTE
);

-- Tabela sala
CREATE TABLE sala (
    id_sala SERIAL PRIMARY KEY,
    capacidade INT NOT NULL,
    tipo VARCHAR(50)
);

-- Tabela evento
CREATE TABLE evento (
    id_evento SERIAL PRIMARY KEY,
    status BOOLEAN DEFAULT TRUE, -- ativo ou arquivado
    nome_evento VARCHAR(150) NOT NULL,
    descricao TEXT,
    data_inicio TIMESTAMP NOT NULL,
    data_fim TIMESTAMP NOT NULL,
    categoria VARCHAR(50),
    class_indicativa INT,
    id_sala INT,
    FOREIGN KEY (id_sala) REFERENCES sala(id_sala)
);

-- Tabela sessao
CREATE TABLE sessao (
    id_sessao SERIAL PRIMARY KEY,
    data_inicio TIMESTAMP NOT NULL,
    data_fim TIMESTAMP NOT NULL,
    preco_sessao FLOAT NOT NULL,
    num_ingressos_disp INT NOT NULL,
    id_evento INT NOT NULL,
    id_sala INT NOT NULL,
    FOREIGN KEY (id_evento) REFERENCES evento(id_evento),
    FOREIGN KEY (id_sala) REFERENCES sala(id_sala)
);

-- Tabela ingresso
CREATE TABLE ingresso (
    id_ingresso SERIAL PRIMARY KEY,
    id_assento INT,
    tipo_ingresso VARCHAR(50),
    preco_ingresso FLOAT,
    status BOOLEAN DEFAULT TRUE,
    id_cliente INT,
    id_sessao INT,
    FOREIGN KEY (id_cliente) REFERENCES pessoa(id_pessoa),
    FOREIGN KEY (id_sessao) REFERENCES sessao(id_sessao)
);

-- Tabela venda
CREATE TABLE venda (
    id_venda SERIAL PRIMARY KEY,
    data_venda TIMESTAMP NOT NULL,
    forma_pagamento VARCHAR(50),
    id_cliente INT,
    FOREIGN KEY (id_cliente) REFERENCES pessoa(id_pessoa)
);

-- Tabela pagamento
CREATE TABLE pagamento (
    id_pagamento SERIAL PRIMARY KEY,
    id_venda INT,
    valor_pago FLOAT,
    data_pagamento TIMESTAMP NOT NULL,
    status VARCHAR(20),
    metodo_pagamento VARCHAR(50),
    FOREIGN KEY (id_venda) REFERENCES venda(id_venda)
);
