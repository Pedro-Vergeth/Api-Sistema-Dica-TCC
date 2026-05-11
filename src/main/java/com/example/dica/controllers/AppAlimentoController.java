package com.example.dica.controllers;

import com.example.dica.domain.alimento.AlimentoGameResponseDto;
import com.example.dica.domain.alimento.AlimentoResponseDto;
import com.example.dica.domain.alimento.AlimentoService;
import com.example.dica.domain.grupoAlimentar.GrupoAlimentar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/app/alimento")
public class AppAlimentoController {
    
    @Autowired
    AlimentoService alimentoService;

    @GetMapping("/pegar-alimentos-aleatorios")
    public ResponseEntity<List<AlimentoGameResponseDto>> pegarAlimentosAleatorios() {
        var alimentos = alimentoService.getRandonly();
        var response = alimentos.stream()
                .map(AlimentoGameResponseDto::new)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<AlimentoResponseDto>> listarAlimentos(
            @PageableDefault(size = 5) Pageable pageable,
            @RequestParam(required = false) GrupoAlimentar grupoAlimentar) {
        var alimentos = alimentoService.getAll(pageable, grupoAlimentar);
        var response = alimentos.map(AlimentoResponseDto::new);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/busca")
    public ResponseEntity<Page<AlimentoResponseDto>> buscarAlimentos(
            @RequestParam(defaultValue = "") String buscaLivre,
            @PageableDefault(size = 8) Pageable pageable) {

        var alimentos = alimentoService.buscarPorBuscaLivre(buscaLivre, pageable);
        var response = alimentos.map(AlimentoResponseDto::new);

        return ResponseEntity.ok(response);
    }



}
