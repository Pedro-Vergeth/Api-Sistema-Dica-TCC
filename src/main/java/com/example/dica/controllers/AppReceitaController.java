package com.example.dica.controllers;

import com.example.dica.domain.estatistica.EstatisticaService;
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

    @Autowired
    private EstatisticaService estatisticaService;

    @GetMapping
    public ResponseEntity<Page<ReceitaResponseDto>> listarReceitas(
            @RequestParam Optional<TipoRefeicao> tipoRefeicao,
            @RequestParam Optional<GrupoAlimentar> grupoAlimentar,
            @RequestParam(name = "estado_id") Optional<Long> estadoId,
            @RequestParam(defaultValue = "") String buscaLivre,
            @PageableDefault(sort = "id") Pageable pageable) {

        estatisticaService.registrarPesquisaReceitas();

        var page = receitaService
                .getAllApp(pageable, buscaLivre, tipoRefeicao.orElse(null), grupoAlimentar.orElse(null), estadoId.orElse(null))
                .map(ReceitaResponseDto::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/regiao")
    public ResponseEntity<Page<ReceitaResponseDto>> listarReceitasPorRegiao(
            @RequestParam(name = "estado_id") Long estadoId,
            @PageableDefault(size = 5, sort = "id") Pageable pageable) {

        estatisticaService.registrarPesquisaReceitas();

        var page = receitaService
                .getPorRegiaoApp(pageable, estadoId)
                .map(ReceitaResponseDto::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaResponseDto> getReceitaById(@PathVariable Long id) {
        var receita = receitaService.getById(id);
        return ResponseEntity.ok(new ReceitaResponseDto(receita));
    }
}
