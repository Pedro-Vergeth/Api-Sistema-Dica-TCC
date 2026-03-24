package com.example.dica.domain.alimento;

import com.example.dica.domain.estado.EstadoDto;
import com.example.dica.domain.grupoAlimentar.GrupoAlimentar;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.stream.Collectors;

public record AlimentoResponseDto(
        Long id,
        String nomePrincipal,
        String sinonimos,
        String porcao,
        String medidaCaseira,
        String textoInformativo,
        GrupoAlimentar grupoAlimentar,
        String imagem64
) {
    public AlimentoResponseDto(Alimento alimento) {
        this(
                alimento.getId(),
                alimento.getNomePrincipal(),
                alimento.getSinonimos(),
                alimento.getPorcao(),
                alimento.getMedidaCaseira(),
                alimento.getTextoInformativo(),
                alimento.getGrupoAlimentar(),
                alimento.getImagem()!= null ? java.util.Base64.getEncoder().encodeToString(alimento.getImagem()) : null
        );

    }
}
