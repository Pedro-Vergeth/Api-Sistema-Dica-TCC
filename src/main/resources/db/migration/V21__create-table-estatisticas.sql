CREATE TABLE estatisticas (
    id BIGINT PRIMARY KEY,
    total_pesquisa_alimentos BIGINT NOT NULL DEFAULT 0,
    total_pesquisa_receitas BIGINT NOT NULL DEFAULT 0,
    total_pesquisa_videos_educativos BIGINT NOT NULL DEFAULT 0
);

INSERT INTO estatisticas (
    id,
    total_pesquisa_alimentos,
    total_pesquisa_receitas,
    total_pesquisa_videos_educativos
) VALUES (1, 0, 0, 0);

