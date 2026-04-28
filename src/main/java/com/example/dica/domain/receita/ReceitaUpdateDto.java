package com.example.dica.domain.receita;

import com.example.dica.domain.grupoAlimentar.GrupoAlimentar;
import org.springframework.web.multipart.MultipartFile;

public record ReceitaUpdateDto(
        String titulo,
        TipoRefeicao tipoRefeicao,
        Integer tempoPreparoMinutos,
        String porcao,
        GrupoAlimentar grupoAlimentar,
        String ingredientes,
        String modoPreparo,
        String rendimento,
        MultipartFile imagem,
        Long idEstado
) {
}

