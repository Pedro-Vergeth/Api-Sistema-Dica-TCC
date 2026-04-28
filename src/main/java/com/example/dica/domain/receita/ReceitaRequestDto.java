package com.example.dica.domain.receita;

import com.example.dica.domain.grupoAlimentar.GrupoAlimentar;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record ReceitaRequestDto(
        @NotBlank String titulo,
        @NotNull TipoRefeicao tipoRefeicao,
        Integer tempoPreparoMinutos,
        String porcao,
        @NotNull GrupoAlimentar grupoAlimentar,
        @NotBlank String ingredientes,
        @NotBlank String modoPreparo,
        @NotBlank String rendimento,
        MultipartFile imagem,
        Long idEstado
) {
}

