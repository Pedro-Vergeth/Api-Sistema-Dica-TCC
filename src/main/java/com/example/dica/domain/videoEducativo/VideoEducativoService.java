package com.example.dica.domain.videoEducativo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VideoEducativoService {

    @Autowired
    private VideoEducativoRepository videoEducativoRepository;


    public Page<VideoEducativo> getAll(Pageable pageable, String buscaLivre) {
        if (buscaLivre == null || buscaLivre.trim().isEmpty()) {
            return videoEducativoRepository.findAll(pageable);
        }
        return videoEducativoRepository.buscarPorBuscaLivre(buscaLivre, pageable);
    }

    public VideoEducativo getById(Long id) {
        return videoEducativoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vídeo educativo não encontrado"));
    }

    public VideoEducativo createVideoEducativo(VideoEducativoRequestDto dto) {
        var videoEducativo = new VideoEducativo();
        videoEducativo.setTitulo(dto.titulo());
        videoEducativo.setDuracaoSegundos(dto.duracaoSegundos());
        videoEducativo.setDescricao(dto.descricao());
        videoEducativo.setVideoUrl(dto.videoUrl());
        return videoEducativoRepository.save(videoEducativo);
    }

    public VideoEducativoResponseDto updateVideoEducativo(Long id, VideoEducativoUptadeDto dto) {
        var videoEducativo = videoEducativoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vídeo educativo não encontrado, tente outro id"));

        if (dto.titulo() != null) {
            videoEducativo.setTitulo(dto.titulo());
        }
        if (dto.duracaoSegundos() != null) {
            videoEducativo.setDuracaoSegundos(dto.duracaoSegundos());
        }
        if (dto.descricao() != null) {
            videoEducativo.setDescricao(dto.descricao());
        }
        if (dto.videoUrl() != null) {
            videoEducativo.setVideoUrl(dto.videoUrl());
        }

        videoEducativoRepository.save(videoEducativo);
        return new VideoEducativoResponseDto(videoEducativo);
    }

    public void deleteVideoEducativo(Long id) {
        if (!videoEducativoRepository.existsById(id)) {
            throw new RuntimeException("Vídeo educativo não encontrado, tente outro id");
        }
        videoEducativoRepository.deleteById(id);
    }
}

