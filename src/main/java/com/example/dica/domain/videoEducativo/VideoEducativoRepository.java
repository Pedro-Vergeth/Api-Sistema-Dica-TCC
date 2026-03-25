package com.example.dica.domain.videoEducativo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VideoEducativoRepository extends JpaRepository<VideoEducativo, Long> {

	@Query("SELECT v FROM VideoEducativo v WHERE " +
			"LOWER(v.titulo) LIKE LOWER(CONCAT('%', :buscaLivre, '%')) OR " +
			"LOWER(v.videoUrl) LIKE LOWER(CONCAT('%', :buscaLivre, '%'))")
	Page<VideoEducativo> buscarPorBuscaLivre(@Param("buscaLivre") String buscaLivre, Pageable pageable);
}

