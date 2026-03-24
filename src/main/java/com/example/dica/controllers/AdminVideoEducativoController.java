package com.example.dica.controllers;

import com.example.dica.domain.videoEducativo.VideoEducativoRequestDto;
import com.example.dica.domain.videoEducativo.VideoEducativoResponseDto;
import com.example.dica.domain.videoEducativo.VideoEducativoService;
import com.example.dica.domain.videoEducativo.VideoEducativoUptadeDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gerenciador/video-educativo")
public class AdminVideoEducativoController {

    @Autowired
    private VideoEducativoService videoEducativoService;

    @GetMapping
    public ResponseEntity<Page<VideoEducativoResponseDto>> getVideosEducativos(@PageableDefault(sort = "id") Pageable pageable) {
        var page = videoEducativoService.getAll(pageable).map(VideoEducativoResponseDto::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoEducativoResponseDto> getVideoEducativoById(@PathVariable Long id) {
        var videoEducativo = videoEducativoService.getById(id);
        return ResponseEntity.ok(new VideoEducativoResponseDto(videoEducativo));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<VideoEducativoResponseDto> criarVideoEducativo(@RequestBody @Valid VideoEducativoRequestDto dto) {
        var videoEducativo = videoEducativoService.createVideoEducativo(dto);
        return ResponseEntity.ok(new VideoEducativoResponseDto(videoEducativo));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<VideoEducativoResponseDto> atualizarVideoEducativo(@PathVariable Long id, @RequestBody @Valid VideoEducativoUptadeDto dto) {
        var videoEducativo = videoEducativoService.updateVideoEducativo(id, dto);
        return ResponseEntity.ok(videoEducativo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVideoEducativo(@PathVariable Long id) {
        videoEducativoService.deleteVideoEducativo(id);
        return ResponseEntity.noContent().build();
    }
}

