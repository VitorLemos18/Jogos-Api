CREATE TABLE vendas (
    id UUID PRIMARY KEY,
    data_compra DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    valor_total DECIMAL NOT NULL,
    create_at TIMESTAMP NOT NULL DEFAULT NOW(),
    update_at TIMESTAMP,
    create_by VARCHAR(100),
    update_by VARCHAR(100),
    deleted_at TIMESTAMP,
    usuario_id UUID NOT NULL,

    CONSTRAINT fk_usuarios_vendas FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE item_vendas(
    id UUID PRIMARY KEY,
    venda_id UUID NOT NULL,
    jogo_id UUID NOT NULL ,
    quantidade INTEGER NOT NULL,
    preco_unitario DECIMAL NOT NULL,

    CONSTRAINT fk_vendas_item_vendas foreign key (venda_id) REFERENCES vendas(id),
    CONSTRAINT fk_jogos FOREIGN KEY (jogo_id) REFERENCES jogos(id)
);