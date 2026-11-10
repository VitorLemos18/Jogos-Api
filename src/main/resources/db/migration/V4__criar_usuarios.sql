CREATE TABLE usuarios (
    id UUID PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    role VARCHAR(20) NOT NULL ,
    data_nascimento DATE NOT NULL,
    create_at TIMESTAMP NOT NULL DEFAULT NOW(),
    update_at TIMESTAMP,
    create_by VARCHAR(100),
    update_by VARCHAR(100),
    deleted_at TIMESTAMP
);