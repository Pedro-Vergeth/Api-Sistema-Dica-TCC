package com.example.dica.domain.receita;

import com.example.dica.domain.estado.EstadoDto;
import com.example.dica.domain.grupoAlimentar.GrupoAlimentar;

public record ReceitaResponseDto(
        Long id,
        String titulo,
        TipoRefeicao tipoRefeicao,
        Integer tempoPreparoMinutos,
        String porcao,
        GrupoAlimentar grupoAlimentar,
        String ingredientes,
        String modoPreparo,
        String rendimento,
        String imagem64,
        EstadoDto estado
) {
    public ReceitaResponseDto(Receita receita) {
        this(
                receita.getId(),
                receita.getTitulo(),
                receita.getTipoRefeicao(),
                receita.getTempoPreparoMinutos(),
                receita.getPorcao(),
                receita.getGrupoAlimentar(),
                receita.getIngredientes(),
                receita.getModoPreparo(),
                receita.getRendimento(),
                receita.getImagem() != null ? java.util.Base64.getEncoder().encodeToString(receita.getImagem()) : null,
                receita.getEstado() != null ? new EstadoDto(receita.getEstado()) : null
        );
    }
}

