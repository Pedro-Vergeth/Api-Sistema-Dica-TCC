package com.example.dica.controllers;

import com.example.dica.domain.estatistica.EstatisticaService;
import com.example.dica.domain.videoEducativo.VideoEducativoResponseDto;
import com.example.dica.domain.videoEducativo.VideoEducativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/app/video-educativo")
public class AppVideoEducativoController {

    @Autowired
    private VideoEducativoService videoEducativoService;

    @Autowired
    private EstatisticaService estatisticaService;

    @GetMapping
    public ResponseEntity<Page<VideoEducativoResponseDto>> listarVideosEducativos(
            @RequestParam(defaultValue = "") String buscaLivre,
            @PageableDefault(sort = "id") Pageable pageable) {

        estatisticaService.registrarPesquisaVideosEducativos();

        var videos = videoEducativoService.getAll(pageable, buscaLivre);
        var response = videos.map(VideoEducativoResponseDto::new);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoEducativoResponseDto> getVideoEducativoById(@PathVariable Long id) {
        var videoEducativo = videoEducativoService.getById(id);
        return ResponseEntity.ok(new VideoEducativoResponseDto(videoEducativo));
    }
}
