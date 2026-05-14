package com.example.dica.domain.videoIntroducao;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record VideoIntroducaoUpdateDto(
        @NotNull MultipartFile dadosFicheiro
) {
}

