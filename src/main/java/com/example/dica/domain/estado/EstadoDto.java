package com.example.dica.domain.estado;

public record EstadoDto(
        Long id,
        String nome,
        String sigla,
        Regiao regiao
) {
    public EstadoDto(Estado estado){
        this(estado.getId(), estado.getNome(), estado.getSigla(), estado.getRegiao());
    }
}
