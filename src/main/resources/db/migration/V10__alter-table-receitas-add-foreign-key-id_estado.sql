ALTER TABLE receitas
    ADD CONSTRAINT fk_receitas_estados
        FOREIGN KEY (id_estado)
            REFERENCES estados(id)
            ON DELETE SET NULL;