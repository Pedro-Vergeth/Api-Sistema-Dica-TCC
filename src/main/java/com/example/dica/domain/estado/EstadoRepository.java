package com.example.dica.domain.estado;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Optional<Estado> findBySigla(String sigla);

    List<Estado> findByRegiao(Regiao regiao);
}
