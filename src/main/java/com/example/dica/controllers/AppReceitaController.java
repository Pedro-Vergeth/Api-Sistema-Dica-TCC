package com.example.dica.controllers;

import com.example.dica.domain.grupoAlimentar.GrupoAlimentar;
import com.example.dica.domain.receita.ReceitaResponseDto;
import com.example.dica.domain.receita.ReceitaService;
import com.example.dica.domain.receita.TipoRefeicao;
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

import java.util.Optional;

@RestController
@RequestMapping("api/app/receita")
public class AppReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping
    public ResponseEntity<Page<ReceitaResponseDto>> listarReceitas(
            @RequestParam Optional<TipoRefeicao> tipoRefeicao,
            @RequestParam Optional<GrupoAlimentar> grupoAlimentar,
            @RequestParam(defaultValue = "") String buscaLivre,
            @PageableDefault(sort = "id") Pageable pageable) {

        var page = receitaService
                .getAll(pageable, buscaLivre, tipoRefeicao.orElse(null), grupoAlimentar.orElse(null))
                .map(ReceitaResponseDto::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaResponseDto> getReceitaById(@PathVariable Long id) {
        var receita = receitaService.getById(id);
        return ResponseEntity.ok(new ReceitaResponseDto(receita));
    }
}

