package com.example.dica.domain.videoIntroducao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoIntroducaoRepository extends JpaRepository<VideoIntroducao, Long> {

    Optional<VideoIntroducao> findTopByOrderByIdAsc();
}

