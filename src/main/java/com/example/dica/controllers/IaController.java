package com.example.dica.controllers;

import com.example.dica.domain.ia.IaResponseDto;
import com.example.dica.domain.ia.IaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("api/app/ia")
public class IaController {

    @Autowired
    private IaService iaService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<IaResponseDto> identificarAlimento(@RequestParam("imagem") MultipartFile imagem) {
        validarPng(imagem);

        try {
            var imagem64 = Base64.getEncoder().encodeToString(imagem.getBytes());
            return ResponseEntity.ok(iaService.identifyFoodByImage(imagem64));
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível ler a imagem enviada", e);
        }
    }

    private void validarPng(MultipartFile imagem) {
        if (imagem == null || imagem.isEmpty()) {
            throw new IllegalArgumentException("Envie uma imagem PNG do alimento");
        }

        var contentType = imagem.getContentType();
        if (!MediaType.IMAGE_PNG_VALUE.equalsIgnoreCase(contentType)) {
            throw new IllegalArgumentException("A imagem deve ser do tipo PNG");
        }

        var nomeArquivo = imagem.getOriginalFilename();
        if (nomeArquivo != null && !nomeArquivo.toLowerCase().endsWith(".png")) {
            throw new IllegalArgumentException("A imagem enviada precisa ter extensão .png");
        }
    }
}
