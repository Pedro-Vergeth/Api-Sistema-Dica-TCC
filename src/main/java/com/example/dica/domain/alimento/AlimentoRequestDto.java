package com.example.dica.domain.alimento;

import com.example.dica.domain.grupoAlimentar.GrupoAlimentar;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record AlimentoRequestDto(
        @NotBlank String nomePrincipal,
        String sinonimos,
        String unidade,
        String unidadeMedidaCaseira,
        @NotNull Double qtdParaUmCoracao,
        Double qtdMedidaCaseira,
        String textoInformativo,
        MultipartFile imagem,
        @NotNull GrupoAlimentar grupoAlimentar
) {
}
