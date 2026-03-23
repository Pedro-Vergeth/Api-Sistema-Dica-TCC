CREATE TABLE estados (
                           id SERIAL PRIMARY KEY,
                           nome VARCHAR(50) NOT NULL,
                           sigla VARCHAR(2) NOT NULL,
                           regiao VARCHAR(30) NOT NULL
);