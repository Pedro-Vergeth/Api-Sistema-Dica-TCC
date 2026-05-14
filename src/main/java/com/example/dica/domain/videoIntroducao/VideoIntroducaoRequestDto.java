package com.example.dica.domain.videoIntroducao;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record VideoIntroducaoRequestDto(
        @NotNull MultipartFile dadosFicheiro
) {
}

