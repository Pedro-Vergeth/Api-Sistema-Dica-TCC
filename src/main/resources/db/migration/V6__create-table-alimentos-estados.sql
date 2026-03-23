CREATE TABLE alimentos_estados (
                id_alimento INT REFERENCES alimentos(id) ON DELETE CASCADE,
                id_estado INT REFERENCES estados(id) ON DELETE CASCADE,
                PRIMARY KEY (id_alimento, id_estado)
);