package com.example.dica.domain.alimento;

import com.example.dica.domain.grupoAlimentar.GrupoAlimentar;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public record AlimentoRequestDto(
        @NotBlank String nomePrincipal,
        String sinonimos,
        String porcao,
        String medidaCaseira,
        String textoInformativo,
        MultipartFile imagem,
        @NotNull GrupoAlimentar grupoAlimentar


) {
}
