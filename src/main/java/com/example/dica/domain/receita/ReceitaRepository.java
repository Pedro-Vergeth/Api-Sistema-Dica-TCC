package com.example.dica.domain.receita;

import com.example.dica.domain.grupoAlimentar.GrupoAlimentar;
import com.example.dica.domain.receita.TipoRefeicao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

	@Query("SELECT r FROM Receita r WHERE " +
			"(:buscaLivre IS NULL OR :buscaLivre = '' OR " +
			"LOWER(r.titulo) LIKE LOWER(CONCAT('%', :buscaLivre, '%')) OR " +
			"LOWER(r.ingredientes) LIKE LOWER(CONCAT('%', :buscaLivre, '%')) OR " +
			"LOWER(r.modoPreparo) LIKE LOWER(CONCAT('%', :buscaLivre, '%'))) AND " +
			"(:tipoRefeicao IS NULL OR r.tipoRefeicao = :tipoRefeicao) AND " +
			"(:grupoAlimentar IS NULL OR r.grupoAlimentar = :grupoAlimentar)")
	Page<Receita> buscarReceitas(@Param("buscaLivre") String buscaLivre,
								 @Param("tipoRefeicao") TipoRefeicao tipoRefeicao,
								 @Param("grupoAlimentar") GrupoAlimentar grupoAlimentar,
								 Pageable pageable);
}

