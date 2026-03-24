package com.example.dica.domain.alimento;

import com.example.dica.domain.estado.Estado;
import com.example.dica.domain.grupoAlimentar.GrupoAlimentar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

    @Query("SELECT a FROM Alimento a WHERE LOWER(a.nomePrincipal) LIKE LOWER(CONCAT('%', :termo, '%')) OR LOWER(a.sinonimos) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<Alimento> buscarPorNomeOuSinonimo(String termo, Pageable pageable);
}
