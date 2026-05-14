package com.example.dica.domain.estatistica;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface EstatisticaPesquisaRepository extends JpaRepository<EstatisticaPesquisa, Long> {

    long countByTipoPesquisaAndDataPesquisaBetween(TipoPesquisaEstatistica tipoPesquisa,
                                                  LocalDateTime inicio,
                                                  LocalDateTime fim);
}

