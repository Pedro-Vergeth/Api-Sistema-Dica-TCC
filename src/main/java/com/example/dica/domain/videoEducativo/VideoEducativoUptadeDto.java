package com.example.dica.domain.videoEducativo;

public record VideoEducativoUptadeDto(
        String titulo,
        Integer duracaoSegundos,
        String descricao,
        String videoUrl
) {
}
