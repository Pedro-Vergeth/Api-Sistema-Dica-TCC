package com.example.dica.domain.videoIntroducao;

import java.util.Base64;

public record VideoIntroducaoResponseDto(
        Long id,
        String dadosFicheiroBase64
) {
    public VideoIntroducaoResponseDto(VideoIntroducao videoIntroducao) {
        this(
                videoIntroducao.getId(),
                videoIntroducao.getDadosFicheiro() != null
                        ? Base64.getEncoder().encodeToString(videoIntroducao.getDadosFicheiro())
                        : null
        );
    }
}

