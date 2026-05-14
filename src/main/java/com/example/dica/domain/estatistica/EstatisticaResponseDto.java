package com.example.dica.domain.estatistica;

public record EstatisticaResponseDto(
        Integer anoReferencia,
        Integer mesReferencia,
        Long totalPesquisaAlimentos,
        Long totalPesquisaReceitas,
        Long totalPesquisaVideosEducativos,
        Long quantidadeAlimentosCadastrados,
        Long quantidadeReceitasCadastradas
) {
}

