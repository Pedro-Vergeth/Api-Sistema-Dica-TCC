package com.example.dica.domain.estatistica;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "estatistica_pesquisas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EstatisticaPesquisa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pesquisa", nullable = false, length = 50)
    private TipoPesquisaEstatistica tipoPesquisa;

    @Column(name = "data_pesquisa", nullable = false)
    private LocalDateTime dataPesquisa;
}

