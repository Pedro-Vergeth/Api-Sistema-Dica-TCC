package com.example.dica.controllers;

import com.example.dica.domain.alimento.AlimentoRequestDto;
import com.example.dica.domain.alimento.AlimentoResponseDto;
import com.example.dica.domain.alimento.AlimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/gerenciador/alimento")
public class AdminAlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @GetMapping
    public ResponseEntity<Page<AlimentoResponseDto>> getAlimentos(
            @RequestParam(required = false) String buscaLivre,
            @PageableDefault(size = 10) Pageable pageable){
        System.out.println("Busca Livre: " + buscaLivre);
        if (buscaLivre != null && !buscaLivre.isEmpty()) {
            var page = alimentoService.buscarPorBuscaLivre(buscaLivre, pageable).map(AlimentoResponseDto::new);
            return ResponseEntity.ok(page);
        }

        var page = alimentoService.getAll(pageable).map(AlimentoResponseDto::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlimentoResponseDto> getAlimentoById(@RequestParam Long id) {
        var alimento = alimentoService.getById(id);
        return ResponseEntity.ok(new AlimentoResponseDto(alimento));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity criarAlimento(@ModelAttribute @Valid AlimentoRequestDto dto) throws IOException {
        System.out.println("entrando no post");
        var alimento = alimentoService.createAlimento(dto);
        return ResponseEntity.ok(new AlimentoResponseDto(alimento));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity atualizarAlimento(@PathVariable Long id, @Valid @ModelAttribute AlimentoRequestDto dto) throws IOException {
        var alimento = alimentoService.updateAlimento(id, dto);
        return ResponseEntity.ok(alimento);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity deletarAlimento(@PathVariable Long id){
        alimentoService.deleteAlimento(id);
        return ResponseEntity.noContent().build();
    }

}
