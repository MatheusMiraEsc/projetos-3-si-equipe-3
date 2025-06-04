-- Drop existing tables if they exist
DROP TABLE IF EXISTS ingresso CASCADE;
DROP TABLE IF EXISTS sessao CASCADE;
DROP TABLE IF EXISTS evento CASCADE;
DROP TABLE IF EXISTS peca CASCADE;
DROP TABLE IF EXISTS sala CASCADE;
DROP TABLE IF EXISTS pessoa CASCADE;
DROP TABLE IF EXISTS venda CASCADE;

-- Create tables in the correct order
CREATE TABLE pessoa (
    id_pessoa SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(15),
    data_nascimento DATE,
    endereco VARCHAR(200),
    senha VARCHAR(100) NOT NULL,
    tipo_usuario VARCHAR(20) NOT NULL
);

CREATE TABLE sala (
    id_sala SERIAL PRIMARY KEY,
    capacidade INTEGER NOT NULL,
    tipo VARCHAR(50) NOT NULL
);

CREATE TABLE peca (
    id_peca SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    descricao TEXT,
    data DATE NOT NULL,
    hora TIME NOT NULL,
    valor_ingresso FLOAT NOT NULL
);

CREATE TABLE sessao (
    id_sessao SERIAL PRIMARY KEY,
    data_inicio TIMESTAMP NOT NULL,
    preco_sessao FLOAT NOT NULL,
    num_ingressos_disp INTEGER NOT NULL,
    id_peca INTEGER NOT NULL,
    id_sala INTEGER NOT NULL,
    FOREIGN KEY (id_peca) REFERENCES peca(id_peca),
    FOREIGN KEY (id_sala) REFERENCES sala(id_sala)
);

CREATE TABLE ingresso (
    id_ingresso SERIAL PRIMARY KEY,
    id_assento INTEGER,
    tipo_ingresso VARCHAR(50),
    preco_ingresso FLOAT,
    status BOOLEAN DEFAULT TRUE,
    id_cliente INTEGER,
    id_peca INTEGER,
    FOREIGN KEY (id_cliente) REFERENCES pessoa(id_pessoa),
    FOREIGN KEY (id_peca) REFERENCES peca(id_peca)
);

CREATE TABLE venda (
    id_venda SERIAL PRIMARY KEY,
    data_venda TIMESTAMP NOT NULL,
    forma_pagamento VARCHAR(50),
    id_cliente INTEGER,
    FOREIGN KEY (id_cliente) REFERENCES pessoa(id_pessoa)
);