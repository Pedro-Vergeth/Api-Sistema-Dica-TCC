package com.example.dica.domain.alimento;

import com.example.dica.domain.grupoAlimentar.GrupoAlimentar;

public record AlimentoGameResponseDto(
        String nomePrincipal,
        GrupoAlimentar grupoAlimentar,
        String imagem64
) {
    public AlimentoGameResponseDto(Alimento alimento) {
        this(
                alimento.getNomePrincipal(),
                alimento.getGrupoAlimentar(),
                alimento.getImagem() != null ? java.util.Base64.getEncoder().encodeToString(alimento.getImagem()) : null
        );
    }
}
