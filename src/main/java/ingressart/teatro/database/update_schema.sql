-- Drop existing tables if they exist (ordem importante por causa das FKs)
DROP TABLE IF EXISTS ingresso CASCADE;
DROP TABLE IF EXISTS sessao CASCADE;
DROP TABLE IF EXISTS evento CASCADE;
DROP TABLE IF EXISTS peca CASCADE;
DROP TABLE IF EXISTS sala CASCADE;
DROP TABLE IF EXISTS pessoa CASCADE;
DROP TABLE IF EXISTS venda CASCADE;
DROP TABLE IF EXISTS pagamento CASCADE;
DROP TABLE IF EXISTS review CASCADE;

-- Recriação das tabelas

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

CREATE TABLE evento (
    id_evento SERIAL PRIMARY KEY,
    status BOOLEAN DEFAULT TRUE,
    nome_evento VARCHAR(150) NOT NULL,
    descricao TEXT,
    data_inicio TIMESTAMP NOT NULL,
    data_fim TIMESTAMP NOT NULL,
    categoria VARCHAR(50),
    class_indicativa INT,
    id_sala INT,
    FOREIGN KEY (id_sala) REFERENCES sala(id_sala)
);

CREATE TABLE sessao (
    id_sessao SERIAL PRIMARY KEY,
    data_inicio TIMESTAMP NOT NULL,
    data_fim TIMESTAMP NOT NULL,
    preco_sessao FLOAT NOT NULL,
    num_ingressos_disp INTEGER NOT NULL,
    id_evento INT,
    id_peca INT,
    id_sala INT NOT NULL,
    FOREIGN KEY (id_evento) REFERENCES evento(id_evento),
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
    id_sessao INTEGER,
    id_peca INTEGER,
    FOREIGN KEY (id_cliente) REFERENCES pessoa(id_pessoa),
    FOREIGN KEY (id_sessao) REFERENCES sessao(id_sessao),
    FOREIGN KEY (id_peca) REFERENCES peca(id_peca)
);

CREATE TABLE venda (
    id_venda SERIAL PRIMARY KEY,
    data_venda TIMESTAMP NOT NULL,
    forma_pagamento VARCHAR(50),
    id_cliente INTEGER,
    FOREIGN KEY (id_cliente) REFERENCES pessoa(id_pessoa)
);

CREATE TABLE pagamento (
    id_pagamento SERIAL PRIMARY KEY,
    id_venda INT,
    valor_pago FLOAT,
    data_pagamento TIMESTAMP NOT NULL,
    status VARCHAR(20),
    metodo_pagamento VARCHAR(50),
    FOREIGN KEY (id_venda) REFERENCES venda(id_venda)
);

CREATE TABLE review (
    id_review SERIAL PRIMARY KEY,
    id_cliente INTEGER NOT NULL,
    id_peca INTEGER NOT NULL,
    rating INTEGER NOT NULL CHECK (rating >= 1 AND rating <= 5),
    comentario TEXT,
    data_review TIMESTAMP NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES pessoa(id_pessoa),
    FOREIGN KEY (id_peca) REFERENCES peca(id_peca)
);
