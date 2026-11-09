CREATE TABLE estudio (
    id UUID PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    descricao VARCHAR(200) NOT NULL,
    ano_fundacao INTEGER NOT NULL
);

CREATE TABLE jogos (
    id UUID PRIMARY KEY,
    titulo VARCHAR(50) NOT NULL,
    descricao TEXT,
    quantidade INTEGER NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    data_lancamento DATE NOT NULL,
    criado_em TIMESTAMP NOT NULL,
    estudio_id UUID NOT NULL,

    CONSTRAINT fk_jogos_estudio FOREIGN KEY (estudio_id) REFERENCES estudio(id)
);
