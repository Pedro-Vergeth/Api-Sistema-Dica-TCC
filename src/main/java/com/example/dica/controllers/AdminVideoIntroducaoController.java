package com.example.dica.controllers;

import com.example.dica.domain.videoIntroducao.VideoIntroducaoRequestDto;
import com.example.dica.domain.videoIntroducao.VideoIntroducaoResponseDto;
import com.example.dica.domain.videoIntroducao.VideoIntroducaoService;
import com.example.dica.domain.videoIntroducao.VideoIntroducaoUpdateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;

@RestController
@RequestMapping("api/gerenciador/video-introdutorio")
public class AdminVideoIntroducaoController {

    @Autowired
    private VideoIntroducaoService videoIntroducaoService;

    @GetMapping
    public ResponseEntity<VideoIntroducaoResponseDto> getVideoIntroducao() {
        var videoIntroducao = videoIntroducaoService.getVideoAtual();
        return ResponseEntity.ok(new VideoIntroducaoResponseDto(videoIntroducao));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<VideoIntroducaoResponseDto> criarVideoIntroducao(@ModelAttribute @Valid VideoIntroducaoRequestDto dto) throws IOException {
        var videoIntroducao = videoIntroducaoService.createVideoIntroducao(dto);
        return ResponseEntity.ok(new VideoIntroducaoResponseDto(videoIntroducao));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<VideoIntroducaoResponseDto> atualizarVideoIntroducao(@ModelAttribute @Valid VideoIntroducaoUpdateDto dto) throws IOException {
        var videoIntroducao = videoIntroducaoService.updateVideoIntroducao(dto);
        return ResponseEntity.ok(videoIntroducao);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<Void> deletarVideoIntroducao() {
        videoIntroducaoService.deleteVideoIntroducao();
        return ResponseEntity.noContent().build();
    }
}

