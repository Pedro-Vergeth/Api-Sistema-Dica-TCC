CREATE TABLE alimentos (
                           id SERIAL PRIMARY KEY,
                           nome_principal VARCHAR(100) NOT NULL,
                           sinonimos TEXT,
                           porcao VARCHAR(50),
                           medida_caseira VARCHAR(100),
                           texto_informativo TEXT,
                           imagem BYTEA,
                           grupo_alimentar VARCHAR(20) NOT NULL,

                           id_estado INT REFERENCES estados(id) ON DELETE SET NULL
);