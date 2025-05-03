-- Criação da tabela sala
CREATE TABLE sala (
    id_sala SERIAL PRIMARY KEY,
    capacidade INTEGER NOT NULL,
    tipo VARCHAR(50) NOT NULL
);

-- Criação da tabela evento
CREATE TABLE evento (
    id_evento SERIAL PRIMARY KEY,
    status BOOLEAN,
    nome_evento VARCHAR(100),
    descricao TEXT,
    data_inicio TIMESTAMP,
    data_fim TIMESTAMP,
    categoria VARCHAR(50),
    class_indicativa INTEGER,
    id_sala INTEGER,
    FOREIGN KEY (id_sala) REFERENCES sala(id_sala)
);

-- Criação da tabela sessao
CREATE TABLE sessao (
    id_sessao SERIAL PRIMARY KEY,
    data_inicio TIMESTAMP,
    data_fim TIMESTAMP,
    preco_sessao FLOAT,
    num_ingressos_disp INTEGER,
    id_evento INTEGER,
    id_sala INTEGER,
    FOREIGN KEY (id_evento) REFERENCES evento(id_evento),
    FOREIGN KEY (id_sala) REFERENCES sala(id_sala)
);

-- Criação da tabela pessoa
CREATE TABLE pessoa (
    id_pessoa SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    cpf VARCHAR(14) UNIQUE,
    telefone VARCHAR(20),
    data_nascimento DATE,
    endereco TEXT,
    senha VARCHAR(100),
    tipo_usuario VARCHAR(20)
);

-- Criação da tabela venda
CREATE TABLE venda (
    id_venda SERIAL PRIMARY KEY,
    data_venda TIMESTAMP,
    forma_pagamento VARCHAR(20),
    id_cliente INTEGER,
    FOREIGN KEY (id_cliente) REFERENCES pessoa(id_pessoa)
);

-- Criação da tabela ingresso
CREATE TABLE ingresso (
    id_ingresso SERIAL PRIMARY KEY,
    id_assento INTEGER,
    tipo_ingresso VARCHAR(20),
    preco_ingresso FLOAT,
    status BOOLEAN,
    id_cliente INTEGER,
    id_sessao INTEGER,
    FOREIGN KEY (id_cliente) REFERENCES pessoa(id_pessoa),
    FOREIGN KEY (id_sessao) REFERENCES sessao(id_sessao)
);
