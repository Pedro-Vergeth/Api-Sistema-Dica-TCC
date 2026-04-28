package com.example.dica.domain.videoEducativo;

import jakarta.validation.constraints.NotBlank;

public record VideoEducativoRequestDto(
        @NotBlank String titulo,
        Integer duracaoSegundos,
        String descricao,
        @NotBlank String videoUrl
) {
}

