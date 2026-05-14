package com.example.dica.controllers;

import com.example.dica.domain.videoIntroducao.VideoIntroducaoResponseDto;
import com.example.dica.domain.videoIntroducao.VideoIntroducaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/app/video-introdutorio")
public class AppVideoIntroducaoController {

    @Autowired
    private VideoIntroducaoService videoIntroducaoService;

    @GetMapping
    public ResponseEntity<VideoIntroducaoResponseDto> getVideoIntroducao() {
        var videoIntroducao = videoIntroducaoService.getVideoAtual();
        return ResponseEntity.ok(new VideoIntroducaoResponseDto(videoIntroducao));
    }
}

