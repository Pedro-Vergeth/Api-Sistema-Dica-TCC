package com.example.dica.domain.videoEducativo;

public record VideoEducativoResponseDto(
        Long id,
        String titulo,
        Integer duracaoSegundos,
        String videoUrl
) {
    public VideoEducativoResponseDto(VideoEducativo videoEducativo) {
        this(
                videoEducativo.getId(),
                videoEducativo.getTitulo(),
                videoEducativo.getDuracaoSegundos(),
                videoEducativo.getVideoUrl()
        );
    }
}

