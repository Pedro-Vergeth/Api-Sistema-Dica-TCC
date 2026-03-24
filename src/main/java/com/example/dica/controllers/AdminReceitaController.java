package com.example.dica.controllers;

import com.example.dica.domain.receita.ReceitaRequestDto;
import com.example.dica.domain.receita.ReceitaResponseDto;
import com.example.dica.domain.receita.ReceitaService;
import com.example.dica.domain.receita.ReceitaUpdateDto;
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
@RequestMapping("api/gerenciador/receita")
public class AdminReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping
    public ResponseEntity<Page<ReceitaResponseDto>> getReceitas(@PageableDefault(sort = "id") Pageable pageable) {
        var page = receitaService.getAll(pageable).map(ReceitaResponseDto::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaResponseDto> getReceitaById(@PathVariable Long id) {
        var receita = receitaService.getById(id);
        return ResponseEntity.ok(new ReceitaResponseDto(receita));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ReceitaResponseDto> criarReceita(@ModelAttribute @Valid ReceitaRequestDto dto) throws IOException {
        var receita = receitaService.createReceita(dto);
        return ResponseEntity.ok(new ReceitaResponseDto(receita));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ReceitaResponseDto> atualizarReceita(@PathVariable Long id, @ModelAttribute ReceitaUpdateDto dto) throws IOException {
        var receita = receitaService.updateReceita(id, dto);
        return ResponseEntity.ok(receita);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReceita(@PathVariable Long id) {
        receitaService.deleteReceita(id);
        return ResponseEntity.noContent().build();
    }
}



