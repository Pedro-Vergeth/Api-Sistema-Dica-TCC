package com.example.dica.domain.estatistica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EstatisticaRepository extends JpaRepository<Estatistica, Long> {

    @Modifying
    @Query("UPDATE Estatistica e SET e.totalPesquisaAlimentos = e.totalPesquisaAlimentos + 1 WHERE e.id = 1")
    int incrementarPesquisaAlimentos();

    @Modifying
    @Query("UPDATE Estatistica e SET e.totalPesquisaReceitas = e.totalPesquisaReceitas + 1 WHERE e.id = 1")
    int incrementarPesquisaReceitas();

    @Modifying
    @Query("UPDATE Estatistica e SET e.totalPesquisaVideosEducativos = e.totalPesquisaVideosEducativos + 1 WHERE e.id = 1")
    int incrementarPesquisaVideosEducativos();
}

