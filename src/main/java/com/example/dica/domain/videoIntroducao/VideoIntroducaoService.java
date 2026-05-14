package com.example.dica.domain.videoIntroducao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class VideoIntroducaoService {

    @Autowired
    private VideoIntroducaoRepository videoIntroducaoRepository;

    public VideoIntroducao getVideoAtual() {
        return videoIntroducaoRepository.findTopByOrderByIdAsc()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vídeo introdutório não encontrado"));
    }

    public VideoIntroducao createVideoIntroducao(VideoIntroducaoRequestDto dto) throws IOException {
        if (videoIntroducaoRepository.findTopByOrderByIdAsc().isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um vídeo introdutório cadastrado");
        }

        var videoIntroducao = new VideoIntroducao();
        videoIntroducao.setDadosFicheiro(toBytes(dto.dadosFicheiro()));
        return videoIntroducaoRepository.save(videoIntroducao);
    }

    public VideoIntroducaoResponseDto updateVideoIntroducao(VideoIntroducaoUpdateDto dto) throws IOException {
        var videoIntroducao = videoIntroducaoRepository.findTopByOrderByIdAsc()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vídeo introdutório não encontrado"));

        if (dto.dadosFicheiro() != null) {
            videoIntroducao.setDadosFicheiro(toBytes(dto.dadosFicheiro()));
        }

        videoIntroducaoRepository.save(videoIntroducao);
        return new VideoIntroducaoResponseDto(videoIntroducao);
    }

    public void deleteVideoIntroducao() {
        var videoIntroducao = videoIntroducaoRepository.findTopByOrderByIdAsc()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vídeo introdutório não encontrado"));
        videoIntroducaoRepository.delete(videoIntroducao);
    }

    private byte[] toBytes(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O arquivo do vídeo não pode estar vazio");
        }
        return file.getBytes();
    }
}


