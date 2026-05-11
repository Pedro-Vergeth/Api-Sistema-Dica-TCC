package com.example.dica.domain.alimento;

import com.example.dica.domain.grupoAlimentar.GrupoAlimentar;

public record AlimentoResponseDto(
        Long id,
        String nomePrincipal,
        String sinonimos,
        String unidade,
        String unidadeMedidaCaseira,
        Double qtdParaUmCoracao,
        Double qtdMedidaCaseira,
        String textoInformativo,
        GrupoAlimentar grupoAlimentar,
        String imagem64
) {
    public AlimentoResponseDto(Alimento alimento) {
        this(
                alimento.getId(),
                alimento.getNomePrincipal(),
                alimento.getSinonimos(),
                alimento.getUnidade(),
                alimento.getUnidadeMedidaCaseira(),
                alimento.getQtdParaUmCoracao(),
                alimento.getQtdMedidaCaseira(),
                alimento.getTextoInformativo(),
                alimento.getGrupoAlimentar(),
                alimento.getImagem() != null ? java.util.Base64.getEncoder().encodeToString(alimento.getImagem()) : null
        );

    }
}
