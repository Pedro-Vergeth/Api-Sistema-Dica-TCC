package com.example.dica.domain.alimento;

import com.example.dica.domain.grupoAlimentar.GrupoAlimentar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

    @Query("SELECT a FROM Alimento a WHERE LOWER(a.nomePrincipal) LIKE LOWER(CONCAT('%', :termo, '%')) OR LOWER(a.sinonimos) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<Alimento> buscarPorNomeOuSinonimo(String termo, Pageable pageable);

    Page<Alimento> findByGrupoAlimentar(GrupoAlimentar grupoAlimentar, Pageable pageable);

    @Query(value = "SELECT a.* FROM alimentos a WHERE a.grupo_alimentar <> 'VERMELHO' ORDER BY RANDOM() LIMIT 7", nativeQuery = true)
    List<Alimento> getAlimentoRandomly();
}
