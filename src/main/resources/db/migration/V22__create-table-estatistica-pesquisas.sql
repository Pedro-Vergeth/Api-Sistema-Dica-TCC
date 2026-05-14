CREATE TABLE estatistica_pesquisas (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    tipo_pesquisa VARCHAR(50) NOT NULL,
    data_pesquisa TIMESTAMP NOT NULL
);

CREATE INDEX idx_estatistica_pesquisas_tipo_data
    ON estatistica_pesquisas (tipo_pesquisa, data_pesquisa);

