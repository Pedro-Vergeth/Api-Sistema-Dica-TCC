package com.example.dica.controllers;

import com.example.dica.domain.estatistica.EstatisticaResponseDto;
import com.example.dica.domain.estatistica.EstatisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/gerenciador/estatistica")
public class AdminEstatisticaController {

    @Autowired
    private EstatisticaService estatisticaService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<EstatisticaResponseDto> getEstatisticas(
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) Integer mes) {
        if (ano == null && mes == null) {
            return ResponseEntity.ok(estatisticaService.getResumo());
        }

        return ResponseEntity.ok(estatisticaService.getResumo(ano, mes));
    }
}

