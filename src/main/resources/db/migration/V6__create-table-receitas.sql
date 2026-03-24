CREATE TABLE receitas (
                            id SERIAL PRIMARY KEY,
                            titulo VARCHAR(150) NOT NULL,
                            tipo_refeicao VARCHAR(50) NOT NULL,  -- Enum: 'CAFE', 'ALMOCO', 'JANTAR', 'LANCHE'
                            tempo_preparo_minutos INT,
                            porcao VARCHAR(50),                  -- Ex: "Rende 4 porções"
                            grupo_alimentar VARCHAR(20) NOT NULL,-- Enum para o filtro de cores
                            ingredientes TEXT NOT NULL,          -- Texto livre (Fácil de cadastrar no web)
                            modo_preparo TEXT NOT NULL,
                            imagem BYTEA
);