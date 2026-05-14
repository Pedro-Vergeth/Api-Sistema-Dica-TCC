package com.example.dica.domain.estatistica;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "estatisticas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Estatistica {

    @Id
    private Long id;

    @Column(name = "total_pesquisa_alimentos", nullable = false)
    private Long totalPesquisaAlimentos;

    @Column(name = "total_pesquisa_receitas", nullable = false)
    private Long totalPesquisaReceitas;

    @Column(name = "total_pesquisa_videos_educativos", nullable = false)
    private Long totalPesquisaVideosEducativos;
}
