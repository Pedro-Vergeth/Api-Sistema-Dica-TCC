CREATE TABLE alimentos (
                             id SERIAL PRIMARY KEY,
                             nome_principal VARCHAR(100) NOT NULL,
                             sinonimos text,
                             porcao VARCHAR(50),
                             medida_caseira VARCHAR(100),
                             texto_informativo TEXT,
                             imagem bytea,
                             grupo_alimentar VARCHAR(20) NOT NULL
);